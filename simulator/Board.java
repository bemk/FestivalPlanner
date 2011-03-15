import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JPanel;


public class Board extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	
	private Thread animator;
	private EHBO ehbo;
	private Snackbar snackBar;
	private StagePicture stage;
	private int dragX;
	private int dragY;
	private boolean canDrag = false;
	private Building buildingSelection;
	private LinkedList<Building> buildings = new LinkedList<Building>();

	
	public Board()
	{
		initSimulator();
		setDoubleBuffered(true);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void initSimulator()
	{
		addEHBO(100, 100);
		addEHBO(50, 100);
		addSnackBar(200, 300);
		addSnackBar(400, 300);
		addStage(300, 200);
		addStage(300, 80);
		animator = new Thread();
		animator.start();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.green);
		g2.fillRect(0, 0, 640, 480);
		g2.setColor(null);
		for(Building building: buildings)
		{
			drawBuilding(building, g2);
		}
		g2.dispose();
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
	
	public void run() {		
		repaint();	
	}

	public void mouseDragged(MouseEvent e) {
		if (canDrag) {
		for (Building b : buildings)
		{
			if (buildingSelection == b)
			{
				moveDragBuilding(b, e.getX(), e.getY());
				break;
			}
		}
		this.repaint();
		}
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

	public void mouseReleased(MouseEvent e) {
		canDrag = false;
	}
}
