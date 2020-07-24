package User;

public class User {
	// 유저 ID , PW, 이름, 전화번호, 이메일
	// <User> 형태의 ArrayList에 유저의 정보를 누적시킴
	private String userID;
	private String userPW;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userStatus;
	
	public User() {	// 초기 상태는 activated 로 초기화.
		userStatus = "activated";
	}
	
	public String getuserID() {
		return userID;
	}
	
	public void setuserID(String userID) {
		this.userID = userID;
	}

	public String getuserPW() {
		return userPW;
	}

	public void setuserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getuserPhone() {
		return userPhone;
	}

	public void setuserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getuserEmail() {
		return userEmail;
	}

	public void setuserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getuserStatus() {
		return userStatus;
	}

	public void setuserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
}
