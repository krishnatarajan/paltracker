package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeEntryConfiguration {

    @Bean
    public TimeEntryRepository createInMemoryRepo(){

        return new InMemoryTimeEntryRepository();
    }
}
