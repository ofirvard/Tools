package HarmonicaGenerator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RichterTuningGenerator
{
    public void run() throws IOException
    {
        Gson gson = new Gson();
        Type listOfMyClassObject = new TypeToken<ArrayList<Key>>()
        {
        }.getType();


        List<Key> keys = gson.fromJson(Files.readString(Path.of("src/HarmonicaGenerator/Keys.json")), listOfMyClassObject);
        Tuning richter = gson.fromJson(Files.readString(Path.of("src/HarmonicaGenerator/Tunings.json")), Tuning.class);

        for (Key key : keys)
        {
            System.out.println("key of " + key.keyName);

            Harmonica harmonica = new Harmonica(key);
            for (HalfStepsPerHole halfStepsPerHole : richter.halfStepsPerHoles)
            {
                if (halfStepsPerHole.hole == -10)
                    System.out.println("hello world");

                harmonica.addHole(new Hole(halfStepsPerHole.hole, halfStepsPerHole.bend, key, halfStepsPerHole.halfSteps));
            }
            System.out.println("Harmonica:");
            printHarmonica(harmonica);
            // TODO: 12/9/2022 print harmonica
        }


        //todo  C C# D D# E F F# G G# A  A# B
        //todo  0 1  2 3  4 5 6  7 8  9 10 11

//        System.out.println("-------------------------------");
//        System.out.println("|  C |  E |  G |  C |  E |  G |  C |  E |  G |  C |");
//        System.out.println("-------------------------------");
//        System.out.println("|  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |");
//        System.out.println("-------------------------------");
//        System.out.println("|  D |  G |  B |  D |  F |  A |  B |  D |  F |  A |");
//        System.out.println("-------------------------------");
//        System.out.println("-------------------------------");
//        System.out.println("|-12 | -8 | -5 |  0 |  4 |  7 | 12 | 16 | 19 | 24 |");
//        System.out.println("-------------------------------");
//        System.out.println("|  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |");
//        System.out.println("-------------------------------");
//        System.out.println("|-10 | -5 | -1 |  2 |  5 |  9 | 11 | 14 | 17 | 21 |");
//        System.out.println("-------------------------------");

    }

    private int newSemitone(int baseSemitone, int steps)
    {
        return (baseSemitone + steps) % 12;
    }

    private int newOctave(int baseOctave, int baseSemitone, int steps)
    {
        return baseOctave + ((baseSemitone + steps) / 12);
    }

    private void printHarmonica(Harmonica harmonica)
    {
        System.out.println("-----------------------------------------");
        for (int i = 1; i < 11; i++)
        {
            Hole h = harmonica.getHole(i, 2);
            if (h.note.octave != 0)
                System.out.printf("|%3s", h.toString(harmonica.key));
            else System.out.printf("|%3s", "");
        }
        System.out.println("|");

        System.out.println("-----------------------------------------");
        for (int i = 1; i < 11; i++)
        {
            Hole h = harmonica.getHole(i, 1);
            if (h.note.octave != 0)
                System.out.printf("|%3s", h.toString(harmonica.key));
            else System.out.printf("|%3s", "");
        }
        System.out.println("|");

        System.out.println("-----------------------------------------");
        for (int i = 1; i < 11; i++)
            System.out.printf("|%3s", harmonica.getHole(i, 0).toString(harmonica.key));
        System.out.println("|");

        System.out.println("-----------------------------------------");
        for (int i = -1; i > -11; i--)
            System.out.printf("|%3s", harmonica.getHole(i, 0).toString(harmonica.key));
        System.out.println("|");
        System.out.println("-----------------------------------------");

        for (int i = -1; i > -11; i--)
        {
            Hole h = harmonica.getHole(i, 1);
            if (h.note.octave != 0)
                System.out.printf("|%3s", h.toString(harmonica.key));
            else System.out.printf("|%3s", "");
        }
        System.out.println("|");
        System.out.println("-----------------------------------------");

        for (int i = -1; i > -11; i--)
        {
            Hole h = harmonica.getHole(i, 2);
            if (h.note.octave != 0)
                System.out.printf("|%3s", h.toString(harmonica.key));
            else System.out.printf("|%3s", "");
        }
        System.out.println("|");
        System.out.println("-----------------------------------------");

        for (int i = -1; i > -11; i--)
        {
            Hole h = harmonica.getHole(i, 3);
            if (h.note.octave != 0)
                System.out.printf("|%3s", h.toString(harmonica.key));
            else System.out.printf("|%3s", "");
        }
        System.out.println("|");
        System.out.println("-----------------------------------------");

    }
}