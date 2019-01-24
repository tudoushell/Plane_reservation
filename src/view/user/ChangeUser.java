package view.user;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.User;
import exception.PlaneException;

import manager.UserManager;
import manager.Impl.UserManagerImp;

public class ChangeUser extends JFrame{
	
	// 标签
	JLabel passWord = new JLabel("输入密码：");
	JLabel confirmPw = new JLabel("确认密码：");
	JLabel name = new JLabel("真实姓名：");
	JLabel sex = new JLabel("性别：");
	JLabel phone = new JLabel("电话号码：");
	JLabel address = new JLabel("输入地址：");
	JLabel Id = new JLabel("身份证号：");
	
	//输入框
	JTextField pwdText = new JPasswordField(16);
	JTextField cpwdText = new JPasswordField(16);
	JTextField nameText = new JTextField(16);
	JTextField phoneText = new JTextField(16);
	JTextField addresText = new JTextField(16);
	JTextField idText = new JTextField(16);
	
	//面板
	JPanel regPanel = new JPanel();
	JPanel pwdP = new JPanel();
	JPanel cwdP = new JPanel();
	JPanel nameP = new JPanel();
	JPanel sexP = new JPanel();
	JPanel phoneP = new JPanel();
	JPanel addressP = new JPanel();
	JPanel idP = new JPanel();
	JPanel btnP = new JPanel();
	
	//修改按钮
	JButton btChange = new JButton("修改");
	JButton btExit = new JButton("取消");
	
	//下拉框
	JComboBox<String> sexBox = null;
	String[] sexArr = { "男", "女" };
	//登录名
	String loginName;
	
	
	//添加事件 
	public void addAction(){

		//修改按钮事件
		btChange.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//	1.先获取表单中的数据
				String pwd = pwdText.getText();
				String cwd =  cpwdText.getText();
				String  	name = nameText.getText();
				String phone = phoneText.getText();
				String address = addresText.getText();
				String id = idText.getText();
				
				//	2.进行密码判断且两者相同,同时进行输入信息判断，才存入数据库中
				String regPwd = "\\w{6,14}";
				String regId = "[0-9]{18}";
				String regPhone = "[0-9]{11}";
				if(!pwd.matches(regPwd)){
					JOptionPane.showMessageDialog(null, "您输入的密码不规范！");
					return;
				}
				if(!pwd.equals(cwd)){
					JOptionPane.showMessageDialog(null, "您输入的密码不一致！");
				}
				if(!id.matches(regId)){
					JOptionPane.showMessageDialog(null, "您输入身份证号18位格式不对！");
				}
				if(!phone.matches(regPhone)){
					JOptionPane.showMessageDialog(null, "您输入手机号格式不对！");
				}
				//	3.新建一个user类，将所有修改的信息存入user类
				User user = new User();
				user.setLogin_name(loginName);
				user.setPassWord(pwd);
				user.setName(name);
				user.setId(id);
				user.setPhone_number(phone);
				user.setAddress(address);				
				
				//	4.调用UserManagerImp类中 UpDateUser(User user) 方法来进行修改
				UserManager userManager = new UserManagerImp();
				boolean flag = userManager.UpDateUser(user);
				
				//5.返回是否修改成功，成功后返回到用户功能页面
				if(flag == true){
					JOptionPane.showMessageDialog(null, "修改成功！");
					new UserGui(loginName);
				}else{
					JOptionPane.showMessageDialog(null, "修改失败！");
				}
			}
		});
		
		//取消修改用户信息
		btExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserGui(loginName);
			}
		});
	}
	
	//添加组件的同时，获取数据库中用户的信息
	public void addComponent(){
		//将所有的组件添加到面板中
		pwdP.add(passWord);
		pwdP.add(pwdText);
		
		cwdP.add(confirmPw);
		cwdP.add(cpwdText);
		
		nameP.add(name);
		nameP.add(nameText);

		sexP.add(sex);
		sexBox = new JComboBox<String>(sexArr);
		sexP.add(sexBox);
	
		phoneP.add(phone);
		phoneP.add(phoneText);

	
		addressP.add(address);
		addressP.add(addresText);
		
		idP.add(Id);
		idP.add(idText);
		
		btnP.add(btChange);
		btnP.add(btExit);
		
		regPanel.setLayout(new GridLayout(8, 1));
		regPanel.add(pwdP);
		regPanel.add(cwdP);
		regPanel.add(nameP);
		regPanel.add(idP);
		regPanel.add(sexP);
		regPanel.add(phoneP);
		regPanel.add(addressP);
		regPanel.add(btnP);
		add(regPanel, BorderLayout.CENTER);
		//获取数据库中用户的信息
		UserManager userManager = new UserManagerImp();
		try {
			//将用户信息添加到表单中进行更改
			User user = userManager.getUserByLoginName(loginName);
			nameText.setText(user.getName());
			idText.setText(user.getId());
			phoneText.setText(user.getPhone_number());
			addresText.setText(user.getAddress());
		} catch (PlaneException e) {
			e.printStackTrace();
		}
	}
	
	//初始化界面
	public void initFrame(){
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//测试方法 
	public ChangeUser() {
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}
	
	public ChangeUser(String loginName){
		this.loginName = loginName;
		setTitle(loginName +" 信息修改界面");
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		ChangeUser c  = new ChangeUser("abcd123");
	}
}
