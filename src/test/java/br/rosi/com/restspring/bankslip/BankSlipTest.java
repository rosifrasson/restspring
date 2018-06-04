package br.rosi.com.restspring.bankslip;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class BankSlipTest {
	
	BankSlip bankSlipMock;
	
	@Before
	public void before(){
		bankSlipMock = new BankSlip();
		bankSlipMock.setDueDate(LocalDate.of(2018, 01, 01));
		bankSlipMock.setStatus(Status.PENDING);
		bankSlipMock.setTotalInCents(new BigDecimal(1000));
	}

	@Test
	public void calculateTaxToMoreTenDays() {
		assertEquals(new BigDecimal("10.0"), bankSlipMock.getFine());
	}
	
	@Test
	public void calculateTaxToMinusTenDays() {
		bankSlipMock.setDueDate(LocalDate.of(2018, 06, 01));
		assertEquals(new BigDecimal("5.0"), bankSlipMock.getFine());
	}
	
	@Test
	public void notCalculateTax() {
		bankSlipMock.setDueDate(LocalDate.of(2018, 06, 10));
		assertEquals(null, bankSlipMock.getFine());
	}
	

}
