package com.lawnmower.Tondeuse.position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelouse {

    private int maxX;

    private int maxY;

    private Tondeuse tondeuse;
}
