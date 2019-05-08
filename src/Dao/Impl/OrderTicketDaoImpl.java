package Dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import util.impl.OrderTicketMapperlmp;
import Dao.OrderTicketDao;
import entity.OrderTicket;

public class OrderTicketDaoImpl implements OrderTicketDao{

	/*
	 * 根据用户的登录名和订单号来删除信息
	 * (non-Javadoc)
	 * @see Dao.OrderTicketDao#delOrderById(int, java.lang.String)
	 */
	public boolean delOrderById(int orderNumber, String loginName)  {
		String sql = "delete from order_ticket where order_number=? && passenger_name=?";
		try {
			int flag = JDBCUtil.executeUpdate(sql, orderNumber,loginName);
			if(flag != 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 添加一个订票
	 */
	public boolean addOrder(OrderTicket orderTicket) {
//		String sql = " insert into order_ticket values(?,?,?,?,?,?,?,?)";
		
		String sql = " insert into order_ticket values(?,?,?,?,?,?,?,?,?)";
		try{
//			int flag = JDBCUtil.executeUpdate(sql, orderTicket.getOrder_number(),
//														  orderTicket.getFlight_number(),
//														  orderTicket.getStart_place(),
//														  orderTicket.getEnd_place(),
//														  orderTicket.getTakeoff_time(),
//														  orderTicket.getPrice(),
//														  orderTicket.getPassenger_name(),
//														  orderTicket.getPassenger_id());
			int flag = JDBCUtil.executeUpdate(sql, orderTicket.getOrder_number(),
					  orderTicket.getFlight_number(),
					  orderTicket.getStart_place(),
					  orderTicket.getEnd_place(),
					  orderTicket.getTakeoff_time(),
					  orderTicket.getPrice(),
					  orderTicket.getPassenger_name(),
					  orderTicket.getPassenger_id(),
					  orderTicket.getName());
			if(flag != 0){
				return true;
			}
		}catch(SQLException e){
			e.getStackTrace();
		}
		return false;
	}
	/**
	 * 删除订单
	 */
	public boolean delOrderByloginName(String loginName) {
		String sql = "delete from order_ticket where passenger_name=?";
		try{
				int flag = JDBCUtil.executeUpdate(sql, loginName);
				if(flag != 0){
					return true;
				}
			}catch(SQLException e){
				e.getStackTrace();
			}
			return false;
	}
	
	/**
	 * 根据登录名来查询订单
	 */
	public List<OrderTicket> listOrderByloginName(String loginName) {
		String sql = "select * from order_ticket where passenger_name=?";
		try{
			List<OrderTicket> list = new ArrayList<OrderTicket>();
			List<Object>  obj =	JDBCUtil.executeQuery(sql, new OrderTicketMapperlmp(), loginName);
			if(obj.size() == 0){
				return null;
			}
			for (Object object : obj) {
				list.add((OrderTicket)object);
			}
			return list;
			}catch(SQLException e){
				e.getStackTrace();
			}
		return null;
	}
	
	/**
	 * 修改订单信息
	 *  order_number | flight_number | start_place | end_place | takeoff_time        
	 *  | price | passenger_name | passenger_id
	 */
	
	public boolean upDateOrderByloginName(OrderTicket orderTicket) {
//		String sql = "update order_ticket set  order_number=?," +
//																	"flight_number=?," +
//																	"start_place=?," +
//																	"end_place=?," +
//																	"takeoff_time=?," +
//																	"price=?," +
//																	"passenger_id=?" +
//																	"where  passenger_name =?";
		String sql = "update order_ticket set  order_number=?," +
							"flight_number=?," +
							"start_place=?," +
							"end_place=?," +
							"takeoff_time=?," +
							"price=?," +
							"passenger_id=?" +
							"name="+
							"where  passenger_name =?";
		try{
//			int flag = JDBCUtil.executeUpdate(sql,  orderTicket.getOrder_number(),
//																			orderTicket.getFlight_number(),
//																			orderTicket.getStart_place(),
//																			orderTicket.getEnd_place(),
//																			orderTicket.getTakeoff_time(),
//																			orderTicket.getPrice(),
//																			orderTicket.getPassenger_name(),
//																			orderTicket.getPassenger_id());
			
			int flag = JDBCUtil.executeUpdate(sql,  orderTicket.getOrder_number(),
					orderTicket.getFlight_number(),
					orderTicket.getStart_place(),
					orderTicket.getEnd_place(),
					orderTicket.getTakeoff_time(),
					orderTicket.getPrice(),
					orderTicket.getPassenger_name(),
					orderTicket.getPassenger_id(),
					orderTicket.getName());
			if(flag != 0){
				return true;
			}
			
		}catch(SQLException e){
			e.getStackTrace();
		}
		return false;
	}
	
	/**
	 * 列出所有的订单的信息
	 */
	public List<OrderTicket> listOrder() {
		String sql = "select * from order_ticket";
		try{
			List<OrderTicket> listOrder = new ArrayList<OrderTicket>();
			List<Object>  obj =	JDBCUtil.executeQuery(sql, new OrderTicketMapperlmp());
			for (Object object : obj) {
				listOrder.add((OrderTicket)object);
			}
			return listOrder;
			}catch(SQLException e){
				e.getStackTrace();
			}
		return null;
	}
	




}
