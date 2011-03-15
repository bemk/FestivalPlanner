import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;


public class Board extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	
	private Thread animator;
	private int dragX;
	private int dragY;
	private boolean canDrag = false;
	private Building buildingSelection;
	private LinkedList<Building> buildings = new LinkedList<Building>();
	private LinkedList<Path> paths = new LinkedList<Path>();
	private LinkedList<Path> stayingPaths = new LinkedList<Path>();
	private Legenda legenda;
	
	
	public Board()
	{
		initSimulator();
		setDoubleBuffered(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		legenda = new Legenda();
	}
	
	public void initSimulator()
	{
		addRoad(48,48);
		addEHBO(100, 100);
		addEHBO(50, 100);
		addSnackBar(200, 300);
		addSnackBar(400, 300);
		addStage(300, 200);
		addStage(300, 80);
		animator = new Thread(this, "1");
		animator.start();
	}
	
	public void drawRaster(Graphics2D g2)
	{
		int x = 0;
	    int y = 0;
	    for(int i = 0; i < 19; i++)
	    {
	    	 y = i;
	    	 Shape s = new Rectangle(x * 24, y * 24, 24, 24);
	    	 g2.draw(s);
	     
	    for(int t = 0; t < 30; t++)
	    {
	    	 x = t;
	    	 Shape z = new Rectangle(x * 24, y * 24, 24, 24);
	    	 g2.draw(z);
	    }
	    }
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		checkSelection();
		g2.setColor(Color.green);
		g2.fillRect(0, 0, 720, 480);
		g2.setColor(Color.black);
		drawRaster(g2);
		for(Building building: buildings)
		{
			drawBuilding(building, g2);
		}
//		for(Path path: paths)
//		{
//			drawPath(path, g2);
//		}
//		for(Path path: stayingPaths)
//		{
//			drawPath(path, g2);
//		}
		g2.dispose();
	}
	
	public void drawPath(Path path, Graphics2D g2)
	{
		g2.drawImage(path.getImageIcon().getImage(), path.getX(), path.getY(), this);
	}
	
	public void addRoad(int x, int y)
	{
		paths.add(new Road(x, y));
	}
	
	public void addStayingRoad(int x, int y)
	{
		stayingPaths.add(new Road(x, y));
	}
	
	public void drawBuilding(Building building, Graphics2D g2)
	{
		g2.drawImage(building.getImageIcon().getImage(), building.getX(), building.getY(), this);
	}
	
	public void addEHBO(int x, int y)
	{
		buildings.add(new EHBO(x, y));
	}
	
	public void addSnackBar(int x, int y)
	{
		buildings.add(new Snackbar(x, y));
	}
	
	public void addStage(int x, int y)
	{
		buildings.add(new StagePicture(x, y));
	}
	
	public void removeBuilding(Building building)
	{
		buildings.remove(building);
	}
	
	public void createPaths(int x, int y)
	{
		if(x%24 == 0 && y%24 == 0)
		{
			addStayingRoad(x, y);
		}
	}
	
	public void run() {		
		while(true)
		{
		repaint();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void checkSelection()
	{
		if(legenda.RightClicked())
		{
		if(legenda.getSelection() == "Selection: EHBO")
		{
			addEHBO(0, 0);
		}
		else if(legenda.getSelection() == "Selection: SnackBar")
		{
			addSnackBar(0, 0);
		}
		else if(legenda.getSelection() == "Selection: Stage")
		{
			addStage(0, 0);
		}
		}		
	}

	public void mouseDragged(MouseEvent e) {
		if (canDrag) {
		for (Building building : buildings)
		{
			if (buildingSelection == building)
			{
				moveDragBuilding(building, e.getX(), e.getY());
				break;
			}
		}
//		createPaths(e.getX(), e.getY());
//		for(Path path: paths)
//		{
//			moveDragPath(path, e.getX(), e.getY());
//		}
		this.repaint();
		}
	}
	
	public void moveDragPath(Path path, int x, int y)
	{
		path.setX(x - dragX);
		path.setY(y - dragY);
	}
	
	public void moveDragBuilding(Building building, int x, int y)
	{
		building.setX(x - dragX);
		building.setY(y - dragY);
	}

	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
       for(Building building: buildings)
       {
		checkDragBuilding(building, e.getX(), e.getY());
       }
//       for(Path path: paths)
//       {
//    	   checkDragPath(path, e.getX(), e.getY());
//       }
	}
	
	public void checkDragBuilding(Building building, int x, int y)
	{
	       if (x >= building.getX() && x <= (building.getX() + building.getWidth())
	                && y >= building.getY() && y <= (building.getY() + building.getHeight())) {
	            canDrag = true;
	            dragX = x - building.getX();  
	            dragY = y - building.getY(); 
	            buildingSelection = building;
	        }
	}
	
	public void checkDragPath(Path path, int x, int y)
	{
	       if (x >= path.getX() && x <= (path.getX() + path.getWidth())
	                && y >= path.getY() && y <= (path.getY() + path.getHeight())) {
	            canDrag = true;
	            dragX = x - path.getX();  
	            dragY = y - path.getY(); 
	        }
	}


	public void mouseReleased(MouseEvent e) {
		canDrag = false;
		for(Building building: buildings)
		{
			building.setX((building.getX()/24)*24);
			building.setY((building.getY()/24)*24);
		}
		repaint();
	}
}
