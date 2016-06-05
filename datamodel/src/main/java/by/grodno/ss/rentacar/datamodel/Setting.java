package by.grodno.ss.rentacar.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "setting")
public class Setting extends AbstractModel {

	@Column
	private Integer minBookingLength;

	@Column
	private Integer carWhilePending;

	@Column
	private Integer depositPayment;

	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private Currency currency;

	public Integer getMinBookingLength() {
		return minBookingLength;
	}

	public void setMinBookingLength(Integer minBookingLength) {
		this.minBookingLength = minBookingLength;
	}

	public Integer getCarWhilePending() {
		return carWhilePending;
	}

	public void setCarWhilePending(Integer carWhilePending) {
		this.carWhilePending = carWhilePending;
	}

	public Integer getDepositPayment() {
		return depositPayment;
	}

	public void setDepositPayment(Integer depositPayment) {
		this.depositPayment = depositPayment;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
