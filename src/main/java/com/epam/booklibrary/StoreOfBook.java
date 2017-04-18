package com.epam.booklibrary;

import java.util.ArrayList;
import java.util.List;

import com.epam.booklibrary.model.Book;

public class StoreOfBook {

    public static List<Book> getAllBook() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(01, "Title1", "Author1", "2000-01-12 00:00:00 -0800"));
        books.add(new Book(02, "Title2", "Author2", "2000-01-12 00:00:00 -0700"));
        return books;
    }

    public static void addBook(Book book) {
        List<Book> allBook = getAllBook();
        allBook.add(book);
    }

}
