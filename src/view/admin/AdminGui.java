package view.admin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AdminGui extends JFrame{
	String loginName ;
	JButton  addFlight = new JButton("添加航班");
    JButton  changeAndListFlight = new JButton("修改/显示航班");

    
    public void addAction(){
    	//修改 显示航班
    	changeAndListFlight.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new listAndChangeFlight(loginName);
			}
		});
    	
    	
    	//添加航班	
    	addFlight.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
						setVisible(false);
						new AddFlight(loginName);
				}
			});
    }
    
    
	public void addComponent(){
		setLayout(new FlowLayout());
		add(addFlight);
		add(changeAndListFlight);
	}
	//初始化框架
	public void initFrame(){
		setSize(500,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public AdminGui(String loginName) {
		this.loginName = loginName;
		 setTitle("管理员界面");
		 initFrame();
		 addComponent();
		 addAction();
		 setVisible(true);
	}
	public static void main(String[] args) {
		AdminGui a = new AdminGui("admin");
	}
}
