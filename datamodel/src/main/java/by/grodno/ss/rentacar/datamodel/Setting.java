package by.grodno.ss.rentacar.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private String currency;

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
