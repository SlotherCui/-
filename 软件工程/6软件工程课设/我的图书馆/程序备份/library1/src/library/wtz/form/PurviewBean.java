package library.wtz.form;

public class PurviewBean {
	private String id;
	private String name;
    private int bookNum;//最多能借几本书
    private int bookTime;//最多能借多久
    private int orderNum;//做多能预约几本书
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBookNum() {
		return bookNum;
	}
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public int getBookTime() {
		return bookTime;
	}
	public void setBookTime(int bookTime) {
		this.bookTime = bookTime;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
}
