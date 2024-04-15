package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.model.Position;

import com.lawnmower.Tondeuse.model.Tondeuse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Classe de config pour Définir et configurer un job
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TondeuseWriter tondeuseWriter;

    @Autowired
    private TondeuseReader tondeuseReader;

    @Autowired
    private TondeuseProcessor tondeuseItemProcessor;

    @Bean
    public Job tondeuseJob() {
        Step step = new StepBuilder("tondeuse-data-load-step", jobRepository)
                .<Tondeuse, Position>chunk(1, transactionManager)
                .reader(tondeuseReader)
                .processor(tondeuseItemProcessor)
                .writer(tondeuseWriter)
                .build();


        return new JobBuilder("tondeuse-data-loader-job", jobRepository)
                .start(step)
                .build();
    }
}