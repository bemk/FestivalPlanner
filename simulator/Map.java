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
		if (x/width > width || y/height > height)
		{
			return false;
		}
		Block toGo = map.get(x/width).get(y/height);
		if (toGo.claim(x%block, y%block))
		{
			return true;
		}
		return false;
	}

	public boolean free(int x, int y)
	{
		if (x/width > width || y/height > height)
		{
			return false;
		}
		Block toFree = map.get(x/width).get(y/height);
		if (toFree.free(x%block, y%block))
		{
			return true;
		}
		return false;
	}
}
