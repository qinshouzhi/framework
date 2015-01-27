/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: Book.java 9552 2015年1月26日 下午5:56:30 WangLijun$
 */
package com.newtouch.lion.common.excel;

/**
 * <p>
 * Title:Excel导出功能测试类
 * </p>
 * <p>
 * Description:Excel导出功能测试类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class Book {
	private int bookId;
	private String name;
	private String author;
	private float price;
	private String isbn;
	private String pubName;
	private byte[] preface;

	public Book() {
		super();
	}

	public Book(int bookId, String name, String author, float price,
			String isbn, String pubName, byte[] preface) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.pubName = pubName;
		this.preface = preface;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPubName() {
		return pubName;
	}

	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	public byte[] getPreface() {
		return preface;
	}

	public void setPreface(byte[] preface) {
		this.preface = preface;
	}
}
