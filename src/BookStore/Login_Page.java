package BookStore;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import User.User;

public class Login_Page extends JFrame {
	private MainProcess main;
	private AdminFrm adminFrm;
	private RegisterIDFrm registerFrm;

	private JButton btnLogin;
	private JButton btnRegister;
	private JPasswordField passText;
	private JTextField userText;
	private boolean bLoginCheck;
	private String loginType;

	public Login_Page(MainProcess main) {
		// setting
		setTitle("login");
		setSize(280, 150);
		setResizable(false);
		setLocation(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();
		placeLoginPanel(panel);

		// add
		add(panel);

		// visible
		setVisible(true);
	}

	public void placeLoginPanel(JPanel panel) {
		panel.setLayout(null);
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JLabel passLabel = new JLabel("Pass");
		passLabel.setBounds(10, 40, 80, 25);
		panel.add(passLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		passText = new JPasswordField(20);
		passText.setBounds(100, 40, 160, 25);
		panel.add(passText);
		passText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck(main.userList);
				isRegisterCheck();
			}
		});

		btnRegister = new JButton("Register");
		btnRegister.setBounds(10, 80, 100, 25);
		panel.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isRegisterCheck();
			}
		});

		btnLogin = new JButton("Login");
		btnLogin.setBounds(160, 80, 100, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck(main.userList);
			}
		});
	}

	public void isRegisterCheck() {
		main.customerRegister(); // ����â �޼ҵ带 �̿��� â����
	}

	public void isLoginCheck(ArrayList<User> userList) {
		bLoginCheck = false;

		if (userText.getText().equals("admin") && new String(passText.getPassword()).equals("nayana")) {
			userText.setText("");
			passText.setText("");
			JOptionPane.showMessageDialog(null, "Login");
			bLoginCheck = true;
			loginType = "admin";
		}

		// �α��� �����̶�� �Ŵ���â ����
		if (isLogin() && loginType.equals("admin")) {
			main.showAdminPage(); // ����â �޼ҵ带 �̿��� â����
		}

		int index = 0;
		while (index < userList.size()) { // ��ϵ� ID�� ��ȸ�Ͽ� ��ġ�ϸ� �α���
			if (userText.getText().equals(userList.get(index).getuserID())
					&& new String(passText.getPassword()).equals(userList.get(index).getuserPW())) {
				if (userList.get(index).getuserStatus().equals("deactivated")) {
					break;
				} else {
					userText.setText("");
					passText.setText("");
					JOptionPane.showMessageDialog(null, "Login");
					bLoginCheck = true;
					loginType = "user";
				}
				break;
			}
			index++;
		}
		// �α��� �����̶�� ����â ����
		if (isLogin() && loginType.equals("user")) {
			main.showUserPage(userList.get(index)); // ����â �޼ҵ带 �̿��� â����
		}
		if (!isLogin()) {
			if (userList.get(index).getuserStatus().equals("deactivated"))
				JOptionPane.showMessageDialog(null, "This ID is deactivated");
			else {
				JOptionPane.showMessageDialog(null, "Invalid ID or PW");
			}
		}

	}

	// mainProcess�� ����
	public void setMain(MainProcess main) {
		this.main = main;
	}

	public boolean isLogin() {
		return bLoginCheck;
	}

}
