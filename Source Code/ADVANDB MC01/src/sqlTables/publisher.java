package sqlTables;

public class publisher {
	private String PublisherName;
	private String Address;
	private int Phone;
	
	public publisher(String publisherName, String address, int phone) {
		PublisherName = publisherName;
		Address = address;
		Phone = phone;
	}

	public String getPublisherName() {
		return PublisherName;
	}

	public String getAddress() {
		return Address;
	}

	public int getPhone() {
		return Phone;
	}
	
	
}
