package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private Date created;
	
	@Column
	private BigDecimal summ;
	
	@Column
	private String note;
	
	@ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
	private Order order;
	
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
