package com.epam.handler.imphandler;

import java.io.IOException;

import com.epam.Store;
import com.epam.constants.ResponseConstants;
import com.epam.handler.IHandle;
import com.epam.method.Request;
import com.epam.method.Response;
import com.epam.model.BookPojo;
import com.epam.utils.jackson.JsonUtils;

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
			Store.addBook(bookCreate.getBook());
			rp.setStatusCode(ResponseConstants.STATUS_CODE_201_CREATED);
		}

		rp.write();
	}

}
