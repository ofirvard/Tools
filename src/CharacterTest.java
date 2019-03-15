import java.text.Format;
import java.util.ArrayList;
import java.util.Random;

public class CharacterTest
{
	static String[] statNames = {"health"
			, "aggro"
			, "strength"
			, "intellect"
			, "wisdom"
			, "dexterity"
			, "agility"};
	
	public static void main(String[] args)
	{
		int[] paladin =   {3, 3, 3, 3, 3, 1, 1};
		int[] mercenary = {4, 3, 5, 1, 1, 2, 1};
		int[] wizard =    {1, 1, 1, 5, 4, 3, 2};

//		avgStats(paladin, 100, "paladin");
//		System.out.println("====================================");
//		avgStats(mercenary, 100, "mercenary");
//		System.out.println("====================================");
//		avgStats(wizard, 100, "wizard");
		
		ArrayList<int[]> base = new ArrayList<>();
		base.add(paladin);
		base.add(mercenary);
		base.add(wizard);
		compareClassAvgStats(base, 100);
	}
	
	static void compareClassAvgStats(ArrayList<int[]> base, int amount)
	{
		ArrayList<int[]> avgs = new ArrayList<>();
		
		for (int[] stats : base)
		{
			int[] avg = new int[7];
			
			ArrayList<Stat[]> heroes = new ArrayList<>();
			for (int i = 0; i < amount; i++)
				heroes.add(character(stats));
			
			for (Stat[] hero : heroes)
				for (int i = 0; i < 7; i++)
					avg[i] += hero[i].value;
			
			for (int i = 0; i < 7; i++)
				avg[i] /= amount;
			
			avgs.add(avg);
		}
		
		for (int i = 0; i < 7; i++)
		{
			System.out.format("%-10s| paladin   |%" + avgs.get(0)[i] + "d%n", statNames[i], avgs.get(0)[i]);
			System.out.format("%-10s| mercenary |%" + avgs.get(1)[i] + "d%n", statNames[i], avgs.get(1)[i]);
			System.out.format("%-10s| wizard    |%" + avgs.get(2)[i] + "d%n%n", statNames[i], avgs.get(2)[i]);
		}
	}
	
	static void avgStats(int[] base, int amount, String name)
	{
		ArrayList<Stat[]> heroes = new ArrayList<>();
		for (int i = 0; i < amount; i++)
			heroes.add(character(base));
		
		int[] avgs = new int[7];
		
		for (Stat[] hero : heroes)
			for (int i = 0; i < 7; i++)
				avgs[i] += hero[i].value;
		
		for (int i = 0; i < 7; i++)
			avgs[i] /= amount;
		
		System.out.println(name + " " + amount);
		for (int i = 0; i < 7; i++)
		{
			System.out.format("%-10s| base|%" + (base[i] + 2) + "d%n", statNames[i], base[i] + 2);
			System.out.format("%-10s| avg |%" + avgs[i] + "d%n%n", statNames[i], avgs[i]);
		}
	}
	
	static void print(ArrayList<Stat[]> characters)
	{
		String health = String.format("%-10s|", "health");
		String aggro = String.format("%-10s|", "aggro");
		String strength = String.format("%-10s|", "strength");
		String intellect = String.format("%-10s|", "intellect");
		String wisdom = String.format("%-10s|", "wisdom");
		String dexterity = String.format("%-10s|", "dexterity");
		String agility = String.format("%-10s|", "agility");
		
		for (Stat[] stats : characters)
		{
			health = String.format("%s%3d   |", health, stats[0].value);
			aggro = String.format("%s%3d   |", aggro, stats[1].value);
			strength = String.format("%s%3d   |", strength, stats[2].value);
			intellect = String.format("%s%3d   |", intellect, stats[3].value);
			wisdom = String.format("%s%3d   |", wisdom, stats[4].value);
			dexterity = String.format("%s%3d   |", dexterity, stats[5].value);
			agility = String.format("%s%3d   |", agility, stats[6].value);
		}
		
		int avg = avg(characters, 0);
		System.out.println(health + "|" + String.format("%" + avg + "d", avg));
		avg = avg(characters, 1);
		System.out.println(aggro + "|" + String.format("%" + avg + "d", avg));
		avg = avg(characters, 2);
		System.out.println(strength + "|" + String.format("%" + avg + "d", avg));
		avg = avg(characters, 3);
		System.out.println(intellect + "|" + String.format("%" + avg + "d", avg));
		avg = avg(characters, 4);
		System.out.println(wisdom + "|" + String.format("%" + avg + "d", avg));
		avg = avg(characters, 5);
		System.out.println(dexterity + "|" + String.format("%" + avg + "d", avg));
		avg = avg(characters, 6);
		System.out.println(agility + "|" + String.format("%" + avg + "d", avg));
	}
	
	static int avg(ArrayList<Stat[]> characters, int i)
	{
		int avg = 0;
		for (Stat[] stats : characters)
			avg += stats[i].value;
		return avg / characters.size();
	}
	
	static Stat[] character(int[] arr)
	{
		Random random = new Random();
//		Stat[] stats = {new Stat("health", arr[0]),
//				new Stat("aggro", arr[1]),
//				new Stat("strength", arr[2]),
//				new Stat("intellect", arr[3]),
//				new Stat("wisdom", arr[4]),
//				new Stat("dexterity", arr[5]),
//				new Stat("agility", arr[6])};
		Stat[] stats = {new Stat(arr[0]),
				new Stat(arr[1]),
				new Stat(arr[2]),
				new Stat(arr[3]),
				new Stat(arr[4]),
				new Stat(arr[5]),
				new Stat(arr[6])};
		
		ArrayList<Stat> temp = new ArrayList<>();
		for (Stat stat : stats)
			for (int i = 0; i < stat.weight; i++)
				temp.add(stat);
		
		for (int i = 0; i < 15; i++)
			temp.get(random.nextInt(temp.size())).value++;
		
		return stats;
	}
}

class Stat
{
	int value = 3;
	int weight;
//	String name;

//	Stat(String name, int weight)
//	{
//		this.name = name;
//		this.weight = weight;
//	}
	
	public Stat(int weight)
	{
		this.weight = weight;
	}
}