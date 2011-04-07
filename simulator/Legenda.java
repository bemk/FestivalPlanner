import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Legenda extends JFrame {

	private ItemBoard itemBoard = new ItemBoard();
	public Legenda()
	{
		add(itemBoard);
		setTitle("Legenda");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(100, 485);
	    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point p = GE.getCenterPoint();
        Point o= new Point((int) (p.getX()+372),(int) ((p.getY() -242.5)));
        setLocation(o);
	    setBackground(Color.blue);
	    setVisible(true);
	    setResizable(false);
	}
	
	public String getSelection()
	{
		return itemBoard.getSelection();
	}
	
	public void resetSelection()
	{
		itemBoard.resetSelection();
	}
	
	public boolean RightClicked()
	{
		if(itemBoard.rightClicked())
		{
			itemBoard.setRightClicked(false);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	class ItemBoard extends JPanel implements MouseListener
	{
		private EHBO ehbo = new EHBO(10, 120);
		private Snackbar snackBar = new Snackbar(10, 160);
		private StagePicture stage = new StagePicture(10, 200);
		private WC wc = new WC(10, 240);
		private Rectangle2D river = new Rectangle2D.Double(10, 280, 24, 24); 
		private Bridge bridge = new Bridge(10, 320);
		private Forest forest = new Forest(10, 360);
		public String selection = "Selection: ";
		public boolean rightClicked = false;
		
		public ItemBoard()
		{
			setDoubleBuffered(true);
			addMouseListener(this);
		}
		
		public void resetSelection()
		{
			this.selection = "Selection: ";
			repaint();
		}
		
		public String getSelection()
		{
			return this.selection;
		}
		
		public void setRightClicked(boolean r)
		{
			this.rightClicked = r;
		}
		
		public boolean rightClicked()
		{
			return this.rightClicked;
		}
		
		public void paint(Graphics g)
		{
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.gray);
			g2.fillRect(0, 0, 140, 485);
			g2.setColor(Color.black);
			g2.drawImage(ehbo.getImageIcon().getImage(), ehbo.getX(), ehbo.getY(), this);
			g2.drawImage(snackBar.getImageIcon().getImage(), snackBar.getX(), snackBar.getY(), this);
			g2.drawImage(stage.getImageIcon().getImage(), stage.getX(), stage.getY(), this);
			g2.drawImage(wc.getImageIcon().getImage(), wc.getX(), wc.getY(), this);
			g2.drawImage(forest.getImageIcon().getImage(), forest.getX(), forest.getY(), this);
			g2.drawImage(bridge.getImageIcon().getImage(), bridge.getX(), bridge.getY(), this);
			g2.setColor(new Color(0, 125, 255));
			g2.fill(river);
			g2.setColor(Color.BLACK);
			g2.drawString(selection, 0, 50);
		}

		
		public void mouseClicked(MouseEvent e) 
		{
			checkBuilding(ehbo, e.getX(), e.getY());
			checkBuilding(snackBar, e.getX(), e.getY());
			checkBuilding(stage, e.getX(), e.getY());
			checkBuilding(wc, e.getX(), e.getY());
			checkBorder(river, e.getX(), e.getY());
			checkObstacle(forest, e.getX(), e.getY());
			checkPath(bridge, e.getX(), e.getY());
			repaint();
			if (e.getButton() == MouseEvent.BUTTON3)
			{
				rightClicked = true;
			}
		}

		public void checkBuilding(Building building, int x, int y)
		{
			if (x >= building.getX() && x <= (building.getX() + building.getWidth())
	                && y >= building.getY() && y <= (building.getY() + building.getHeight())) 
			{
	            if(building == ehbo)
	            {
	            	selection = "Selection: EHBO";
	            }
	            else if(building == snackBar)
	            {
	            	selection = "Selection: SnackBar";
	            }
	            else if(building == stage)
	            {
	            	selection = "Selection: Stage";
	            }
	            else if(building == wc)
	            {
	            	selection = "Selection: WC";
	            }
	            else if(building == wc)
	            {
	            	selection = "Selection: Bridge";
	            }
	        }
		}
		
		public void checkBorder(Rectangle2D a, int x, int y)
		{
			if (x >= a.getX() && x <= (a.getX() + a.getWidth())
	                && y >= a.getY() && y <= (a.getY() + a.getHeight())) 
			{
	           	selection = "Selection: River";
	        }
		}
		
		public void checkObstacle(Obstacle obstacle, int x, int y)
		{
			if (x >= obstacle.getX() && x <= (obstacle.getX() + obstacle.getWidth())
	                && y >= obstacle.getY() && y <= (obstacle.getY() + obstacle.getHeight())) 
			{
	           	selection = "Selection: Forest";
	        }
		}
		
		public void checkPath(Path path, int x, int y)
		{
			if (x >= path.getX() && x <= (path.getX() + path.getWidth())
	                && y >= path.getY() && y <= (path.getY() + path.getHeight())) 
			{
	           	selection = "Selection: Bridge";
	        }
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {	}
	}
}
