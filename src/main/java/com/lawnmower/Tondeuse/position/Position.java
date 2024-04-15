package com.lawnmower.Tondeuse.position;

/**
 * Classe de modèle pour représenter une position
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    private int x;

    private int y;

    private String orientation;


}