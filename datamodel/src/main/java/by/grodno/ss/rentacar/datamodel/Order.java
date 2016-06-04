package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order extends AbstractModel {

	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY)
	private UserProfile client;

	@ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	private Car car;

	@Column
	private Date created;

	@Column
	private Date dateFrom;

	@Column
	private Date dateTo;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	//@JoinColumn(name = "location_id", nullable = false)
	private Location locationFrom;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	//@JoinColumn(name = "location_id", nullable = false)
	private Location locationTo;

	@Column
	private String contract;

	@Column
	private BigDecimal summ;

	@Column
	private String note;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<Damage> damage;

	@Column
	@Enumerated(value = EnumType.STRING)
	private OrderStatus orderStatus;

	@ManyToOne(targetEntity = Reason.class, fetch = FetchType.LAZY)
	private Reason reason;

	@ManyToOne(targetEntity = Invoice.class, fetch = FetchType.LAZY)
	private Invoice invoice;

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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
