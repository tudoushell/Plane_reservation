package entity;

/*
 * 管理员的的信息
 * 
 */
public class Admin {
	
		//身份证号
		private  String id;
		//管理员的登录账号
		private  String login_name;
		//管理员的密码
		private  String passWord;
		//管理员的名字
		private String name;
		//管理员的手机号
		private String phone_number;
		
		//无参构造器
		public Admin() {
			super();
		}
		
		//对管理员的信息进行初始化
		public Admin(String id, String loginName, String password, String name, String phoneNumber) {
			super();
			this.id = id;
			login_name = loginName;
			this.passWord = password;
			this.name = name;
			phone_number = phoneNumber;
		}


		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLogin_name() {
			return login_name;
		}
		public void setLogin_name(String loginName) {
			login_name = loginName;
		}
		public String getPassword() {
			return passWord;
		}
		public void setPassword(String password) {
			this.passWord = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(String phoneNumber) {
			phone_number = phoneNumber;
		}

		@Override
		public String toString() {
			return "Admin [id=" + id + ", login_name=" + login_name + ", name=" + name + ", passWord=" + passWord + ", phone_number=" + phone_number + "]";
		}
		
		
		
}
