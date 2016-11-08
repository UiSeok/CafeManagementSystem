package tmon.data;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Beverage implements Serializable{

	private static final long serialVersionUID = -7063930317520943565L;
	
	public Beverage(int bevId, String bevName, int count) {
		super();
		this.bevId = bevId;
		this.bevName = bevName;
		this.count = count;
	}

	@SerializedName("bevId")
	public int bevId;
	@SerializedName("bevName")
	public String bevName;
	@SerializedName("count")
	public int count;
}
