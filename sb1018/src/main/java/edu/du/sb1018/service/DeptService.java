package edu.du.sb1018.service;

import edu.du.sb1018.entity.Dept;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
public class DeptService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public Dept findById(Integer deptno) {
        EntityManager em = emf.createEntityManager();
        Dept dept = em.find(Dept.class, deptno);
        em.close();
        return dept;
    }

    public void save(Dept dept) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        if (dept.getDeptno() == null) {
            em.persist(dept);
        } else {
            em.merge(dept);
        }
        tx.commit();
        em.close();
    }

    public void deleteById(Integer deptno) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Dept dept = em.find(Dept.class, deptno);
        if (dept != null) {
            em.remove(dept);
        }
        tx.commit();
        em.close();
    }

    public List<Dept> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Dept> depts = em.createQuery("select d from Dept d", Dept.class).getResultList();
        em.close();
        return depts;
    }
}
