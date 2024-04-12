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
public class PositionWriter implements ItemWriter<Tondeuse> {
    private final String outputFilePath;

    public PositionWriter(@Value("${outputFile}") String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    // Implémenter la logique d'écriture ici
    @Override
    public void write(Chunk<? extends Tondeuse> finalPositions) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            for (Tondeuse tondeuse : finalPositions) {
                writer.println(tondeuse.getPosition().getX() + " " + tondeuse.getPosition().getY() + " " + tondeuse.getPosition().getOrientation());
            }
        }

    }

}