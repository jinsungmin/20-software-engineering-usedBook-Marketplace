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
		main.customerRegister(); // 메인창 메소드를 이용해 창띄우기
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

		// 로그인 성공이라면 매니저창 띄우기
		if (isLogin() && loginType.equals("admin")) {
			main.showAdminPage(); // 메인창 메소드를 이용해 창띄우기
		}

		int index = 0;
		while (index < userList.size()) { // 등록된 ID를 조회하여 일치하면 로그인
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
		// 로그인 성공이라면 유저창 띄우기
		if (isLogin() && loginType.equals("user")) {
			main.showUserPage(userList.get(index)); // 메인창 메소드를 이용해 창띄우기
		}
		if (!isLogin()) {
			if (userList.get(index).getuserStatus().equals("deactivated"))
				JOptionPane.showMessageDialog(null, "This ID is deactivated");
			else {
				JOptionPane.showMessageDialog(null, "Invalid ID or PW");
			}
		}

	}

	// mainProcess와 연동
	public void setMain(MainProcess main) {
		this.main = main;
	}

	public boolean isLogin() {
		return bLoginCheck;
	}

}
