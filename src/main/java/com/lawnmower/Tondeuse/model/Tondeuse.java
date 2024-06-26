package com.lawnmower.Tondeuse.model;

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

    private Position position;

    private String instructions;


}