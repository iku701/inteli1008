package edu.du.sb1010.config;

import edu.du.sb1010.spring.MemberDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"edu.du.sb1010.spring"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {NoProduct.class, ManualBean.class}))

public class AppCtxWithExclude {
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
}
