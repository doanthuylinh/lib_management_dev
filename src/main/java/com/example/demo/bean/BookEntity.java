/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * [OVERVIEW] Book Entity.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new
 * 002       1.1       2021/04/21      LinhDT             Update DB
 * 003       1.2       2021/05/22      LinhDT             Update DB
*/
@Entity
@Table(name = "Book")
public class BookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @SerializedName("book_id")
    @JsonProperty("book_id")
    private Integer bookId;

    @Column(name = "book_name")
    @SerializedName("book_name")
    @JsonProperty("book_name")
    private String bookName;

    @Column(name = "description")
    @SerializedName("description")
    @JsonProperty("description")
    private String description;

    @Column(name = "language")
    @SerializedName("language")
    @JsonProperty("language")
    private String language;

    @Column(name = "author")
    @SerializedName("author")
    @JsonProperty("author")
    private String author;

    @Column(name = "category_id")
    @SerializedName("category_id")
    @JsonProperty("category_id")
    private Integer categoryId;

    @Column(name = "department_id")
    @SerializedName("department_id")
    @JsonProperty("department_id")
    private Integer departmentId;

    @Column(name = "publication_date")
    @SerializedName("publication_date")
    @JsonProperty("publication_date")
    private Date publicationDate;

    @Column(name = "thumbnail")
    @SerializedName("thumbnail")
    @JsonProperty("thumbnail")
    private String thumbnail;

    @Column(name = "rent_cost", columnDefinition = "double default 0")
    @SerializedName("rent_cost")
    @JsonProperty("rent_cost")
    private Double rentCost;

    @Column(name = "price", columnDefinition = "double default 0")
    @SerializedName("price")
    @JsonProperty("price")
    private Double price;

    @Column(name = "create_date")
    @SerializedName("create_date")
    @JsonProperty("create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @JsonProperty("category")
    @JsonInclude(Include.NON_NULL)
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    @JsonProperty("department")
    @JsonInclude(Include.NON_NULL)
    private DepartmentEntity departmentEntity;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getRentCost() {
        return rentCost;
    }

    public void setRentCost(Double rentCost) {
        this.rentCost = rentCost;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date datetime) {
        this.createDate = datetime;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public BookEntity(Integer bookId, String bookName, String description, String language, String author, Integer categoryId, Integer departmentId,
            Date publicationDate, String thumbnail, Double rentCost, Double price, Date createDate) {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
        this.description = description;
        this.language = language;
        this.author = author;
        this.categoryId = categoryId;
        this.departmentId = departmentId;
        this.publicationDate = publicationDate;
        this.thumbnail = thumbnail;
        this.rentCost = rentCost;
        this.price = price;
        this.createDate = createDate;
    }

    public BookEntity() {
        super();
    }

}
