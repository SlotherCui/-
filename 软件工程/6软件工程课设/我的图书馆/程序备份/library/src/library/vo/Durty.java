package library.vo;
/**
 * 违章记录的javabean 对应数据库表中的durty
 * @author wangtianzhi
 *
 */
public class Durty {
	private int id;
	private int borrowId;
	private long money;
	private String describtion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

}
