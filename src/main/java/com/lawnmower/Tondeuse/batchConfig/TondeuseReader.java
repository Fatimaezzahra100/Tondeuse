package com.lawnmower.Tondeuse.batchConfig;


import com.lawnmower.Tondeuse.InstructionFieldSetMapper;
import com.lawnmower.Tondeuse.model.Tondeuse;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

/**
 * Lecteur Spring Batch pour lire les données à partir du fichier
 */


@Component
@StepScope
public class TondeuseReader extends FlatFileItemReader<Tondeuse>{

    public TondeuseReader(@Value("${inputFile}") String inputData) throws MalformedURLException {
        initializeReader(inputData);
    }
    private void initializeReader(String inputData) throws MalformedURLException {
        setLineMapper(createLineMapper());
        setResource(new UrlResource(inputData));
    }
    private static DefaultLineMapper<Tondeuse> createLineMapper() {
        DefaultLineMapper<com.lawnmower.Tondeuse.model.Tondeuse> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(" ");
        tokenizer.setNames("maxX", "maxY", "positionX", "positionY", "orientation", "instructions");

        InstructionFieldSetMapper fieldSetMapper = new InstructionFieldSetMapper();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }



}