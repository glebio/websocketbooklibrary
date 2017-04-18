package com.epam.booklibrary.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlType(name = "Book", propOrder = { "id", "titleOfBook", "edition", "authorOfBook", "dateOfBook"})
@XmlAccessorType(XmlAccessType.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @XmlElement(required = true, name = "id")
    @JsonProperty("id")
    private int id;

    @XmlElement(required = true, name = "titleOfBook")
    @JsonProperty("titleOfBook")
    private String titleOfBook;

    @XmlElement(required = true, name = "authorOfBook")
    @JsonProperty("authorOfBook")
    private String authorOfBook;

    @XmlElement(required = true, name = "create_date")
    @JsonProperty("create_date")
    private String dateOfBook;

    public Book() {
    }

    public Book(int id, String titleOfBook, String authorOfBook, String dateOfBook) {
	this.id = id;
	this.titleOfBook = titleOfBook;
	this.authorOfBook = authorOfBook;
	this.dateOfBook = dateOfBook;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTitleOfBook() {
	return titleOfBook;
    }

    public void setTitleOfBook(String titleOfBook) {
	this.titleOfBook = titleOfBook;
    }


    public String getAuthorOfBook() {
	return authorOfBook;
    }

    public void setAuthorOfBook(String authorOfBook) {
	this.authorOfBook = authorOfBook;
    }

    public String getDateOfBook() {
	return dateOfBook;
    }

    public void setDateOfBook(String dateOfBook) {
	this.dateOfBook = dateOfBook;
    }

}
