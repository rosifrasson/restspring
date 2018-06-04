package br.rosi.com.restspring.bankslip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankSlipRepositoryTest {

	@Autowired
	private BankSlipRepository bankSlipRepository;

	private BankSlip bankSlip = new BankSlip();

	@Before
	public void before(){
		bankSlip.setDueDate(LocalDate.of(2018, 01, 01) );
		bankSlip.setStatus(Status.PENDING);
		bankSlip.setCustomer("Ford Prefect Company");
		bankSlip.setTotalInCents(new BigDecimal(1000));
	}

	@Test
	public void testSaveBankSlip(){
		BankSlip saved = bankSlipRepository.save(bankSlip);

		assertNotNull(saved);
	}

	@Test
	public void testFindById() {
		BankSlip saved = bankSlipRepository.save(bankSlip);
		Optional<BankSlip> find = bankSlipRepository.findById(bankSlip.getId());
		assertEquals(find.get().getId(), saved.getId());
	}

	@Test
	public void testFindAll() {
		bankSlipRepository.deleteAll();
		BankSlip b = new BankSlip();
		b.setDueDate(LocalDate.of(2018, 01, 02) );
		b.setStatus(Status.PENDING);
		b.setCustomer("Company");
		b.setTotalInCents(new BigDecimal(1000));

		BankSlip b1 = new BankSlip();
		b1.setDueDate(LocalDate.of(2018, 01, 03) );
		b1.setStatus(Status.CANCELED);
		b1.setCustomer("Other Company");
		b1.setTotalInCents(new BigDecimal(1000));
		bankSlipRepository.save(b);
		bankSlipRepository.save(b1);

		List<BankSlip> bs = bankSlipRepository.findAll();
		assertEquals(2, bs.size());
	}


}
