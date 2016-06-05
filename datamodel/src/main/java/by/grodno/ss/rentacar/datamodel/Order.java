package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order extends AbstractModel {

	@Column
	private Date created;
	
	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY)
	private UserProfile client;

	@ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	private Car car;

	@Column
	private Date dateFrom;

	@Column
	private Date dateTo;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	private Location locationFrom;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	private Location locationTo;
	
	@Column
	private BigDecimal summ;
	
	@ManyToOne(targetEntity = Reason.class, fetch = FetchType.LAZY)
	private Reason reason;

	@ManyToOne(targetEntity = Invoice.class, fetch = FetchType.LAZY)
	private Invoice invoice;

	@Column
	private String damage;
	
	@Column
	private String note;

	@Column
	@Enumerated(value = EnumType.STRING)
	private OrderStatus orderStatus;

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

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
