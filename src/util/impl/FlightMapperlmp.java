package util.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import entity.Flight;

import util.RowMapperObject;

public class FlightMapperlmp implements RowMapperObject {

	public Object rowMapperObject(ResultSet rs) throws SQLException {
		Flight flight = new Flight();
		flight.setFlight_number(rs.getString("flight_number"));
		flight.setTakeoff_time(rs.getString("takeoff_time"));
		flight.setFlying_time(rs.getString("flying_time"));
		flight.setStart_place(rs.getString("start_place"));
		flight.setEnd_place(rs.getString("end_place"));
		flight.setTickets(rs.getInt("tickets"));
		flight.setPrice(rs.getFloat("price"));
		return flight;
	}

}
