package com.example.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.example.domain.Book;
import com.example.domain.Books;

/**
 * Asynchronous Integration Test by Jersey Test Framework
 * 
 * @author hanl
 *
 */
public class TIAsyncJFTTest extends JerseyTest {
    private final static Logger LOGGER = Logger.getLogger(TIAsyncJFTTest.class);
    private static final int COUNT = 2;

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(AsyncResource.class);
    }

    @Test
    public void testAsyncBatchSave() throws InterruptedException, ExecutionException {
        List<Book> bookList = new ArrayList<>(COUNT);
        TIAsyncJFTTest.LOGGER.debug("**->Test Batch Save");
        try {
            for (int i = 0; i < COUNT; i++) {
                final Book newBook = new Book(i + "-" + System.nanoTime());
                bookList.add(newBook);
            }
            Books books = new Books(bookList);
            final Entity<Books> booksEntity = Entity.entity(books, MediaType.APPLICATION_XML_TYPE);
            final Builder request = target("books").request();
            final AsyncInvoker async = request.async();
            final Future<String> responseFuture = async.post(booksEntity, String.class);
            TIAsyncJFTTest.LOGGER.debug("First response @" + System.currentTimeMillis());
            String result = null;
            try {
                result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                TIAsyncJFTTest.LOGGER.debug("%%%% " + e.getMessage());
            } finally {
                TIAsyncJFTTest.LOGGER.debug("Second response @" + System.currentTimeMillis());
                TIAsyncJFTTest.LOGGER.debug("<<<<<<<<<<< book id array: " + result);
            }
        } finally {
            TIAsyncJFTTest.LOGGER.debug("<-**Test Batch Save");
        }
    }

    @Test
    public void testAsyncBatchSaveCallBack() throws InterruptedException, ExecutionException {
        List<Book> bookList = new ArrayList<>(COUNT);
        TIAsyncJFTTest.LOGGER.debug("**->Test Batch Save");
        try {
            for (int i = 0; i < COUNT; i++) {
                final Book newBook = new Book(i + "-" + System.nanoTime());
                bookList.add(newBook);
            }
            Books books = new Books(bookList);
            final Entity<Books> booksEntity = Entity.entity(books, MediaType.APPLICATION_XML_TYPE);
            final AsyncInvoker async = target("books").request().async();
            final Future<String> responseFuture = async.post(booksEntity, new InvocationCallback<String>() {
                @Override
                public void completed(String result) {
                    TIAsyncJFTTest.LOGGER.debug("On Completed: " + result);
                }

                @Override
                public void failed(Throwable throwable) {
                    TIAsyncJFTTest.LOGGER.debug("On Failed: " + throwable.getMessage());
                    throwable.printStackTrace();
                }
            });
            TIAsyncJFTTest.LOGGER.debug("First response @" + System.currentTimeMillis());
            String result = null;
            try {
                result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                TIAsyncJFTTest.LOGGER.debug("%%%% " + e.getMessage());
            } finally {
                TIAsyncJFTTest.LOGGER.debug("Second response @" + System.currentTimeMillis());
                TIAsyncJFTTest.LOGGER.debug("<<<<<<<<<<< book id array: " + result);
            }
        } finally {
            TIAsyncJFTTest.LOGGER.debug("<-**Test Batch Save");
        }
    }

}
