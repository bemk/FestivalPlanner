import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.html.HTMLDocument.Iterator;


public class Board extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	private int delay = 100;
	private Thread animator;
	private int dragX;
	private int dragY;
	private boolean canDrag = false;
	private Building buildingSelection;
	private LinkedList<Building> buildings = new LinkedList<Building>();
	private LinkedList<Path> paths = new LinkedList<Path>();
	private LinkedList<Visitor> people = new LinkedList<Visitor>();
	private Legenda legenda;
	private int destinationX;
	private int destinationY;
	private Interface iface;
	private Map bitmap = new Map();
	private Timer timer;
	private boolean paused = false;
	private ArrayList<String> stages;
	
	//Constructor
	public Board(Interface iface)
	{
		this.stages = new ArrayList<String>();
		this.iface = iface;
		initSimulator();
		setDoubleBuffered(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		legenda = new Legenda();
		this.timer = new Timer(delay, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				run();
			}
		});
		this.timer.start();
	}
	
	
	public void speedUp()
	{
		delay -= 20;
		if (delay <= 0)
		{
			delay = 1;
		}
		timer.setDelay(delay);
	}
	
	public void slowDown()
	{
		delay += 20;
		timer.setDelay(delay);
	}
	
	public void pause()
	{
		timer.stop();
		paused = true;
	}
	
	public void go()
	{
		timer.start();
		paused = false;
	}
	
	//Initialize method
	public void initSimulator()
	{
		addRoad(48,48);
		addEHBO(100, 100);
		addEHBO(50, 100);
		addSnackBar(200, 300);
		addSnackBar(400, 300);
//		addStage(300, 200);
//		addStage(300, 80);
		
		stages = iface.getAllStages();
		Random r = new Random();
		for (String s : stages)
		{
			addStage(r.nextInt(24*24), r.nextInt(24*24));
		}
		for(int i = 0; i <= 100; i+=4)
		{
			for(int t = 0; t <=100; t+=4)
			{
		    addVisitor(t, i);
			}
		}
	    for(Person person: people)
	    {
			Random random = new Random();
			if(random.nextInt()%2 == 0)
			{
				person.setAppearance(2);
			}
			else
				person.setAppearance(3);
	    }
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
		for(Person person: people)
		{
			drawPerson(person, g2);
		}
		for(Building building: buildings)
		{
			drawBuilding(building, g2);
		}
		g2.dispose();
	}
	
	
	//Draw methods
	public void drawRaster(Graphics2D g2)
	{
		int x = 0;
	    int y = 0;
	    for(int i = 0; i < 20; i++)
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
		if(person.getAppearance() == 2)
		{
			g2.setColor(Color.blue);
		}
		else
			g2.setColor(Color.pink);
		
		g2.fillRect(person.getX(), person.getY(), 4, 4);
		g2.setColor(null);
	}
	
	
	//Run method
	public void run() {			
		for(Visitor visitor: people)
		{
			movePerson(visitor);
		}
		repaint();		
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
	
	public void movePerson(Visitor visitor)
	{
		Point p = searchNearestDestination(visitor.getDestination(), visitor);
		Point space = getAvailableSpace((int)(p.getX()), (int)(p.getY()));
		int x = (int) (space.getX()/4 * 4);
		int y = (int) (space.getY()/4 * 4);
		visitor.setDestinationPoint(new Point(x,y));
		if(visitor.getTimesTried() == 5 && visitor.getStatus() !="WayPointMade")
		{
			Random r = new Random();
			int i = r.nextInt(4);
			if( i == 0)
			{
			x = visitor.getX() + 8;
			y = visitor.getY() + 8;
			}
			else if ( i == 1)
			{
				x = visitor.getX() - 8;
				y = visitor.getY() - 8;
			}
			else if ( i == 2)
			{
				x = visitor.getX() + 8;
				y = visitor.getY() - 8;
			}
			else if ( i == 3)
			{
				x = visitor.getX() - 8;
				y = visitor.getY() + 8;
			}
			visitor.setStatus("WayPointMade");
		}
		if (visitor.getStatus()!="DestinationReached")
		{
			if (x < visitor.getX() && y < visitor.getY() && bitmap.claim((visitor.getX())-4, (visitor.getY())-4))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("LEFT", 4);
				visitor.act("UP", 4);
			}
			else if (x > visitor.getX() && y < visitor.getY() && bitmap.claim((visitor.getX())+4, (visitor.getY())-4))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("RIGHT", 4);
				visitor.act("UP", 4);
			}
			else if (x < visitor.getX() && y > visitor.getY() && bitmap.claim((visitor.getX())-4, (visitor.getY())+4))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("LEFT", 4);
				visitor.act("DOWN", 4);
			}
			else if (x > visitor.getX() && y > visitor.getY() && bitmap.claim((visitor.getX())+4, (visitor.getY())+4))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("RIGHT", 4);
				visitor.act("DOWN", 4);
			}
			else if (x < visitor.getX() && bitmap.claim((visitor.getX())-4, visitor.getY()))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("LEFT", 4);
			}
			else if (x > visitor.getX() && bitmap.claim(visitor.getX()+4, visitor.getY()))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("RIGHT", 4);
			}
		
			else if (y < visitor.getY() && bitmap.claim(visitor.getX(), visitor.getY()-4))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("UP", 4);
			}
			else if (y>visitor.getY() && bitmap.claim(visitor.getX(), visitor.getY()+4))
			{
				bitmap.free(visitor.getX(), visitor.getY());
				visitor.act("DOWN", 4);
			}
			else if(visitor.getX() == x && visitor.getY() == y)
			{
				visitor.setStatus("DestinationReached");
				visitor.resetTimesTried();
			}

			else
			{
				visitor.increaseTimesTried();
			}

	}

		}
	
	//Methods for behavior
	public void destinationChange(Visitor visitor)
	{
		Random random = new Random();
		if(random.nextInt()%3 == 0)
		{
			visitor.setDestination("EHBO");
		}
		else
		{
			visitor.setDestination("SnackBar");
		}
	}
	
	public void changeDestinationPeople()
	{
		for(Visitor visitor: people)
		{
			destinationChange(visitor);
			visitor.setStatus(null);
		}
	}
	
	public Point searchNearestDestination(String destination, Visitor visitor)
	{
		int x = 1000;
		int y = 1000;
		int difference;
		int difference2;
		int visitorXY = visitor.getX()+visitor.getY();
		for(Building building: buildings)
		{
			int buildingX = building.getX();
			int buildingY = building.getY();
			Point p = new Point();
			if(!getAvailableSpace(buildingX, buildingY).equals(p))
			{
			int buildingXY = building.getX()+building.getY();
			//Calculate differences
			if(visitorXY > buildingXY)
			{
				difference = visitorXY - buildingXY;
			}
			else
			{
				difference = buildingXY - visitorXY;
			}
			if(visitorXY > x+y)
			{
				difference2 = visitorXY - x+y;
			}
			else
			{
				difference2 = x+y - visitorXY;
			}
			//Destination chooser
			if(destination == "EHBO" && building instanceof EHBO)
			{
				if(difference < difference2)
				{
					x = building.getX();
					y = building.getY();
				}
			}
			if(destination == "SnackBar" && building instanceof Snackbar)
			{
				if(difference < difference2)
				{
					x = building.getX();
					y = building.getY();
				}
			}
			}
		}
		return new Point(x,y);
	}
	
	public Point getAvailableSpace(int x, int y)
	{
		Point space = new Point();
		boolean breaker = false;
		for(int i = x; i <= x+24; i+=4)
		{
			for(int t = y; t <= y+24; t+=4)
			{
				if(bitmap.check(i, t))
				{
					space.setLocation(i, t);
					breaker = true;
					break;
				}
				else
				{
					
				}
			}
			if(breaker)
			{
				break;
			}
		}
		return space;
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

	
	//mouseClick method
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON2)
		{
			if(!paused)
			{
				pause();
			}
			else {
				go();
			}
		}
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			for(Building building: buildings)
			{
				if(building.getX() == e.getX()/24*24 && building.getY() == e.getY()/24*24)
				{
					destinationX = building.getX();
					destinationY = building.getY();
					break;
				}
			}
		}
		if(e.getButton() == MouseEvent.BUTTON1)	
		{
			changeDestinationPeople();
		}
	}
	
	 //Not used
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() < 0)
		{
			if(!paused)
			slowDown();
		}
		else if(e.getWheelRotation() > 0)
		{
			if(!paused)
			speedUp();
		}
	}
	

	
	



}
