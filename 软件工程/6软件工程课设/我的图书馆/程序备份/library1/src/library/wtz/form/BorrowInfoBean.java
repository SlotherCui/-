package library.wtz.form;

import java.sql.Date;

public class BorrowInfoBean {
	private int id;//�ǿ�
	private String isbn;//�ǿ�
	private String name;//�ǿ�
	private String author;//��������м��ö��Ÿ��������ߺ�����֮���÷ֺŸ������ǿ�
	private Date borrowTime;//�ǿ�
	private Date restrictTime;//Ӧ��ʱ�䣬�ǿ�
	private String library;//�ݲصأ��ǿ�
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
