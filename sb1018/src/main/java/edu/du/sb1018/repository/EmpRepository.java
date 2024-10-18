package edu.du.sb1018.repository;

import edu.du.sb1018.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
}
