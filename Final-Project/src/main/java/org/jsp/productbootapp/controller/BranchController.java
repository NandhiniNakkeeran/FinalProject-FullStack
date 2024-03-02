package org.jsp.productbootapp.controller;

import java.util.List;

import org.jsp.productbootapp.dto.Branch;
import org.jsp.productbootapp.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/Branchs")
public class BranchController {
	@Autowired
	private org.jsp.productbootapp.services.branchServices branchServices;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<Branch> saveBranch(@RequestBody Branch Branch) {
		return branchServices.saveBranch(Branch);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch Branch) {
		return branchServices.updateBranch(Branch);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Branch>> findById(@PathVariable(name = "id") int id) {
		return branchServices.findById(id);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
		return branchServices.deleteById(id);
	}
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseStructure<List<Branch>> findAll() {
		return branchServices.findAll();
	}
	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Branch>> verifyBranch(@RequestParam long phone,
			@RequestParam String password) {
		return branchServices.verifyBranch(phone, password);
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Branch>>> findByName(@PathVariable String name) {
		return branchServices.findByName(name);
	}

}

