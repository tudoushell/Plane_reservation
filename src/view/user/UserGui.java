package view.user;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.flight.ListAndOrderFilght;
import view.order.ListAndChangeOrder;
import view.order.RefundTicket;

public class UserGui extends JFrame{
	/**
	 * 2. 修改用户信息
		3. 查询航班
		4. 预订机票
		5. 订单改签
		6. 退票
	 */
	String loginName ;
	JButton  changeInfo = new JButton("修改个人信息");
    JButton  getFlight = new JButton("查询/预订航班");
    JButton  getChangeOrder = new JButton("查询/改签用户订单");
    JButton   refund = new JButton("退票"); 
    
	public void initFrame(){
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addComponent(){
		setLayout(new FlowLayout());
		add(changeInfo);
		add(getFlight);
		add(getChangeOrder);
		add(refund);
		
	}
	
	public void addAction(){
			//用户信息修改
			changeInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new ChangeUser(loginName);
				}
			});
			
			//查询航班
			getFlight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 setVisible(false);
					 new ListAndOrderFilght(loginName);
					
				}
			});
			//获取用户订单信息改签订单
			getChangeOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new ListAndChangeOrder(loginName);
				}
			});
			
			//退票
			refund.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new RefundTicket(loginName);
				}
			});
		
	}
	
	public UserGui() {
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}
	public UserGui(String loginName){
		super("航空用户界面");
		this.loginName = loginName;
		initFrame();
		addComponent();
		addAction();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		UserGui ug = new UserGui("abcd123");
	}
	
}
