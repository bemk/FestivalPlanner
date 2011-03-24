import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	    setLocationRelativeTo(null);
	    setBackground(Color.blue);
	    setVisible(true);
	    setResizable(false);
	}
	
	public String getSelection()
	{
		return itemBoard.getSelection();
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
		private Snackbar snackBar = new Snackbar(10, 168);;
		private StagePicture stage = new StagePicture(10, 216);
		public String selection = "Selection: ";
		public boolean rightClicked = false;
		
		public ItemBoard()
		{
			setDoubleBuffered(true);
			addMouseListener(this);
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
			g2.drawString(selection, 0, 50);
		}

		
		public void mouseClicked(MouseEvent e) 
		{
			checkBuilding(ehbo, e.getX(), e.getY());
			checkBuilding(snackBar, e.getX(), e.getY());
			checkBuilding(stage, e.getX(), e.getY());
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
	        }
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {	}
	}
}
