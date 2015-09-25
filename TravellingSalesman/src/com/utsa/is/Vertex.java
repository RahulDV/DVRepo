package com.utsa.is;

public class Vertex {
	
	int id;
	double key;
	Vertex parent;
	
	public Vertex(int id){
		this.id = id;
		this.key = 999;
		this.parent = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getKey() {
		return key;
	}
	public void setKey(double key) {
		this.key = key;
	}
	public Vertex getParent() {
		return parent;
	}
	public void setParent(Vertex parent) {
		this.parent = parent;
	}
	
	

}
