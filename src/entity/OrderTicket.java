package entity;

/*
 *	订票信息
 * 
 */
public class OrderTicket {
	//订单号
	private int order_number;
	//航班号
	private String flight_number;
	//出发地
	private String start_place;
	//目的地
	private String end_place;
	//出发时间 
	private String takeoff_time;
	//票价
	private float price;
	//乘客姓名 
	private String passenger_name;
	//乘客身份证
	private String passenger_id;
	
	
	
	public OrderTicket() {
		super();
	}
	
	public OrderTicket(int orderNumber, String flightNumber, String startPlace, String endPlace, String takeoffTime, float price, String passengerName, String passengerId) {
		super();
		order_number = orderNumber;
		flight_number = flightNumber;
		start_place = startPlace;
		end_place = endPlace;
		takeoff_time = takeoffTime;
		this.price = price;
		passenger_name = passengerName;
		passenger_id = passengerId;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int orderNumber) {
		order_number = orderNumber;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flightNumber) {
		flight_number = flightNumber;
	}
	public String getStart_place() {
		return start_place;
	}
	public void setStart_place(String startPlace) {
		start_place = startPlace;
	}
	public String getEnd_place() {
		return end_place;
	}
	public void setEnd_place(String endPlace) {
		end_place = endPlace;
	}
	public String getTakeoff_time() {
		return takeoff_time;
	}
	public void setTakeoff_time(String takeoffTime) {
		takeoff_time = takeoffTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPassenger_name() {
		return passenger_name;
	}
	public void setPassenger_name(String passengerName) {
		passenger_name = passengerName;
	}
	public String getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(String passengerId) {
		passenger_id = passengerId;
	}
	@Override
	public String toString() {
		return "OrderTicket [end_place=" + end_place + ", flight_number=" + flight_number + ", order_number=" + order_number + ", passenger_id=" + passenger_id + ", passenger_name=" + passenger_name
				+ ", price=" + price + ", start_place=" + start_place + ", takeoff_time=" + takeoff_time + "]";
	}
	
	
	
	
	
}
