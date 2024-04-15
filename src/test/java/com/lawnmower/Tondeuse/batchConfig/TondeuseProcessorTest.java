package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.model.Position;
import com.lawnmower.Tondeuse.model.Tondeuse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class TondeuseProcessorTest {

    @Mock
    TondeuseReader tondeuseReader;

    @InjectMocks
    TondeuseProcessor tondeuseProcessor;

    @Test
    public void testProcess() {
        doReturn(5).when(tondeuseReader).getMaxX();
        doReturn(5).when(tondeuseReader).getMaxY();


        Tondeuse tondeuse = new Tondeuse();
        Position initialPosition = new Position(1, 2, "N");
        tondeuse.setPosition(initialPosition);
        tondeuse.setInstructions("GAGAGAGAA");

        Tondeuse tondeuse2 = new Tondeuse();
        Position secondPosition = new Position(3, 3, "E");
        tondeuse2.setPosition(secondPosition);
        tondeuse2.setInstructions("AADAADADDA");



        Position finalPosition = tondeuseProcessor.process(tondeuse);
        assert finalPosition != null;
        assertEquals(1, finalPosition.getX());
        assertEquals(3, finalPosition.getY());
        assertEquals("N", finalPosition.getOrientation());

        Position secondFinalPosition = tondeuseProcessor.process(tondeuse2);
        assert secondFinalPosition != null;
        assertEquals(5, secondFinalPosition.getX());
        assertEquals(1, secondFinalPosition.getY());
        assertEquals("E", secondFinalPosition.getOrientation());
    }
}