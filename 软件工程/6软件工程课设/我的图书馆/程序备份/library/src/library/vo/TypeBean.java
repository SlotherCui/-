package library.vo;
/**
 * javabean,对应于数据库的Type表
 * @author wangtianzhi
 *
 */
public class TypeBean {
	private String id;
	private String name;
	private String describtion;
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
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

}
