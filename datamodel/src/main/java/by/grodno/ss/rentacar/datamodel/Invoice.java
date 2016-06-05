package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice extends AbstractModel {

	@Column
	private Date created;
	
	@Column
	private BigDecimal summ;
	
	@Column
	private String note;
	
	public Date getCreated() {
		return created;
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

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
