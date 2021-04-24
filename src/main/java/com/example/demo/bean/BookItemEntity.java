/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    private Integer bookItemId;

    @Column(name = "barcode")
    @SerializedName("barcode")
    private String barcode;

    @Column(name = "book_id")
    @SerializedName("book_id")
    private Integer bookId;

    @Column(name = "date_of_purchase")
    @SerializedName("date_of_purchase")
    private String dateOfPurchase;

    @Column(name = "date_added_to_library")
    @SerializedName("date_added_to_library")
    private String dateAddedToLibrary;

    @Column(name = "location")
    @SerializedName("location")
    private String location;

    @Enumerated
    @Column(name = "state", columnDefinition = "int")
    @SerializedName("state")
    private State state;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable=false, updatable=false)
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public BookItemEntity(Integer bookItemId, String barcode, Integer bookId, String dateOfPurchase, String dateAddedToLibrary, String location, State state) {
        super();
        this.bookItemId = bookItemId;
        this.barcode = barcode;
        this.bookId = bookId;
        this.dateOfPurchase = dateOfPurchase;
        this.dateAddedToLibrary = dateAddedToLibrary;
        this.location = location;
        this.state = state;
    }

}
