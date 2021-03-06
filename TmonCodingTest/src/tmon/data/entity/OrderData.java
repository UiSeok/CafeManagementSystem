package tmon.data.entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OrderData implements Serializable{

	private static final long serialVersionUID = -4103602890710571485L;
	
	@SerializedName("orderId")
	public long orderId;
	@SerializedName("orderTime")
	public long orderTime;
	@SerializedName("beverages")
	public List<Beverage> beverages;
}
