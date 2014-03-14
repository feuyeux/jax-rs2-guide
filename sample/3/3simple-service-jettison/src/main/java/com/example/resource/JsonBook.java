package com.example.resource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonBook {
    private String bookId;
    private String bookName;

    public JsonBook() {
        bookId = "1";
        bookName = "Java Restful Web Services使用指南";
    }

    @XmlElement
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @XmlElement
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
