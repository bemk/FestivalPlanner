import javax.swing.JFrame;

public class Simulator extends JFrame{
	
	public Simulator()
	{
		add(new Board());
		setTitle("Simulatie");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(640, 480);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    setResizable(false);
	}
	
	public static void main(String[] args)
	{
		new Simulator();
	}
	

}
