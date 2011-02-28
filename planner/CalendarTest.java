import javax.swing.*;
	import javax.swing.event.*;
	import javax.swing.table.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	 
	public class CalendarTest{
	    static JLabel lblMonth, lblYear;
	    static JButton btnPrev, btnNext;
	    static JTable tblCalendar;
	    static JComboBox cmbYear;
	    static JFrame frmMain;
	    static Container pane;
	    static DefaultTableModel mtblCalendar; //Table model
	    static JScrollPane stblCalendar; //The scrollpane
	    static JPanel pnlCalendar; //The panel
	    static int realDay, realMonth, realYear, currentMonth, currentYear;
	    
	    public CalendarTest()
	    {
	    	frmMain = new JFrame("Calendar application");
	    	
	    		lblMonth = new JLabel ("January");
	    		lblYear = new JLabel ("Change year:");
	    		cmbYear = new JComboBox();
	    		btnPrev = new JButton ("<<");
	    		btnNext = new JButton (">>");
	    		mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	    		tblCalendar = new JTable(mtblCalendar); //Table using the above model
	    		stblCalendar = new JScrollPane(tblCalendar); //The scrollpane of the above table
	    		pnlCalendar = new JPanel(null); //Create the "panel" to place components
	    		frmMain.setSize(330, 375); //Two arguments: width and height
	    		
	    		pane = frmMain.getContentPane();
	    		pane.setLayout(null);
	    		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
	    		pane.add(pnlCalendar);
	    		
	    		btnPrev.addActionListener(new ActionListener()
	    		{
	    			public void actionPerformed(ActionEvent e)
	    			{
	    				if(currentMonth == 0)
	    				{
	    					currentMonth = 11;
	    					currentYear = -1;
	    				}
	    				else
	    					currentMonth -= 1;
	    				refreshCalendar(currentMonth,currentYear);
	    			}
	    		});
	    		btnNext.addActionListener(new ActionListener()
	    		{
	    			public void actionPerformed(ActionEvent e)
	    			{
		    		if(currentMonth == 11)
		    		{
		    			currentMonth = 0;
		    			currentYear += 1;
		    		}
		    		else
		    			currentMonth += 1;
		    		refreshCalendar(currentMonth,currentYear);
	    			}
	    		});
	    			
	    		cmbYear.addActionListener(new ActionListener()
	    		{
	    			public void actionPerformed(ActionEvent e)
	    			{
		    				if(cmbYear.getSelectedItem() != null)
		    				{
		    					String b = cmbYear.getSelectedItem().toString();
		    					currentYear = Integer.parseInt(b);
		    					refreshCalendar(currentMonth,currentYear);
		    				}
	    			}
	    		});
	    		
	    		pnlCalendar.add(lblMonth);
	    		pnlCalendar.add(lblYear);
	    		pnlCalendar.add(cmbYear);
	    		pnlCalendar.add(btnPrev);
	    		pnlCalendar.add(btnNext);
	    		pnlCalendar.add(stblCalendar);
	    		
	    		pnlCalendar.setBounds(0,0,320,335);
	    		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2,25,100,25);
	    		lblYear.setBounds(10,305,80,20);
	    		cmbYear.setBounds(230,305,80,20);
	    		btnPrev.setBounds(10,25,50,25);
	    		btnNext.setBounds(260, 25, 50, 25);
	    		stblCalendar.setBounds(10,50,300,250);
	    		
	    		GregorianCalendar cal = new GregorianCalendar(Locale.FRANCE);
	    		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
	    		realMonth = cal.get(GregorianCalendar.MONTH);
	    		realYear = cal.get(GregorianCalendar.YEAR);
	    		
	    		currentMonth = realMonth;
	    		currentYear = realYear;
	    		
	    		String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	    		for(int i=0; i<7; i++)
	    		{
	    			mtblCalendar.addColumn(headers[i]);
	    		}
	    		
	    		tblCalendar.getParent().setBackground(Color.BLUE);
	    		
	    		tblCalendar.getTableHeader().setResizingAllowed(false);
	    		tblCalendar.getTableHeader().setReorderingAllowed(false);
	    		
	    		tblCalendar.setColumnSelectionAllowed(true);
	    		tblCalendar.setRowSelectionAllowed(true);
	    		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    		
	    		tblCalendar.setRowHeight(38);
	    		mtblCalendar.setColumnCount(7);
	    		mtblCalendar.setRowCount(6);
	    		
	 
	    		
	    		for(int i = realYear-100; i<realYear+100; i++)
	    		{
	    			cmbYear.addItem(String.valueOf(i));
	    		}
	    		
	    		refreshCalendar(realMonth,realYear);
	    		

	    		
	    		frmMain.setResizable(false);
	    		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    		frmMain.setAlwaysOnTop(true);
	    		frmMain.setVisible(true);
	    		
	    }
	    
	    public void refreshCalendar(int month, int year)
	    {
	    	String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    		    int nod, som; //Number Of Days, Start Of Month
	    		         
	    		    btnPrev.setEnabled(true); //Enable buttons at first
	    		    btnNext.setEnabled(true);
	    		    if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
	    		    if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
	    		    lblMonth.setText(months[month]); //Refresh the month label (at the top)
	    		    lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
	    		    cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
	    		    GregorianCalendar cal = new GregorianCalendar(year, month, 1);
	    		    nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	    		    som = cal.get(GregorianCalendar.DAY_OF_WEEK);
	    		    for (int i=0; i<6; i++)
	    		    {
	    		    	for (int j=0; j<7; j++)
	    		    	{
	    		    		mtblCalendar.setValueAt(null, i, j);
	    		    	}
	    		    }
	    		    
	    		    for (int i=1; i<=nod; i++)
	    		    {
	    		    	int row = new Integer((i+som-2)/7);
	    		    	int column  =  (i+som-2)%7;
	    		    	mtblCalendar.setValueAt(i, row, column);
	    		    }
	    		    
	    		    		        
	    		    
	    }
	}