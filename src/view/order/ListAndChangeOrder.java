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

import Dao.OrderTicketDao;

import manager.FlightManger;
import manager.OrderTicketManager;
import manager.UserManager;
import manager.Impl.FlightManagerImp;
import manager.Impl.OrderTicketManagerImp;
import manager.Impl.UserManagerImp;
import entity.Flight;
import entity.OrderTicket;
import entity.User;
import exception.PlaneException;

import view.user.UserGui;

public class ListAndChangeOrder extends JFrame{
	String loginName;
	String info[] = null;
	JTextArea orderMsg=new JTextArea("订单号\t航班号\t  用户名\t  出发地\t  目的地\t  起飞时间\n");
	//面板
	JPanel panel = new JPanel();
	JPanel panel2=new JPanel(new GridLayout(2, 6,10, 10));
	JPanel panel3 = new JPanel();
	//按钮
	JButton changeOrder = new JButton("改签");
	JButton btnReturn  = new JButton("返回主界面");
	//标签和文本区域
	JLabel[] orderLbArr=new JLabel[6];
	JTextArea[] orderTxArr=new JTextArea[6];
	
	
		
	//列出用户订单信息
	public void listFlight() throws SQLException{
		/*
		 * 列出用户订单信息
		 */
		//1.调用OderTicketImp中的listOrderByloginName的信息,如没有任何订单则提示
		OrderTicketManager orderTicketManager = new OrderTicketManagerImp();
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
				orderMsg.append(f.getOrder_number() + "\t  "
											+ f.getFlight_number() + " \t  " 
											+ f.getPassenger_name() + "\t  "
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
		//改签订单事件 
		changeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//1.先判断文本框中有没有订单号和用户名如果没有则提示 orderTxArr[0] orderTxArr[2]
				if(orderTxArr[0].getText().length() == 0 || orderTxArr[2].getText().length() == 0){
					JOptionPane.showMessageDialog(null, "请选择要改签的订单");
				}else{
					int orderNumber = Integer.parseInt(orderTxArr[0].getText());
					String flightId = orderTxArr[1].getText();
					OrderTicketManager order = new OrderTicketManagerImp();
					try {
						FlightManger flight = new FlightManagerImp();
						//获取没有过期的航班
						List<Flight>flightList = flight.listFlyFlight();
						Flight [] flightArr = flightList.toArray(new Flight[flightList.size()]);
						boolean flag = order.changeOrders(orderNumber, loginName,flightId);
						
						//将新的航班添加到和订单添加到用户中
						//2.获取已选的航班
						Object obj = JOptionPane.showInputDialog(null,"请选择航班","改签航班",JOptionPane.INFORMATION_MESSAGE,null,flightArr,null);
						if(obj == null){
							return;
						}
						Flight newFlight = (Flight)obj;
						
						//3.将新的订单信息写入order_ticket中
						UserManager userManager = new UserManagerImp();
						User user = null;
						try {
							user = userManager.getUserByLoginName(loginName);
						} catch (PlaneException e1) {
							e1.printStackTrace();
						}
						String passengerId = user.getId();
						//设置用户选的新的订单
						OrderTicket newOrder = new OrderTicket();
						newOrder.setFlight_number(newFlight.getFlight_number());
						newOrder.setStart_place(newFlight.getStart_place());
						newOrder.setEnd_place(newFlight.getEnd_place());
						newOrder.setTakeoff_time(newFlight.getTakeoff_time());
						newOrder.setPrice(newFlight.getPrice());
						newOrder.setPassenger_name(loginName);
						newOrder.setPassenger_id(passengerId);
						
						// 4.将新的航班信息的ticket减1
						boolean flag1 = order.addOrder(newOrder, newFlight.getFlight_number());
						System.out.println(flag +" "+flag1);
						
						// 5.更新订单信息
						if(flag&&flag1){
							JOptionPane.showMessageDialog(null, "改签成功！");
							orderMsg.setText("订单号\t航班号\t  用户名\t  出发地\t  目的地\t  起飞时间\n");
							//更新用户订单界面
							listFlight();
						}else{
							JOptionPane.showMessageDialog(null, "改签失败！");
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});	
		
		//将要改签的信息输入下方
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
					JOptionPane.showMessageDialog(null, "请选择要改签的航班");
				}
			}
		});
		
		//返回主界面
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
		
		panel3.add(changeOrder);
		panel3.add(btnReturn);
		add(panel3);
		
		
		try {
			//列出的用户订单信息
			listFlight();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public ListAndChangeOrder(String loginName){
		this.loginName = loginName;
		setTitle("订单查询/改签窗口");
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}

	public static void main(String[] args) {
		 ListAndChangeOrder l = new ListAndChangeOrder("abcd123");
		 
	}
}
