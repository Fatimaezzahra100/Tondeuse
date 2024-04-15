package com.lawnmower.Tondeuse;

import com.lawnmower.Tondeuse.model.Position;
import com.lawnmower.Tondeuse.model.Tondeuse;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


public class InstructionFieldSetMapper implements FieldSetMapper<Tondeuse> {

    @Override
    public Tondeuse mapFieldSet(FieldSet fieldSet) {

        int positionX = fieldSet.readInt("positionX");
        int positionY = fieldSet.readInt("positionY");
        String orientation = fieldSet.readString("orientation");
        String instructions = fieldSet.readString("instructions");

        Position position = new Position(positionX, positionY, orientation);
        return new Tondeuse(position, instructions);
    }
}
