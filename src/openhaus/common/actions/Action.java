package openhaus.common.actions;

import java.io.Serializable;

public abstract class Action implements Serializable {

	static final long serialVersionUID = 4957513657744644857L;
	
	private String source;
	private String destination;
	private String type;

	public Action() {
		super();
	}

	public Action(String source, String destination, String type) {
		super();
		this.source = source;
		this.destination = destination;
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}