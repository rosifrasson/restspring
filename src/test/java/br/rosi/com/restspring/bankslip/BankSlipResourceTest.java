package br.rosi.com.restspring.bankslip;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BankSlipResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private BankSlip bankSlip = new BankSlip();

	@Before
	public void before(){
		bankSlip.setDueDate(LocalDate.of(2018, 01, 01) );
		bankSlip.setStatus(Status.PENDING);
		bankSlip.setCustomer("Ford Prefect Company");
		bankSlip.setTotalInCents(new BigDecimal(1000));
	}

	@Test
	public void testCreateBankSlip(){
		ResponseEntity<BankSlip> response = restTemplate.postForEntity("/rest/bankslips/",
				bankSlip, BankSlip.class);
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
	}

}
