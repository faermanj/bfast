package jfaerman.bfast.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
@NamedQueries({
  @NamedQuery(name="order.all", query="select o from Order o"),
  @NamedQuery(name="order.count",query="select count(o) from Order o")
})
public class Order {
  @Id
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
			 ( description.toLowerCase().contains("bacon") 
			|| description.length() > 20);
	}
	
}
