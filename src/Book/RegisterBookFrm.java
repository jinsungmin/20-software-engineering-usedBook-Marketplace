package Book;

import User.User;
import BookStore.MainProcess;
import BookStore.UserFrm;
import BookStore.AdminFrm;
import Book.SellingBookFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterBookFrm extends JFrame {

	private JTextField titleText;
	private JTextField isbnText;
	private JTextField authorText;
	private JTextField publisherText;
	private JTextField pubyearText;
	private JTextField priceText;
	private JButton btnRegister;
	private JButton btnBack;
	private JPanel panel = new JPanel();
	private JComboBox<String> statusCombo;
	private SellingBookFrm sellingBookFrm;

	public RegisterBookFrm(UserFrm userFrm, User user, MainProcess main) {
		// setting
		setTitle("register");
		setSize(300, 400);
		setResizable(false);
		setLocation(400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		placeRegisterPanel(user, main);
		
		inputbtnBack(userFrm);
		
		// add
		add(panel);

		// visible
		setVisible(true);
	}
	
	public RegisterBookFrm(SellingBookFrm sellingBookFrm, UserFrm userFrm,User user, MainProcess main, JTable table, int row) {
		// setting
		setTitle("register");
		setSize(300, 400);
		setResizable(false);
		setLocation(400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		placeRegisterPanel(user, main, table ,row);
		
		inputbtnBack(sellingBookFrm, userFrm, user);
		// add
		add(panel);

		// visible
		setVisible(true);
	}
	
	public void placeRegisterPanel(User user, MainProcess main){
        panel.setLayout(null);    
        
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 30, 80, 25);
        panel.add(titleLabel);
       
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setBounds(10, 60, 80, 25);
        panel.add(isbnLabel);
        
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(10, 90, 80, 25);
        panel.add(authorLabel);
        
        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(10, 120, 80, 25);
        panel.add(publisherLabel);
        
        JLabel pubyearLabel = new JLabel("PubYear:");
        pubyearLabel.setBounds(10, 150, 80, 25);
        panel.add(pubyearLabel);
        
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 180, 80, 25);
        panel.add(priceLabel);
        
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 210, 80, 25);
        panel.add(statusLabel);
        
        titleText = new JTextField(20);
        titleText.setBounds(90, 30, 150, 25);
        panel.add(titleText);
       
        isbnText = new JTextField(20);
        isbnText.setBounds(90, 60, 150, 25);
        panel.add(isbnText);
        
        authorText = new JTextField(20);
        authorText.setBounds(90, 90, 150, 25);
        panel.add(authorText);
        
        publisherText = new JTextField(20);
        publisherText.setBounds(90, 120, 150, 25);
        panel.add(publisherText);
        
        pubyearText = new JTextField(20);
        pubyearText.setBounds(90, 150, 150, 25);
        panel.add(pubyearText);
        
        priceText = new JTextField(20);
        priceText.setBounds(90, 180, 150, 25);
        panel.add(priceText);
        
        String status [] = { "Excellent", "Good", "Fair" };
        statusCombo = new JComboBox<String>(status);
        statusCombo.setSelectedIndex(0);
        statusCombo.setBounds(90, 210, 150, 25);
        panel.add(statusCombo);
        
        btnRegister = new JButton("Register");
        btnRegister.setBounds(160, 260, 100, 25);
        panel.add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerBook(user, main, false);
            }
        });
       
    }
	
	public void placeRegisterPanel(User user, MainProcess main, JTable table, int row){
        panel.setLayout(null);    
        
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 30, 80, 25);
        panel.add(titleLabel);
       
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setBounds(10, 60, 80, 25);
        panel.add(isbnLabel);
        
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(10, 90, 80, 25);
        panel.add(authorLabel);
        
        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(10, 120, 80, 25);
        panel.add(publisherLabel);
        
        JLabel pubyearLabel = new JLabel("PubYear:");
        pubyearLabel.setBounds(10, 150, 80, 25);
        panel.add(pubyearLabel);
        
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 180, 80, 25);
        panel.add(priceLabel);
        
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 210, 80, 25);
        panel.add(statusLabel);
        
        titleText = new JTextField(20);
        titleText.setText((String)table.getValueAt(row, 0));
        titleText.setBounds(90, 30, 150, 25);
        panel.add(titleText);
       
        isbnText = new JTextField(20);
        isbnText.setBounds(90, 60, 150, 25);
        isbnText.setText((String)table.getValueAt(row, 1));
        panel.add(isbnText);
        
        authorText = new JTextField(20);
        authorText.setBounds(90, 90, 150, 25);
        authorText.setText((String)table.getValueAt(row, 2));
        panel.add(authorText);
        
        publisherText = new JTextField(20);
        publisherText.setBounds(90, 120, 150, 25);
        publisherText.setText((String)table.getValueAt(row, 3));
        panel.add(publisherText);
        
        pubyearText = new JTextField(20);
        pubyearText.setBounds(90, 150, 150, 25);
        pubyearText.setText((String)table.getValueAt(row, 4));
        panel.add(pubyearText);
        
        priceText = new JTextField(20);
        priceText.setBounds(90, 180, 150, 25);
        priceText.setText((String)table.getValueAt(row, 5));
        panel.add(priceText);
        
        String status [] = { "Excellent", "Good", "Fair" };
        statusCombo = new JComboBox<String>(status);
        for(int i = 0; i< status.length; i++) {
        	if(status[i].equals((String)table.getValueAt(row, 6))) {
        		statusCombo.setSelectedIndex(i);
        		break;
        	}
        }
        statusCombo.setBounds(90, 210, 150, 25);
        panel.add(statusCombo);
        
        btnRegister = new JButton("Register");
        btnRegister.setBounds(160, 260, 100, 25);
        panel.add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(SellingBookFrm.compareBook(table) != -1) {
            		MainProcess.bookList.remove(SellingBookFrm.compareBook(table));
            		
            	}
                registerBook(user, main, true);
            }
        });
       
    }
	
	public void inputbtnBack(UserFrm userFrm) {
		btnBack = new JButton("Back");
        btnBack.setBounds(30, 260, 100, 25);
        panel.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBackCheck(userFrm);
            }
        });
	}
	
	public void inputbtnBack(SellingBookFrm sellingBookFrm, UserFrm userFrm, User user) {
		btnBack = new JButton("Back");
        btnBack.setBounds(30, 260, 100, 25);
        panel.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBackCheck(sellingBookFrm, userFrm, user);
            }
        });
	}

	public void registerBook(User user, MainProcess main, boolean bool) {
		Book book = new Book();
		book.setbookTitle(titleText.getText());
		book.setbookISBN(isbnText.getText());
		book.setbookAuthor(authorText.getText());
		book.setbookPublisher(publisherText.getText());
		book.setbookPubYear(pubyearText.getText());
		book.setbookPrice(priceText.getText());
		book.setbookStatus(statusCombo.getSelectedItem().toString());
		book.setbookSeller(user.getuserName());
		book.setuserID(user.getuserID());
		MainProcess.bookList.add(book);
		
		AdminFrm.reWriteFile("bookList.txt");
		
		titleText.setText(""); // clear Screen
		isbnText.setText("");
		authorText.setText("");
		publisherText.setText("");
		pubyearText.setText("");
		priceText.setText("");

		JOptionPane.showMessageDialog(null, "Success Register");
	}

	public void isBackCheck(UserFrm userFrm) {
		userFrm.closeRegisterBookPage();
	}
	
	public void isBackCheck(SellingBookFrm sellingBookFrm, UserFrm userFrm, User user) {
		sellingBookFrm.closeRegisterBookPage(userFrm, user);
	}

}
