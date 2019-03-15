import java.util.Arrays;
import java.util.Random;

public class RoomTestRecu
{
	static String BLACK = "\u001B[30m";
	static String RED = "\u001B[31m";
	static String GREEN = "\u001B[32m";
	static String BLUE = "\u001B[34m";
	static String GREY = "\u001B[90m";
	static String YELLOW = "\u001B[93m";
	static int COL = 10, ROW = 10;
	static Random r = new Random();
	
	public static void main(String[] args)
	{
		char[][] rooms = new char[ROW][COL];
		
		for (int i = 0; i < ROW; i++)
		{
			for (int j = 0; j < COL; j++)
			{
				rooms[i][j] = 'E';
			}
		}
		
		rooms[ROW / 2][COL / 2] = 'S';
		print(rooms);
		
		makeRooms(rooms, ROW / 2, COL / 2);
		print(rooms);
	}
	
	public static void makeRooms(char[][] rooms, int row, int col)
	{
		if (rooms[row][col] == 'E' || rooms[row][col] == 'S')
		{
			if (row > 0 && col > 0 && row < ROW - 1 && col < COL - 1)//this means its not on a border
			{
				if (rooms[row + 1][col] != 'E' || rooms[row - 1][col] != 'E' || rooms[row][col + 1] != 'E' || rooms[row][col - 1] != 'E')
					if (r.nextDouble() > 0.5)
						rooms[row][col] = 'R';
				
				makeRooms(rooms, row + 1, col);
				makeRooms(rooms, row - 1, col);
				makeRooms(rooms, row, col + 1);
				makeRooms(rooms, row, col - 1);
			}
		}
	}
	
	public static void print(char[][] arr)
	{
		for (int i = 0; i < ROW; i++)
		{
			for (int j = 0; j < COL; j++)
				switch (arr[i][j])
				{
					case 'E':
						System.out.print(GREY + "E ");
						break;
					
					case 'R':
						System.out.print(BLUE + "R ");
						break;
					
					case 'F':
						System.out.print(RED + "F ");
						break;
					
					case 'S':
						System.out.print(GREEN + "S ");
						break;
				}
			System.out.println();
		}
		System.out.println(BLACK + "----------------------------");
	}
}
