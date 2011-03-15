import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//import java.util.ArrayList;
import javax.swing.*;


public class ArtistScherm 
{
	private JFrame frame;
	private Container content;
	private JTextField nametxt;
	private boolean edit = false;
	private StarPanel rating;
	private int i;
	private int tmprating;
	private JTextArea preferencetxt;
	private Interface iface;
	public ArtistScherm(Interface iface)
	{
		edit = false;
		this.iface = iface;
		frame = new JFrame("Add artist");
		frame.setLocationRelativeTo(null);
		content = frame.getContentPane();
		content.setLayout(new GridLayout(4,1,10,8));
		
		content.add(initNaam(""));
		content.add(initRating(0));
		content.add(initWensen(""));
		content.add(initButtons());
		GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point p = GE.getCenterPoint();
        Point o= new Point((int) (p.getX() -150),(int) ((p.getY() -100)));
        frame.setLocation(o);
		frame.setSize(300, 200);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	public ArtistScherm(Interface iface, int i)
	{
		this.i = i;
		this.edit = true;
		this.iface = iface;
		frame = new JFrame("Edit Artist");
		frame.setLocationRelativeTo(null);
		content = frame.getContentPane();
		content.setLayout(new GridLayout(4,1,10,8));
		content.add(initNaam(iface.findArtist(i).getName()));
		content.add(initRating(iface.findArtist(i).getRating()));
		content.add(initWensen(iface.findArtist(i).getPreferences()));
		content.add(initButtons());
		GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point p = GE.getCenterPoint();
		Point o = new Point((int) (p.getX() -150),(int) ((p.getY() - 100)));
		frame.setLocation(o);
		frame.setSize(300,200);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public JPanel initWensen(String s)
	{
		JPanel Wensen = new JPanel();
		Wensen.setLayout(box(Wensen,'x'));
		JLabel wensen = new JLabel("Preferences:");
		Wensen.add(wensen);
		preferencetxt = new JTextArea(s);
		wensen.setLabelFor(preferencetxt);
		preferencetxt.setSize(20,20);
		JScrollPane wsScroll = new JScrollPane(preferencetxt);
		Wensen.add(wsScroll);
		return Wensen;
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
	
	public JPanel initNaam(String s)
	{
		JPanel naam = new JPanel();
		naam.setLayout(box(naam, 'x'));
		JLabel Naam = new JLabel("Artist name:");
		naam.add(Naam);
		nametxt = new JTextField(s);
		nametxt.setSize(20,1);
		Naam.setLabelFor(nametxt);
		naam.add(nametxt);
		return naam;
	}
	
	public JPanel initRating(int i)
	{
		rating = new StarPanel(i);
		rating.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) 
			{
				int s = e.getX()/32+1;
				if (s>5)
					s=5;
				
				rating.setScore(s);
				tmprating = s;
				rating.repaint();
			}
		});
		
		return rating;
	}
	
	public JPanel initButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(box(buttons, 'x'));
		JButton save = new JButton("Save");
		JButton cancel = new JButton("Cancel");
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				save(edit);
			}
		});
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		buttons.add(save);
		buttons.add(cancel);
		return buttons;
	}
	
	public void save(boolean edit1)
	{
		if(edit1)
			iface.editArtist(nametxt.getText(), tmprating, preferencetxt.getText(), i);
		else if(!edit1)
		 iface.newArtist(nametxt.getText(), tmprating, preferencetxt.getText());
		frame.dispose();
	}
}
