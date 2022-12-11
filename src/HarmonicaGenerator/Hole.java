package HarmonicaGenerator;

public class Hole
{
    int hole;
    int bend;
    Note note;

    public Hole(Note note)
    {
        this.note = note;
    }

    public Hole(int hole, int bend, Key key, int steps)
    {
        this.hole = hole;
        this.bend = bend;
        this.note = new Note(key, steps);
    }

    public String toString(Key key)
    {
        return note.toString(key);
    }
}
