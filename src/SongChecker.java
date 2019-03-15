import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.util.ArrayList;

public class SongChecker
{

    private final static String LOCATION = "D:\\Music";

    public static void main(String[] args)
    {
        File folder1 = new File(LOCATION);

        ArrayList<File> songs;

        songs = getSongsFromFolder(folder1.getAbsolutePath());

        for (File song : songs)
            check(song);
    }

    private static ArrayList<File> getSongsFromFolder(String folderPath)
    {
        File folder1 = new File(folderPath);
        File[] listOfFiles = folder1.listFiles();
        ArrayList<File> songs = new ArrayList<>();

        for (File file : listOfFiles)
            if (file.isFile() && file.getName().contains(".mp3"))
                songs.add(file);

        songs.sort((File o1, File o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        return songs;
    }

    private static void check(File file)
    {
        String name = WordUtils.capitalize(file.getName());
        name = name.replace("–", "-");

        if (name.contains("Official") || name.contains("official"))
            System.out.println(name);

        if (name.contains("  "))
            System.out.println(name);

        if (name.contains("(") || name.contains(")") || !name.contains(" - "))
        {
            System.out.println(name);
            name = name.replace("(", "");
            name = name.replace(")", "");
            System.out.println(name);
        }

        if (name.contains("feat") || name.contains("Feat") || name.contains("feat.") || name.contains("Feat.") || name.contains("ft.") || name.contains("Ft."))
        {
            name = name.replace("feat", "Ft");
            name = name.replace("Feat", "Ft");
            name = name.replace("feat.", "Ft");
            name = name.replace("Feat.", "Ft");
            name = name.replace("ft.", "Ft");
            name = name.replace("Ft.", "Ft");

            System.out.println(name);
        }

        rename(file, name);
    }

    private static void rename(File file, String newName)
    {
        File newFile = new File(LOCATION + "\\" + newName);

        if (file.renameTo(newFile))
            System.out.println("rename worked");
        else
            System.out.println("rename failed");
    }
}
