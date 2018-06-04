package br.rosi.com.restspring.bankslip;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.rosi.com.restspring.exceptions.BankSlipNotFoundException;
import br.rosi.com.restspring.utils.MessageDetails;


@RestController
public class BankSlipResource {
	
	@Autowired
	private BankSlipRepository bankSlipRepository;

	@GetMapping("/rest/bankslips")
	public List<BankSlip> retrieveAllBankSlips() {
		return bankSlipRepository.findAll();
	}
	
	@GetMapping("/rest/bankslips/{id}")
	public BankSlip retrieveBankSlip(@PathVariable UUID id) {
		Optional<BankSlip> bankSlip = bankSlipRepository.findById(id);

		if (!bankSlip.isPresent())
			throw new BankSlipNotFoundException("Bankslip not found with the specified id");
		return bankSlip.get();
	}
	
	@PostMapping("/rest/bankslips")
	public ResponseEntity<Object> createBankSlip(@Valid @RequestBody BankSlip bankSlip) {
				bankSlipRepository.save(bankSlip);
		return new ResponseEntity<>(new MessageDetails("Bankslip created"), HttpStatus.CREATED);
	}
	
	@PutMapping("/rest/bankslips/{id}")
	public ResponseEntity<Object> updateBankSlip(@RequestBody BankSlip bankSlip, @PathVariable UUID id) {

		Optional<BankSlip> bankSlipOptional = bankSlipRepository.findById(id);

		if (!bankSlipOptional.isPresent())
			throw new BankSlipNotFoundException("Bankslip not found with the specified id");
		
		bankSlipOptional.get().setId(id);
		bankSlipOptional.get().setStatus(bankSlip.getStatus());
		
		bankSlipRepository.save(bankSlipOptional.get());

		return new ResponseEntity<>(createMessageStatus(bankSlip), HttpStatus.OK);
	}

	private MessageDetails createMessageStatus(BankSlip bankSlip) {
		MessageDetails messageDetails = null;
		if(bankSlip.getStatus().equals(Status.PAID)){
			messageDetails = new MessageDetails("Bankslip paid");
		}else if(bankSlip.getStatus().equals(Status.CANCELED)){
			messageDetails = new MessageDetails("Bankslip canceled");
		}
		return messageDetails;
	}

}
