import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class StarPanel extends JPanel
{
	private ImageIcon star_gray = new ImageIcon(this.getClass().getResource("Images/star_gray.png"));
	private ImageIcon star_yellow = new ImageIcon(this.getClass().getResource("Images/star_yellow.png"));
	private int score;
	
	public StarPanel()
	{
		this(0);
		
	}
	
	public StarPanel(int score)
	{
		//System.out.println(score);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		fillPanel();
		this.score = score;
		
		
	}
	
	private void fillPanel()
	{
		JPanel rating = new JPanel();
    	rating.setLayout(new GridLayout(1,5,0,0));
    	JLabel star1 = new JLabel(star_gray);
    	JLabel star2 = new JLabel(star_yellow);
    	JLabel star3 = new JLabel(star_gray);
    	JLabel star4 = new JLabel(star_yellow);
    	JLabel star5 = new JLabel(star_gray); 	
    	rating.add(star1);
    	rating.add(star2);
    	rating.add(star3);
    	rating.add(star4);
    	rating.add(star5);
	}
	
	public void setScore(int i)
	{
		this.score = i;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
	
		int i = 0;
		for(;i<score;i++)
		{
			if(i >= 5)
				break;
			star_yellow.paintIcon(this, g2, 32*i, 7);
		}
		for(;i<5;i++)
		{
			star_gray.paintIcon(this, g2, 32*i, 7) ;
		}
		
	}
}
