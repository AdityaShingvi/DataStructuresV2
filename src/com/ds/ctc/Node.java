package com.ds.ctc;

public class Node {
	
	public char data;
	public Node next;
	
	public Node() {
		
	}
	
	public Node(char c) {
		this.data = c;
	}
	
	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(char data, Node next){
		this.data = data;
		this.next = next;
	}
}
