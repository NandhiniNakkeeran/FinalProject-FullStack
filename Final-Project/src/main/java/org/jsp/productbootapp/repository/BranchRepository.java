package org.jsp.productbootapp.repository;

import java.util.List;
import java.util.Optional;


import org.jsp.productbootapp.dto.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
@Query("select m from Branch m Where m.phone=?1 and m.password=?2")
public Optional<Branch> verify(long phone,String password);
@Query("select m from Branch m Where m.name=?1")
public List<Branch> findByName(String name);
}