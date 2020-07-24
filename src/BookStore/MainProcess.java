package BookStore;

import java.util.ArrayList;
import java.io.*;
import javax.swing.JFrame;

import User.User;
import Book.Book;

public class MainProcess {
	Login_Page login_Page;
	AdminFrm adminFrm;
	UserFrm userFrm;
	RegisterIDFrm registerFrm;
	public static ArrayList<User> userList = new ArrayList<User>();
	public static ArrayList<Book> bookList = new ArrayList<Book>();
	public static MainProcess main = new MainProcess();

	public static void main(String[] args) {

		getUserList();
		getBookList();

		// ����Ŭ���� ����
		main = new MainProcess();
		main.login_Page = new Login_Page(main); // �α���â ���̱�
		main.login_Page.setMain(main); // �α���â���� ���� Ŭ����������
	}

	public static void getUserList() {
		try { // userList �� ����Ǿ� �ִ� user ��� ��������.
			// ���� ��ü ����
			File userFile = new File("userList.txt");
			// �Է� ��Ʈ�� ����
			FileReader filereader = new FileReader(userFile);
			// �Է� ���� ����
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				User user = new User(); // ���� �ҷ�����
				String[] array = line.split("/");
				user.setuserID(array[0]);
				user.setuserPW(array[1]);
				user.setuserName(array[2]);
				user.setuserPhone(array[3]);
				user.setuserEmail(array[4]);
				user.setuserStatus(array[5]);
				userList.add(user);
			}
			// .readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public static void getBookList() {
		try { // bookList.txt �� ����Ǿ� �ִ� book ��� ��������.
			// ���� ��ü ����
			File bookFile = new File("bookList.txt");
			// �Է� ��Ʈ�� ����
			FileReader filereader = new FileReader(bookFile);
			// �Է� ���� ����
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			
			while ((line = bufReader.readLine()) != null) {
				Book book = new Book(); // book �ҷ�����
				String[] array = line.split("/");
				book.setbookTitle(array[0]);
				book.setbookISBN(array[1]);
				book.setbookAuthor(array[2]);
				book.setbookPublisher(array[3]);
				book.setbookPubYear(array[4]);
				book.setbookPrice(array[5]);
				book.setbookStatus(array[6]);
				book.setbookSeller(array[7]);
				book.setuserID(array[8]);
				book.setSale(array[9]);
				bookList.add(book);
			}
			// .readLine()�� ���� ���๮�ڸ� ���� �ʴ´�.
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	// ������ ������â
	public void showAdminPage() {
		login_Page.setVisible(false); // �α���â�ݱ�
		this.adminFrm = new AdminFrm(main); // ������������ ����
	}

	// ���� ������â
	public void showUserPage(User user) {
		login_Page.setVisible(false); // �α���â�ݱ�
		this.userFrm = new UserFrm(main, user); // ���������� ����
	}

	// ȸ����� ������â
	public void customerRegister() {
		login_Page.setVisible(false);
		// login_Page.dispose(); // �α���â�ݱ�
		this.registerFrm = new RegisterIDFrm(main); // ȸ�� ��� ������ ����
	}

	public void closeRegisterPage() {
		registerFrm.dispose();
		login_Page.setVisible(true);
	}

	public void closeAdminPage() {
		adminFrm.dispose();
		login_Page.setVisible(true);
	}

	public void closeUserPage() {
		userFrm.dispose();
		login_Page.setVisible(true);
	}

}
