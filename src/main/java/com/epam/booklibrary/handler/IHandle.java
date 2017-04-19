package com.epam.booklibrary.handler;

import java.io.IOException;

import com.epam.booklibrary.method.Request;
import com.epam.booklibrary.method.Response;

public interface IHandle {

	public void handle(Request rq, Response rp) throws IOException;

}
