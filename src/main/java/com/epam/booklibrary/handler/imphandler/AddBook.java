package com.epam.booklibrary.handler.imphandler;

import java.io.IOException;


import com.epam.booklibrary.StoreOfBook;
import com.epam.booklibrary.handler.IHandle;
import com.epam.booklibrary.method.Request;
import com.epam.booklibrary.method.Response;
import com.epam.booklibrary.model.BookPojo;
import com.epam.booklibrary.constants.ResponseConstants;
import com.epam.booklibrary.utils.jackson.JsonUtils;

public class AddBook implements IHandle {

	public void handle(Request rq, Response rp) throws IOException {
		boolean isMap = true;
		rp.setVersion(rq.getVersion());

		BookPojo bookCreate = null;
		try {
			bookCreate = JsonUtils.fromJson(rq.getBody(), BookPojo.class);
		} catch (Exception ex) {
			rp.setStatusCode(ResponseConstants.STATUS_CODE_400_BAD_REQUEST);
			isMap = false;
		}

		if (isMap) {
			StoreOfBook.addBook(bookCreate.getBook());
			rp.setStatusCode(ResponseConstants.STATUS_CODE_201_CREATED);
		}

		rp.write();
	}

}
