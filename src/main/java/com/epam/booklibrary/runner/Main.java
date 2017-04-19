package com.epam.booklibrary.runner;

import java.io.IOException;

import com.epam.booklibrary.Server;
import com.epam.booklibrary.constants.CommonConstants;
import com.epam.booklibrary.handler.imphandler.AddBook;
import com.epam.booklibrary.handler.imphandler.GetAllBooks;

public class Main {

    public static void main(String[] args) throws IOException {

        Server webServer = new Server(8081, 25);
        webServer.addHendler(CommonConstants.GET, "/book", new GetAllBooks());
        webServer.addHendler(CommonConstants.POST, "/book", new AddBook());
        webServer.start();
    }
}
