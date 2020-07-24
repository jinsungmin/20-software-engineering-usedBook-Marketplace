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

		// 메인클래스 실행
		main = new MainProcess();
		main.login_Page = new Login_Page(main); // 로그인창 보이기
		main.login_Page.setMain(main); // 로그인창에게 메인 클래스보내기
	}

	public static void getUserList() {
		try { // userList 에 저장되어 있는 user 목록 가져오기.
			// 파일 객체 생성
			File userFile = new File("userList.txt");
			// 입력 스트림 생성
			FileReader filereader = new FileReader(userFile);
			// 입력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				User user = new User(); // 유저 불러오기
				String[] array = line.split("/");
				user.setuserID(array[0]);
				user.setuserPW(array[1]);
				user.setuserName(array[2]);
				user.setuserPhone(array[3]);
				user.setuserEmail(array[4]);
				user.setuserStatus(array[5]);
				userList.add(user);
			}
			// .readLine()은 끝에 개행문자를 읽지 않는다.
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public static void getBookList() {
		try { // bookList.txt 에 저장되어 있는 book 목록 가져오기.
			// 파일 객체 생성
			File bookFile = new File("bookList.txt");
			// 입력 스트림 생성
			FileReader filereader = new FileReader(bookFile);
			// 입력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			
			while ((line = bufReader.readLine()) != null) {
				Book book = new Book(); // book 불러오기
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
			// .readLine()은 끝에 개행문자를 읽지 않는다.
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	// 관리자 프레임창
	public void showAdminPage() {
		login_Page.setVisible(false); // 로그인창닫기
		this.adminFrm = new AdminFrm(main); // 관리자프레임 오픈
	}

	// 유저 프레임창
	public void showUserPage(User user) {
		login_Page.setVisible(false); // 로그인창닫기
		this.userFrm = new UserFrm(main, user); // 유저프레임 오픈
	}

	// 회원등록 프레임창
	public void customerRegister() {
		login_Page.setVisible(false);
		// login_Page.dispose(); // 로그인창닫기
		this.registerFrm = new RegisterIDFrm(main); // 회원 등록 프레임 오픈
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
