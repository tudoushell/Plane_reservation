package manager;

import java.sql.SQLException;
import java.util.List;

import entity.Flight;
import entity.OrderTicket;

public interface OrderTicketManager {
	
	/*
	 *根据用户登录名和订单号来删除订单
	 * 
	 */
	
	boolean changeOrders(int orderNumber,String loginName,String flightId) throws SQLException;
	/**
	 * 将当前的订单信息存入数据库中
	 * @param orderTicket
	 * @return 更新后的航班信息
	 */
	boolean addOrder(OrderTicket orderTicket,String flightId) throws SQLException ;
	
	/**
	 * 列出所有的用户订单信息
	 * @return  OrderTicket
	 * @throws SQLException
	 */
	List<OrderTicket> listOrder() throws SQLException;
	
	/**
	 * 根据登录名来获取用户信息
	 * @param loginName
	 * @return  orderTicket
	 * @throws SQLException
	 */
	
	List<OrderTicket> listOrderByloginName(String loginName) throws SQLException;

}
