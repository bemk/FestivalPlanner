import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class GUI 
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
	private JPanel wensen;
	private JPanel description;
	private static GUI gui1;
	private String s;
	private Interface iface = new Interface();
	protected ArrayList<TimePanel> timelines = new ArrayList<TimePanel>();
	private JPanel time = new JPanel();
	private int timeSize = 0;

	
    public static void main(String[] args)
    {
    	gui1 = new GUI();
    }

    public GUI()
    {
    	//popupMenu1();
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
        addStage(); // TODO ask for new planning or to load stored one.
    }
    
	
	
	public void addArtist()
	{
		new ArtistScherm(iface);
	}
	
	public void removeArtist()
	{
		
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
		JLabel datum = new JLabel(datetxt);
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
        frame.setJMenuBar(menuBar);
    }

    protected void exit() {
    	System.exit(0);

    }

    protected void save() {
    	try{
    		FileDialog fd = new FileDialog(new Frame(), 
    	        "Save As...", FileDialog.SAVE);
    		  fd.setVisible(true);
    	      String filePath = new String( fd.getDirectory() + fd.getFile() );

    	      //  Create a stream for writing.
    	      FileOutputStream fos = new FileOutputStream( filePath );

    	      //  create an object that can write to that file.
    	      ObjectOutputStream outStream = 
    	        new ObjectOutputStream( fos );
    	      
				outStream.writeObject(iface.addressBook);
				outStream.writeObject(iface.planning);
				outStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    }

    protected void open() {
    	try {
    	 FileDialog fd = new FileDialog( new Frame(), "Open...",
    	          FileDialog.LOAD );
    	      fd.setVisible(true);
    	      String filePath = new String( fd.getDirectory() + fd.getFile() );

    	      //  Create a stream for reading.
    	      FileInputStream fis = new FileInputStream( filePath );

    	      //  create an object that can read from that file.
    	      ObjectInputStream inStream = new ObjectInputStream( fis );

    	      // Retrieve the Serializable object.
    	      
				iface.addressBook = (AddressBook) inStream.readObject();
				iface.planning = (Planning) inStream.readObject();
			}
    		catch (Exception e)
			{
    			e.printStackTrace();
			}
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

	protected void editAct() 
	{
		// TODO Auto-generated method stub
		
	}

	protected void removeAct() 
	{
		// TODO Auto-generated method stub
		
	}

	protected void addAct(TimePanel stage) 
	{
		try {
			new DataScherm(iface, stage, this);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	public void drawAct(int stage)
	{
		System.out.println(stage);
		TimePanel j = null;
		for(TimePanel i : timelines)
		{
			if(i.getID() == stage)
			{
				j=i;
			}
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
