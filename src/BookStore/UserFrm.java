package BookStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import User.User;
import Book.Book;
import Book.RegisterBookFrm;
import Book.SellingBookFrm;



public class UserFrm extends JFrame {
	RegisterBookFrm registerBookFrm;
	SellingBookFrm sellingBookFrm;
	
	private JButton btnAdd;
	private JButton btnBack;
	// panel
	JPanel panel = new JPanel();
	
	public UserFrm(MainProcess main, User user) {
		setTitle("user");
		setSize(300, 300);
		setLocation(500, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel.setLayout(null);
		
		btnBack = new JButton("Back");
        btnBack.setBounds(20, 230, 80, 20);
        panel.add(btnBack);
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBackCheck(main);
            }
        });
        
        btnAdd = new JButton("도서 구매");
        btnAdd.setBounds(30, 100, 100, 40);
        panel.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSellingBookPage(sellingBookFrm, user);
            }
        });
        
        btnAdd = new JButton("판매 등록");
        btnAdd.setBounds(160, 100, 100, 40);
        panel.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	showRegisterBookPage(registerBookFrm, user, main);
            }
        });
        
		// add
		add(panel);

		// visible
		setVisible(true);
	}
	public void showSellingBookPage(SellingBookFrm sellingBookFrm, User user){
		this.setVisible(false); // 로그인창닫기
		this.sellingBookFrm = new SellingBookFrm(this, user); // 유저프레임 오픈
	}
	
	public void closeSellingBookPage() {
		sellingBookFrm.dispose();
		this.setVisible(true);	
	}
	
	public void showRegisterBookPage(RegisterBookFrm registerBookFrm, User user, MainProcess main){
		this.setVisible(false);
	    this.registerBookFrm = new RegisterBookFrm(this, user, main);
	}
	
	public void closeRegisterBookPage() {
		registerBookFrm.dispose();
		this.setVisible(true);	
	}
	
	public void isBackCheck(MainProcess main) {
		main.closeUserPage();
    }
	
}

