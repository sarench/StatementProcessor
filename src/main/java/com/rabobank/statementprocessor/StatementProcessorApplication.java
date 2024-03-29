package com.rabobank.statementprocessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StatementProcessorApplication implements CommandLineRunner
{
    @Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    @Qualifier(value = "parallelStepsJob")
    Job job;
     
    public static void main(String[] args) 
    {
        SpringApplication.run(StatementProcessorApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception 
    {
        JobParameters params = new JobParametersBuilder()
                    .addString("StatementProcesser", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
        jobLauncher.run(job, params);
    }
}
