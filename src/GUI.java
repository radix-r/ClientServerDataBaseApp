import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Button;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTable resultsTable;
	private JComboBox jdbcComboBox;
	private JTextPane sqlCommand;
	private JComboBox databaseComboBox;
	private JButton connectButton;
	private JButton clearResultButton;
	private JButton clearSQLButton;
	private Button executeSQLButton;
	//static final String DEFAULT_QUERY = "SELECT * FROM bikes";
	private ResultSetTableModel tableModel;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("SQL Client GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 712);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterDatabaseInformation = new JLabel("Enter Database Information");
		lblEnterDatabaseInformation.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEnterDatabaseInformation.setForeground(Color.BLUE);
		lblEnterDatabaseInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterDatabaseInformation.setBounds(12, 0, 238, 31);
		contentPane.add(lblEnterDatabaseInformation);
		
		JLabel lblNewLabel = new JLabel("Enter An SQL Command");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(420, 0, 327, 31);
		contentPane.add(lblNewLabel);
		
		sqlCommand = new JTextPane();
		sqlCommand.setBounds(420, 26, 327, 158);
		contentPane.add(sqlCommand);
		
		Panel jdbcPanel = new Panel();
		jdbcPanel.setBackground(Color.GRAY);
		jdbcPanel.setBounds(10, 34, 90, 30);
		contentPane.add(jdbcPanel);
		
		JLabel lblJdbcDriver = new JLabel("JDBC Driver");
		lblJdbcDriver.setForeground(Color.BLACK);
		jdbcPanel.add(lblJdbcDriver);
		lblJdbcDriver.setFont(new Font("FreeSans", Font.BOLD, 14));
		lblJdbcDriver.setBackground(Color.DARK_GRAY);
		
		jdbcComboBox = new JComboBox();
		jdbcComboBox.setModel(new DefaultComboBoxModel(new String[] {"com.mysql.cj.jdbc.Driver"}));
		jdbcComboBox.setBounds(99, 34, 309, 30);
		contentPane.add(jdbcComboBox);
		
		Panel databasePanel = new Panel();
		databasePanel.setBackground(Color.GRAY);
		databasePanel.setBounds(10, 74, 90, 30);
		contentPane.add(databasePanel);
		
		JLabel lblDatabase = new JLabel("  Database URL");
		databasePanel.add(lblDatabase);
		lblDatabase.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatabase.setToolTipText("Database URL");
		lblDatabase.setForeground(Color.BLACK);
		lblDatabase.setFont(new Font("FreeSans", Font.BOLD, 14));
		lblDatabase.setBackground(Color.DARK_GRAY);
		
		databaseComboBox = new JComboBox();
		databaseComboBox.setModel(new DefaultComboBoxModel(new String[] {"jdbc:mysql://localhost:3306/project3"}));
		databaseComboBox.setBounds(99, 74, 309, 30);
		contentPane.add(databaseComboBox);
		
		Panel UsernamePanel = new Panel();
		UsernamePanel.setBackground(Color.GRAY);
		UsernamePanel.setBounds(10, 114, 90, 30);
		contentPane.add(UsernamePanel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setToolTipText("");
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setForeground(Color.BLACK);
		usernameLabel.setFont(new Font("FreeSans", Font.BOLD, 14));
		usernameLabel.setBackground(Color.DARK_GRAY);
		UsernamePanel.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(99, 114, 309, 30);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		Panel passwordPanel = new Panel();
		passwordPanel.setBackground(Color.GRAY);
		passwordPanel.setBounds(10, 154, 90, 30);
		contentPane.add(passwordPanel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setToolTipText("");
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("FreeSans", Font.BOLD, 14));
		passwordLabel.setBackground(Color.DARK_GRAY);
		passwordPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 154, 309, 30);
		contentPane.add(passwordField);
		
		Panel statusPanel = new Panel();
		statusPanel.setBackground(Color.BLACK);
		statusPanel.setBounds(10, 204, 220, 24);
		contentPane.add(statusPanel);
		
		JLabel statusLabel = new JLabel("No Connection Now");
		statusLabel.setForeground(Color.RED);
		statusPanel.add(statusLabel);
		
		connectButton = new JButton("Connect to Database");
		connectButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		connectButton.setForeground(Color.YELLOW);
		connectButton.setBackground(Color.BLUE);
		connectButton.setBounds(240, 204, 165, 24);
		contentPane.add(connectButton);
		
		clearSQLButton = new JButton("Clear SQL Command");
		clearSQLButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		clearSQLButton.setForeground(Color.RED);
		clearSQLButton.setBackground(Color.WHITE);
		clearSQLButton.setBounds(411, 204, 165, 24);
		contentPane.add(clearSQLButton);
		
		executeSQLButton = new Button("Execute SQL Command");
		executeSQLButton.setForeground(Color.BLACK);
		executeSQLButton.setBackground(Color.GREEN);
		executeSQLButton.setBounds(584, 204, 165, 24);
		contentPane.add(executeSQLButton);
		
		JLabel resultLabel = new JLabel("SQL Execution Result Window");
		resultLabel.setHorizontalAlignment(SwingConstants.LEFT);
		resultLabel.setForeground(Color.BLUE);
		resultLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		resultLabel.setBounds(12, 234, 276, 31);
		contentPane.add(resultLabel);
		
		resultsTable = new JTable();
		resultsTable.setBackground(Color.LIGHT_GRAY);
		resultsTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultsTable.setBounds(10, 260, 735, 380);
		contentPane.add(resultsTable);
		
		clearResultButton = new JButton("Clear Result Window");
		clearResultButton.setForeground(Color.BLACK);
		clearResultButton.setBackground(Color.YELLOW);
		clearResultButton.setBounds(10, 645, 202, 25);
		contentPane.add(clearResultButton);
		
		
		
		displayQueryResults();
	}


	// create ResultSetTableModel and GUI
   public void displayQueryResults() 
   {   
      //super( "Displaying Query Results" );
        
      // create ResultSetTableModel and display database table
      
    	  /*
    	 // get connection info
	  	String username = usernameField.getText();
    	char[] password = passwordField.getPassword();
    	String jdbc = jdbcComboBox.getSelectedItem().toString();
    	String database = databaseComboBox.getSelectedItem().toString();
         // create TableModel for results of query SELECT * FROM bikes
    	// String url, String username, char[] password
         tableModel = new ResultSetTableModel( DEFAULT_QUERY,database, username, password );
		*/
         
         
         
         
 
         
         

         
         
         
         // create event listener for submitButton
         executeSQLButton.addActionListener( 
         
            new ActionListener() 
            {
               // pass query to table model
               public void actionPerformed( ActionEvent event )
               {
            	   // get connection info
	           	  	String username = usernameField.getText();
	               	char[] password = passwordField.getPassword();
	               	String jdbc = jdbcComboBox.getSelectedItem().toString();
	               	String database = databaseComboBox.getSelectedItem().toString();
                    // create TableModel for results of query SELECT * FROM bikes
	               	try 
	                {
	               		//System.out.println("try connect");
	               		//System.out.printf("%s \n%s \n%s \n%s \n", username,new String(password),jdbc,database);
	               		tableModel = new ResultSetTableModel( sqlCommand.getText(),database,jdbc, username, password );
	                }catch ( ClassNotFoundException classNotFound ) 
	                {
	                    JOptionPane.showMessageDialog( null, 
	                       "MySQL driver not found", "Driver not found",
	                       JOptionPane.ERROR_MESSAGE );
	                    
	                    System.exit( 1 ); // terminate application
	                 } // end catch
	                 catch ( SQLException sqlException ) 
	                 {
	                	 
						JOptionPane.showMessageDialog( null, sqlException.getMessage(), 
						   "Database error", JOptionPane.ERROR_MESSAGE );
						  
						// ensure database connection is closed
						// tableModel.disconnectFromDatabase();
	                    return;
	                    //System.exit( 1 );   // terminate application
	                 } // end catch
	               	
                  
                  // create JTable delegate for tableModel
                  System.out.println("table update");
                  resultsTable.setModel(tableModel );
               } // end actionPerformed
            }  // end ActionListener inner class         
         ); // end call to addActionListener

         setSize( 798, 720 ); // set window size
         setVisible( true ); // display window  
      
      
      
      // dispose of window when user quits application (this overrides
      // the default of HIDE_ON_CLOSE)
      setDefaultCloseOperation( DISPOSE_ON_CLOSE );
      
      // ensure database connection is closed when user quits application
      addWindowListener(new WindowAdapter() 
         {
            // disconnect from database and exit when window has closed
            public void windowClosed( WindowEvent event )
            {
               tableModel.disconnectFromDatabase();
               System.exit( 0 );
            } // end method windowClosed
         } // end WindowAdapter inner class
      ); // end call to addWindowListener
   } // end DisplayQueryResults constructor
   


}
