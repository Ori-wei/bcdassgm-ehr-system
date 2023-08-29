package Clinician;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginClinician {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginClinician window = new LoginClinician();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginClinician() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 574, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 548, 356);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbSystem = new JLabel("EHR System");
		lbSystem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbSystem.setBounds(214, 31, 145, 33);
		panel.add(lbSystem);
		
		JLabel lbTitle = new JLabel("Login Clinician");
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTitle.setBounds(224, 74, 135, 33);
		panel.add(lbTitle);
		
		JLabel lbUsername = new JLabel("Username: ");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbUsername.setBounds(76, 151, 85, 34);
		panel.add(lbUsername);
		
		JLabel lbPassword = new JLabel("Password: ");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbPassword.setBounds(76, 209, 85, 34);
		panel.add(lbPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(160, 151, 157, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(160, 209, 157, 34);
		panel.add(textField_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(170, 287, 105, 33);
		panel.add(btnLogin);
	}
}
