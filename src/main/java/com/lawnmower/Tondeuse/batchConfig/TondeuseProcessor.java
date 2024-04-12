package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.position.Position;
import com.lawnmower.Tondeuse.position.Tondeuse;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Processeur Spring Batch pour traiter les instructions de la tondeuse
 */

@Component
public class TondeuseProcessor implements ItemProcessor<Tondeuse, Tondeuse> {


    // Implémenter la logique de traitement ici
    @Override
    public Tondeuse process(Tondeuse tondeuse) {

        Position position = tondeuse.getPosition();
        for (char instruction : tondeuse.getInstructions().toCharArray()) {
            switch (instruction) {
                case 'D' -> position = turnRight(position);
                case 'G' -> position = turnLeft(position);
                case 'A' -> position = moveForward(position);
                default -> {
                }
                // Ignore unknown instruction
            }
        }
        Tondeuse tondeusePositionFinal = new Tondeuse();
        tondeuse.setPosition(position);
        return tondeusePositionFinal;
    }


    /**
     * Implémenter la logique de rotation vers la droite
     *
     * @param position position
     * @return Retourner la nouvelle position
     */
    private Position turnRight(Position position) {
        char orientation = position.getOrientation();
        return switch (orientation) {
            case 'N' -> new Position(position.getX(), position.getY(), 'E');
            case 'E' -> new Position(position.getX(), position.getY(), 'S');
            case 'S' -> new Position(position.getX(), position.getY(), 'W');
            case 'W' -> new Position(position.getX(), position.getY(), 'N');
            default ->
                // Should not happen, return unchanged position
                    position;
        };
    }

    /**
     * mplémenter la logique de rotation vers la gauche
     *
     * @param position position
     * @return Retourner la nouvelle position
     */
    private Position turnLeft(Position position) {

        char orientation = position.getOrientation();
        return switch (orientation) {
            case 'N' -> new Position(position.getX(), position.getY(), 'W');
            case 'W' -> new Position(position.getX(), position.getY(), 'S');
            case 'S' -> new Position(position.getX(), position.getY(), 'E');
            case 'E' -> new Position(position.getX(), position.getY(), 'N');
            default ->
                // Should not happen, return unchanged position
                    position;
        };
    }

    /**
     * Implémenter la logique de déplacement en avant
     *
     * @param position position
     * @return Retourner la nouvelle position
     */
    private Position moveForward(Position position) {
        char orientation = position.getOrientation();
        int x = position.getX();
        int y = position.getY();
        return switch (orientation) {
            case 'N' -> new Position(x, y + 1, orientation);
            case 'E' -> new Position(x + 1, y, orientation);
            case 'S' -> new Position(x, y - 1, orientation);
            case 'W' -> new Position(x - 1, y, orientation);
            default ->
                // Should not happen, return unchanged position
                    position;
        };
    }

}