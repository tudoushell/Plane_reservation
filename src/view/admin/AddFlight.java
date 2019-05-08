package view.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import Dao.AdminDao;

import manager.AdminManager;
import manager.Impl.AdminManagerImp;

import entity.Flight;

public class AddFlight extends JFrame{
	AdminManager adminManager = (AdminManager) ObjectFactory.getObject("AdminManager");
	String loginName;
	// 面板
	JPanel mainP = new JPanel();
	JPanel numberP = new JPanel();
	JPanel takeoffTP = new JPanel();
	JPanel flyP = new JPanel();
	JPanel startP = new JPanel();
	JPanel endP = new JPanel();
	JPanel ticketP = new JPanel();
	JPanel priceP = new JPanel();
	JPanel btnP = new JPanel();
	
	// 标签
	JLabel numbersJL = new JLabel("航班编号：");
	JLabel takeoffJL = new JLabel("起飞时间：");
	JLabel flytJL = new JLabel("飞行时间：");
	JLabel startJL = new JLabel("出发地点：");
	JLabel endJL = new JLabel("目的地点：");
	JLabel ticketJL = new JLabel("票的数量：");
	JLabel priceJL = new JLabel("票的价格：");
	
	// 输入框
	JTextField numberText = new JTextField(16);
	JTextField takeoffText = new  JTextField(16);
	JTextField flyText = new  JTextField(16);
	JTextField startText = new JTextField(16);
	JTextField endText = new JTextField(16);
	JTextField ticketText = new JTextField(16);
	JTextField priceText = new JTextField(16);
	
	// 提交、重置、返回按钮
	JButton sumbit = null;
	JButton reset = null;
	JButton returnBt = null;
	
	
	
	
	//各种事件 
	public void addAction(){
		
		
		
		//添加航班事件 
		sumbit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					String flightNum = numberText.getText();
					String takeoffTime = takeoffText.getText();
					String flyTime = flyText.getText();
					String startPlace = startText.getText();
					String endPlace = endText.getText();
					String ticket = ticketText.getText();
					String price = priceText.getText();
					if(flightNum.length() == 0 && takeoffTime.length() == 0 && flyTime.length() == 0 && 
					   startPlace.length() == 0 && endPlace.length() == 0 && ticket.length() == 0 && price.length() == 0){
						JOptionPane.showMessageDialog(null, "输入的内容不能为空");
					}
					//将这些信息添加到类中
					Flight flight = new Flight();
					flight.setFlight_number(flightNum);
					flight.setTakeoff_time(takeoffTime);
					flight.setFlying_time(flyTime);
					flight.setStart_place(startPlace);
					flight.setEnd_place(endPlace);
					flight.setTickets(Integer.parseInt(ticket));
					flight.setPrice(Float.parseFloat(price));
					//调用manager管理进行存储
					
//					AdminManager adminManager =  new AdminManagerImp();
					boolean flag = adminManager.addFlight(flight);
					if(flag){
						JOptionPane.showMessageDialog(null, "添加成功");
					}else{
						JOptionPane.showMessageDialog(null, "添加失败");
					}
			}
		});
		
		//重置事件 
		reset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				numberText.setText(null);
				takeoffText.setText(null);
				flyText.setText(null);
				startText.setText(null);
				endText.setText(null);
				ticketText.setText(null);
				priceText.setText(null);
			}
		});
		
		//返回事件 
		returnBt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new AdminGui(loginName);
			}
		});
	}
	
	//添加组件
	public void addComponent(){
		numberP.add(numbersJL);
		numberP.add(numberText);
		
		takeoffTP.add(takeoffJL);
		takeoffTP.add(takeoffText);
		
		flyP.add(flytJL);
		flyP.add(flyText);
		
		startP.add(startJL);
		startP.add(startText);
		
		endP.add(endJL);
		endP.add(endText);
		
		ticketP.add(ticketJL);
		ticketP.add(ticketText);
		
		priceP.add(priceJL);
		priceP.add(priceText);
		
		sumbit = new JButton("提交");
		reset = new JButton("重置");
		returnBt = new JButton("返回界面");
		
		btnP.add(sumbit);
		btnP.add(reset);
		btnP.add(returnBt);
		
		mainP.setLayout(new GridLayout(8, 1));
		mainP.add(numberP);
		mainP.add(takeoffTP);
		mainP.add(flyP);
		mainP.add(startP);
		mainP.add(endP);
		mainP.add(ticketP);
		mainP.add(priceP);
		mainP.add(btnP);
		
		add(mainP, BorderLayout.CENTER);
		
	}
	
	// 初始化界面
	public void initFrame() {
		setSize(800, 600);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	//构造器
	public AddFlight(String loginName) {
		this.loginName = loginName;
		setTitle("添加航班");
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}
}
