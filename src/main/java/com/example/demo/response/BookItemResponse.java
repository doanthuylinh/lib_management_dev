/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] Book Item Response.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT             Create new
 * 002       1.1       2021/04/21      LinhDT             Update Json Properties
*/
public class BookItemResponse extends BookResponse {

    @JsonProperty("book_item_id")
    private Integer bookItemId;
    @JsonProperty("barcode")
    private String barcode;
    @JsonProperty("date_of_purchase")
    private String dateOfPurchase;
    @JsonProperty("date_added_to_library")
    private String dateAddedToLibrary;
    @JsonProperty("location")
    private String location;
    @JsonProperty("state")
    private Integer state;

    public Integer getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(Integer bookItemId) {
        this.bookItemId = bookItemId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getDateAddedToLibrary() {
        return dateAddedToLibrary;
    }

    public void setDateAddedToLibrary(String dateAddedToLibrary) {
        this.dateAddedToLibrary = dateAddedToLibrary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BookItemResponse(Integer bookId, String bookName, String description, String language, String author, String categoryName, String departmentName,
            String publicationDate, String thumbnail, Double price, Integer bookItemId, String barcode, String dateOfPurchase, String dateAddedToLibrary,
            String location, Double rentCost, Date createDate, Integer state) {
        super(bookId, bookName, description, language, author, categoryName, departmentName, publicationDate, thumbnail, rentCost, price, createDate);
        this.bookItemId = bookItemId;
        this.barcode = barcode;
        this.dateOfPurchase = dateOfPurchase;
        this.dateAddedToLibrary = dateAddedToLibrary;
        this.location = location;
        this.state = state;
    }

    public BookItemResponse() {
        super();
    }

    public BookItemResponse(Integer bookId, String bookName, String description, String language, String author, String categoryName, String departmentName,
            String publicationDate, String thumbnail, Double rentCost, Date createDate, Double price) {
        super(bookId, bookName, description, language, author, categoryName, departmentName, publicationDate, thumbnail, rentCost, price, createDate);

    }

}
