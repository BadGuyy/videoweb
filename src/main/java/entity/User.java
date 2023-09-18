package entity;

public class User 
{
	private String account, password, icon;

	public User() {
	}

	public User(String account, String password, String icon) {
		this.account = account;
		this.password = password;
		this.icon = icon;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", password=" + password + ", icon=" + icon + "]";
	}
}
