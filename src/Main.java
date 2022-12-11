import HarmonicaGenerator.RichterTuningGenerator;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        RichterTuningGenerator richterTuningGenerator = new RichterTuningGenerator();

        try
        {
            richterTuningGenerator.run();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void getPlaylists()
    {
        SpotifiyPlaylistChecker playlistChecker = new SpotifiyPlaylistChecker();

        System.out.println("hello world");
    }

    private static void makeMap()
    {
        Map2Generator map2Generator = new Map2Generator();
        map2Generator.makeMap();
    }
}
