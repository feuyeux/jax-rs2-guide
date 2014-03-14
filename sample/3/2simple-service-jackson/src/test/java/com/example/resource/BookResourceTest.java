package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class BookResourceTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(BookResourceTest.class);
    WebTarget booksTarget = target("books");
    
    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        ResourceConfig resourceConfig = new ResourceConfig(BookResource.class);
        resourceConfig.register(JacksonFeature.class);
        return resourceConfig;
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(new JacksonFeature());
        config.register(JsonContextProvider.class);
    }

    @Test
    public void testEmptyArray() {
        JsonBook book = booksTarget.path("emptybook").request(MediaType.APPLICATION_JSON).get(JsonBook.class);
        LOGGER.debug(book);
    }

    @Test
    public void testHybrid() {
        JsonHybridBook book = booksTarget.path("hybirdbook").request(MediaType.APPLICATION_JSON).get(JsonHybridBook.class);
        LOGGER.debug(book);
    }

    @Test
    public void testNoJaxb() {
        JsonNoJaxbBook book = booksTarget.path("nojaxbbook").request(MediaType.APPLICATION_JSON).get(JsonNoJaxbBook.class);
        LOGGER.debug(book);
    }

    @Test
    public void testGetBooks() {
        Books books = booksTarget.request(MediaType.APPLICATION_JSON_TYPE).get(Books.class);
        for (Book book : books.getBookList()) {
            LOGGER.debug(book.getBookName());
        }
    }

    @Test
    public void testPost() {
        Entity<Book> e = Entity.entity(new Book("abc"), MediaType.APPLICATION_JSON_TYPE);
        Book book = booksTarget.request(MediaType.APPLICATION_JSON_TYPE).post(e, Book.class);
        LOGGER.debug(book);
    }
}