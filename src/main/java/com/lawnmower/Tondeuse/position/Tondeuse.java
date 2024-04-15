package com.lawnmower.Tondeuse.position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de modèle pour représenter une tondeuse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tondeuse {

    private int maxX;

    private int maxY;

    private Position position;

    private String instructions;


}