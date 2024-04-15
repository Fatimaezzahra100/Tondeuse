package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.position.Position;
import com.lawnmower.Tondeuse.position.Tondeuse;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Processeur Spring Batch pour traiter les instructions de la tondeuse
 */

@Component
public class TondeuseProcessor implements ItemProcessor<Tondeuse, Position> {

    @Override
    public Position process(Tondeuse tondeuse) {
        Position position = tondeuse.getPosition();
        for (char instruction : tondeuse.getInstructions().toCharArray()) {
            switch (instruction) {
                case 'D' -> position = turnRight(position);
                case 'G' -> position = turnLeft(position);
                case 'A' -> position = moveForward(position, tondeuse.getMaxX(), tondeuse.getMaxY());
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

        // Vérifier si la nouvelle position est à l'intérieur de la pelouse
        if (x >= 0 && x <= xMax && y >= 0 && y <= yMax) {
            // La nouvelle position est à l'intérieur de la pelouse, retourner la nouvelle position
            return new Position(x, y, orientation);
        } else {
            // La nouvelle position est en dehors de la pelouse, retourner la position actuelle sans la modifier
            return position;
        }
    }

}