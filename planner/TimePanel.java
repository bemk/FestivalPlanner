import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


public class TimePanel extends JPanel implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 4466302497626327762L;
	private JPopupMenu popupMenu1;
	public String title;
	private GUI gui;
    private JMenuItem editAct;
    private TimePanel arg;
    private boolean drawn = false;
    private Graphics g;
    private boolean removed = false;
    private int stage;
    private ArrayList<Integer> acts = new ArrayList<Integer>();
    private Interface iface;
    
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
				if(e.getButton() == MouseEvent.BUTTON3)
				{
					if(e.getButton() == MouseEvent.BUTTON3)
						popupMenu1.show(e.getComponent(), e.getX(),e.getY());
				}
			}

			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
		});
		this.setPreferredSize(new Dimension(width, height));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		popupMenu1();
		
	}
	
	public void update()
	{
		acts = iface.getAllActs(stage);
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
	
	public JPopupMenu popupMenu1(){
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
	    		gui.addAct(stage);
	    	}
	    });
	    popupMenu1.add(addAct);
	    JMenuItem removeAct = new JMenuItem("Remove Act");
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
		
		// TODO add act drawing code.
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
