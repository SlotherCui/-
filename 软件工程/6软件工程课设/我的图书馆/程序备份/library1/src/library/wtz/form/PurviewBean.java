package library.wtz.form;

public class PurviewBean {
	private String id;
	private String name;
    private int bookNum;//����ܽ輸����
    private int bookTime;//����ܽ���
    private int orderNum;//������ԤԼ������
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
