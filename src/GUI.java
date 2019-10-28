import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTable resultsTable;

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
		
		JTextPane sqlCommand = new JTextPane();
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
		
		JComboBox jdbcComboBox = new JComboBox();
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
		
		JComboBox databaseComboBox = new JComboBox();
		databaseComboBox.setModel(new DefaultComboBoxModel(new String[] {"jdbc:mysql://localhost:3312/project3"}));
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
		
		Button connectButton = new Button("Connect to Database");
		connectButton.setFont(new Font("Dialog", Font.BOLD, 12));
		connectButton.setForeground(Color.YELLOW);
		connectButton.setBackground(Color.BLUE);
		connectButton.setBounds(240, 204, 165, 24);
		contentPane.add(connectButton);
		
		Button clearSQLButton = new Button("Clear SQL Command");
		clearSQLButton.setFont(new Font("Dialog", Font.BOLD, 12));
		clearSQLButton.setActionCommand("Clear SQL Command");
		clearSQLButton.setBounds(411, 205, 165, 24);
		contentPane.add(clearSQLButton);
		clearSQLButton.setForeground(Color.RED);
		clearSQLButton.setBackground(Color.WHITE);
		
		Button executeSQLButton = new Button("Execute SQL Command");
		executeSQLButton.setForeground(Color.BLACK);
		executeSQLButton.setFont(new Font("Dialog", Font.BOLD, 12));
		executeSQLButton.setBackground(Color.GREEN);
		executeSQLButton.setActionCommand("");
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
		
		JButton btnClearResultWindow = new JButton("Clear Result Window");
		btnClearResultWindow.setForeground(Color.BLACK);
		btnClearResultWindow.setBackground(Color.YELLOW);
		btnClearResultWindow.setBounds(10, 645, 202, 25);
		contentPane.add(btnClearResultWindow);
	}
}
