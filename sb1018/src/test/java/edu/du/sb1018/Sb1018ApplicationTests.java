package edu.du.sb1018;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.entity.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
class Sb1018ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Test
    void 영속성_find_테스트() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("서울");
        System.out.println(dept);
        tx.commit();
    }

    @Test
    void 영속성_merge_테스트() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("성남");
        System.out.println(dept);
        em.detach(dept);    //영속성을 잃음
        em.merge(dept);     //merge = update
        tx.commit();    //dbms에 update를 발생
    }
    
    @Test
    void 영속성_persist_테스트() {
        //트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        //Dept 엔티티 객체 생성
        Dept newDept = new Dept();
        newDept.setDeptno(50);
        newDept.setDname("연구소");
        newDept.setLoc("서울");
        
        //엔티티를 데이터베이스에 저장
        em.persist(newDept);
        
        //트랜잭션 커밋
        em.getTransaction().commit();
    }

    @Test
    void 영속성_remove_테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Dept dept = em.find(Dept.class, 50);
        System.out.println(dept);
        em.remove(dept);
        tx.commit();
    }

    @Test
    void update_테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("ACCOUNTING");
        dept.setLoc("SEOUL");
        System.out.println(dept);
        tx.commit();
    }

    @Test
    void createQuery_Test() {
        Dept dept = em.find(Dept.class, 10);
        System.out.println(dept);

        TypedQuery<Dept> query = em.createQuery("select d from Dept d", Dept.class);
        List<Dept> depts = query.getResultList();
        for (Dept d : depts) {
            System.out.println(d);
        }
        System.out.println("----------------");

        TypedQuery<Emp> query2 = em.createQuery("select e from Emp e", Emp.class);
        List<Emp> emps = query2.getResultList();
        for (Emp e : emps) {
            System.out.println(e);
        }
    }

    @Test
    void createQuery_영속성테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        System.out.println("=======================");

        TypedQuery<Dept> query = em.createQuery("select d from Dept d", Dept.class);
        List<Dept> depts = query.getResultList();
        for (Dept d : depts) {
            System.out.println(d);
        }
        System.out.println(depts.get(0));
        depts.get(0).setLoc("서울");
        tx.commit();
    }

    @Test
    void createQuery_Test2() {
        String jpql = "select d from Dept d where d.dname = :aaa";
        TypedQuery<Dept> query = em.createQuery(jpql, Dept.class);
        query.setParameter("aaa", "ACCOUNTING");
        List<Dept> depts = query.getResultList();
        Dept dept = depts.get(0);
        System.out.println(depts);
        System.out.println("=====================================");
        String jpql2 = "select d from Emp d where d.deptno = :deptNo";
        TypedQuery<Emp> query2 = em.createQuery(jpql2, Emp.class);
        query2.setParameter("deptNo", dept.getDeptno());
        List<Emp> emps = query2.getResultList();
        for (Emp e : emps) {
            System.out.println(e);
        }
    }
}
