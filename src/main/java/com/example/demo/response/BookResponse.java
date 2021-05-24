/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] BookResponse.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT             Create new
 * 002       1.1       2021/04/21      LinhDT             Update Json Properties
*/
public class BookResponse {

    @JsonProperty("book_id")
    protected Integer bookId;
    @JsonProperty("book_name")
    protected String bookName;
    @JsonProperty("description")
    protected String description;
    @JsonProperty("language")
    protected String language;
    @JsonProperty("author")
    protected String author;
    @JsonProperty("category_name")
    protected String categoryName;
    @JsonProperty("department_name")
    protected String departmentName;
    @JsonProperty("publication_date")
    protected Date publicationDate;
    @JsonProperty("thumbnail")
    protected String thumbnail;
    @JsonProperty("rent_cost")
    protected Double rentCost;
    @JsonProperty("price")
    protected Double price;
    @JsonProperty("create_date")
    protected Date createDate;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }
    
    @JsonGetter("publication_date")
    public String getPublicationDateValue() {
    	if (publicationDate == null) return null;
    	
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateValue = df.format(publicationDate);
        return dateValue;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRentCost() {
        return rentCost;
    }

    public void setRentCost(Double rentCost) {
        this.rentCost = rentCost;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BookResponse(Integer bookId, String bookName, String description, String language, String author, String categoryName, String departmentName,
            Date publicationDate, String thumbnail, Double rentCost, Double price, Date createDate) {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
        this.description = description;
        this.language = language;
        this.author = author;
        this.categoryName = categoryName;
        this.departmentName = departmentName;
        this.publicationDate = publicationDate;
        this.thumbnail = thumbnail;
        this.rentCost = rentCost;
        this.price = price;
        this.createDate = createDate;
    }

    public BookResponse(Integer bookId, String bookName, String description, String language, String author, String categoryName, String departmentName,
            Date publicationDate, String thumbnail, Double rentCost, Double price) {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
        this.description = description;
        this.language = language;
        this.author = author;
        this.categoryName = categoryName;
        this.departmentName = departmentName;
        this.publicationDate = publicationDate;
        this.thumbnail = thumbnail;
        this.rentCost = rentCost;
        this.price = price;
    }

    public BookResponse() {
        super();
    }

}
