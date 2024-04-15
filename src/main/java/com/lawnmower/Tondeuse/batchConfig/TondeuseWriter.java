package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.model.Position;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class TondeuseWriter extends FlatFileItemWriter<Position> {

    public TondeuseWriter(@Value("${outputFile}") String outputFile) {
        initializeTondeuseWriter(outputFile);
    }

    private void initializeTondeuseWriter(String outputFile) {
        setName("tondeuseItemWriter");
        setResource(new FileSystemResource(outputFile));
        setLineAggregator(new DelimitedLineAggregator<>() {{
            setDelimiter(" ");
            setFieldExtractor(new BeanWrapperFieldExtractor<>() {{
                setNames(new String[]{"x", "y", "orientation"});
            }});
        }});

    }

}