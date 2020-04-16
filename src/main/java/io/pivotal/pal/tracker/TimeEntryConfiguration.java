package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TimeEntryConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean
    public TimeEntryRepository createInMemoryRepo(){

        return new JdbcTimeEntryRepository(dataSource);
    }
}
