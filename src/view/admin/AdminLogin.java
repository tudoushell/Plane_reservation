package view.admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import objectfactory.ObjectFactory;

import manager.AdminManager;
import manager.UserManager;
import manager.Impl.AdminManagerImp;
import manager.Impl.UserManagerImp;
import view.user.UserGui;

public class AdminLogin extends JFrame{
//	AdminManager adminManager = (AdminManager) ObjectFactory.getObject("AdminManager");

	JPanel titleP = new JPanel();
	JLabel title = new JLabel("航空管理员登录系统");
	
	JPanel loginP = new JPanel();
	JLabel user = new JLabel("账号:");
	JLabel pwd = new JLabel("密码:");
	
	JTextField userName = new JTextField(16);
	JTextField passWord = new JPasswordField(16);
	
	JPanel btnP = new JPanel();
	JButton login = new JButton("登录");
	JButton reset = new JButton("重置");
	
	
	
	
	
	//界面初始化
	public void initFrame(){
		
		setSize(400,250);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//添加组件
	public void addComponent(){
			//设置标题
			titleP.setLayout(new FlowLayout());
			titleP.add(title);	
			add(titleP,"North");
			//设置输入框
			loginP.setLayout(null);
			user.setBounds(90, 40, 50, 20);
			pwd.setBounds(90, 80, 50, 20);
			loginP.add(user);
			loginP.add(pwd);
			userName.setBounds(160,40,120,20);
			passWord.setBounds(160,80,120,20);
			loginP.add(userName);
			loginP.add(passWord);
			add(loginP,"Center");
			//设置按钮
			btnP.setLayout(new FlowLayout());
			btnP.add(login);
			btnP.add(reset);
			add(btnP,"South");
	}
	
	//添加事件 
	public void addAction(){
		//登录按钮事件
		login.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				AdminManager adminManager = new AdminManagerImp();
				String loginName = userName.getText();
				String pwds = passWord.getText();
				//判断用户输入的管理员和密码是否为空
				if(loginName.length() == 0){
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
					return ;
				}
				if(pwds.length() == 0){
					JOptionPane.showMessageDialog(null, "密码不能为空！");
					return ;
				}
				String regLogin = "[A-Z a-z 0-9]{1,}";
				//判断用户输入的用户名和密码是否正确，防止特殊字符进行SQL注入
				if(!loginName.matches(regLogin)){
					JOptionPane.showMessageDialog(null, "禁止输入特殊字符！");
					return;
				}
				//判断是否登录成功
				if(adminManager.adminLogin(loginName, pwds)){
					JOptionPane.showMessageDialog(null, "登录成功！");
					setVisible(false);
					//成功后传递登录名
					new AdminGui(loginName);
					
				}else{
					JOptionPane.showMessageDialog(null, "输入的账号或密码错误！");
					return;
				}
			}
		});
		
		//重置按钮事件 
		reset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				userName.setText(null);
				passWord.setText(null);
			}
		});
	}
	public AdminLogin() {
		super("管理员登录");
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}

	
	public static void main(String[] args) {
		AdminLogin a = new AdminLogin();
	}


}
