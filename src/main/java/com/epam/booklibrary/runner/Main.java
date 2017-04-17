package com.epam.booklibrary.runner;

import java.io.IOException;

import com.epam.booklibrary.Server;

public class Main {

    public static void main(String[] args) throws IOException {

	Server webServer = new Server(8081, 25);
	webServer.start();
    }
}
