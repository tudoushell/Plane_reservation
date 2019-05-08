package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.admin.AdminLogin;
import view.user.RegisterPage;
import view.user.UserLogin;

public class MainPage extends JFrame{
	//背景图片 
	JLabel Jpic = new JLabel(new ImageIcon("src/plane.jpg"));
	//按钮组件
	JButton btn_login = null;
	JButton btn_register = null;
	JButton btn_admin = null;
	//按钮面板
	JPanel btn_panel = null;

	//界面初始化
	public void initFrame(){
		setSize(516,430);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addComponent(){
		//设置按钮和面板
		btn_register = new JButton("会员注册");
		btn_login = new JButton("会员登录");
		btn_admin = new JButton("管理员通道");
		btn_panel = new JPanel();
		btn_panel.add(btn_register);
		btn_panel.add(btn_login);
		btn_panel.add(btn_admin);
		Jpic.setBounds(0, 0, 516, 500);
		Jpic.setLayout(new FlowLayout());
		
		add(Jpic,BorderLayout.NORTH);
		add(btn_panel,BorderLayout.SOUTH);
	}
	
	public void addAction(){
		//管理员登录事件 
		btn_admin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AdminLogin();
			}
		});
		
		//会员注册按钮事件
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new RegisterPage();
			}
		});
		
		//会员登录事件
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserLogin();
				
			}
		});
	}
	
	public MainPage() {
		super("航空订单系统");
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainPage main = new MainPage();
	}
}
