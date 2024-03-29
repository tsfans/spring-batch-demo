package cn.swift.batchprocessing;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport{
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT id, first_name, last_name FROM people",
              (rs, row) -> new Person(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3))
            ).forEach(person -> log.info("Found <" + person + "> in the database."));
          }
    }
}
