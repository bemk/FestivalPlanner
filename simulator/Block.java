public class Block {
	private final static int height = 6;
	private final static int width = 6;
	private boolean point[][] = new boolean[width][height];
	private boolean allowed = true;
	public Block()
	{
		for (int i = 0; i < width*height; i++)
		{
			point[i/width][i%width] = false;
		}
	}
	
	public boolean claim(int x, int y)
	{
		if (allowed == false)
		{
			return false;
		}
		if (point[x][y] == false)
		{
			point [x][y] = true;
			return true;
		}
		return false;
	}
	public boolean free(int x, int y)
	{
		if (point [x][y] == true)
		{
			point [x][y] = false;
			return true;
		}
		return false;
	}
	
	public boolean check(int x, int y)
	{
		if (allowed == false)
		{
			return false;
		}
		if (point[x][y] == false)
		{
			return true;
		}
		return false;
	}
}
