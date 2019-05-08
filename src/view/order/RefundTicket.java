package view.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import objectfactory.ObjectFactory;

import manager.FlightManger;
import manager.OrderTicketManager;
import manager.Impl.FlightManagerImp;
import manager.Impl.OrderTicketManagerImp;
import entity.Flight;
import entity.OrderTicket;

import view.user.UserGui;

public class RefundTicket extends JFrame{
	String loginName;
	String info[] = null;
	JTextArea orderMsg=new JTextArea("订单号\t航班号\t  用户名\t  出发地\t  目的地\t  起飞时间\n");
	//面板
	JPanel panel = new JPanel();
	JPanel panel2=new JPanel(new GridLayout(2, 6,10, 10));
	JPanel panel3 = new JPanel();
	//按钮
	JButton refundTicket = new JButton("退票");
	JButton btnReturn  = new JButton("返回主界面");
	//标签和文本区域
	JLabel[] orderLbArr=new JLabel[6];
	JTextArea[] orderTxArr=new JTextArea[6];
	
	OrderTicketManager orderTicketManager = (OrderTicketManager) ObjectFactory.getObject("OrderTicketManager");

	
//	OrderTicketManager orderTicketManager = new OrderTicketManagerImp();

	//列出用户订单信息
	public void listFlight() throws SQLException{
		/*
		 * 列出用户订单信息
		 */
		//1.调用OderTicketImp中的listOrderByloginName的信息,如没有任何订单则提示
		try{
			List<OrderTicket>list = orderTicketManager.listOrderByloginName(loginName);
			if(list == null){
				JOptionPane.showMessageDialog(null, "该用户没有订单信息");
				return;
			}
			//	2.遍历list中Flight中的信息将信息添加到FlightMsg中
			Iterator<OrderTicket> it = list.iterator();
			while(it.hasNext()){
				OrderTicket f = it.next();
//				orderMsg.append(f.getOrder_number() + "\t  "
//											+ f.getFlight_number() + " \t  " 
//											+ f.getPassenger_name() + "\t  "
//											+ f.getStart_place() +" \t  "
//											+ f.getEnd_place()+ " \t  "
//											+f.getTakeoff_time()+"\n"
//											);
				orderMsg.append(f.getOrder_number() + "\t  "
						+ f.getFlight_number() + " \t  " 
						+ f.getName() + "\t  "
						+ f.getStart_place() +" \t  "
						+ f.getEnd_place()+ " \t  "
						+f.getTakeoff_time()+"\n"
						);
			}
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "该用户没有订单信息");
		}
	}
	
	
	//添加事件
	public void addAction(){
		
		//退票
		refundTicket.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					//1.先判断文本框中有没有订单号和用户名如果没有则提示 orderTxArr[0] orderTxArr[2]
					if(orderTxArr[0].getText().length() == 0 || orderTxArr[2].getText().length() == 0){
						JOptionPane.showMessageDialog(null, "请选择要退票的订单");
					}else{
						int orderNumber = Integer.parseInt(orderTxArr[0].getText());
						String flightId = orderTxArr[1].getText();
//						OrderTicketManager order = new OrderTicketManagerImp();
						try {
							//将订单删除且flight中的ticket票加1
							boolean flag = orderTicketManager.changeOrders(orderNumber, loginName,flightId);
							if(flag){
								JOptionPane.showMessageDialog(null, "退票成功！");
								orderMsg.setText("订单号\t航班号\t  用户名\t  出发地\t  目的地\t  起飞时间\n");
								//更新用户订单界面
								listFlight();
							}else{
								JOptionPane.showMessageDialog(null, "退票失败！");
							}
						}catch (SQLException e1) {
						e1.printStackTrace();
						}
					}
			}
		});
		//列出要退票的航班
		orderMsg.addCaretListener(new CaretListener() {
			
				public void caretUpdate(CaretEvent e) {
					//获取插入符位置
					int offset=e.getDot();
					try {
						//将组件文本中的偏移量转换为行号
						int row = orderMsg.getLineOfOffset(offset);
						if(row == 0){
							return ;
						}
						int start=orderMsg.getLineStartOffset(row);
						int end=orderMsg.getLineEndOffset(row);
						String str=orderMsg.getText(start, end-start);
						info = str.split("\\s{2,}");
						for(int i = 0 ; i < info.length; i++){
							orderTxArr[i].setText(info[i]);
						}
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}catch(ArrayIndexOutOfBoundsException e2){
						JOptionPane.showMessageDialog(null, "请选择要退票的航班");
					}
				}
		});
		
		//添加返回事件 
		btnReturn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserGui(loginName);			
			}
		});
	}
	
	
	
	
	
	//添加组件
	public void addComponent(){
		
		panel.add(orderMsg);
		panel.setPreferredSize(new Dimension(600, 400));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED ));
		panel.setBackground(Color.white);
		JScrollPane scoll = new JScrollPane(panel);
		setLayout(new FlowLayout());
		add(scoll);
		
		String[] arr={"\t订单号","航班号","用户名","出发地","目的地","起飞时间"};
		for(int i=0;i<orderLbArr.length;i++){
			orderLbArr[i]=new JLabel();
			orderLbArr[i].setText(arr[i]);
			panel2.add(orderLbArr[i]);
		}
		
		for(int i=0;i<orderTxArr.length;i++){
			orderTxArr[i]=new JTextArea();
			panel2.add(orderTxArr[i]);
		}
		panel2.setPreferredSize(new Dimension(600,50));
		add(panel2);
		
		panel3.add(refundTicket);
		panel3.add(btnReturn);
		add(panel3);
		
		try {
			//列出的用户订单信息
			listFlight();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//初始化窗口
	public void initFrame(){
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//构造器
	public RefundTicket(String loginName) {
		this.loginName = loginName;
		setTitle("用户退票");
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		RefundTicket r = new RefundTicket("abcd123");
	}
}
