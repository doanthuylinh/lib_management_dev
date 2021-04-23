/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * [OVERVIEW] Book Entity.
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
@Table(name = "Book")
public class BookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "description")
    private String description;

    @Column(name = "language")
    private String language;

    @Column(name = "author")
    private String author;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "thumbnail")
    private String thumbnail;
    
    @Column(name = "rent_cost")
    private Double rentCost;

    @Column(name = "price")
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity categoryEntity;
    
    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
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

    public BookEntity(Integer bookId, String bookName, String description, String language, String author, Integer categoryId, Integer departmentId,
            String publicationDate, String thumbnail, Double rentCost, Double price) {
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
    }

    public BookEntity() {
        super();
    }

}
