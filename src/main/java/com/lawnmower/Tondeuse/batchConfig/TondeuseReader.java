package com.lawnmower.Tondeuse.batchConfig;

import com.lawnmower.Tondeuse.position.Position;
import com.lawnmower.Tondeuse.position.Tondeuse;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lecteur Spring Batch pour lire les données à partir du fichier
 */


public class TondeuseReader {

}