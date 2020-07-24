package BookStore;

import java.util.ArrayList;

import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import User.User;
import Book.Book;

public class AdminFrm extends JFrame {
	private JButton btnAct;
	// panel
	JPanel panel = new JPanel();

	public AdminFrm(MainProcess main) {
		setTitle("administer");
		setSize(600, 500);
		setLocation(400, 100);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel.setLayout(null);

		initBookTable(MainProcess.bookList);

		initUserTable(MainProcess.userList);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(30, 430, 80, 20);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isBackCheck(main);
			}
		});

		// add
		add(panel);

		// visible
		setVisible(true);
	}

	public void initBookTable(ArrayList<Book> bookList) {
		String header[] = { "Title", "ISBN", "Author", "Publisher", "PubYear", "Price", "Status", "Seller", "userID",
				"isStock" }; // table
		// label
		String contents[][] = new String[bookList.size()][10]; // 테이블에 bookList 추가

		for (int i = 0; i < bookList.size(); i++) {
			contents[i][0] = bookList.get(i).getbookTitle();
			contents[i][1] = bookList.get(i).getbookISBN();
			contents[i][2] = bookList.get(i).getbookAuthor();
			contents[i][3] = bookList.get(i).getbookPublisher();
			contents[i][4] = bookList.get(i).getbookPubYear();
			contents[i][5] = bookList.get(i).getbookPrice();
			contents[i][6] = bookList.get(i).getbookStatus();
			contents[i][7] = bookList.get(i).getbookSeller();
			contents[i][8] = bookList.get(i).getuserID();
			contents[i][9] = bookList.get(i).getSale();
		}

		DefaultTableModel model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		JTable table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(70, 200, 450, 180);

		JButton deleteBtn = new JButton("삭제");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					return;
				} else {
					System.out.println(table.getSelectedRow());
					delete(table.getSelectedRow());
				}
			}
		});

		deleteBtn.setBounds(440, 430, 80, 20);

		panel.add(deleteBtn);

		panel.add(scrollpane);

	}

	public void initUserTable(ArrayList<User> userList) {
		String header[] = { "ID", "PW", "Name", "Phone", "Email", "Status" }; // table label
		String contents[][] = new String[userList.size()][6]; // 테이블에 userList 추가
		for (int i = 0; i < userList.size(); i++) {
			contents[i][0] = userList.get(i).getuserID();
			contents[i][1] = userList.get(i).getuserPW();
			contents[i][2] = userList.get(i).getuserName();
			contents[i][3] = userList.get(i).getuserPhone();
			contents[i][4] = userList.get(i).getuserEmail();
			contents[i][5] = userList.get(i).getuserStatus();
		}

		DefaultTableModel model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		JTable table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(70, 10, 450, 140);
		panel.add(scrollpane);

		JButton convertBtn = new JButton("변환");
		convertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() == -1) {
					return;
				} else {
					if (table.getValueAt(table.getSelectedRow(), 5).equals("activated")) {
						MainProcess.userList.get(table.getSelectedRow()).setuserStatus("deactivated");
						table.setValueAt("deactivated", table.getSelectedRow(), 5);

						reWriteFile("userList.txt");
					} else {
						MainProcess.userList.get(table.getSelectedRow()).setuserStatus("activated");
						table.setValueAt("activated", table.getSelectedRow(), 5);

						reWriteFile("userList.txt");
					}
				}
			}
		});
		convertBtn.setBounds(440, 160, 80, 20);
		panel.add(convertBtn);

		JButton deleteBtn = new JButton("삭제");
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() == -1) {
					return;
				} else {
					if (table.getValueAt(table.getSelectedRow(), 5).equals("deactivated")) {

						for (int i = 0; i < MainProcess.bookList.size(); i++) {
							if (MainProcess.bookList.get(i).getuserID()
									.equals(table.getValueAt(table.getSelectedRow(), 0))) {
								MainProcess.bookList.remove(i);
								i--;
							}
						}
						initBookTable(MainProcess.bookList);

						userList.remove(table.getSelectedRow());
						model.removeRow(table.getSelectedRow());

						reWriteFile("userList.txt");
						reWriteFile("bookList.txt");
					} else {
						JOptionPane.showMessageDialog(null, "This User status is activated.");
					}

				}
			}
		});
		deleteBtn.setBounds(70, 160, 80, 20);
		panel.add(deleteBtn);

	}

	public void delete(int index) {
		MainProcess.bookList.remove(index);
	
		reWriteFile("bookList.txt");
		initBookTable(MainProcess.bookList);

		JOptionPane.showMessageDialog(null, "Delete Success");
	}

	public static void reWriteFile(String file) {
		if (file.equals("userList.txt")) {
			try {
				File userFile = new File(file);

				FileWriter fw = new FileWriter(userFile);
				for (int i = 0; i < MainProcess.userList.size(); i++) {
					String infor = MainProcess.userList.get(i).getuserID() + "/"
							+ MainProcess.userList.get(i).getuserPW() + "/" + MainProcess.userList.get(i).getuserName()
							+ "/" + MainProcess.userList.get(i).getuserPhone() + "/"
							+ MainProcess.userList.get(i).getuserEmail() + "/"
							+ MainProcess.userList.get(i).getuserStatus() + "/\n";
					fw.write(infor);
				}
				fw.flush();
				fw.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		if (file.equals("bookList.txt")) {
			try {
				File bookFile = new File(file);

				FileWriter fw = new FileWriter(bookFile);
				for (int i = 0; i < MainProcess.bookList.size(); i++) {
					String infor = MainProcess.bookList.get(i).getbookTitle() + "/"
							+ MainProcess.bookList.get(i).getbookISBN() + "/"
							+ MainProcess.bookList.get(i).getbookAuthor() + "/"
							+ MainProcess.bookList.get(i).getbookPublisher() + "/"
							+ MainProcess.bookList.get(i).getbookPubYear() + "/"
							+ MainProcess.bookList.get(i).getbookPrice() + "/"
							+ MainProcess.bookList.get(i).getbookStatus() + "/"
							+ MainProcess.bookList.get(i).getbookSeller() + "/"
							+ MainProcess.bookList.get(i).getuserID() + "/" + MainProcess.bookList.get(i).getSale()
							+ "/\n";
					fw.write(infor);
				}
				fw.flush();
				fw.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

	public void isBackCheck(MainProcess main) {
		main.closeAdminPage();
	}

}
