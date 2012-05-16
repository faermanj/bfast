package jfaerman.bfast.model;


public class Order {
	private String uuid;
	private String customerName;
	private String description;
	
	public Order() {}
	
	public Order(String uuid,String customerName, String description){
		setUuid(uuid);
		setCustomerName(customerName);
		setDescription(description);
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFat() {
		return description != null &&
			 ( description.contains("bacon") 
			|| description.length() > 20);
	}
	
}
