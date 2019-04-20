package tests.card.cardFactory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.Family;
import underlings.card.Temperature;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class GsonLearningTests {

    @Test
    public void testOneCardNoEffects() throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(getFile("oneCardNoEffects.json"));
        Card[] cards = gson.fromJson(fileReader, Card[].class);

        assertEquals(1, cards.length);

        Card testCard = cards[0];
        assertEquals("test", testCard.name);
        assertEquals("fakePath", testCard.filePath);
        assertEquals(1, testCard.points);
        assertEquals(Temperature.COOL, testCard.temperature);
        assertEquals(Family.TRIADIC, testCard.family);
    }

    @Test
    public void testTwoCardsNoEffects() throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(getFile("twoCardsNoEffects.json"));
        Card[] cards = gson.fromJson(fileReader, Card[].class);

        assertEquals(2, cards.length);

        Card firstCard = cards[0];
        assertEquals("first", firstCard.name);
        assertEquals("fakePath", firstCard.filePath);
        assertEquals(1, firstCard.points);
        assertEquals(Temperature.COOL, firstCard.temperature);
        assertEquals(Family.TRIADIC, firstCard.family);

        Card secondCard = cards[1];
        assertEquals("second", secondCard.name);
        assertEquals("diffFakePath", secondCard.filePath);
        assertEquals(2, secondCard.points);
        assertEquals(Temperature.NEUTRAL, secondCard.temperature);
        assertEquals(Family.TERTIARY, secondCard.family);
    }

    private File getFile(String fileName){
        String workingDirectory = Paths.get("").toAbsolutePath().toString();
        return new File(workingDirectory + "\\src\\test\\java\\tests\\card\\cardFactory\\jsonFiles\\" + fileName);
    }


}
