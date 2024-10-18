package edu.du.sb1018.service;

import edu.du.sb1018.entity.Emp;
import edu.du.sb1018.repository.EmpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    private final EmpRepository empRepository;

    public EmpService(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    public List<Emp> findAll() {
        return empRepository.findAll();
    }

    public Emp findById(Integer id) {
        return empRepository.findById(id).orElse(null);
    }

    public Emp save(Emp emp) {
        return empRepository.save(emp);
    }

    public void deleteById(Integer id) {
        empRepository.deleteById(id);
    }
}
