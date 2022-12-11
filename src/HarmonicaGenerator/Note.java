package HarmonicaGenerator;

public class Note
{
    int semitone;
    int octave;

    public Note(int semitone, int octave)
    {
        this.semitone = semitone;
        this.octave = octave;
    }

    public Note(Key key, int steps)
    {
        this.semitone = newSemitone(key.baseNote.semitone, steps);
        this.octave = newOctave(key.baseNote.octave, key.baseNote.semitone, steps);
    }

    public String toString(Key key)
    {
        return semitoneToNote(semitone, key) + octave;
    }

    private static String semitoneToNote(int semitone, Key key)
    {
        switch (semitone)
        {
            case 0:
                return "C";

            case 1:
                return key.isSharp ? "C#" : "Db";

            case 2:
                return "D";

            case 3:
                return key.isSharp ? "D#" : "Eb";

            case 4:
                return "E";

            case 5:
                return "F";

            case 6:
                return key.isSharp ? "F#" : "Gb";

            case 7:
                return "G";

            case 8:
                return key.isSharp ? "G#" : "Ab";

            case 9:
                return "A";

            case 10:
                return key.isSharp ? "A#" : "Bb";

            case 11:
                return "B";


            default:
                return "";
        }
    }

    private int newSemitone(int baseSemitone, int steps)
    {
        int newSemitone = (baseSemitone + steps) % 12;

        if (newSemitone < 0)
            return 12 + newSemitone;
        else return newSemitone;

//        return (baseSemitone + steps) % 12;
    }

    private int newOctave(int baseOctave, int baseSemitone, int steps)
    {
        int newSemitone = baseSemitone + steps;
        int newOctave = baseOctave + newSemitone / 12;

        if (newSemitone < 0 && newSemitone > -12)
            newOctave--;

        return newOctave;
    }

}
