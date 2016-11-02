package tmon.data;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Beverage implements Serializable{

	private static final long serialVersionUID = -7063930317520943565L;
	
	public Beverage(int bevId, int count) {
		super();
		this.bevId = bevId;
		this.count = count;
	}

	@SerializedName("bevId")
	public int bevId;
	@SerializedName("count")
	public int count;
}
