package library.wtz.form;

import java.sql.Date;

public class BorrowInfoBean {
	private int id;//非空
	private String isbn;//非空
	private String name;//非空
	private String author;//多个作者中间用逗号隔开，作者和译者之间用分号隔开，非空
	private Date borrowTime;//非空
	private Date restrictTime;//应还时间，非空
	private String library;//馆藏地，非空
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Date getRestrictTime() {
		return restrictTime;
	}
	public void setRestrictTime(Date restrictTime) {
		this.restrictTime = restrictTime;
	}
	public String getLibrary() {
		return library;
	}
	public void setLibrary(String library) {
		this.library = library;
	}

}
