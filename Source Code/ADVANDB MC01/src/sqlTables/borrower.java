package sqlTables;

public class borrower {
	private int CardNo;
	private String BorrowerLName;
	private String BorrowerFName;
	private String Address;
	private String Phone;
	
	public borrower(int cardNo, String borrowerLName, String borrowerFName, String address, String phone) {
		CardNo = cardNo;
		BorrowerLName = borrowerLName;
		BorrowerFName = borrowerFName;
		Address = address;
		Phone = phone;
	}

	public int getCardNo() {
		return CardNo;
	}

	public String getBorrowerLName() {
		return BorrowerLName;
	}

	public String getBorrowerFName() {
		return BorrowerFName;
	}

	public String getAddress() {
		return Address;
	}

	public String getPhone() {
		return Phone;
	}
	
	
}
