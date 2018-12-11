package library.wtz.form;

import java.sql.Date;

/**
 * 图书预约表单
 * @author wangtianzhi
 *
 */
public class OrderInfoBean {
	private int bookId;//书编号ID
	private String bookName;//书名
	private String library;//馆藏地
	private String state;//书刊状态
	private Date restrictedTime;//应还日期
	private Date returnTime;//归还时间，可以为空
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getLibrary() {
		return library;
	}
	public void setLibrary(String library) {
		this.library = library;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getRestrictedTime() {
		return restrictedTime;
	}
	public void setRestrictedTime(Date restrictedTime) {
		this.restrictedTime = restrictedTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	

}
