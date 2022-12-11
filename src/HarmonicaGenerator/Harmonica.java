package HarmonicaGenerator;

import java.util.ArrayList;
import java.util.List;

public class Harmonica
{
    List<Hole> holes = new ArrayList<>();
    Key key;

    public Harmonica(Key key)
    {
        this.key = key;
    }

    public void addHole(Hole hole)
    {
        holes.add(hole);
    }

    public Hole getHole(int holeNumber, int bend)
    {
        return holes.stream().filter(hole -> hole.hole == holeNumber && hole.bend == bend).findFirst().orElse(new Hole(new Note(0, 0)));
    }
}
