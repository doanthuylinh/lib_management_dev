/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.data.BookItemStatus;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * [OVERVIEW] Book Item Entity.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT       	  Create new
 * 002       1.1       2021/04/21      LinhDT             Update DB
*/
@Entity
@Table(name = "BookItem")
public class BookItemEntity {

    @Id
    @Column(name = "book_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("book_item_id")
    @JsonProperty("book_item_id")
    private Integer bookItemId;

    @Column(name = "barcode")
    @SerializedName("barcode")
    @JsonProperty("barcode")
    private String barcode;

    @Column(name = "book_id")
    @SerializedName("book_id")
    @JsonProperty("book_id")
    private Integer bookId;

    @Column(name = "date_of_purchase")
    @SerializedName("date_of_purchase")
    @JsonProperty("date_of_purchase")
    private Date dateOfPurchase;

    @Column(name = "date_added_to_library")
    @SerializedName("date_added_to_library")
    @JsonProperty("date_added_to_library")
    private Date dateAddedToLibrary;

    @Column(name = "location")
    @SerializedName("location")
    @JsonProperty("location")
    private String location;

    @Column(name = "status")
    @SerializedName("status")
    @JsonProperty("status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    @JsonInclude(Include.NON_NULL)
    @JsonIgnore
    private BookEntity bookEntity;

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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date getDateAddedToLibrary() {
        return dateAddedToLibrary;
    }

    public void setDateAddedToLibrary(Date dateAddedToLibrary) {
        this.dateAddedToLibrary = dateAddedToLibrary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BookItemStatus getStatus() {
        return BookItemStatus.parse(this.status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @JsonGetter("status")
    public Integer getStatusValue() {
    	return this.status;
    }

    public void setStatus(BookItemStatus status) {
        this.status = status.value();
    }

    public BookItemEntity() {
        super();
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public BookItemEntity(Integer bookItemId, String barcode, Integer bookId, Date dateOfPurchase, Date dateAddedToLibrary, String location, Integer status,
            BookEntity bookEntity) {
        super();
        this.bookItemId = bookItemId;
        this.barcode = barcode;
        this.bookId = bookId;
        this.dateOfPurchase = dateOfPurchase;
        this.dateAddedToLibrary = dateAddedToLibrary;
        this.location = location;
        this.status = status;
        this.bookEntity = bookEntity;
    }

}
