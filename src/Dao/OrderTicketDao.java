package Dao;

import java.sql.SQLException;
import java.util.List;

import entity.OrderTicket;

public interface OrderTicketDao {
		
		/**
		 * 添加一个订票
		 * @return 添加成功
		 */
		boolean addOrder(OrderTicket orderTicket) throws SQLException;
		
		/**
		 * 根据登录名和订单号来删除
		 * @param orderNumber
		 * @param loginName
		 * @return  boolean
		 * @throws SQLException
		 */
		boolean delOrderById(int orderNumber,String loginName) throws SQLException;
		/**
		 * 删除一个订票
		 * @param loginName 登录名
		 * @return 是否删除成功
		 */
		boolean delOrderByloginName(String loginName) throws SQLException;
		
		/**
		 * 根据id获取订票信息
		 * @param loginName 登录名
		 * @return 订票信息
		 */
		List<OrderTicket> listOrderByloginName(String loginName) throws SQLException;
		
		/**
		 * 修改订单信息
		 * @param id 订单号
		 * @return 是否修改成功
		 */
		boolean upDateOrderByloginName(OrderTicket orderTicket) throws SQLException;
		
		/**
		 * 获取所有的订单信息
		 * @return 订单信息
		 */
		List<OrderTicket> listOrder() throws SQLException;
}
