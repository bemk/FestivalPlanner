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
import java.util.Random;

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
	private LinkedList<Person> people = new LinkedList<Person>();
	private Legenda legenda;
	private int mouseX;
	private int mouseY;
	
	
	//Constructor
	public Board()
	{
		initSimulator();
		setDoubleBuffered(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		legenda = new Legenda();
	}
	
	
	//Initialize method
	public void initSimulator()
	{
		addRoad(48,48);
		addEHBO(100, 100);
		addEHBO(50, 100);
		addSnackBar(200, 300);
		addSnackBar(400, 300);
		addStage(300, 200);
		addStage(300, 80);
		for(int i = 0; i <= 160; i+=4)
		{
			for(int t = 0; t <=100; t+=4)
			{
			Random random = new Random();
			if(random.nextInt()%2 == 0)
			{
		    addVisitor(t, i);
			}
			else
			addVisitor(t, i);
			}
		}
	    for(Person person: people)
	    {
			Random random = new Random();
			if(random.nextInt()%2 == 0)
			{
				person.setStatus(2);
			}
			else
				person.setStatus(3);
	    }
		addVisitor(500, 100);
		addVisitor(488, 100);
		animator = new Thread(this, "1");
		animator.start();
	}
	
	
	// List adds
	public void addRoad(int x, int y)
	{
		paths.add(new Road(x, y));
	}
	
	public void addVisitor(int x, int y)
	{
		people.add(new Visitor(x, y));
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
	
	
	//List removes
	public void removeRoad(int x, int y)
	{
		paths.remove(new Road(x, y));
	}
	
	public void removeVisitor(int x, int y)
	{
		people.remove(new Visitor(x, y));
	}
	
	public void removeEHBO(int x, int y)
	{
		buildings.remove(new EHBO(x, y));
	}
	
	public void removeSnackBar(int x, int y)
	{
		buildings.remove(new Snackbar(x, y));
	}
	
	public void removeStage(int x, int y)
	{
		buildings.remove(new StagePicture(x, y));
	}
	
	
	//Paint method
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
		for(Person person: people)
		{
			drawPerson(person, g2);
		}
		g2.dispose();
	}
	
	
	//Draw methods
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
	
	public void drawPath(Path path, Graphics2D g2)
	{
		g2.drawImage(path.getImageIcon().getImage(), path.getX(), path.getY(), this);
	}
	
	public void drawBuilding(Building building, Graphics2D g2)
	{
		g2.drawImage(building.getImageIcon().getImage(), building.getX(), building.getY(), this);
	}
	
	public void drawPerson(Person person, Graphics2D g2)
	{
		g2.drawImage(person.getImageIcons().get(person.getStatus()).getImage(), person.getX(), person.getY(), this);
	}
	
	
	//Run method
	public void run() {		
		while(true)
		{
		for(Person person: people)
		{
			movePerson(person);
		}
		repaint();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	//Object checks
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
	
	
	//Movement methods
	public void moveDragBuilding(Building building, int x, int y)
	{
		building.setX(x - dragX);
		building.setY(y - dragY);
	}
	
	public void movePerson(Person person)
	{
		int x = mouseX/4 * 4;
		int y = mouseY/4 * 4;
		Person otherPersonLeft = new Visitor(person.getX()-4, person.getY());
		Person otherPersonRight = new Visitor(person.getX()+4, person.getY());
		Person otherPersonUp = new Visitor(person.getX(), person.getY()-4);
		Person otherPersonDown = new Visitor(person.getX(), person.getY()+4);
		if(x < person.getX() && (!people.contains(otherPersonLeft)))
		{
			person.act("LEFT", 4);
		}
		else if(x > person.getX() && (!people.contains(otherPersonRight)))
		{
			person.act("RIGHT", 4);
		}
		if(y < person.getY() && (!people.contains(otherPersonUp)))
		{
			person.act("UP", 4);
		}
		else if(y > person.getY() && (!people.contains(otherPersonDown)))
		{
			person.act("DOWN", 4);
		}
	}

	//mouseDrag method
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
		}
		}
	
	
	//mousePress method
	public void mousePressed(MouseEvent e) {
	       for(Building building: buildings)
	       {
			checkDragBuilding(building, e.getX(), e.getY());
	       }
		}
	
	
	//mouseRelease method
	public void mouseReleased(MouseEvent e) {
		canDrag = false;
		for(Building building: buildings)
		{
			building.setX((building.getX()/24)*24);
			building.setY((building.getY()/24)*24);
		}
		repaint();
	}

	
    //Not used
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	

	
	



}
