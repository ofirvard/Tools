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
        Leaf tree = new Leaf(0, 0, ROW - 1, COL - 1);
        dividerLeafVertical(tree, MAP_DIVIDE);
        colorMap(map, tree);
        print(map);

        char[][] map2 = new char[ROW][COL];
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                map2[i][j] = 'E';

        Room[] rooms = new Room[NUMBER_OF_ROOMS];
        setRooms(rooms, tree);
        colorMap(map2, rooms);
        //		print(map2);

        connectRoomsByLeaf(map2, tree);
        print(map2);
    }

    private static void cleanMap(char[][] map)//this removes C corridors in special situations, C = corridors, E = empty, N = not empty, D = don't care
    {
        //remove C in corner that leads nowhere
        // D  N  N  ||  D  E  D  ||  D  E  N  ||  N  N  D
        // E 'C' N  ||  E 'C' N  ||  N 'C' E  ||  N 'C' E
        // D  E  D  ||  D  N  N  ||  D  N  D  ||  D  E  D
        for (int row = 1; row < ROW - 1; row++)
        {
            for (int col = 1; col < COL - 1; col++)
            {
                if (map[row][col] == 'C')
                    if (map[row - 1][col] != 'E' && map[row][col + 1] != 'E' && map[row - 1][col + 1] != 'E' && map[row + 1][col] == 'E' && map[row][col - 1] == 'E')
                        map[row][col] = 'E';
            }
        }
    }

    private static void connectRoomsByLeaf(char[][] map, Leaf leaf)
    {
        if (leaf.leaf1 != null && leaf.leaf2 != null)//only if you have kids
        {
            Leaf leaf1 = leaf.leaf1;
            Leaf leaf2 = leaf.leaf2;

            connectRoomsByLeaf(map, leaf.leaf1);
            connectRoomsByLeaf(map, leaf.leaf2);

            leaf.copyChildsRooms();
            //todo add random for opposite leaf2 -> leaf1
            connectTwoRooms(map, leaf1.randomRoom(), leaf2.randomRoom(), leaf1, leaf);
            //			print(map);
        }
    }

    private static void connectTwoRooms(char[][] map, Room start, Room destination, Leaf bounds, Leaf leaf)
    {
        int row = start.r;
        int col = start.c;
        int rowModifier;
        int colModifier;
        boolean connected = false;//if you meet another path

        while (!connected)
        {
            if (row != destination.r && col != destination.c)//need to go in two directions, (up or down) and (right or left)
            {
                if (row < destination.r)//if above room2
                    rowModifier = 1;//move down
                else//or bellow
                    rowModifier = -1;//move up

                if (col < destination.c)//if left of room2
                    colModifier = 1;//move right
                else//or right
                    colModifier = -1;//move left

                if (random.nextBoolean())//randomly change row or col
                    row += rowModifier;
                else
                    col += colModifier;
            }
            else if (row == destination.r)//need to go left or right
                if (col < destination.c)//if left of room2
                    col++;//move right
                else//or right
                    col--;//move left

            else //if (col == destination.c)//need to go up or down
                if (row < destination.r)//if above room2
                    row++;//move down
                else//or bellow
                    row--;//move up

            if (map[row][col] != 'E')//check if now connected
            {
                if ((row < bounds.rs || row > bounds.re) || (col < bounds.cs || col > bounds.ce))//checks if you are outside of your leaf
                    connected = true;
                else
                    leaf.addRoom(new Room(row, col, map[row][col]));
            }
            else//color tile
            {
                leaf.addRoom(new Room(row, col, 'C'));
                map[row][col] = 'C';
            }
        }
    }

    private static void setRooms(Room[] rooms, Leaf leaf)
    {
        if (leaf.leaf1 != null && leaf.leaf2 != null)
        {
            setRooms(rooms, leaf.leaf1);
            setRooms(rooms, leaf.leaf2);
        }
        else
        {
            for (int i = 0; i < NUMBER_OF_ROOMS; i++)
            {
                if (rooms[i] == null)
                {
                    Room room = new Room(random.nextInt(leaf.re + 1 - leaf.rs) + leaf.rs, random.nextInt(leaf.ce + 1 - leaf.cs) + leaf.cs, 'R');
                    leaf.addRoom(room);
                    rooms[i] = room;
                    break;
                }
            }
        }
    }

    private static void dividerLeafVertical(Leaf leaf, int count)
    {
        if (leaf.ce - leaf.cs + 1 >= DIVIDE_SIZE_LIMIT)
        {
            NUMBER_OF_ROOMS++;
            int divider = getDivider(leaf.cs, leaf.ce);

            leaf.leaf1 = new Leaf(leaf.rs, leaf.cs, leaf.re, divider - 1);//left
            leaf.leaf2 = new Leaf(leaf.rs, divider, leaf.re, leaf.ce);//right
            //		colorMap(map, leaf);
            //		print(map);
            count--;
            if (count > 0)
            {
                dividerLeafHorizontal(leaf.leaf1, count);
                dividerLeafHorizontal(leaf.leaf2, count);
            }
        }
    }

    private static void dividerLeafHorizontal(Leaf leaf, int count)
    {
        if (leaf.re - leaf.rs + 1 >= DIVIDE_SIZE_LIMIT)
        {
            NUMBER_OF_ROOMS++;
            int divider = getDivider(leaf.rs, leaf.re);

            leaf.leaf1 = new Leaf(leaf.rs, leaf.cs, divider - 1, leaf.ce);//top
            leaf.leaf2 = new Leaf(divider, leaf.cs, leaf.re, leaf.ce);//right
            //		colorMap(map, leaf);
            //		print(map);
            count--;
            if (count > 0)
            {
                dividerLeafVertical(leaf.leaf1, count);
                dividerLeafVertical(leaf.leaf2, count);
            }
        }
    }

    private static int getDivider(int left, int right)
    {
        int max = (int) (Math.floor(right + 1 - (double) (right - left + 1) / 4));//always rounds down
        int min = (int) (Math.ceil(left + (double) (right - left + 1) / 4));//always rounds up
        int divider = random.nextInt(max - min) + min;
        if (divider > 1)
            return divider;
        else
            return 2;
    }

    private static void colorMap(String[][] map, Leaf leaf)
    {
        if (leaf.leaf1 != null && leaf.leaf2 != null)
        {
            colorMap(map, leaf.leaf1);
            colorMap(map, leaf.leaf2);
        }
        else
        {
            //color body
            for (int row = leaf.rs + 1; row < leaf.re; row++)
            {
                for (int col = leaf.cs + 1; col < leaf.ce; col++)
                {
                    map[row][col] = color + "";
                }
            }

            //color sides
            for (int row = leaf.rs; row <= leaf.re; row++)
            {
                try
                {
                    map[row][leaf.cs] = "|";
                    map[row][leaf.ce] = "|";
                } catch (Exception e)
                {
                    e.printStackTrace();
                    System.out.print(RESET + "row: " + row + " leaf.re: " + leaf.re);
                }
            }

            //color top and bottom
            for (int col = leaf.cs; col <= leaf.ce; col++)
            {
                map[leaf.re][col] = "-";
                map[leaf.rs][col] = "-";
            }

            //color edges
            map[leaf.rs][leaf.cs] = "1";
            map[leaf.rs][leaf.ce] = "2";
            map[leaf.re][leaf.cs] = "4";
            map[leaf.re][leaf.ce] = "3";

            color++;
        }
    }

    private static void colorMap(char[][] map, Room[] rooms)
    {
        for (Room room : rooms)
            map[room.r][room.c] = room.type;
    }

    private static void print(String[][] map)
    {
        for (String[] strings : map)
        {
            for (String s : strings)
                if (s == "-" || s == "+" || s == "|")
                    System.out.printf(BLUE + "%-3s", s);
                else if (s == "1" || s == "2" || s == "3" || s == "4")

                    System.out.printf(PURPLE + "%-3s", s);
                else
                    System.out.printf(RED + "%-3s", s);
            System.out.println();
        }
        System.out.println(GREEN + "======================================================================================================================");
        System.out.println(GREEN + "======================================================================================================================");
    }

    private static void print(char[][] map)
    {
        for (char[] rows : map)
        {
            for (char c : rows)
                switch (c)
                {
                    case 'E'://empty
                        System.out.print(GREY + "E  ");
                        break;

                    case 'S'://start
                        System.out.print(GREEN + "S  ");
                        break;

                    case 'F'://finish/exit
                        System.out.print(RED + "F  ");
                        break;

                    case 'R'://room
                        System.out.print(BLUE + "R  ");
                        break;

                    case 'C'://corridor
                        System.out.print(YELLOW + "C  ");
                        break;

                    case 'T'://corridor
                        System.out.print(CYAN + "T  ");
                        break;

                    case 'M'://corridor
                        System.out.print(PURPLE + "M  ");
                        break;
                }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
