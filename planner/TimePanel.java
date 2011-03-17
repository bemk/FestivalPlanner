import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;


public class TimePanel extends JPanel implements TimeLinePanel, Serializable
{

	private static final long serialVersionUID = 4466302497626327762L;
	private JPopupMenu popupMenu1;
	public String title;
	private Shape s;
	protected GUI gui;
	private JMenuItem removeAct;
    private JMenuItem editAct;
    private TimePanel arg;
    private boolean drawn = false;
    private Graphics g;
    private boolean dragged = false;
    private boolean removed = false;
    private int stage;
    private ArrayList<Integer> acts = new ArrayList<Integer>();
    private Interface iface;
	protected ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Shape SelectedObject = null;
	//private Shape dragObject = null;
	//private Point lastMousePosition = null;
    
	public TimePanel(GUI gui)
	{
		this(gui, 800, 100, "null", 0, null);
	}
	public TimePanel(GUI gui, int width, int height, String title, int stage, Interface iface)
	{
		System.out.println(stage);
		this.iface = iface;
		this.stage = stage;
		this.arg = this;
		this.gui = gui;
		this.title = title;
		this.addMouseListener(new MouseListener(){
			public void mouseReleased(MouseEvent e)
			{
				for (Shape s : shapes)
				{
					Point tmpPoint = new Point(e.getPoint().x, (int) ((int)(e.getPoint().y)/arg.height()));
					if(e.getButton() == MouseEvent.BUTTON3 && s.contains(tmpPoint))
					{
						if(s != null)
						SelectedObject = s;
						removeAct.setEnabled(true);
						editAct.setEnabled(true);
						popupMenu1.show(e.getComponent(),e.getX(),e.getY());
						break;
					}
					else
					{
						
						if(e.getButton() == MouseEvent.BUTTON3)
						{
							removeAct.setEnabled(false);
							editAct.setEnabled(false);
							popupMenu1.show(e.getComponent(), e.getX(), e.getY());
							break;
						}
					}
				}
				if(e.getButton() == MouseEvent.BUTTON3 && SelectedObject == null)
				{
					if(e.getButton() == MouseEvent.BUTTON3)
					{
						removeAct.setEnabled(false);
						editAct.setEnabled(false);
						popupMenu1.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
		});
		
		this.setPreferredSize(new Dimension(width, height));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
//		this.addMouseListener(this);
//		this.addMouseMotionListener(this);
		
		popupMenu();
		
	}
	
	public void update()
	{
		acts = iface.getAllActs(stage);
		Integer a = null;
		Iterator<Integer> i = acts.iterator();
		while(i.hasNext())
		{
			try{
			a = i.next();
			Calendar act = iface.getStartTime(stage, a);
			if (gui.getDate().get(Calendar.DAY_OF_MONTH) != act.get(Calendar.DAY_OF_MONTH)
					|| gui.getDate().get(Calendar.MONTH) != act.get(Calendar.MONTH)
					|| gui.getDate().get(Calendar.YEAR) != act.get(Calendar.YEAR))
			{
				i.remove();
			}
			} catch (Exception e)
			{
				System.out.println(e.getLocalizedMessage());
				break;
			}
		}
		repaint();
	}
	
	public boolean drawn()
	{
		return drawn;
	}
	public boolean removed()
	{
		return removed;
	}
	public void drawn (boolean drawn)
	{
		this.drawn = drawn;
	}
	public void removed(boolean removed)
	{
		this.removed = removed;
	}
	
	public JPopupMenu popupMenu(){
	    popupMenu1 = new JPopupMenu();
	    JMenuItem addArtist = new JMenuItem("Add Artist");
		addArtist.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.addArtist();
			}
		});
		popupMenu1.add(addArtist);
		JMenuItem removeArtist = new JMenuItem("Remove Artist");
		removeArtist.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.removeArtist();
			}
		});
		popupMenu1.add(removeArtist);
		JMenuItem editArtist = new JMenuItem("Edit Artist");
		editArtist.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.editArtist();
			}
		});
		popupMenu1.add(editArtist);
	    JMenuItem addAct = new JMenuItem("Add Act");
	    addAct.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		gui.addAct(arg);
	    	}
	    });
	    popupMenu1.add(addAct);
	    removeAct = new JMenuItem("Remove Act");
	    removeAct.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		gui.removeAct();
	    	}
	    });
	    
	    removeAct.setEnabled(false);
	    popupMenu1.add(removeAct);
	    editAct = new JMenuItem("Edit Act");
	    editAct.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		gui.editAct();
	    	}
	    });
	    editAct.setEnabled(false);
	    popupMenu1.add(editAct);
	    
	    
	    popupMenu1.addSeparator();
	  JMenuItem addStage = new JMenuItem("Add Stage");
	  addStage.addActionListener(new ActionListener()
	  {
	  	public void actionPerformed(ActionEvent e)
	  	{
	  		gui.addStage();
	  	}
	  });
	  popupMenu1.add(addStage);
	  JMenuItem removeStage = new JMenuItem("Remove Stage");
	  removeStage.addActionListener(new ActionListener()
	  {
	  	public void actionPerformed(ActionEvent e)
	  	{
		  		gui.removeStage(arg);
	  	}
	  });
	  popupMenu1.add(removeStage);
	  return popupMenu1;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getID()
	{
		return stage;
	}
	
	public void paintComponent(Graphics g)
	{
		this.g = g;
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.red);
		g2.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		for(int i = -this.getWidth();i<24;i++)
		{
			g2.drawLine(i*(this.getWidth()/24), this.getHeight()/2-this.getHeight()/8, i*(this.getWidth()/24), (this.getHeight()/2)+(this.getHeight()/8));
		}
		g2.drawString(title, 4, 3*(this.getHeight()/8));

		g2.translate(0, height()/2);
		if(!dragged)
		{
		for(int act : acts)
		{
			ActPaint a = new ActPaint(act, iface, this);
			a.paintComponent(g2);
		}
		}
		else if(dragged)
		{
			ActPaint a = new ActPaint(this);
			a.paintComponent(s,g2);
		}
		dragged = false;
		g2.translate(0,-height()/2);
		// TODO add act drawing code.
	}
	
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		if(dragObject != null)
//		{
//			
//			s = dragObject;
//			dragged = true;
//			AffineTransform tr = new AffineTransform();
//			if(((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0))
//			{
//				tr.translate(e.getPoint().x - lastMousePosition.x, e.getPoint().y - lastMousePosition.y);
//				System.out.println("hij vind hem wel met draggen");
//			}
//			s = tr.createTransformedShape(s);
//			if(shapes.contains(dragObject))
//			shapes.set(shapes.indexOf(dragObject), s);
//			lastMousePosition = e.getPoint();
//			this.repaint();
//			this.updateUI(); 
//			//shapes.remove(dragObject);
//			//this.updateUI();
//		}
//		
//		
//	}
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mousePressed(MouseEvent e) {
////		for(int i = 0; i < shapes.size(); i++)
////		if (Interface.dbg)
////		{
////			System.out.printf("\nX coord:\t%f\nY coord:\t%f\n", e.getPoint().getX(), e.getPoint().getY()/this.height());
////		}
//		for (Shape s : shapes)
//		{
//			Point tmpPoint = new Point(e.getPoint().x, (int) ((int)(e.getPoint().y)/this.height()));
//			if(s.contains(tmpPoint))
//			{
//				System.out.println("pressed");
//				dragObject = s;
//				lastMousePosition = tmpPoint;
//				break;
//			}
//		}
//	}
//	
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		this.dragObject = null;
//		
//	}
	
	
	public double height()
	{
		return (double)this.getHeight();
	}
	public double width()
	{
		return (double)this.getWidth();
	}
}
