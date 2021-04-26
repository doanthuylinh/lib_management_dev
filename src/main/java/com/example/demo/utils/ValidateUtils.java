package com.example.demo.utils;

import com.example.demo.bean.BookEntity;
import com.example.demo.bean.BookItemEntity;
import com.example.demo.exception.ApiValidateException;

public class ValidateUtils {
	
	public static void validateDataBody(String data) throws ApiValidateException {
		if (DataUtils.isNullOrEmpty(data)) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "Data is not null"));
		}
	}
	
	public static void validateUpdateBook(BookEntity book) throws ApiValidateException {
		if (DataUtils.isNullOrEmpty(book.getBookId())) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "book_id"));
		}
		
		validateAddBook(book);
	}
	
	public static void validateAddBook(BookEntity book) throws ApiValidateException {
		
		if (DataUtils.isNullOrEmpty(book.getBookName())) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "book_name"));
		}
		
		if (DataUtils.isNullOrEmpty(book.getDescription())) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "description"));
		}
		
		if (DataUtils.isNullOrEmpty(book.getAuthor())) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "author"));
		}
	}
	
	public static void validateUpdateBookItem(BookItemEntity bookItem) throws ApiValidateException {
		if (DataUtils.isNullOrEmpty(bookItem.getBookItemId())) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "book__item_id"));
		}
		
		validateAddBookItem(bookItem);
	}
	
	public static void validateAddBookItem(BookItemEntity bookItem) throws ApiValidateException {
		if (DataUtils.isNullOrEmpty(bookItem.getBookId())) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "book_id"));
		}
		
		if (DataUtils.isNullOrEmpty(bookItem.getBarcode())) {
			throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "barcode"));
		}
	}
}
