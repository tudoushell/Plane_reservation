package view.order;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RefundTicket extends JFrame{
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
	
	
	
	
	
	
	
	
	//添加组件
	public void addComponent(){
		
	}
	
	//初始化窗口
	public void initFrame(){
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	public RefundTicket(String loginName) {
		this.loginName = loginName;
		setTitle("用户退票");
		initFrame();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		RefundTicket r = new RefundTicket("abcd123");
	}
}
