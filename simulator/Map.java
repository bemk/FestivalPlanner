import java.util.*;

public class Map {
	private ArrayList<ArrayList<Block>> map = new ArrayList<ArrayList<Block>>();
	static final int height = 20;
	static final int width = 30;
	static final int block = 6;
	public Map()
	{
		int x, y;
		for (x=0; x < width; x++)
		{
			ArrayList<Block> line = new ArrayList<Block>();
			map.add(line);
			for(y=0; y<height; y++)
			{
				line.add(new Block());
			}
		}
	}
	
	public boolean claim(int x, int y)
	{
		x /= 4;
		y /= 4;
		if (x/block >= width || y/block >= height)
		{
			return false;
		}
		else if (x < 0 || y<0)
		{
			return false;
		}
		Block toGo = map.get(x/block).get(y/block);
		if (toGo.claim(x%block, y%block))
		{
			return true;
		}
		return false;
	}

	public boolean free(int x, int y)
	{
		x /=4;
		y /=4;
		if (x/block >= width || y/block >= height)
		{
			return false;
		}
		else if (x < 0 || y<0)
		{
			return false;
		}
		Block toFree = map.get(x/block).get(y/block);
		if (toFree.free(x%block, y%block))
		{
			return true;
		}
		return false;
	}
	
	public boolean check(int x, int y)
	{
		x /= 4;
		y /= 4;
		if (x/block >= width || y/block >= height)
		{
			return false;
		}
		else if (x < 0 || y<0)
		{
			return false;
		}
		Block toGo = map.get(x/block).get(y/block);
		if (toGo.check(x%block, y%block))
		{
			return true;
		}
		return false;
	}
}
