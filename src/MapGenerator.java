import java.util.Random;

public class MapGenerator
{
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String GREY = "\u001B[90m";

    private static char color = 'a';
    private static final int COL = 10;
    private static final int ROW = 10;
    private static final int DIVIDE_SIZE_LIMIT = 6;//this will allow buffer of two space when choosing divider, 01|23|45
    private static final int MAP_DIVIDE = 5;//it will attempt to make 2^MAP_DIVIDE rooms
    private static int NUMBER_OF_ROOMS = 1;
    private static Random random = new Random();
    private static String[][] map = new String[ROW][COL];

    public static void main(String[] args)
    {
    }
}
