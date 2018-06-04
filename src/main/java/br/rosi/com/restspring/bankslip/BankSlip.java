package br.rosi.com.restspring.bankslip;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({ "id", "dueDate", "total_in_cents", "customer", "fine", "status" })
@Entity
@Table(name="BANK_SLIP")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankSlip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private UUID id;

	@NotNull
	@JsonProperty("due_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DUE_DATE")
	private LocalDate dueDate;

	@NotNull
	@JsonProperty("total_in_cents")
	@Column(name = "TOTAL_IN_CENTS")
	private BigDecimal totalInCents;

	@NotNull
	@Column(name = "CUSTOMER")
	private String customer;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;

	@JsonInclude(Include.NON_NULL)
	@Transient
	private BigDecimal fine;

	public BigDecimal getFine(){
		if(this.dueDate.isBefore(LocalDate.now()) && this.status.equals(Status.PENDING)){
			BigDecimal tax = new BigDecimal("0.5");
			if(this.dueDate.isBefore(LocalDate.now().minusDays(10))){
				tax = new BigDecimal("1.0");
			}
			return this.totalInCents.multiply(tax).divide(new BigDecimal(100));
		}
		return null;
	}
}
