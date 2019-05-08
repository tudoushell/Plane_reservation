package view.user;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import view.*;

public class RegisterPage extends JFrame {
	// 面板
	JPanel regPanel = new JPanel();
	JPanel loginP = new JPanel();
	JPanel pwdP = new JPanel();
	JPanel cwdP = new JPanel();
	JPanel nameP = new JPanel();
	JPanel idP = new JPanel();
	JPanel sexP = new JPanel();
	JPanel phoneP = new JPanel();
	JPanel emailP = new JPanel();
	JPanel addressP = new JPanel();
	JPanel btnP = new JPanel();
	// 标签
	JLabel loginName = new JLabel("输入用户：");
	JLabel passWord = new JLabel("输入密码：");
	JLabel confirmPw = new JLabel("确认密码：");
	JLabel name = new JLabel("真实姓名：");
	JLabel Id = new JLabel("身份证号：");
	JLabel sex = new JLabel("性别：");
	JLabel phone = new JLabel("电话号码：");
	JLabel email = new JLabel("电子邮箱：");
	JLabel address = new JLabel("输入地址：");
	// 输入框
	JTextField loginText = new JTextField(16);
	JTextField pwdText = new JPasswordField(16);
	JTextField cpwdText = new JPasswordField(16);
	JTextField nameText = new JTextField(16);
	JTextField idText = new JTextField(16);
	JTextField phoneText = new JTextField(16);
	JTextField emailText = new JTextField(16);
	JTextField addresText = new JTextField(16);
	// 提示文本
	// JLabel loginPoint = new JLabel("*用户名长度为6－14的字母数字");
	// JLabel pwdPoint = new JLabel("＊密码长度为6-14的数字");
	// JLabel cpwdPoint = new JLabel("*密码必须保持一致");
	// JLabel namePoint = new JLabel("*应为真实姓名");
	// JLabel idPoint = new JLabel("*真实身份证号");
	// JLabel phonePoint = new JLabel("*电话号码长度为11的数字");
	// JLabel emailPoint = new JLabel("*选项");
	// JLabel addressPoint = new JLabel("*选项");
	// 下拉框
	JComboBox<String> sexBox = null;
	String[] sexArr = { "男", "女" };

	// 提交和重置按钮
	JButton sumbit = null;
	JButton reset = null;

	// 初始化界面
	public void initFrame() {
		setSize(800, 600);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 添加组件
	public void addComponent() {
		loginP.add(loginName);
		loginP.add(loginText);

		pwdP.add(passWord);
		pwdP.add(pwdText);

		cwdP.add(confirmPw);
		cwdP.add(cpwdText);

		nameP.add(name);
		nameP.add(nameText);

		idP.add(Id);
		idP.add(idText);

		sexP.add(sex);
		sexBox = new JComboBox<String>(sexArr);
		sexP.add(sexBox);

		phoneP.add(phone);
		phoneP.add(phoneText);

		emailP.add(email);
		emailP.add(emailText);

		addressP.add(address);
		addressP.add(addresText);

		sumbit = new JButton("提交");
		reset = new JButton("重置");
		btnP.add(sumbit);
		btnP.add(reset);

		regPanel.setLayout(new GridLayout(10, 1));
		regPanel.add(loginP);
		regPanel.add(pwdP);
		regPanel.add(cwdP);
		regPanel.add(nameP);
		regPanel.add(idP);
		regPanel.add(sexP);
		regPanel.add(phoneP);
		regPanel.add(emailP);
		regPanel.add(addressP);
		regPanel.add(btnP);

		add(regPanel, BorderLayout.CENTER);
	}

	public void addAction() {
		// 提交按钮提交事件
		sumbit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				UserManager userManager = new UserManagerImp();

				// 匹配登录名
				String loginName = loginText.getText();
				String reg = "[A-Z a-z 0-9]{6,}";
				// 用于判断登录名是否存在
				if (!loginName.matches(reg)) {
					JOptionPane.showMessageDialog(null, "请输入长度为6－14的字母数字");
					return;
				} else {
					try {
						boolean flag = userManager.isLoginName(loginName);
						if (flag == true) {
							JOptionPane.showMessageDialog(null, "该登录名已存在");
							return;
						}
					} catch (PlaneException e1) {
						e1.printStackTrace();
					}
				}

				// 匹配密码
				String passwd = pwdText.getText();
				String cpwd = cpwdText.getText();
				String regs = "\\w{6,14}";
				if (!passwd.matches(regs)) {
					JOptionPane.showMessageDialog(null, "请输入密码长度为6-14位");

				} else if (!passwd.equals(cpwd)) {
					JOptionPane.showMessageDialog(null, "密码不一致");
					return;
				}

				// 匹配姓名
				String names = nameText.getText();
				System.out.println(names);
				if (names.length() == 0) {
					JOptionPane.showMessageDialog(null, "姓名不能为空！");
					return;

				}
				// 匹配身份证号
				String ids = idText.getText();
				String regId = "[0-9]{18}";
				if (!ids.matches(regId)) {
					JOptionPane.showMessageDialog(null, "请输入18位数字的身份证号");
					return;
				}

				// 匹配电话号
				String phoneN = phoneText.getText();
				String regPhone = "[0-9]{11}";
				if (!phoneN.matches(regPhone)) {
					JOptionPane.showMessageDialog(null, "请输入11位数字的电话");
					return;
				}

				// 匹配电子邮箱
				String emails = emailText.getText();
				String regEmail = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]{2,}\\.(com||cn||org)+$";
				if (!emails.matches(regEmail)) {
					JOptionPane.showMessageDialog(null, "请输入正确的邮箱地址");
					return;
				}

				// 获取性别信息
				String sexs = (String) sexBox.getSelectedItem();
				String addr = addresText.getText();

				// 将所有的信息写入user
				User user = new User();
				user.setLogin_name(loginName);
				user.setPassWord(passwd);
				user.setName(names);
				user.setId(ids);
				user.setPhone_number(phoneN);
				user.setAddress(addr);
				// 将用户信息写入到数据库中
				boolean flag = userManager.addUser(user);
				if (flag != false) {
					JOptionPane.showMessageDialog(null, "注册成功");
					// 清空输入框
					loginText.setText(null);
					pwdText.setText(null);
					cpwdText.setText(null);
					nameText.setText(null);
					idText.setText(null);
					phoneText.setText(null);
					emailText.setText(null);
					addresText.setText(null);
					// 判断是否返回界面
					int i = JOptionPane.showConfirmDialog(null, "是否返回主界面？");
					if (i == 0) {
						setVisible(false);
						new MainPage();
					}
				} else {
					JOptionPane.showMessageDialog(null, "注册失败");
				}

			}
		});

		// 重置按钮
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loginText.setText(null);
				pwdText.setText(null);
				cpwdText.setText(null);
				nameText.setText(null);
				idText.setText(null);
				phoneText.setText(null);
				emailText.setText(null);
				addresText.setText(null);
			}
		});
	}

	public RegisterPage() {
		super("会员注册");
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}

}
