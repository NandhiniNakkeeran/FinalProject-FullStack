package org.jsp.productbootapp.dao;

import java.util.List;
import java.util.Optional;


import org.jsp.productbootapp.dto.Branch;
import org.jsp.productbootapp.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BranchDao {
		@Autowired
		private BranchRepository BranchRepository;

		public Branch saveBranch(Branch branch) {
			return BranchRepository.save(branch);
		}

		public Optional<Branch> findById(int id) {
			return BranchRepository.findById(id);
		}

		public List<Branch> findAll() {
			return BranchRepository.findAll();
		}

		public List<Branch> findByName(String name) {
			return BranchRepository.findByName(name);
		}

		public boolean deleteById(int id) {
			Optional<Branch> recAdmin = findById(id);
			if (recAdmin.isPresent()) {
				BranchRepository.delete(recAdmin.get());
				return true;
			}
			return false;
		}

		public Optional<Branch> verify(long phone, String password) {
			return BranchRepository.verify(phone, password);
		}
		

	}

