

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SmoothMoveExample extends JPanel implements
 MouseMotionListener {
  private int X, Y;
  private Image image;
  private static JFrame frame;
  private JPopupMenu rightClickMenu;
  private JMenuBar menuBar;
   

public static void main(String[] args) {
    frame = new JFrame();
    frame.getContentPane().add(new SmoothMoveExample());
    
    frame.setSize(350, 300);
    frame.show();
  }
  public SmoothMoveExample() {
    addMouseMotionListener(this);
    rightClickMenu = new JPopupMenu();
	 JMenuItem bla = new JMenuItem("bla");
	 rightClickMenu.add(bla);
    setVisible(true);
  }
  
 public void mouseMoved(MouseEvent event) {
     
  }
  public void mouseDragged(MouseEvent event) {
	  if(event.getModifiers() == MouseEvent.BUTTON1_MASK)
	  {
		X = (int) event.getPoint().getX();
	    Y = (int) event.getPoint().getY();
	    repaint();
	  }
	  
  }
  public void update(Graphics graphics) {
    paint(graphics);
  }
  public void paint(Graphics g) {
  Dimension dim = getSize();
    checkImage();
    Graphics graphics = image.getGraphics();
    graphics.setColor(getBackground());
    graphics.fillRect(0, 0, dim.width, dim.height);
  paintOffscreen(image.getGraphics());
    g.drawImage(image, 0, 0, null);
  }
  private void checkImage() {
    Dimension dim = getSize();
    if (image == null || image.getWidth(null) != dim.width
        || image.getHeight(null) != dim.height) {
      image = createImage(dim.width, dim.height);
    }
  }
  public void paintOffscreen(Graphics g) {
    int size = 150;
    g.setColor(Color.pink);
    g.fillOval(X - size  / 2, Y - size  / 2, size , size );
  }
}