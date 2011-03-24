import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class GUI implements Serializable
{
	private GridBagLayout gridbag = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();
    private JFrame frame;
    private Container content;
	private String datetxt;
	private int score;
	private JTextArea preference;
	private JTextArea descriptionText;
	private StarPanel rating;
	private boolean open = false;
	private JPanel wensen;
	private JPanel description;
	private static GUI gui1;
	private String s;
	private Interface iface = new Interface();
	protected ArrayList<TimePanel> timelines = new ArrayList<TimePanel>();
	private JPanel time = new JPanel();
	private int timeSize = 0;
	private Calendar date;
	private JLabel datum;

	
    public static void main(String[] args)
    {
    	gui1 = new GUI();
    }

    public GUI()
    {
    	//popupMenu1();
        this.date = new GregorianCalendar();
    	frame = new JFrame("Festival Planner");
        content = frame.getContentPane();
        content.setLayout(gridbag);
        c.fill = GridBagConstraints.BOTH;
        initMenuBar();
        initDatum();
        initTimeline();
        initWensen();
        initBeschrijving();
        GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point p = GE.getCenterPoint();
        Point o= new Point((int) (p.getX() -400),(int) ((p.getY() -275)));
        frame.setLocation(o);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);
        frame.setResizable(false);
        frame.setVisible(true);
        timeSize = time.getHeight()/2;
        Object[] options = {"Open", "New"};
        int optionPane =JOptionPane.showConfirmDialog(null,
                "Do you want to open a previous Planner?\n Or do you want to open a new planner?"
        		+"\n\n To Open a previous click Yes,\n for a new one click No.",
                "Open or New?",
                JOptionPane.YES_NO_OPTION);
        if(optionPane == JOptionPane.YES_OPTION)
        {
        	open = true;
        	open();
        }
        else
        addStage(); // TODO ask for new planning or to load stored one.
    }
    
	public Calendar getDate()
	{
		return this.date;
	}
	
	public void addArtist()
	{
		new ArtistScherm(iface);
	}
	
	public void removeArtist()
	{
		final JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(2,0));
		frame.getContentPane().add(new JLabel("Name"));
		final JTextField field = new JTextField();
		frame.getContentPane().add(field);
		JButton oke = new JButton("Oke");
		oke.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s = field.getText();
				for(int i : iface.getAllArtists())
				{
					if(iface.findArtist(i).getName().equals(s))
					{
						iface.removeArtist(i);
						frame.dispose();
					}
					else
					{
						errorFrame();
					}
				}
				
			}
		});
		frame.getContentPane().add(oke);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		frame.getContentPane().add(cancel);
		frame.setSize(200,75);
		frame.setResizable(false);
		GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point p = GE.getCenterPoint();
        Point o= new Point((int) (p.getX() -100),(int) ((p.getY() -37)));
        frame.setLocation(o);
		frame.setVisible(true);
		
	}
	public void errorFrame()
	{
		final JFrame frame2 = new JFrame();
		frame2.setLocationRelativeTo(null);
		frame2.setLayout(new GridLayout(2,0));
		frame2.getContentPane().add(new JLabel("The name you've search is not in the database. Please try a different name!"));
		JButton button = new JButton("oke");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				frame2.dispose();
			}
		});
		frame2.getContentPane().add(button);
		frame2.setSize(450,75);
		frame2.setResizable(false);
		frame2.setAlwaysOnTop(true);
		frame2.setVisible(true);
	}
	public void editArtist()
	{
		
		final JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(2,0));
		frame.getContentPane().add(new JLabel("Name"));
		final JTextField field = new JTextField();
		frame.getContentPane().add(field);
		JButton oke = new JButton("Oke");
		oke.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s = field.getText();

				for(int i : iface.getAllArtists())
				{
					if(iface.findArtist(i).getName().equals(s))
					{
						new ArtistScherm(iface,i);
					}
					else
					{
						errorFrame();
					}
				}
				frame.dispose();
			}
		});
		frame.getContentPane().add(oke);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		frame.getContentPane().add(cancel);
		frame.setSize(200,75);
		frame.setResizable(false);
		GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point p = GE.getCenterPoint();
        Point o= new Point((int) (p.getX() -100),(int) ((p.getY() -37)));
        frame.setLocation(o);
		frame.setVisible(true);
	}

	private void initDatum() {
		c.fill = GridBagConstraints.BOTH;
		JPanel Datum = new JPanel(gridbag);
		JButton rechts = new JButton(new ImageIcon(this.getClass().getResource("Images/button_right.png")));
		JButton links = new JButton(new ImageIcon(this.getClass().getResource("Images/button_left.png")));
		rechts.setSize(50, 50);
		links.setSize(50, 50);
		datum = new JLabel(datetxt);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.002;
		c.weighty = 0.01;
		c.gridwidth = 1;
		Datum.add(links,c);
		c.gridx = 2;
		c.weightx = 1;
		c.gridwidth = GridBagConstraints.RELATIVE;
		Datum.add(datum, c);
		c.gridx = 3;
		c.weightx = 0.002;
		c.gridwidth = GridBagConstraints.REMAINDER;
		Datum.add(rechts,c);
		c.gridx = 1;
		c.weightx = 0.5;
		frame.add(Datum, c);
		
		updateDatum();
		
		rechts.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{	
				date.add(Calendar.DAY_OF_YEAR, 1);
				updateActs();
				updateDatum();
			}
		});
		links.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				date.add(Calendar.DAY_OF_YEAR, -1);
				updateActs();
				updateDatum();
			}
		});
		
	}

	private void updateDatum()
	{
		datum.setText(date.get(Calendar.DAY_OF_MONTH) + " - " + Integer.toString(date.get(Calendar.MONTH)+1) + " - " + date.get(Calendar.YEAR));
	}
	
	private void initTimeline() {
		c.fill = GridBagConstraints.BOTH;
    	time.setLayout(new BoxLayout(time,BoxLayout.Y_AXIS));
		//TimePanel timeLine = new TimePanel(this);
		JScrollPane scroll = new JScrollPane(time);
    	//timeLine.setBorder(BorderFactory.createLineBorder(Color.black));
    	c.gridx = 1;
    	c.gridy = 2;
    	c.weightx = 1;
    	c.weighty = .75f;
    	frame.add(scroll,c);
    	gridbag.setConstraints(scroll, c);
    	/*int size = scroll.getHeight()/2;
    	System.out.println("The height is: "+size);
    	timelines.add(new TimePanel(this, time.getWidth(), size, Integer.toString(timelines.size())));
		timelines.add(new TimePanel(this, time.getWidth(), size, Integer.toString(timelines.size())));
    	for (Iterator<TimePanel> i = timelines.iterator(); i.hasNext();)
    	{
    		time.add(i.next());
    	}*/
	}

	private void initWensen() {
		// TODO Auto-generated method stub
		c.fill = GridBagConstraints.BOTH;
    	wensen = new JPanel(gridbag);
    	wensen.setBorder(BorderFactory.createLineBorder(Color.black));
    	JLabel foto = new JLabel("Foto");
    	c.gridx = 1;
    	c.gridy = 1;
    	c.weightx = 1;
    	c.weighty = 5;
    	c.gridwidth = GridBagConstraints.RELATIVE;
  	    gridbag.setConstraints(foto, c);
  	    foto.setBorder(BorderFactory.createLineBorder(Color.gray));
    	wensen.add(foto, c);
    	preference = new JTextArea("Wensen");
    	preference.setEditable(false);
    	JScrollPane wensplane = new JScrollPane(preference);
    	c.gridx = 2;
    	c.gridy = 1;
    	c.weightx = 1;
    	c.weighty = 2;
    	c.gridwidth = GridBagConstraints.REMAINDER;
    	gridbag.setConstraints(preference,c);
    	preference.setBorder(BorderFactory.createLineBorder(Color.gray));
    	wensen.add(wensplane,c);
    	JLabel naam = new JLabel("naam");
    	c.gridx = 1;
    	c.gridy = 2;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridwidth = GridBagConstraints.RELATIVE;
    	gridbag.setConstraints(naam, c);
    	naam.setBorder(BorderFactory.createLineBorder(Color.gray));
    	wensen.add(naam,c);
	    	
    	c.gridx = 1;
    	c.gridy = 3;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridwidth = GridBagConstraints.RELATIVE;
    	gridbag.setConstraints(wensen, c);
    	frame.add(wensen,c);
		
	}
	
	public void updateScore(int i)
	{
		this.score = i;
		rating.setScore(iface.getRating(i));
		rating.repaint();
	}

	private void initBeschrijving() 
	{
		
		c.fill = GridBagConstraints.BOTH;
    	description = new JPanel(gridbag);
    	description.setBorder(BorderFactory.createLineBorder(Color.black));
    	rating = new StarPanel();
    	c.gridx = 1;
    	c.gridy = 2;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridwidth = GridBagConstraints.RELATIVE;
  	    gridbag.setConstraints(rating, c);
  	    rating.setBorder(BorderFactory.createLineBorder(Color.gray));
    	description.add(rating, c);
    	descriptionText = new JTextArea("descriptionText");
    	descriptionText.setEditable(false);
    	JScrollPane descriptionplane = new JScrollPane(descriptionText);
    	c.gridx = 1;
    	c.gridy = 1;
    	c.weightx = 2;
    	c.weighty = 5;
    	c.gridwidth = GridBagConstraints.REMAINDER;
    	gridbag.setConstraints(descriptionText, c);
    	descriptionText.setBorder(BorderFactory.createLineBorder(Color.gray));
    	description.add(descriptionplane,c);
    	JLabel genre = new JLabel("genre");
    	c.gridx = 2;
    	c.gridy = 2;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridwidth = GridBagConstraints.REMAINDER;
    	gridbag.setConstraints(genre, c);
    	genre.setBorder(BorderFactory.createLineBorder(Color.gray));
    	description.add(genre,c);
	    	
    	c.gridx = 2;
    	c.gridy = 3;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridwidth = GridBagConstraints.REMAINDER;
    	gridbag.setConstraints(description, c);
    	frame.add(description,c);
	}

	private void initMenuBar() 
    {   
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem nieuw = new JMenuItem("New");
        nieuw.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e)
                {
                    nieuw();    
                }
            });
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    open();
                }
            });
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    save();
                }
            });
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    exit();
                }
            });
        file.add(nieuw);
        file.addSeparator();
        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        menuBar.add(file);
        JMenu simulator = new JMenu("Simulator");
        JMenuItem activate = new JMenuItem("Run");
        activate.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		activate();
        	}
        });
        simulator.add(activate);
        menuBar.add(simulator);
        frame.setJMenuBar(menuBar);
    }

    protected void exit() {
    	System.exit(0);

    }
    
   private void activate()
   {
	   new Simulator(iface);
   }
    
    protected void save() {
    	try{
    		
    		JFileChooser fc = new JFileChooser();
    		fc.setAcceptAllFileFilterUsed(false);
    		fc.addChoosableFileFilter(new AgnFilter());
            int returnVal =	fc.showSaveDialog(frame);
            if(returnVal != JFileChooser.APPROVE_OPTION) 
            	{
            		return; // geannuleerd
            	}
            	File selectedFile = new File(fc.getSelectedFile().getAbsolutePath()+".pln");
            	FileOutputStream f = new FileOutputStream(selectedFile); 
            	ObjectOutputStream s = new ObjectOutputStream(f); 
            	s.writeObject(iface);
                s.flush(); 
    		}
            catch (IOException io)
                 { 
            		io.printStackTrace();
                 }
    }
    	
    protected void open() {
    	try {
    			JFileChooser fc = new JFileChooser();
    			fc.addChoosableFileFilter(new AgnFilter());
    			int returnVal = fc.showOpenDialog(frame);
    			if(returnVal == JFileChooser.CANCEL_OPTION)
    			{
    				return;
    			}
    			File selectedFile = fc.getSelectedFile();
    			FileInputStream f = new FileInputStream(selectedFile);
    			ObjectInputStream s = new ObjectInputStream(f);
    			Interface i = (Interface) s.readObject();
    			setIface(i);
    			openStages();
    			redrawStages();
			}
    		catch (Exception e)
			{
    			e.printStackTrace(); 
			}
	}
    
    private void openStages()
    {
    	for (TimeLine tl : iface.timelines)
    	{
    		this.timelines.add(new TimePanel(this, time.getWidth(), timeSize, tl.getName(), tl.ID(), iface));
    	}
    	redrawStages();
    	for (TimePanel tp : this.timelines)
    	{
    		tp.update();
    	}
    }

    private void setIface(Interface iface)
    {
    	this.iface = iface;
    }
    protected void nieuw() 
    {
       gui1 = this;
       gui1.frame.dispose();
       new GUI();
    }

    protected void removeStage(TimePanel t) 
    {
		System.out.println(t.getTitle());
		if (timelines.size() > 1)
		{
			if (JOptionPane.showConfirmDialog(null, "Do you really want to remove this stage?") == 0)
			{
				t.removed(true); // Mark stage for removal.
				redrawStages(); // Remove the stage from the panel.
				timelines.remove(t); // Remove the stage from the list.
				iface.removeStage(t.getTitle());
			}
		}
	}

	protected void addStage() 
	{
//		System.out.println("Size = "+timeSize);
		// Add a time panel to the interface
		String stageName = null;
		while (stageName == null)
		{
			stageName = JOptionPane.showInputDialog(null,
				"Type the stage name",
				"",
				JOptionPane.QUESTION_MESSAGE);
			if (timelines.size()>=1 && stageName == null)
			{
				return;
			}
		}
		int tmpStage = iface.newStage(stageName);
		timelines.add(new TimePanel(this, time.getWidth(), timeSize, stageName, tmpStage, iface));
		redrawStages(); // paint the time panel to screen.
	}

	protected void editAct(ActPaint ap) 
	{
		Act tmpAct = iface.getAct(ap.getTimePanel().getStage(), ap.getActID());
		System.out.println(tmpAct.getGenre());
		new DataScherm(iface,tmpAct);
	}

	protected void removeAct(ActPaint ap, TimePanel t) 
	{
			iface.removeAct(t.getStage(), ap.getActID());
			t.update();
	}

	protected void addAct(TimePanel stage) 
	{
		
		try {
			new DataScherm(iface, stage, this);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateActs()
	{
		for (TimePanel p : timelines)
		{
			p.update();
		}
	}
	
	public void redrawStages()
	{
		TimePanel j = null; //Language quirk
		for (Iterator<TimePanel> i = timelines.iterator(); i.hasNext();) // Loop through all the timelines
    	{
			j = i.next(); // Get the next element
			if (j.removed()) // If up for removal, remove from panel
			{
				time.remove(j);
			} else if (!j.drawn()){ // If up for drawing, draw
				time.add(j);
				j.drawn(true); // Take away from the to draw list
			}
    	}
		time.updateUI(); // Do the actual redrawing.
	}

}
class AgnFilter extends FileFilter {

    //Accept all directories and agn files.
	 public boolean accept(File f) {
	        if (f.isDirectory()) {
	            return true;
	        }

	        String extension = getExtension(f);
	        if (extension != null) {
	            if (extension.equals("pln")) {
	                    return true;
	            } else {
	                return false;
	            }
	        }

	        return false;
	    }

	    //The description of this filter
	    public String getDescription() {
	        return ".pln";
	    }
	    
	    public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }
}


