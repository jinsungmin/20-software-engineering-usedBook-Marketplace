package Book;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BookStore.MainProcess;
import BookStore.UserFrm;
import User.User;
import BookStore.AdminFrm;
import Book.RegisterBookFrm;

public class SellingBookFrm extends JFrame {

	private JPanel panel = new JPanel();
	private JButton btnBack;
	private JButton btnSearch;
	private JButton btnBuy;
	private JButton btnDelete;
	private JButton btnModify;
	private JTextField infor;
	private JComboBox<String> searchCombo;
	private JTable table;
	private String text;
	private RegisterBookFrm registerBookFrm;
	
	public SellingBookFrm(UserFrm userFrm, User user) {
		// setting
		setTitle("Sale List");
		setSize(600, 500);
		setResizable(false);
		setLocation(400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		placeRegisterPanel(userFrm, user);
		
		placeUserBookPanel(userFrm, user);
		// add
		add(panel);

		// visible
		setVisible(true);
	}
	

	public void placeRegisterPanel(UserFrm userFrm, User user) {
		panel.setLayout(null);

		String header[] = { "Title", "ISBN", "Author", "Publisher", "PubYear", "Price", "Status", "Seller", "userID",
				"isStock" }; // table
		// label
		String contents[][] = new String[0][10]; // 테이블에 userList 추가

		DefaultTableModel model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(20, 10, 550, 140);
		panel.add(scrollpane);

		btnBack = new JButton("Back");
		btnBack.setBounds(30, 400, 100, 25);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				isBackCheck(userFrm);
			}
		});

		String search[] = { "Title", "ISBN", "Author","Publisher", "PubYear", "SellerID" };
		searchCombo = new JComboBox<String>(search);
		searchCombo.setBounds(20, 160, 80, 25);
		panel.add(searchCombo);

		infor = new JTextField(20);
		infor.setBounds(110, 160, 100, 25);
		panel.add(infor);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(220, 160, 80, 25);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchBook();
			}
		});

		btnBuy = new JButton("Buy");
		btnBuy.setBounds(490, 160, 80, 25);
		panel.add(btnBuy);
		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyBook(user);
				
			}
		});

	}

	public void placeUserBookPanel(UserFrm userFrm, User user) {
		panel.setLayout(null);

		JTable usertable;
		String header[] = { "Title", "ISBN", "Author", "Publisher", "PubYear", "Price", "Status", "Seller", "userID",
				"isStock" }; // table
		// label
		String contents[][] = new String[0][10]; // 테이블에 userList 추가

		DefaultTableModel model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		usertable = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(usertable);
		scrollpane.setBounds(20, 230, 550, 140);
		panel.add(scrollpane);

		selectUserOwnBook(usertable, user);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(490, 400, 80, 25);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usertable.getSelectedRow() >= 0) {
					deleteUserOwnBook(user, usertable);
				} else {
					JOptionPane.showMessageDialog(null, "Please select data from user table");
				}
			}
		});

		btnModify = new JButton("Modify");
		btnModify.setBounds(390, 400, 80, 25);
		panel.add(btnModify);
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usertable.getSelectedRow() >= 0) {
					showRegisterBookPage(registerBookFrm, userFrm, user, MainProcess.main, usertable,
							usertable.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null, "Please select data from user table");
				}
			}

		});
	}

	public void deleteUserOwnBook(User user, JTable usertable) {
		int index = compareBook(usertable);
		if (index >= 0) {
			MainProcess.bookList.remove(index);

			((DefaultTableModel) usertable.getModel()).removeRow(usertable.getSelectedRow());

			AdminFrm.reWriteFile("bookList.txt");
		}
	}

	public void selectUserOwnBook(JTable usertable, User user) {
		DefaultTableModel model = (DefaultTableModel) usertable.getModel();
		model.setNumRows(0);
		for (int i = 0; i < MainProcess.bookList.size(); i++) {
			if (user.getuserID().equals(MainProcess.bookList.get(i).getuserID())) {
				model.insertRow(0, new String[] { MainProcess.bookList.get(i).getbookTitle(),
						MainProcess.bookList.get(i).getbookISBN(), MainProcess.bookList.get(i).getbookAuthor(),
						MainProcess.bookList.get(i).getbookPublisher(), MainProcess.bookList.get(i).getbookPubYear(),
						MainProcess.bookList.get(i).getbookPrice(), MainProcess.bookList.get(i).getbookStatus(),
						MainProcess.bookList.get(i).getbookSeller(), MainProcess.bookList.get(i).getuserID(),
						MainProcess.bookList.get(i).getSale() });
				usertable.updateUI();
			}
		}
	}

	public void searchBook() {
		String search = searchCombo.getSelectedItem().toString();
		text = infor.getText();
		printTable(search, text);
	}

	public void buyBook(User user) {
		if (table.getSelectedRow() == -1) {
			return;
		} else {
			if (table.getValueAt(table.getSelectedRow(), 9).equals("OK")) {
				if (!table.getValueAt(table.getSelectedRow(), 8).equals(user.getuserID())) {
					if (compareBook(table) != -1) {
						changeSaleStatus(compareBook(table));
						JOptionPane.showMessageDialog(null, "Trading match complete");
					}
				} else {
					JOptionPane.showMessageDialog(null, "It's your book. Find another book.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Trading. Find another book.");
			}
		}
	}

	public static int compareBook(JTable table) {

		for (int i = 0; i < MainProcess.bookList.size(); i++) {
			if (MainProcess.bookList.get(i).getbookTitle()
					.equals(table.getValueAt(table.getSelectedRow(), 0)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getbookISBN()
					.equals(table.getValueAt(table.getSelectedRow(), 1)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getbookAuthor()
					.equals(table.getValueAt(table.getSelectedRow(), 2)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getbookPublisher()
					.equals(table.getValueAt(table.getSelectedRow(), 3)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getbookPubYear()
					.equals(table.getValueAt(table.getSelectedRow(), 4)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getbookPrice()
					.equals(table.getValueAt(table.getSelectedRow(), 5)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getbookStatus()
					.equals(table.getValueAt(table.getSelectedRow(), 6)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getbookSeller()
					.equals(table.getValueAt(table.getSelectedRow(), 7)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getuserID().equals(table.getValueAt(table.getSelectedRow(), 8)) == false) {
				continue;
			}
			if (MainProcess.bookList.get(i).getSale().equals(table.getValueAt(table.getSelectedRow(), 9)) == false) {
				continue;
			}
			return i;
		}
		return -1;
	}

	public void changeSaleStatus(int index) {
		MainProcess.bookList.get(index).setSale("isSold");
		AdminFrm.reWriteFile("bookList.txt");
		searchBook();
	}
	
	public void printTable(String search, String text) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		if (search.equals("Title")) {
			for (int i = 0; i < MainProcess.bookList.size(); i++) {
				if (MainProcess.bookList.get(i).getbookTitle().equals(text)) {
					insert(model, i);
				}
			}
		}
		if (search.equals("ISBN")) {
			for (int i = 0; i < MainProcess.bookList.size(); i++) {
				if (MainProcess.bookList.get(i).getbookISBN().equals(text)) {
					insert(model, i);
				}
			}
		}
		if (search.equals("Author")) {
			for (int i = 0; i < MainProcess.bookList.size(); i++) {
				if (MainProcess.bookList.get(i).getbookAuthor().equals(text)) {
					insert(model, i);
				}
			}
		}
		if (search.equals("Publisher")) {
			for (int i = 0; i < MainProcess.bookList.size(); i++) {
				if (MainProcess.bookList.get(i).getbookPublisher().equals(text)) {
					insert(model, i);
				}
			}
		}
		if (search.equals("PubYear")) {
			for (int i = 0; i < MainProcess.bookList.size(); i++) {
				if (MainProcess.bookList.get(i).getbookPubYear().equals(text)) {
					insert(model, i);
				}
			}
		}
		if (search.equals("SellerID")) {
			for (int i = 0; i < MainProcess.bookList.size(); i++) {
				if (MainProcess.bookList.get(i).getuserID().equals(text)) {
					insert(model, i);
				}
			}
		}
		if (table.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "No Data");
		}
	}
	
	public void insert(DefaultTableModel model, int index) {
		model.insertRow(0, new String[] { MainProcess.bookList.get(index).getbookTitle(),
				MainProcess.bookList.get(index).getbookISBN(), MainProcess.bookList.get(index).getbookAuthor(),
				MainProcess.bookList.get(index).getbookPublisher(),
				MainProcess.bookList.get(index).getbookPubYear(), MainProcess.bookList.get(index).getbookPrice(),
				MainProcess.bookList.get(index).getbookStatus(), MainProcess.bookList.get(index).getbookSeller(),
				MainProcess.bookList.get(index).getuserID(), MainProcess.bookList.get(index).getSale() });
		table.updateUI();
	}
	
	public void isBackCheck(UserFrm userFrm) {
		this.dispose();
		userFrm.closeSellingBookPage();
	}

	public void showRegisterBookPage(RegisterBookFrm registerBookFrm, UserFrm userFrm, User user, MainProcess main,
			JTable table, int row) {
		this.setVisible(false);
		this.registerBookFrm = new RegisterBookFrm(this, userFrm, user, main, table, row);
	}

	public void closeRegisterBookPage(UserFrm userFrm, User user) {
		registerBookFrm.dispose();
		this.dispose();
		new SellingBookFrm(userFrm, user);
		// this.setVisible(true);
	}

}
