import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;

public class DataScherm implements Serializable
{
	private Container content;
	private JFrame frame;
	private JTextArea descriptiontxt;
	private JTextArea preferencestxt;
	private JFormattedTextField dateyr, datem, dated;
	private JFormattedTextField startTimehr, startTimemin, endTimemin, endTimehr;
	private Object[] Names;
	private JComboBox namebox;
	private JList chosenbox;
	private DefaultListModel chosenPeople;
	private Object[] ChosenNames;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> chosenNames = new ArrayList<String>();
	private JTextField genretxt;
	private JComboBox colorBox;
	private String[] colours = { "Black", "Gray", "Yellow", "Red", "Orange", "Blue", "Green", "Pink", "Cyan", "Magenta"};
	private StarPanel rating;
	private ImageIcon fotoIMG;
	private JPanel foto;
	private String file;
	private JLabel label;
	private Interface iface;
	private GregorianCalendar gc;
	private TimePanel stage;
	private GUI gui;
	private AbstractFormatter intFilter;
	private AbstractFormatterFactory filter;
	
	public DataScherm(Interface iface, Act prvAct)
	{
		this.iface = iface;
		frame = new JFrame("Edit Act");
		frame.setLocationRelativeTo(null);
		content = frame.getContentPane();
		content.setLayout(new GridLayout(9,0,20,10));
		addNamen();
		content.add(initDate(prvAct.getStartTime().get(Calendar.YEAR),prvAct.getStartTime().get(Calendar.MONTH),prvAct.getStartTime().get(Calendar.DAY_OF_YEAR),true));
		content.add(initTime(prvAct.getStartTime().get(Calendar.HOUR),prvAct.getStartTime().get(Calendar.MINUTE),prvAct.getEndTime(),true));
		content.add(initName(prvAct.getArtistNames(),true));
		content.add(initGenre_Colour(prvAct.getGenre(),prvAct.getColor(),true));
		content.add(initRating());
		content.add(initDescription_Preferences(prvAct.getDescription(),true));
		content.add(initFoto());
		content.add(buttons(true));
		GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point p = GE.getCenterPoint();
        Point o= new Point((int) (p.getX() -200),(int) ((p.getY() -225)));
        frame.setLocation(o);
		frame.setSize(400, 450);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
		try {
			this.intFilter = new MaskFormatter("##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.filter = new DefaultFormatterFactory(this.intFilter);
	}
	public DataScherm(Interface iface, TimePanel stage, GUI gui) throws ParseException
	{
		this.gui = gui;
		this.stage = stage;
		this.iface = iface;
		frame = new JFrame("Add act");
		frame.setLocationRelativeTo(null);
		content = frame.getContentPane();
		content.setLayout(new GridLayout(9,0,20,10));
		addNamen();
		content.add(initDate(0,0,0,false));
		content.add(initTime(0,0,0,false));
	//	content.add(initDuration());
		content.add(initName(null,false));
		content.add(initGenre_Colour(null,null,false));
		content.add(initRating());
		content.add(initDescription_Preferences(null,false));
		content.add(initFoto());
		content.add(buttons(false));
		GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point p = GE.getCenterPoint();
        Point o= new Point((int) (p.getX() -200),(int) ((p.getY() -225)));
        frame.setLocation(o);
		frame.setSize(400, 450);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
		this.intFilter = new MaskFormatter("##");
		this.filter = new DefaultFormatterFactory(this.intFilter);
	}
	
	public void addNamen()
	{
		for(int i = 0; i < iface.addressBook.getAllArtists().size(); i++)
		{
			names.add(iface.addressBook.getArtist(i).getName());
			//System.out.println(names.size());
		}
	}
	
	
	public BoxLayout box(Container c, char r)
	{
		BoxLayout box = null;
		if(r == 'x' || r == 'X')
			box = new BoxLayout(c, BoxLayout.X_AXIS);
		else if(r == 'y' || r == 'Y')
			box = new BoxLayout(c, BoxLayout.Y_AXIS);
		return box;
	}
	
	public JPanel initDate(int year, int month, int day, boolean edit)
	{
		JPanel datum = new JPanel();
		datum.setLayout(box(datum,'x'));
		JLabel Datum = new JLabel("Date:");
		datum.add(Datum);
		dateyr = new JFormattedTextField(new Integer(0));
		if(edit)
			dateyr.setText(""+year);
		else if(!edit)
			dateyr.setText("YYYY");
		datem = new JFormattedTextField(new Integer(0));
		if(edit)
			datem.setText(""+month);
		else if(!edit)
			datem.setText("MM");
		dated = new JFormattedTextField(new Integer(0));
		if(edit)
			dateyr.setText(""+day);
		else if(!edit)
			dateyr.setText("DD");
		dateyr.addMouseListener(new TextFieldMouseListener());
		datem.addMouseListener(new TextFieldMouseListener());
		dated.addMouseListener(new TextFieldMouseListener());
		Datum.setLabelFor(dated);
		datum.add(dated);
		datum.add(datem);
		datum.add(dateyr);
		return datum;
	}
	
	public JPanel initTime(int hour, int min, int endmins, boolean edit)
	{
		JPanel tijd = new JPanel();
		tijd.setLayout(box(tijd,'x'));
		JLabel beginTijd = new JLabel("StartTime:");
		tijd.add(beginTijd);
		startTimehr = new JFormattedTextField();
		if(edit)
			dateyr.setText(""+hour);
		else if(!edit)
			dateyr.setText("Hour");
		startTimemin = new JFormattedTextField();
		if(edit)
			dateyr.setText(""+min);
		else if(!edit)
			dateyr.setText("Min");
		beginTijd.setLabelFor(startTimehr);
		startTimehr.addMouseListener(new TextFieldMouseListener());
		startTimemin.addMouseListener(new TextFieldMouseListener());
		tijd.add(startTimehr);
		tijd.add(startTimemin);
		JLabel eindTijd = new JLabel("Endtime:");
		tijd.add(eindTijd);
		endTimehr = new JFormattedTextField();
		if(edit)
			dateyr.setText(""+ Math.abs(endmins/60));
		else if(!edit)
			dateyr.setText("Hours");
		endTimemin = new JFormattedTextField();
		if(edit)
			dateyr.setText(""+ (endmins - ((Math.abs(endmins/60))*60)));
		else if(!edit)
			dateyr.setText("Mins");
		eindTijd.setLabelFor(endTimehr);
		endTimehr.addMouseListener(new TextFieldMouseListener());
		endTimemin.addMouseListener(new TextFieldMouseListener());
		tijd.add(endTimehr);
		tijd.add(endTimemin);
		return tijd;
	}
	
	public JPanel initName(final ArrayList<String> editNames, final boolean edit)
	{
		chosenPeople = new DefaultListModel();
		Names = names.toArray();
		if(edit)
		{
			for(String s : editNames)
			{
				chosenPeople.addElement(s);
				String j;
				for (Iterator<String> i = names.iterator(); i.hasNext();) // Loop through all the names
				{
					j = i.next(); // Get the next element
					if(j.equals(s))
					{
						i.remove();
					}
				}
			}
			Names = names.toArray();
		}
		JPanel naam = new JPanel();
		naam.setLayout(box(naam,'x'));
		JLabel name = new JLabel("Artist name:");
		naam.add(name);
		namebox = new JComboBox(Names);
		name.setLabelFor(namebox);
		namebox.addActionListener(new ActionListener()
		{
			public void actionPerformed( ActionEvent e)
			{
				updatescore();
				updatePreferences();
			}

			
		});
		naam.add(namebox);
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				chosenPeople.addElement((String)namebox.getSelectedItem());
				namebox.removeItem(namebox.getSelectedItem());
				Names = names.toArray();
				namebox.updateUI();
				chosenbox.updateUI();
			}
		});
		naam.add(addBtn);
		ChosenNames = chosenNames.toArray();
        chosenbox = new JList(chosenPeople);
        chosenbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chosenbox.setSelectedIndex(0);
        chosenbox.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(chosenbox);
		naam.add(listScrollPane);
		JButton removeBtn = new JButton("Remove");
		removeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
//				names.add((String)chosenbox.getSelectedItem());
//				chosenNames.remove(chosenbox.getSelectedItem());
				try{
					int i = chosenbox.getSelectedIndex();
					namebox.addItem((String)chosenPeople.getElementAt(i));
					chosenPeople.remove(i);
					namebox.updateUI();
					chosenbox.updateUI();
					System.out.println(i);
				}
				catch (Exception ex)
				{
					System.out.println(ex.toString());
					namebox.updateUI();
					chosenbox.updateUI();
				}
			}
		});
		naam.add(removeBtn);
		
		return naam;
	}
	private void updatePreferences()
	{
		try{
			preferencestxt.setText(iface.addressBook.searchArtist((String) namebox.getSelectedItem()).getPreferences());
			preferencestxt.repaint();
		}
		catch (Exception e)
		{
			preferencestxt.setText("");
			System.out.println(e.toString());
			preferencestxt.repaint();
		}
	}
	
	private void updatescore() {
		try{
			rating.setScore((iface.addressBook.searchArtist((String)namebox.getSelectedItem())).getRating());
			rating.repaint();
		}
		catch (Exception e)
		{
			rating.setScore(0);
			rating.repaint();
		}
	}
	public JPanel initGenre_Colour(String editGenre, Color editColor, boolean edit)
	{
		JPanel genre_kleur = new JPanel();
		genre_kleur.setLayout(box(genre_kleur,'x'));
		JLabel genre = new JLabel("Genre:");
		genre_kleur.add(genre);
		if(edit)
			genretxt = new JTextField(editGenre);
		if(!edit)
			genretxt = new JTextField();
		genre.setLabelFor(genretxt);
		genre_kleur.add(genretxt);
		JLabel kleur = new JLabel("Colour:");
		genre_kleur.add(kleur);
		colorBox = new JComboBox(colours);
		kleur.setLabelFor(colorBox);
		colorBox.setEditable(false);
		colorBox.setMaximumRowCount(10);
		genre_kleur.add(colorBox);
		return genre_kleur;
	}
		
	public JPanel initRating()
	{
		rating = new StarPanel(0);
		return rating;
	}
	
	public JPanel initDescription_Preferences(String Editdescription, boolean edit)
	{
		JPanel beschrijving_wensen = new JPanel();
		beschrijving_wensen.setLayout(box(beschrijving_wensen,'x'));
		JLabel beschrijving = new JLabel("Description:");
		beschrijving_wensen.add(beschrijving);
		descriptiontxt = new JTextArea();
		if(edit)
		descriptiontxt.setText(Editdescription);
		beschrijving.setLabelFor(descriptiontxt);
		JScrollPane bsscroll = new JScrollPane(descriptiontxt);
		beschrijving_wensen.add(bsscroll);
		JLabel wensen = new JLabel("Preferences:");
		beschrijving_wensen.add(wensen);
		preferencestxt = new JTextArea();
		preferencestxt.setEditable(false);
		wensen.setLabelFor(preferencestxt);
		JScrollPane wsscroll = new JScrollPane(preferencestxt);
		beschrijving_wensen.add(wsscroll);
		return beschrijving_wensen;
	}
	
	public JPanel initFoto()
	{
		foto = new JPanel();
		foto.setLayout(box(foto,'x'));
		label = new JLabel("FilePath");
		foto.add(label);
		JButton button = new JButton("...");
		label.setLabelFor(button);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				frame.setAlwaysOnTop(false);
				fotoIMG = openPNG();
			//	JLabel fotopad = new JLabel(file);
			//  foto.add(fotopad);
				label.setText(file);
				frame.setAlwaysOnTop(true);
				foto.repaint();
			}
		});
		foto.add(button);
		
		
		return foto;
	}
	
	private ImageIcon openPNG() {
        JFileChooser jfc = new JFileChooser(); 
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".png files", "png");
        jfc.setFileFilter(filter);
        int result = jfc.showOpenDialog(jfc);
        if(result == JFileChooser.CANCEL_OPTION) 
            return null;
        try {
            file = jfc.getSelectedFile().getAbsolutePath();
            //System.out.println(jfc.getSelectedFile().getAbsolutePath());
            ImageIcon i= new ImageIcon(file); 
            return i;
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jfc,e.getMessage(),
                "File error",JOptionPane.ERROR_MESSAGE);}
        	return null;
    }
	
	public JPanel buttons(final boolean edit)
	{
		JPanel buttons = new JPanel();
		JButton save = new JButton("Save");
		JButton cancel = new JButton("Cancel");
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				save(edit);
			}
			
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		buttons.add(save);
		buttons.add(cancel);
		return buttons;
	}

	public String removeComma(String str)
	{
		String ret="";
		if (str.contains(",") || str.contains("."))
		{
			for (int i = 0; i < str.length(); i++)
			{
				if (str.charAt(i)!=','||str.charAt(i)=='.')
				{
					ret += str.charAt(i);
				}
			}
			return ret;
		}
		else
		{
			return str;
		}
	}
	
	private int parseLocale(String str){
		try {
			return Integer.parseInt(removeComma(str));
		} catch (Exception e) {
			return -1;
		}
	}
	
	public void save(boolean edit)
	{
		if(!edit)
		{		
		System.out.printf("year\t%d\n" +
				"month\t%d\n" +
				"day\t%d\n",
				parseLocale(dateyr.getText()),parseLocale(datem.getText()), parseLocale(dated.getText()));

		gc = new GregorianCalendar( parseLocale(dateyr.getText()),
									parseLocale(datem.getText())-1,
									parseLocale(dated.getText()),
									parseLocale(startTimehr.getText()),
									parseLocale(startTimemin.getText()));
		System.out.printf("yr\t%d\nmth\t%d\nday\t%d\n",
				gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DAY_OF_MONTH));
		if (gc == null)
		{
			System.exit(0);
		}
		int endTimeTmpHr = parseLocale(endTimehr.getText());
		int endTimeTmpMin = parseLocale(endTimemin.getText());
		int startTimeTmpHr = parseLocale(startTimehr.getText());
		int startTimeTmpMin = parseLocale(startTimemin.getText());
		
		
		if (endTimeTmpHr < startTimeTmpHr)
		{
			endTimeTmpHr += 24;
		}
		
		
		int durationHr = endTimeTmpHr - startTimeTmpHr;
		if (endTimeTmpMin < startTimeTmpMin)
		{
			durationHr--;
			endTimeTmpMin+=60;
		}
		int durationMin = endTimeTmpMin - startTimeTmpMin;
		durationMin += durationHr*60;
		
		ArrayList<Artist> chosenArtist = new ArrayList<Artist>();
		for(int i = 0; i<chosenPeople.size();i++ )
		{
			chosenArtist.add(iface.addressBook.searchArtist((String)chosenPeople.getElementAt(i)));
		}
		Color c = null;
		switch(colorBox.getSelectedIndex())
		{
		case 0 : 
			c = Color.BLACK;
			break;
		case 1 : 
			c = Color.GRAY;
			break;
		case 2 :
			c = Color.YELLOW;
			break;
		case 3 :
			c = Color.RED;
			break;
		case 4 :
			c = Color.ORANGE;
			break;
		case 5 :
			c = Color.BLUE;
			break;
		case 6 :
			c = Color.GREEN;
			break;
		case 7 :
			c = Color.PINK;
			break;
		case 8 :
			c = Color.CYAN;
			break;
		case 9 :
			c = Color.MAGENTA;
			break;
		default :
			c = Color.BLACK;
			break;
		}
		iface.newAct(gc, durationMin, chosenArtist, descriptiontxt.getText(), genretxt.getText(), c, stage.getID());
		stage.update();
		stage.repaint();
		}
		
		this.frame.dispose();
	}
	
	protected void listener(char key, JTextField tf, String txt)
	{
		switch (key)
		{
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				txt = txt+key;
				tf.setText(txt);
				break;
			default:
				break;
		}
	}
		
}

