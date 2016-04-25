package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order extends AbstractModel {

	private UserCredentials client;
	private Car car;
	private Date created;
	private Date dateFrom;
	private Date dateTo;
	private String contract;
	private BigDecimal summ;
	private String note;
	private List<Damage> damage;
	private List<OrderHistory> orderHistory;
	private List<Invoice> invoice;
	private OrderStatus orderStatus;
	private Reason reason;

	public UserCredentials getClient() {
		return client;
	}

	public void setClient(UserCredentials client) {
		this.client = client;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public BigDecimal getSumm() {
		return summ;
	}

	public void setSumm(BigDecimal summ) {
		this.summ = summ;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Damage> getDamage() {
		return damage;
	}

	public void setDamage(List<Damage> damage) {
		this.damage = damage;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public List<OrderHistory> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(List<OrderHistory> orderHistory) {
		this.orderHistory = orderHistory;
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

}
