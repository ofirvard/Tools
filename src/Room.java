public class Room
{
    int r;
    int c;
    char type;

    Room(int row, int col, char type)
    {
        this.c = col;
        this.r = row;
        this.type = type;
    }

    public String toString()
    {
        return r + "," + c + "," + type;
    }
}
