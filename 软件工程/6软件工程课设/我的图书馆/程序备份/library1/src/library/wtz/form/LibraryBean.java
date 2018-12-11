package library.wtz.form;
/**
 * javabean,对应于数据库的Library表
 * @author wangtianzhi
 *
 */
public class LibraryBean {
	private String id;
	private String name;
	private String address;
	private String tel;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
