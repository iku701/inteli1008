package edu.du.sb1015_2;

import edu.du.sb1015_2.entity.MyData;
import edu.du.sb1015_2.repository.MyDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class Sb10152Application {



//    @Autowired
    final MyDataRepository repository;

//    public Sb10152Application(MyDataRepository repository) {
//        this.repository = repository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Sb10152Application.class, args);
    }

    @PostConstruct
    public void init() {
        MyData d1 = new MyData();
        d1.setName("Kim");
        d1.setAge(18);
        d1.setMail("Kim@gmail.com");
        d1.setMemo("this is my data!");
        repository.saveAndFlush(d1);

        MyData d2 = new MyData();
        d2.setName("Lee");
        d2.setAge(20);
        d2.setMail("Lee@gmail.com");
        d2.setMemo("My girl friend!");
        repository.saveAndFlush(d2);

        MyData d3 = new MyData();
        d3.setName("Choi");
        d3.setAge(23);
        d3.setMail("Choi@gmail.com");
        d3.setMemo("my work friend...!");
        repository.saveAndFlush(d3);
    }
}
