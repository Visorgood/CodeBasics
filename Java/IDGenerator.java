public class IDGenerator
{
	private int id;
	
	public void IDGenerator(int initValue)
	{
		id = initValue;
	}
	
	public void init(int initValue)
	{
		id = initValue;
	}
	
	public int getNextId()
	{
		return id++;
	}
}
