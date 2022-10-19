package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Deposit;
import com.example.demo.model.View_balance;
import com.example.demo.model.Withdrawing;
import com.example.demo.model.Transfer;
import com.example.demo.repository.Deposit_Repository;
import com.example.demo.repository.Transfer_repo;
import com.example.demo.repository.ViewBalance_Repository;
import com.example.demo.repository.Viewbalance_repo;
import com.example.demo.repository.Withdraw_repo;

import reactor.core.publisher.Mono;

@RestController
public class Controller {
	
	@Autowired
	private ViewBalance_Repository bal_repo;
	
	@Autowired
	private Deposit_Repository dep_repo;
	
	@Autowired
	private Withdraw_repo with_repo;
	
	@Autowired
	private Transfer_repo trans_repo;
	
	@Autowired
	private Deposit deposit;
	
	@Autowired
	private Withdrawing withdraw;
	
	@Autowired
	private Viewbalance_repo viewbal;
	
	@Autowired
	private Transfer transfer;

	 @PostMapping("/Adduser")
	    public Mono<ResponseEntity<View_balance>> create(@RequestBody View_balance user){
	        return viewbal.save(user)
	                .map(savedUser -> ResponseEntity.ok(savedUser));
	    }

	
	@GetMapping("/ViewUsers")
	public List<View_balance> checkbal() {
		return bal_repo.findAll();
	}
	
	
	@GetMapping("/viewbalance/{accno}")
	public String viewbal(@PathVariable int accno) {
		View_balance bal = bal_repo.findById(accno).get();
		return "Your Balance is "+bal.getBalance();
	}
	
	@PutMapping("/updateuser/{accno}")
	public String updateuser(@RequestBody View_balance user,@PathVariable int accno) {
		user.setAccount_number(accno);
		bal_repo.save(user);
		return "User with account number: "+ accno + " has been updated";
	}
	
	@DeleteMapping("/deleteuser/{accno}")
	public String deleteuser(@PathVariable int accno) {
		if(bal_repo.existsById(accno)==true) {
			bal_repo.deleteById(accno);
			return "the user is deleted " + accno;
		}
		return "User not Found!";
	}

	@PostMapping("/deposit/{num}/amount/{amt}")
	public Optional<Deposit> depositeAmt(@PathVariable int num, @PathVariable int amt) {
		View_balance bal = bal_repo.findById(num).get();
		deposit.setAccount_number(num);
		deposit.setAmount_deposited(amt);
		dep_repo.save(deposit);
		bal.setBalance(bal.getBalance() + amt);
		bal_repo.save(bal);
		return dep_repo.findById(num);
	}
	
	
	@PostMapping("/withdraw/{num}/amount/{amt}")
	public Optional<Withdrawing> withdrawAmt(@PathVariable int num, @PathVariable float amt) {
		View_balance bal = bal_repo.findById(num).get();
		if(amt > bal.getBalance()) {
			return Optional.empty();
		}
		else 
		{
		withdraw.setAccount_number(num);
		withdraw.setAmount_withdrawn(amt);
		with_repo.save(withdraw);
		bal.setBalance(bal.getBalance() - amt);
		bal_repo.save(bal);
		return with_repo.findById(num);
		}
	}
	
	@PostMapping("/transfer/{acc1}/{acc2}/amount/{amt}")
	public Optional<Transfer> transferamt(@PathVariable int acc1,@PathVariable int acc2,@PathVariable float amt) {
	View_balance bal = bal_repo.findById(acc1).get();
	View_balance bal1 = bal_repo.findById(acc2).get();
		if(amt > bal.getBalance()) {
			return Optional.empty();
		}
		else {
			transfer.setFirst_account(acc1);
			transfer.setAmount_transferred(amt);
			bal.setBalance(bal.getBalance() - amt);
			bal_repo.save(bal);
			
			transfer.setSecond_account(acc2);
			transfer.setAmount_transferred(amt);
			trans_repo.save(transfer);
			bal1.setBalance(bal1.getBalance() + amt);
			bal_repo.save(bal1);
			return trans_repo.findById(acc1);
		}
	}	
}
