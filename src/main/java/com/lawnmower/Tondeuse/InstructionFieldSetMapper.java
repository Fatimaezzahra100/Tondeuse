package com.lawnmower.Tondeuse;

import com.lawnmower.Tondeuse.position.Position;
import com.lawnmower.Tondeuse.position.Tondeuse;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


public class InstructionFieldSetMapper implements FieldSetMapper<Tondeuse> {

    @Override
    public Tondeuse mapFieldSet(FieldSet fieldSet) {
        int x = fieldSet.readInt("maxX");
        int y = fieldSet.readInt("maxY");
        int positionX = fieldSet.readInt("positionX");
        int positionY = fieldSet.readInt("positionY");
        String orientation = fieldSet.readString("orientation");
        String instructions = fieldSet.readString("instructions");

        Position position = new Position(positionX, positionY, orientation);
        return new Tondeuse(x, y, position, instructions);
    }
}
