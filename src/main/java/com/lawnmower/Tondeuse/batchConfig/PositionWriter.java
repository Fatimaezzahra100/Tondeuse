package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.position.Position;
import com.lawnmower.Tondeuse.position.Tondeuse;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Écrivain Spring Batch pour écrire les positions finales des tondeuses dans un fichier de sortie
 */

@Component
public class PositionWriter implements ItemWriter<Position> {
    private final String outputFilePath;

    public PositionWriter(@Value("${outputFile}") String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void write(Chunk<? extends Position> finalPositions) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            for (Position position : finalPositions) {
                writer.println(position.getX() + " " + position.getY() + " " + position.getOrientation());
            }
        }

    }

}