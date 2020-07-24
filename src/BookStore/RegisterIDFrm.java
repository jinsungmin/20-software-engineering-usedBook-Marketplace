package BookStore;

import User.User;
import java.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterIDFrm extends JFrame {
	
	private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnBack;
    private JPasswordField passText;
    private JTextField idText;
    private JTextField nameText;
    private JTextField phoneText;
    private JTextField emailText;
    
    public RegisterIDFrm(MainProcess main) {
    	// setting
        setTitle("register");
        setSize(400, 300);
        setResizable(false);
        setLocation(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        // panel
        JPanel panel = new JPanel();
        placeRegisterPanel(panel, main);
       
        
        // add
        add(panel);
       
        // visible
        setVisible(true);
    }
    
    public void placeRegisterPanel(JPanel panel, MainProcess main){
        panel.setLayout(null);    
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10, 10, 80, 25);
        panel.add(idLabel);
       
        JLabel passLabel = new JLabel("PW");
        passLabel.setBounds(10, 40, 80, 25);
        panel.add(passLabel);
        
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 70, 80, 25);
        panel.add(nameLabel);
        
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(10, 100, 80, 25);
        panel.add(phoneLabel);
        
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 130, 80, 25);
        panel.add(emailLabel);
       
        idText = new JTextField(20);
        idText.setBounds(100, 10, 160, 25);
        panel.add(idText);
       
        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        panel.add(passText);
        
        nameText = new JTextField(20);
        nameText.setBounds(100, 70, 160, 25);
        panel.add(nameText);
        
        phoneText = new JTextField(20);
        phoneText.setBounds(100, 100, 160, 25);
        panel.add(phoneText);
        
        emailText = new JTextField(20);
        emailText.setBounds(100, 130, 160, 25);
        panel.add(emailText);
        
        btnRegister = new JButton("Register");
        btnRegister.setBounds(220, 180, 100, 25);
        panel.add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRegisterCheck();
            }
        });
        
        btnBack = new JButton("Back");
        btnBack.setBounds(70, 180, 100, 25);
        panel.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBackCheck(main);
            }
        });
    }
    public void isRegisterCheck() {
        User user = new User();
        user.setuserID(idText.getText());
        user.setuserPW(String.valueOf(passText.getPassword()));
        user.setuserName(nameText.getText());
        user.setuserPhone(phoneText.getText());
        user.setuserEmail(emailText.getText());
        
        MainProcess.userList.add(user);
        
        try {
        	File file = new File("userList.txt");
        	FileWriter fw = new FileWriter(file, true);
        	String infor = idText.getText() + "/" + String.valueOf(passText.getPassword()) + "/" + nameText.getText() +
        			"/" + phoneText.getText() + "/" + emailText.getText() + "/" + user.getuserStatus() + "/\n";
        	fw.write(infor);
        	fw.flush();
        	fw.close();
        } catch(Exception e) {
        	e.getStackTrace();
        }
        
        idText.setText("");	// clear Screen
        passText.setText("");
        nameText.setText("");
        phoneText.setText("");
        emailText.setText("");
        
        JOptionPane.showMessageDialog(null, "Success Register");
    }
    
    public void isBackCheck(MainProcess main) {
		main.closeRegisterPage();
    }
    
}