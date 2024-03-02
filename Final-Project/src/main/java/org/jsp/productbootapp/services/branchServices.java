package org.jsp.productbootapp.services;

import java.util.List;
import java.util.Optional;

import org.jsp.productbootapp.dto.Branch;
import org.jsp.productbootapp.dto.ResponseStructure;
import org.jsp.productbootapp.exception.IdNotFoundException;
import org.jsp.productbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class branchServices {
	@Autowired
	private org.jsp.productbootapp.dao.BranchDao BranchDao;
	
	public ResponseStructure<Branch> saveBranch(Branch Branch) {
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		structure.setMessage("Branch saved");
		structure.setData(BranchDao.saveBranch(Branch));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch Branch) {
		Optional<Branch> recBranch = BranchDao.findById(Branch.getId());
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			Branch dbBranch = recBranch.get();
			dbBranch.setEmail(Branch.getEmail());
			dbBranch.setName(Branch.getName());
			dbBranch.setPhone(Branch.getPhone());
			dbBranch.setPassword(Branch.getPassword());
			structure.setMessage("Branch Updated");
			structure.setData(BranchDao.saveBranch(Branch));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Branch>> findById(int id) {
		Optional<Branch> recBranch = BranchDao.findById(id);
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			structure.setData(recBranch.get());
			structure.setMessage("Branch Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<Branch> recBranch = BranchDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			structure.setMessage("Branch found");
			structure.setData("Branch deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			BranchDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Branch Not found");
		structure.setData("cannot delete Branch as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseStructure<List<Branch>> findAll() {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();
		structure.setMessage("List of Branchs");
		structure.setData(BranchDao.findAll());
		structure.setStatusCode(HttpStatus.OK.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<Branch>> verifyBranch(
			long phone, String password) {
		Optional<Branch> recBranch = BranchDao.verify(phone, password);
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recBranch.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>
			         (structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Phone Number or Password");
	}


	public ResponseEntity<ResponseStructure<List<Branch>>> findByName(String name) {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();
		List<Branch> Branchs = BranchDao.findByName(name);
		structure.setData(Branchs);
		if (Branchs.size() > 0) {
			structure.setMessage("List of Branchs with entered name ");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("No Branch found with the entered name ");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.NOT_FOUND);
	}


}
