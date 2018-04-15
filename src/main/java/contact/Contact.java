package contact;

 
public class Contact {
	
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String phone;
	private String email;
     
	public Contact() {
		this.firstName = null;
		this.lastName = null;
		this.street = null;
		this.city = null;
		this.state = null;
		this.zipCode = null;
		this.phone = null;
		this.email = null;
	}
	
	public Contact (String firstName, String lastName, String street, String city,
			String state, String zipCode, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phone = phone;
		this.email = email;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getZip() {
		return this.zipCode;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getEmail() {
		return this.email;
	}
    
}

