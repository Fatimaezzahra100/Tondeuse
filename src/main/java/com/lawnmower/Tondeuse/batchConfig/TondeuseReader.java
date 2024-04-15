package com.lawnmower.Tondeuse.batchConfig;


import com.lawnmower.Tondeuse.InstructionFieldSetMapper;
import com.lawnmower.Tondeuse.model.Tondeuse;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Lecteur Spring Batch pour lire les données à partir du fichier
 */

@Log4j2
@Component
@StepScope
public class TondeuseReader extends FlatFileItemReader<Tondeuse> {
    private int maxX;
    private int maxY;

    public TondeuseReader(@Value("${inputFile}") String inputData) throws MalformedURLException {
        initializeReader(inputData);
    }

    private void initializeReader(String inputData) throws MalformedURLException {
        // Extract maxX and maxY from the first line
        readFirstLine(inputData);

        setLineMapper(createLineMapper());
        setResource(new UrlResource(inputData));
        setLinesToSkip(1);
    }

    private void readFirstLine(String inputData) {
        try (BufferedReader br = new BufferedReader(new FileReader(TondeuseConstant.DATA_INPUT))) {
            String line = br.readLine();
            if (line != null) {
                String[] values = line.split(" ");
                if (values.length >= 2) {
                    maxX = Integer.parseInt(values[0].trim());
                    maxY = Integer.parseInt(values[1].trim());
                } else {
                    log.info("La ligne ne contient pas deux nombres entiers.");
                }
            } else {
                log.info("Le fichier est vide.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DefaultLineMapper<Tondeuse> createLineMapper() {
        // Reads data from file and maps it to Trimmer object
        DefaultLineMapper<com.lawnmower.Tondeuse.model.Tondeuse> lineMapper = new DefaultLineMapper<>();

        // a row tokenizer is created to split each row into tokens based on a delimiter
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(" ");
        tokenizer.setNames("positionX", "positionY", "orientation", "instructions");

        InstructionFieldSetMapper fieldSetMapper = new InstructionFieldSetMapper();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }


}