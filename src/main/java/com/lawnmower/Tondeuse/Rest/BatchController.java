package com.lawnmower.Tondeuse.Rest;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Value("${inputFile}")
    private String inputFile;

    @Autowired
    private Job tondeuseJob;

    @GetMapping("/startJob")
    public BatchStatus executeBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(tondeuseJob, jobParameters);

        while (jobExecution.isRunning()) {
            System.out.println("...................");
        }
        return jobExecution.getStatus();

    }
}