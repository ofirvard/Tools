import java.util.Random;

class Leaf
{
    static Random random = new Random();
    int rs, cs, re, ce;
    Leaf leaf1, leaf2;
    Room[] rooms = new Room[0];

    Leaf(int rs, int cs, int re, int ce)
    {
        this.rs = rs;
        this.cs = cs;
        this.re = re;
        this.ce = ce;
    }

    void addRoom(Room room)
    {
        Room[] temp = new Room[rooms.length + 1];
        for (int i = 0; i < rooms.length; i++)
            temp[i] = rooms[i];
        temp[temp.length - 1] = room;
        rooms = temp;
    }

    Room randomRoom()
    {
        if (rooms.length > 0)
            return rooms[random.nextInt(rooms.length)];
        return null;
    }

    void copyChildsRooms()
    {
        for (Room room : leaf1.rooms)
            addRoom(room);

        for (Room room : leaf2.rooms)
            addRoom(room);
    }

    public String toString()
    {
        if (rooms.length == 1)
            return "[" + rs + "," + cs + "], [" + re + "," + ce + "] room:[" + rooms[0].r + "," + rooms[0].c + "]";
        return "[" + rs + "," + cs + "], [" + re + "," + ce + "] rooms: " + rooms.length;
    }
}