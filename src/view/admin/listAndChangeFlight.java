package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import manager.AdminManager;
import manager.FlightManger;
import manager.Impl.AdminManagerImp;
import manager.Impl.FlightManagerImp;
import entity.Flight;

public class listAndChangeFlight extends JFrame{
	String loginName;
	JTextArea FlightMsg=new JTextArea("航班号    出发时间\t飞行时间\t出发地\t目的地\t余票\t票价\n");
	JPanel panel = new JPanel();
	JButton btnReturn  = new JButton("返回主界面");
	JButton btnChange = new JButton("修改");
	JButton btnDel = new JButton("删除");
	
	//下方的文本区
	JLabel[] flightLbArr=new JLabel[7];
	JTextArea[] flightTxArr=new JTextArea[7];
	String info [] =new String[7] ;
	String newInfo [] = new String[7];
	
	//列出航班
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
	public void addActioin(){
		//修改航班
		btnChange.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int ticketCount = Integer.parseInt(info[5]);
				String flightNum = info[0];
				if(ticketCount % 10 != 0){
					JOptionPane.showMessageDialog(null, "当前航班不能修改，已有人订购");
				}else{
					Flight flight = new Flight();
					System.out.println(flightTxArr.length);
					for(int i = 0 ; i < flightTxArr.length; i++){
						newInfo[i] =	 flightTxArr[i].getText();
					}
					System.out.println(newInfo.length);
					flight.setFlight_number(newInfo[0]);
					flight.setTakeoff_time(newInfo[1]);
					flight.setFlying_time(newInfo[2]);
					flight.setStart_place(newInfo[3]);
					flight.setEnd_place(newInfo[4]);
					flight.setTickets(Integer.parseInt(newInfo[5]));
					flight.setPrice(Float.parseFloat(newInfo[6]));
					System.out.println(flight);
					AdminManager adminManager = new AdminManagerImp();
					boolean flag = adminManager.upDateFlight(flight);
					if(flag){
						JOptionPane.showMessageDialog(null, "修改成功");
						FlightMsg.setText("航班号    出发时间\t飞行时间\t出发地\t目的地\t余票\t票价\n");
						//列出所有已更新的航班信息
						listFlight();
					}else{
						JOptionPane.showMessageDialog(null, "修改失败");
					}
				}
				
			}
		});
		
		//删除航班
		btnDel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					int ticketCount = Integer.parseInt(info[5]);
					String flightNum = info[0];
					if(ticketCount % 10 != 0){
						JOptionPane.showMessageDialog(null, "当前航班不能删除，已有人订购");
					}else{
						AdminManager adminManager = new AdminManagerImp();
						boolean flag = adminManager.delFilght(flightNum);
						if(flag){
							JOptionPane.showMessageDialog(null, "删除成功");
							FlightMsg.setText("航班号    出发时间\t飞行时间\t出发地\t目的地\t余票\t票价\n");
							//列出所有已更新的航班信息
							listFlight();
						}else{
							JOptionPane.showMessageDialog(null, "删除失败");
						}
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
		
		
		//返回主界面
		btnReturn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				 setVisible(false);
				 new AdminGui(loginName);
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
		panel3.add(btnChange);
		panel3.add(btnDel);
		panel3.add(btnReturn);
		add(panel3);
		//列出所有的航班信息
		listFlight();
		
	}
	
	//界面初始化
	public void initFrame(){
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//构造器
	public listAndChangeFlight(String loginName) {
		this.loginName = loginName;
		initFrame();
		addComponent();
		 addActioin();
		setTitle("查询 删除 修改航班");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		listAndChangeFlight l = new listAndChangeFlight("admin");
		
	}
}
