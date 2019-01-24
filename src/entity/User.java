package entity;

/*
 * 用户信息
 * 
 */

public class User {
	//身份证号
	private String id;
	//姓名
	private String name;
	//用户登录名
	private String login_name;
	//用户密码
	private String passWord;
	//用户手机号
	private String phone_number;
	//用户地址
	private String address;
	
	
	public User() {
		super();
	}
	
	public User(String id, String name, String loginName, String passWord, String phoneNumber, String address) {
		super();
		this.id = id;
		this.name = name;
		login_name = loginName;
		this.passWord = passWord;
		phone_number = phoneNumber;
		this.address = address;
	}
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
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String loginName) {
		login_name = loginName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phoneNumber) {
		phone_number = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [address=" + address + ", id=" + id + ", login_name=" + login_name + ", name=" + name + ", passWord=" + passWord + ", phone_number=" + phone_number + "]";
	}
	
	
	
}
