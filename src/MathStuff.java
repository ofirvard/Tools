import java.util.Random;


public class MathStuff
{
    public static void main(String[] args)
    {
        int[] a = new int[6];

//        Random random = new Random();
//
//        int[][] chars = new int[6][6];
//        print(chars);
    }

    public static char scanFroLargestChar(int row, int col, char[][] arr)
    {
        char temp = arr[0][0];

        for (int i = 0; i < col; i++)
        {
            if (temp < arr[0][i])
                temp = arr[0][i];

            if (temp < arr[row - 1][i])
                temp = arr[row - 1][i];
        }

        for (int i = 0; i < row; i++)
        {
            if (temp < arr[i][0])
                temp = arr[i][0];

            if (temp < arr[i][col - 1])
                temp = arr[i][col - 1];
        }

        return temp;
    }

    public static void print(int[][] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }

    }
}