class TextFieldMouseListener extends MouseAdapter
{
	@Override
	public void mouseClicked(MouseEvent e)
	{
		((JTextField)e.getSource()).setText("");
	}
}

class IntListener extends KeyAdapter
{
	private boolean number = false;
	@Override
	public void keyPressed(KeyEvent e) 
	{
		number = false;
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
				e.getKeyCode() == KeyEvent.VK_DELETE || 
				e.getKeyCode() == KeyEvent.VK_ENTER ||
				e.getKeyCode() == KeyEvent.VK_0 ||
				e.getKeyCode() == KeyEvent.VK_1 ||
				e.getKeyCode() == KeyEvent.VK_2 ||
				e.getKeyCode() == KeyEvent.VK_3 ||
				e.getKeyCode() == KeyEvent.VK_4 ||
				e.getKeyCode() == KeyEvent.VK_5 ||
				e.getKeyCode() == KeyEvent.VK_6 ||
				e.getKeyCode() == KeyEvent.VK_7 ||
				e.getKeyCode() == KeyEvent.VK_8 ||
				e.getKeyCode() == KeyEvent.VK_9 ||
				e.getKeyCode() == KeyEvent.VK_NUM_LOCK ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD0 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD1 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD2 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD3 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD4 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD5 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD6 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD7 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD8 ||
				e.getKeyCode() == KeyEvent.VK_NUMPAD9)
		{
			number = true;
			System.out.println(e.getKeyChar());
		}
		else if (!number)
		{
			try {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
			} catch (AWTException e1) {
				
			}
		}
	}
}