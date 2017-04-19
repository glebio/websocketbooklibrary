package com.epam.booklibrary.handler.imphandler;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.epam.booklibrary.StoreOfBook;
import com.epam.booklibrary.constants.CommonConstants;
import com.epam.booklibrary.constants.ResponseConstants;
import com.epam.booklibrary.handler.IHandle;
import com.epam.booklibrary.method.Request;
import com.epam.booklibrary.method.Response;
import com.epam.booklibrary.model.Book;
import com.epam.booklibrary.model.BooksPojo;
import com.epam.booklibrary.utils.jackson.JsonUtils;
import com.epam.booklibrary.utils.marshaller.MarshallerHelper;

public class GetAllBooks implements IHandle {

    public void handle(Request rq, Response rp) throws IOException {
	String acceptType = rq.getAccept();

	try {
	    response(rq, rp, acceptType);
	} catch (JAXBException e) {
	    e.printStackTrace();
	}
    }

    private void response(Request rq, Response rp, String acceptType) throws JAXBException {
	String body = "";
	List<Book> books = StoreOfBook.getAllBook();

	rp.setVersion(rq.getVersion());
	rp.setStatusCode(ResponseConstants.STATUS_CODE_200_OK);
	rp.setContentType(rq.getAccept());

	BooksPojo book = new BooksPojo(books);

	if (acceptType.equals(CommonConstants.ACCEPT_TYPE_XML)) {

	    StringWriter writer = new StringWriter();
	    MarshallerHelper.marshall(book, writer);

	    body = writer.toString();

	    rp.setContentLength(String.valueOf(body.getBytes().length));
	    rp.setBody(body);
	} else {
	    body = JsonUtils.toJson(book);
	    rp.setContentLength(String.valueOf(body.getBytes().length));
	    rp.setBody(body);
	}

	try {
	    rp.write();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
