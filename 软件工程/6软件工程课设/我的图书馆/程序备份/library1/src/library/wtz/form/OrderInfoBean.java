package library.wtz.form;

import java.sql.Date;

/**
 * ͼ��ԤԼ��
 * @author wangtianzhi
 *
 */
public class OrderInfoBean {
	private int bookId;//����ID
	private String bookName;//����
	private String library;//�ݲص�
	private String state;//�鿯״̬
	private Date restrictedTime;//Ӧ������
	private Date returnTime;//�黹ʱ�䣬����Ϊ��
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
