package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking extends AbstractModel {
	private static final long serialVersionUID = 1L;
	
	@Column
	private Date created;
	
	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY)
	private UserProfile client;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	private Location locationFrom;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	private Location locationTo;	
	
	@ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	@Column
	private Date dateFrom;

	@Column
	private Date dateTo;
	
	@Column
	private BigDecimal summ;
	
	@ManyToOne(targetEntity = Reason.class, fetch = FetchType.LAZY)
	private Reason reason;

	@Column
	private String damage;
	
	@Column
	private String note;

	@Column
	@Enumerated(value = EnumType.ORDINAL)
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
