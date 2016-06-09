package by.grodno.ss.rentacar.dataaccess.filters;

import java.math.BigDecimal;
import java.util.Date;

import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.OrderStatus;
import by.grodno.ss.rentacar.datamodel.UserProfile;

public class OrderFilter extends AbstractFilter {

	private static final long serialVersionUID = 1L;
	private Date created;
	private UserProfile client;
	private Car car;
	private Date dateFrom;
	private Date dateTo;
	private Location locationFrom;
	private Location locationTo;
	private BigDecimal summ;
	private OrderStatus orderStatus;
	private boolean isFetchClient;
	private boolean isFetchCar;
	private boolean isFetchLocations;
	private boolean isFetchReason;
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public UserProfile getClient() {
		return client;
	}
	public void setClient(UserProfile client) {
		this.client = client;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Location getLocationFrom() {
		return locationFrom;
	}
	public void setLocationFrom(Location locationFrom) {
		this.locationFrom = locationFrom;
	}
	public Location getLocationTo() {
		return locationTo;
	}
	public void setLocationTo(Location locationTo) {
		this.locationTo = locationTo;
	}
	public BigDecimal getSumm() {
		return summ;
	}
	public void setSumm(BigDecimal summ) {
		this.summ = summ;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public boolean isFetchClient() {
		return isFetchClient;
	}
	public void setFetchClient(boolean isFetchClient) {
		this.isFetchClient = isFetchClient;
	}
	public boolean isFetchCar() {
		return isFetchCar;
	}
	public void setFetchCar(boolean isFetchCar) {
		this.isFetchCar = isFetchCar;
	}
	public boolean isFetchLocations() {
		return isFetchLocations;
	}
	public void setFetchLocations(boolean isFetchLocations) {
		this.isFetchLocations = isFetchLocations;
	}
	public boolean isFetchReason() {
		return isFetchReason;
	}
	public void setFetchReason(boolean isFetchReason) {
		this.isFetchReason = isFetchReason;
	}
	
}
