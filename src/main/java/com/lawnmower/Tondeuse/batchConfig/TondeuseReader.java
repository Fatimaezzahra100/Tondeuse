package com.lawnmower.Tondeuse.batchConfig;


import com.lawnmower.Tondeuse.model.Tondeuse;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;


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
        this.readFirstLine();

        setLineMapper(createLineMapper());
        setResource(new FileSystemResource(inputData));
        setLinesToSkip(1);
    }

    private void readFirstLine() {
        try (BufferedReader br = new BufferedReader(new FileReader(TondeuseConstant.DATA_INPUT))) {
            String line = br.readLine();
            if (line != null) {
                String[] values = line.split(" ");
                if (values.length >= 2) {
                    maxX = Integer.parseInt(values[0].trim());
                    maxY = Integer.parseInt(values[1].trim());
                } else {
                    log.info("La ligne ne contient de nombre");
                }
            } else {
                log.info("Le fichier est vide.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DefaultLineMapper<Tondeuse> createLineMapper() {
        DefaultLineMapper<com.lawnmower.Tondeuse.model.Tondeuse> lineMapper = new DefaultLineMapper<>();

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