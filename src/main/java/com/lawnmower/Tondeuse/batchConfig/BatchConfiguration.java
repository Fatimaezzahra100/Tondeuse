package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.position.Position;
import com.lawnmower.Tondeuse.position.Tondeuse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Classe de config pour Définir et configurer un job
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Value("${inputFile}")
    private String inputData;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private ItemReader<Tondeuse> tondeuseItemReader;

    @Autowired
    private ItemWriter<Tondeuse> tondeuseItemWriter ;

    @Autowired
    private ItemProcessor<Tondeuse, Tondeuse> tondeuseItemProcessor;

    // la méthode de config qui permet de retourner un Job
    @Bean
    public Job tondeuseJob() {
        Step step = new StepBuilder("tondeuse-data-load-step", jobRepository)
                .<Tondeuse, Tondeuse>chunk(1, transactionManager)
                .reader(tondeuseItemReader)
                .processor(tondeuseItemProcessor)
                .writer(tondeuseItemWriter)
                .build();


        return new JobBuilder("tondeuse-data-loader-job", jobRepository)
                .flow(step)
                .end()
                .build();
    }


    @Bean
    public FlatFileItemReader<Tondeuse> tondeuseReader() {
        FlatFileItemReader<Tondeuse> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(inputData));
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }

    @Bean
    public LineMapper<Tondeuse> lineMapper() {
        // Configuration du line mapper pour mapper les lignes du fichier CSV à l'objet Tondeuse
        DefaultLineMapper<Tondeuse> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("x", "y", "orientation", "instructions");
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
            setTargetType(Tondeuse.class);
        }});
        return lineMapper;
    }


}