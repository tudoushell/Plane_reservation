package manager.Impl;

import java.sql.SQLException;
import java.util.List;

import Dao.FlightDao;
import Dao.OrderTicketDao;
import Dao.Impl.FlightDaoImpl;
import Dao.Impl.OrderTicketDaoImpl;
import entity.Flight;
import entity.OrderTicket;
import manager.OrderTicketManager;
import util.*;
public class OrderTicketManagerImp implements OrderTicketManager{
	
	private Transaction transaction = new TransactionImpl ();
	private FlightDao flightDao = new FlightDaoImpl();
	private OrderTicketDao  orderTicketImp = new OrderTicketDaoImpl();
	
	
	/**
	 * 改签订单
	 */
	public boolean changeOrders(int orderNumber, String loginName,String flightId) throws SQLException {
		transaction.start();
		// 1.有则根据用户名和订单号将Order_ticket中的数据删除
		orderTicketImp.delOrderById(orderNumber, loginName);
		
		// 2.在根据航班号将Fligh表中的ticket加1
		Flight flight = flightDao.getFlightById(flightId);
		int ticket = flight.getTickets();
		flight.setTickets(ticket+1);
		
		//3.将航班信息更新
		boolean flag = flightDao.upDateFlight(flight);
		
		transaction.commit();
		if(flag){
			System.out.println("更新成功");
			return true;
		}else{
			transaction.rollback();
			return false;
		}
		
	}
	
	
	
	/**
	 * 根据登录名列出所有的订单信息
	 */
	public List<OrderTicket> listOrderByloginName(String loginName) throws SQLException {
		List<OrderTicket> list = orderTicketImp.listOrderByloginName(loginName);
		if(list.size() == 0){
			return null;
		}else{
			return list;
		}
	}
	
	
	/**
	 * 列出所有订单信息
	 */
	public List<OrderTicket> listOrder() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 添加一个订单
	 */
	public boolean addOrder(OrderTicket orderTicket,String flightId) {
		Flight flight = null;
		try {
			transaction.start();
			//1.将订单写入数据库中
			orderTicketImp.addOrder(orderTicket);
			
			//	2.同时对flight进行操作，将票数减1
			flight = flightDao.getFlightById(flightId);
			int ticketCount = flight.getTickets() - 1;
			flight.setTickets(ticketCount);
			
			//3.对flight表进行修改
			flightDao.upDateFlight(flight);
			
			transaction.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}



	
}