package util.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.OrderTicket;

import util.RowMapperObject;

public class OrderTicketMapperlmp implements RowMapperObject{

	public Object rowMapperObject(ResultSet rs) throws SQLException {
		OrderTicket orderTicket = new OrderTicket();
		orderTicket.setOrder_number(rs.getInt("order_number"));
		orderTicket.setFlight_number(rs.getString("flight_number"));
		orderTicket.setStart_place(rs.getString("start_place"));
		orderTicket.setEnd_place(rs.getString("end_place"));
		orderTicket.setTakeoff_time(rs.getString("takeoff_time"));
		orderTicket.setPrice(rs.getFloat("price"));
		orderTicket.setPassenger_name(rs.getString("passenger_name"));
		orderTicket.setPassenger_id(rs.getString("passenger_id"));
		return orderTicket;
	}

}
