import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class SongFolderCompare
{
    public static void main(String[] args)
    {
        File folder1 = new File("D:\\Music");
        File folder2 = new File("D:\\Downloads\\Music");

        ArrayList<String> songs1, songs2;

        songs1 = getSongsFromFolder(folder1.getAbsolutePath());
        songs2 = getSongsFromFolder(folder2.getAbsolutePath());

        Iterator<String> iter = songs1.iterator();

        while (iter.hasNext())
        {
            String str = iter.next();

            if (songs2.remove(str))
                iter.remove();
        }


        print(folder1.getAbsolutePath(), songs1);
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        print(folder2.getAbsolutePath(), songs2);
    }

    public static ArrayList<String> getSongsFromFolder(String folderPath)
    {
        File folder1 = new File(folderPath);
        File[] listOfFiles = folder1.listFiles();
        ArrayList<String> songs = new ArrayList<>();

        for (File file : listOfFiles)
            if (file.isFile() && file.getName().contains(".mp3"))
                songs.add(file.getName());

        songs.sort(String::compareToIgnoreCase);

        return songs;
    }

    public static void print(String folder, ArrayList<String> songs)
    {
        System.out.println(folder);
        for (String song : songs)
            System.out.println(song);
    }
}
