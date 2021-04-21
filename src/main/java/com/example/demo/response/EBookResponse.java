///////////////////////////////////////////////////////////////////////////////
////
//// © 2021 IDTU-CS3332IRFA-21TSP
////
///////////////////////////////////////////////////////////////////////////////
//
//package com.example.demo.response;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
///**
// * [OVERVIEW] E-Book Response.
// *
// * @author: LinhDT
// * @version: 1.0
// * @History
// * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
// * --------------------------------------------------------------------------
// * 001       1.0       2021/04/15      LinhDT       	  Create new
//*/
//public class EBookResponse extends BookResponse {
//
//    @JsonProperty("date_added_to_library")
//    private String dateAddedToLibrary;
//    @JsonProperty("count_download")
//    private Integer countDownload;
//
//    public String getDateAddedToLibrary() {
//        return dateAddedToLibrary;
//    }
//
//    public void setDateAddedToLibrary(String dateAddedToLibrary) {
//        this.dateAddedToLibrary = dateAddedToLibrary;
//    }
//
//    public Integer getCountDownload() {
//        return countDownload;
//    }
//
//    public void setCountDownload(Integer countDownload) {
//        this.countDownload = countDownload;
//    }
//
//    public EBookResponse(Integer bookId, String bookName, String description, String language, String bookAuthor, String categoryName, String departmentName,
//            Integer quantity, String bookTypeName, String publicationDate, String dateAddedToLibrary, Integer countDownload) {
//        super(bookId, bookName, description, language, bookAuthor, categoryName, departmentName, quantity, bookTypeName, publicationDate);
//        this.dateAddedToLibrary = dateAddedToLibrary;
//        this.countDownload = countDownload;
//    }
//
//    public EBookResponse() {
//        super();
//    }
//
//    public EBookResponse(Integer bookId, String bookName, String description, String language, String bookAuthor, String categoryName, String departmentName,
//            Integer quantity, String bookTypeName, String publicationDate) {
//        super(bookId, bookName, description, language, bookAuthor, categoryName, departmentName, quantity, bookTypeName, publicationDate);
//    }
//
//}
