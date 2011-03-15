import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

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

	
	public Board()
	{
		initSimulator();
		setDoubleBuffered(true);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void initSimulator()
	{
		ehbo = new EHBO(100, 100);
		snackBar = new Snackbar(200, 100);
		stage = new StagePicture(300, 200);
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
		g2.drawImage(ehbo.getImageIcon().getImage(), ehbo.getX(), ehbo.getY(), this);
		g2.drawImage(snackBar.getImageIcon().getImage(), snackBar.getX(), snackBar.getY(), this);
		g2.drawImage(stage.getImageIcon().getImage(), stage.getX(), stage.getY(), this);
		g2.dispose();
	}
	
	public void run() {		
		repaint();	
	}

	public void mouseDragged(MouseEvent e) {
		if (canDrag) {
			if(buildingSelection == ehbo)
			{
			moveDragBuilding(ehbo, e.getX(), e.getY());
			}
			else if(buildingSelection == snackBar)
			{
			moveDragBuilding(snackBar, e.getX(), e.getY());
			}
			else if(buildingSelection == stage)
			{
			moveDragBuilding(stage, e.getX(), e.getY());
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
       checkDragBuilding(ehbo, e.getX(), e.getY());
       checkDragBuilding(snackBar, e.getX(), e.getY());
       checkDragBuilding(stage, e.getX(), e.getY());
 
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
	       System.out.println(buildingSelection);
	}

	public void mouseReleased(MouseEvent e) {
		canDrag = false;
	}
}
