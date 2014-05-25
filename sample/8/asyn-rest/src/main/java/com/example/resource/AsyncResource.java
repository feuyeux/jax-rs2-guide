package com.example.resource;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.service.BookService;

@Path("books")
public class AsyncResource {
    private static final Logger LOGGER = Logger.getLogger(AsyncResource.class);
    public static final long TIMEOUT = 120;    
    
    @Autowired
    private BookService bookService;

    public AsyncResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public void asyncBatchSave(@Suspended final AsyncResponse asyncResponse, final Books books) {
        configResponse(asyncResponse);
        final BatchRunner batchTask = new BatchRunner(books.getBookList());
        Future<String> bookIdsFuture = Executors.newSingleThreadExecutor().submit(batchTask);
        LOGGER.debug("submitted.");
        String ids;
        try {
            LOGGER.debug("getting result...");
            ids = bookIdsFuture.get();
            LOGGER.debug("To resume");
            asyncResponse.resume(ids);
            LOGGER.debug("Resume done");
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void configResponse(final AsyncResponse asyncResponse) {
        asyncResponse.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable == null) {
                    LOGGER.info("CompletionCallback-onComplete: OK");
                } else {
                    LOGGER.info("CompletionCallback-onComplete: ERROR: " + throwable.getMessage());
                }
            }
        });

        asyncResponse.register(new ConnectionCallback() {
            @Override
            public void onDisconnect(AsyncResponse disconnected) {
                //Status.GONE=410
                LOGGER.info("ConnectionCallback-onDisconnect");
                disconnected.resume(Response.status(Response.Status.GONE).entity("disconnect!").build());
            }
        });

        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                //Status.SERVICE_UNAVAILABLE=503
                LOGGER.info("TIMEOUT");
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Operation time out.").build());
            }
        });
        asyncResponse.setTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    class BatchRunner implements Callable<String> {
        private List<Book> bookList;

        public BatchRunner(List<Book> bookList) {
            this.bookList = bookList;
        }

        @Override
        public String call() {
            String ids = null;
            try {
                ids = batchSave();
                LOGGER.info(">>>>>>>>>> " + ids);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            return ids;
        }

        private String batchSave() throws InterruptedException {
            if (!CollectionUtils.isEmpty(bookList)) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < bookList.size(); i++) {
                    for (int j = 0; j < 1000; j++) {
                        if (j % 1000 == 0) {
                            Thread.sleep(500);
                            LOGGER.info("" + i);
                        }
                    }
                    Book savedBook = bookService.saveBook(bookList.get(i));
                    result.append(savedBook.getBookId()).append(" ");
                }
                return result.toString();
            } else {
                return "";
            }
        }
    }
}