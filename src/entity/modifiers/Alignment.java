package entity.modifiers;

public class Alignment {
	
	/**Value between 0 and 2 representing how "good" an object is. 0 is good, 1 is neutral, 2 is evil.*/
	private int goodBad;
	/**Value between 0 and 2 representing how "chaotic" an object is. 0 is lawful, 1 is true, 2 is chaotic;*/
	private int lawfulChaotic;
	public Alignment(int lawfulChaotic, int goodBad)
	{
		if(lawfulChaotic > 2 || goodBad > 2 || lawfulChaotic < 0 || goodBad < 2)
		{
			throw new IllegalStateException("Alignments cannot have values less than 0 or greater than 2");
		}
		this.goodBad = goodBad;
		this.lawfulChaotic = lawfulChaotic;
	}
	public int returnGoodness()
	{
		return goodBad;
	}
	public int returnLawfulness()
	{
		return lawfulChaotic;
	}
	public int[] returnAlignmentAsArray()
	{
		return new int[] {goodBad, lawfulChaotic};
	}
	@Override
	public String toString()
	{
		String s = "";
		if(lawfulChaotic == 0) s+="lawful";
		else if(lawfulChaotic == 1) s+="true";
		else if(lawfulChaotic == 2) s+="chaotic";
		if(goodBad == 0) s+=" good";
		else if(goodBad == 1) s+=" neutral";
		else if(goodBad == 2) s+=" bad";
		return s;
	}
}
