import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map2Generator
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

    private Random random = new Random();
    private int[][] map = new int[40][40];
    private String[] colorsForCountries = {RED, GREEN, YELLOW, PURPLE};
    private Country[] countries;

    public int[][] makeMap()
    {
        setCountries(60);
        setWater(10);
        printMap(map);

        while (!done())
        {
            waterGrowth();
            waterGrowth();
            landGrowth();
//            printMap(map);
        }

        printMap(map);
        setBorders();
        setColors();
        printMap(map);

        return map;
    }

    private void setCountries(int num)
    {
        countries = new Country[num];
        for (int i = 0; i < num; i++)
        {
            countries[i] = new Country(i + 2);
        }

        chooseLands(num);
    }

    private void setWater(int num)
    {
        for (int i = 0; i < map.length; i++)
        {
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }

        for (int i = 0; i < map[0].length; i++)
        {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }

        chooseWater(num);
    }

    private void chooseLands(int num)
    {
        for (int land = 2; land < num + 2; land++)
            while (true)
            {
                Tuple tuple = getRandomLandTuple();
                if (map[tuple.row][tuple.col] == 0)
                {
                    map[tuple.row][tuple.col] = land;
                    break;
                }
            }
    }

    private Tuple getRandomTuple()
    {
        int row = random.nextInt(map.length);
        int col = random.nextInt(map[0].length);

        return new Tuple(row, col);
    }

    private Tuple getRandomLandTuple()
    {
        int row = random.nextInt(map.length - 10) + 5;
        int col = random.nextInt(map[0].length - 10) + 5;

        return new Tuple(row, col);
    }

    private void chooseWater(int num)
    {
        for (int water = 0; water < num; water++)
        {
            while (true)
            {
                Tuple tuple = getRandomTuple();
                if (map[tuple.row][tuple.col] == 0)
                {
                    map[tuple.row][tuple.col] = 1;
                    break;
                }
            }
        }
    }

    private Tuple getRandomBorder(int borders)
    {
        int row, col;
        int a = random.nextInt(borders);
        boolean coin = random.nextBoolean();
        if (a < map.length)
        {
            row = a;
            if (coin)
                col = 0;
            else
                col = map[0].length - 1;
        } else
        {
            col = a - map.length;
            if (coin)
                row = 0;
            else
                row = map.length - 1;
        }

        return new Tuple(row, col);
    }

    private void landGrowth()
    {

        int[][] map2 = copy(map);
        for (int row = 0; row < map.length; row++)
        {
            for (int col = 0; col < map[0].length; col++)
            {
                if (map[row][col] == 0)
                {
                    List<Integer> borders = new ArrayList<>();
                    if (row > 0 && map[row - 1][col] != 0 && map[row - 1][col] != 1)
                        borders.add(map[row - 1][col]);

                    if (row < map.length - 1 && map[row + 1][col] != 0 && map[row + 1][col] != 1)
                        borders.add(map[row + 1][col]);

                    if (col > 0 && map[row][col - 1] != 0 && map[row][col - 1] != 1)
                        borders.add(map[row][col - 1]);

                    if (col < map[0].length - 1 && map[row][col + 1] != 0 && map[row][col + 1] != 1)
                        borders.add(map[row][col + 1]);

                    if (!borders.isEmpty())
                        map2[row][col] = borders.get(random.nextInt(borders.size()));
                }
            }
        }

        map = map2;
    }

    private void waterGrowth()
    {
        int[][] map2 = copy(map);
        for (int row = 0; row < map.length; row++)
        {
            for (int col = 0; col < map[0].length; col++)
            {
                if (map[row][col] == 0)
                {
                    boolean isWater = false;
                    if (row > 0 && map[row - 1][col] == 1)
                        isWater = true;

                    if (isWater || (row < map.length - 1 && map[row + 1][col] == 1))
                        isWater = true;

                    if (isWater || (col > 0 && map[row][col - 1] == 1))
                        isWater = true;

                    if (isWater || (col < map[0].length - 1 && map[row][col + 1] == 1))
                        isWater = true;

                    if (isWater)
                        map2[row][col] = 1;
                }
            }
        }

        map = map2;
    }

    private boolean done()
    {
        for (int row = 0; row < map.length; row++)
            for (int col = 0; col < map[0].length; col++)
                if (map[row][col] == 0)
                    return false;
        return true;
    }

    private void printMap(int[][] map)
    {
        for (int row = 0; row < map.length; row++)
        {
            for (int col = 0; col < map[0].length; col++)
            {
                int i = map[row][col];
                switch (i)
                {
                    case 0:
                        System.out.printf(GREY + "%-3d", i);
                        break;

                    case 1:
                        System.out.printf(BLUE + "%-3d", i);
                        break;

                    default:
                        System.out.printf(countries[i - 2].color + "%-3d", i);
//                        System.out.printf(PURPLE + "%-3d", i);
                }
            }
            System.out.println();
        }

        System.out.printf(RESET + "%s", "===============================================================\n");
        System.out.printf(RESET + "%s", "===============================================================\n");
    }

    private int[][] copy(int[][] arr)
    {
        int[][] copy = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                copy[i][j] = arr[i][j];

        return copy;
    }

    private void setBorders()
    {
        for (int row = 0; row < map.length; row++)
        {
            for (int col = 0; col < map[0].length; col++)
            {
                if (map[row][col] != 1)
                {
                    if (row > 0 && map[row - 1][col] != 1 && map[row - 1][col] != map[row][col])
                        countries[map[row][col] - 2].addNeighbour(countries[map[row - 1][col] - 2]);

                    if (row < map.length - 1 && map[row + 1][col] != 1 && map[row + 1][col] != map[row][col])
                        countries[map[row][col] - 2].addNeighbour(countries[map[row + 1][col] - 2]);

                    if (col > 0 && map[row][col - 1] != 1 && map[row][col - 1] != map[row][col])
                        countries[map[row][col] - 2].addNeighbour(countries[map[row][col - 1] - 2]);

                    if (col < map[0].length - 1 && map[row][col + 1] != 1 && map[row][col + 1] != map[row][col])
                        countries[map[row][col] - 2].addNeighbour(countries[map[row][col + 1] - 2]);
                }
            }
        }
    }

    private void setColors()
    {
        for (Country country : countries)
            country.chooseColor();
    }

    private class Tuple
    {
        int row;
        int col;

        public Tuple(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        public int row()
        {
            return row;
        }

        public int col()
        {
            return col;
        }
    }

    private class Country
    {
        private int id;
        private String color = GREEN;
        private List<Country> neighbours = new ArrayList<>();

        public Country(int id)
        {
            this.id = id;
        }

        public void addNeighbour(Country country)
        {
            if (!neighbours.contains(country))
                neighbours.add(country);
        }

        public void chooseColor()
        {
            for (String color : colorsForCountries)
            {
                boolean check = true;
                for (Country neighbour : neighbours)
                {
                    if (neighbour.getColor().equals(color))
                    {
                        check = false;
                        break;
                    }
                }

                if (check)
                {
                    this.color = color;
                    return;
                }
            }
//            color = BLACK;
        }

        public int getId()
        {
            return id;
        }

        public String getColor()
        {
            return color;
        }

        @Override
        public String toString()
        {
            return "Country{" +
                   "id=" + id +
                   ", color='" + color + '\'' +
                   '}';
        }
    }
}