package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.model.Position;
import com.lawnmower.Tondeuse.model.Tondeuse;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class TondeuseProcessor implements ItemProcessor<Tondeuse, Position> {
    @Autowired
    TondeuseReader tondeuseReader;

    public TondeuseProcessor(TondeuseReader tondeuseReader) {
        this.tondeuseReader = tondeuseReader;
    }

    @Override
    public Position process(Tondeuse tondeuse) {
        Position position = tondeuse.getPosition();
        for (char instruction : tondeuse.getInstructions().toCharArray()) {
            switch (instruction) {
                case 'D' -> position = turnRight(position);
                case 'G' -> position = turnLeft(position);
                case 'A' -> position = moveForward(position, tondeuseReader.getMaxX(), tondeuseReader.getMaxY());
                default -> {
                }
            }
        }
        return position;
    }


    /**
     * Implémenter la logique de rotation vers la droite
     *
     * @param position position
     * @return Retourner la nouvelle position
     */
    private Position turnRight(Position position) {
        String orientation = position.getOrientation();
        return switch (orientation) {
            case "N" -> new Position(position.getX(), position.getY(), "E");
            case "E" -> new Position(position.getX(), position.getY(), "S");
            case "S" -> new Position(position.getX(), position.getY(), "W");
            case "W" -> new Position(position.getX(), position.getY(), "N");
            default -> position;
        };
    }

    /**
     * mplémenter la logique de rotation vers la gauche
     *
     * @param position position
     * @return Retourner la nouvelle position
     */
    private Position turnLeft(Position position) {

        String orientation = position.getOrientation();
        return switch (orientation) {
            case "N" -> new Position(position.getX(), position.getY(), "W");
            case "W" -> new Position(position.getX(), position.getY(), "S");
            case "S" -> new Position(position.getX(), position.getY(), "E");
            case "E" -> new Position(position.getX(), position.getY(), "N");
            default -> position;
        };
    }


    /**
     * Implémenter la logique de déplacement en avant
     *
     * @param position position
     * @return Retourner la nouvelle position
     */
    private Position moveForward(Position position, int xMax, int yMax) {
        String orientation = position.getOrientation();
        int x = position.getX();
        int y = position.getY();

        switch (orientation) {
            case "N" -> y = y + 1;
            case "E" -> x = x + 1;
            case "S" -> y = y - 1;
            case "W" -> x = x - 1;
        }

        return this.checkIfTondeuseIsInsidePelouse(x, y, xMax, yMax, orientation, position);
    }

    private Position checkIfTondeuseIsInsidePelouse(int x, int y, int xMax, int yMax, String orientation, Position position) {
        if (x >= 0 && x <= xMax && y >= 0 && y <= yMax) {
            return new Position(x, y, orientation);
        }
        return position;
    }

}