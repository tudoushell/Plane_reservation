package view.flight;

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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import view.user.UserGui;

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

public class ListAndOrderFilght extends JFrame{
	String loginName;
	JTextArea FlightMsg=new JTextArea("航班号    出发时间\t飞行时间\t出发地\t目的地\t余票\t票价\n");
	JPanel panel = new JPanel();
	JButton btnReturn  = new JButton("返回主界面");
	JButton btnOrder = new JButton("预订");
	
	//下方的文本区
	JLabel[] flightLbArr=new JLabel[7];
	JTextArea[] flightTxArr=new JTextArea[7];
	String info [] = null ;
	
	//列出所有航班信息
	public void listFlight(){
		/*
		 * 列出航班
		 */
		//1.调用FlightMangerImp中的listFlight的信息,如没有任何航班则提示
		FlightManger flightManger = new FlightManagerImp();
		try{
			List<Flight>list = flightManger.listFlyFlight();
			if(list == null){
				JOptionPane.showMessageDialog(null, "暂时没有航班信息");
				return;
			}
			//	2.遍历list中Flight中的信息将信息添加到FlightMsg中
			Iterator<Flight> it = list.iterator();
			while(it.hasNext()){
				Flight f = it.next();
				FlightMsg.append(f.getFlight_number() + "    "
											+ f.getTakeoff_time() + " \t" 
											+ f.getFlying_time() +" \t"
											+ f.getStart_place() + " \t"
											+f.getEnd_place()+" \t"
											+f.getTickets()+" \t "
											+ f.getPrice()+"\n"
											);
			}
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "该没有航班信息");
		}
	}
	
	//添加事件
	public void addAction(){
		
		//预订航班
		btnOrder.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
					String ticket = info[5];
					int ticketCount = Integer.parseInt(ticket);
					if(ticketCount == 0){
						//	1.获取前台的数据,先判断当前有没有票，有才进行操作
						JOptionPane.showMessageDialog(null, "当前票不足");
					}else{
						
						/***新添加的功能***/
						String names = JOptionPane.showInputDialog(null,"请输入购票姓名：","姓名",JOptionPane.PLAIN_MESSAGE,null,null,null).toString();
						String  passengerId = JOptionPane.showInputDialog(null,"请输入购票身份证号：","身份证号",JOptionPane.PLAIN_MESSAGE,null,null,null).toString();
						if(names.length() == 0){
							JOptionPane.showMessageDialog(null, "请输入姓名");
							return ;
						}
						if(passengerId.length() == 0){
							JOptionPane.showMessageDialog(null, "请输入身份证号");
							return ;
						}
						/*******/
						
						UserManager userManager = new UserManagerImp();
						OrderTicketManager addOrder =  new OrderTicketManagerImp();
						//获取用户身份证
//						User user = userManager.getUserByLoginName(loginName);
//						String passengerId = user.getId();
						//2.预订之后将预订后的信息存入orderTicket对象中
						float price = Float.parseFloat(info[6]);
						OrderTicket order = new OrderTicket();
						order.setFlight_number(info[0]);
						order.setStart_place(info[3]);
						order.setEnd_place(info[4]);
						order.setTakeoff_time(info[1]);
						order.setPrice(price);
						order.setPassenger_name(loginName);
						order.setPassenger_id(passengerId);
						
						/*新添加的*/
						order.setName(names);
						/**/
						
						//将订单信息添加数据库中,并更新界面
						boolean flag = 	addOrder.addOrder(order,info[0]);
						if(flag){
							JOptionPane.showMessageDialog(null, "预订成功！");
							FlightMsg.setText("航班号    出发时间\t飞行时间\t出发地\t目的地\t余票\t票价\n");
							//列出所有已更新的航班信息
							listFlight();
						}else{
							JOptionPane.showMessageDialog(null, "预订失败！");
						}
					}
				}catch(NullPointerException e1){
					JOptionPane.showMessageDialog(null, "请选择航班");
				}catch(ArrayIndexOutOfBoundsException e2){
					JOptionPane.showMessageDialog(null, "请选择航班");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
		
		//预订航班信息输出到下方
		FlightMsg.addCaretListener(new CaretListener() {
			
			public void caretUpdate(CaretEvent e) {
				//获取插入符位置
				int offset=e.getDot();
				try {
					//将组件文本中的偏移量转换为行号
					int row = FlightMsg.getLineOfOffset(offset);
					if(row == 0){
						return ;
					}
					int start=FlightMsg.getLineStartOffset(row);
					int end=FlightMsg.getLineEndOffset(row);
					String str=FlightMsg.getText(start, end-start);
					info = str.split("\\s{2,}");
					for(int i = 0 ; i < info.length; i++){
						flightTxArr[i].setText(info[i]);
					}
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}catch(ArrayIndexOutOfBoundsException e2){
					JOptionPane.showMessageDialog(null, "请选择航班");
				}
			}
		});
		
		//返回主界面按钮
		btnReturn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new UserGui(loginName);
			}
		});
	}
	
	//添加组件
	public void addComponent(){
		
		//航班信息
		panel.add(FlightMsg);
		panel.setPreferredSize(new Dimension(600, 400));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED ));
		panel.setBackground(Color.white);
		JScrollPane scoll = new JScrollPane(panel);
		setLayout(new FlowLayout());
		add(scoll);
		
		//下方的订单信息
		JPanel panel2=new JPanel(new GridLayout(2, 6,10, 10));
		String[] arr={"\t航班号","出发时间","飞行时间","出发地","目的地","余票","票价"};
		for(int i=0;i<flightLbArr.length;i++){
			flightLbArr[i]=new JLabel();
			flightLbArr[i].setText(arr[i]);
			panel2.add(flightLbArr[i]);
		}
		
		for(int i=0;i<flightTxArr.length;i++){
			flightTxArr[i]=new JTextArea();
			panel2.add(flightTxArr[i]);
		}
		panel2.setPreferredSize(new Dimension(600,50));
		add(panel2);
		
		//两个按钮
		JPanel panel3 = new JPanel();
		panel3.add(btnOrder);
		panel3.add(btnReturn);
		add(panel3);
		listFlight();
		
		
	}
	
	//界面初始化
	public void initFrame(){
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//构造器
	public ListAndOrderFilght(String loginName) {
		 this.loginName = loginName;
		 setTitle("查询预订航班");
		 initFrame();
		 addComponent();
		 addAction();
		 setVisible(true);
	}
	
	public static void main(String[] args) {
		ListAndOrderFilght l = new ListAndOrderFilght("abcd123");
	}

		
}
