package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice extends AbstractModel {

	private Date created;
	private String checkNumber;
	private BigDecimal summ;
	private String reason;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public BigDecimal getSumm() {
		return summ;
	}

	public void setSumm(BigDecimal summ) {
		this.summ = summ;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
