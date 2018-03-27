
public class DAIFgrowthCheck
{
    public static void main(String[] args)
    {
        months(1000, 20, 100);
    }

    public static void months(double pop, int farms, int months)
    {
        double m = foodMade(farms);
        double stored = m;
        double r = foodReq(pop, farms);
        double hr = hungerRate(pop, farms, stored);
        double g = growth(pop, farms, stored);
        stored -= r;
        if (stored < 0) stored = 0;
        System.out.format("%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s%n", "pop", "farms", "stored", "made", "req", "hunger rate", "growth");
        System.out.format("%-15.2f|%-15d|%-15.2f|%-15.2f|%-15.2f|%-15.2f|%-15.2f%n", pop, farms, stored, m, r, hr, g);
        pop += g;


        for (int i = 1; i <= months; i++)
        {
            m = foodMade(farms);
            stored += m;
            r = foodReq(pop, farms);
            hr = hungerRate(pop, farms, stored);
            g = growth(pop, farms, stored);
            stored -= r;
            if (stored < 0) stored = 0;
            System.out.format("%-15.2f|%-15d|%-15.2f|%-15.2f|%-15.2f|%-15.2f|%-15.2f%n", pop, farms, stored, m, r, hr, g);
            pop += g;
        }
    }

    public static double hungerRate(double pop, int farms, double stored)//lower then 0 is bad
    {
        return (stored - foodReq(pop, farms)) / (foodReq(pop, farms));//(stored - req) / req
    }

    public static double foodReq(double pop, int farms)
    {
        return 20 * farms + 2 * pop;//pop need 2, farmers need 3
    }

    public static double foodMade(int farms)
    {
        return 100 * farms;//farmer makes 5, farm has 20 farmers
    }

    public static double growth(double pop, int farms, double stored)
    {
        double h = hungerRate(pop, farms, stored);
        double popGrowth = 0;
        popGrowth += pop / 24;//new birth
        popGrowth -= pop / 360;//old people death
        popGrowth += h * pop;//hunger rate, can increase or decrease
        return popGrowth;
    }
}
