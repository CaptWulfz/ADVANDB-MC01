package sqlTables;

public class library_branch {
	private int BranchID;
	private String BranchName;
	private String BranchAddress;
	
	public library_branch(int branchID, String branchName, String branchAddress) {
		BranchID = branchID;
		BranchName = branchName;
		BranchAddress = branchAddress;
	}

	public int getBranchID() {
		return BranchID;
	}

	public String getBranchName() {
		return BranchName;
	}

	public String getBranchAddress() {
		return BranchAddress;
	}
	
}
