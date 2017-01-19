package com.ds.ctc;

import java.util.HashMap;
import java.util.Map;

public class LinkedList {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		
		//create a list
		Node n1 = new Node(10, null);
		Node n2 = new Node(5, n1);
		Node n3 = new Node(3, n2);
		Node n4 = new Node(1, n3);
		Node n5 = new Node(5, n4);
		Node n6 = new Node(6, n5);
		Node n7 = new Node(0, n6);
		
		Node head = list.removeDuplicates(n7);
		list.printList(head);
	}
	
	//print the complete list
	public void printList(Node head) {
		if (head != null) {
			Node temp = head;
			while (temp.next != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}
	}
	
	//remove duplicates
	public Node removeDuplicates(Node head) {
		Map<Integer, Integer> map = new HashMap<>();
		Node current = head;
		Node previous = head;
		while(current != null) {
			if (map.containsKey(current.data)) {
				previous.next = current.next;
				current = current.next;
			} else {
				map.put(current.data, 1);
				previous = current;
				current = current.next;
			}
		}
		return head;
	}
}
