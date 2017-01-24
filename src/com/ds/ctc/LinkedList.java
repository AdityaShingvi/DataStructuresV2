package com.ds.ctc;

import java.util.HashMap;
import java.util.Map;

public class LinkedList {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		
		//create a list 4 -> 10 -> 5 -> 6 -> 2 -> 19 -> 11 -> 1
		Node n1 = new Node(10, null);
		Node n2 = new Node(5, n1);
		Node n3 = new Node(3, n2);
		Node n4 = new Node(6, n3);
		Node n5 = new Node(5, n4);
		Node n6 = new Node(6, n5);
		Node n7 = new Node(0, n6);
		
		//Node head = list.removeDuplicatesNoBuffer(n7);
		//list.printList(head);
		
		//int data = list.findKthLastElement(n7, 0);
		//System.out.println(data);
		
		list.deletefromMiddle(n4);
		list.printList(n7);
	}
	
	//print the complete list
	public void printList(Node head) {
		if (head != null) {
			Node temp = head;
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}
	}
	
	//remove duplicates USING BUFFER
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
	
	//remove duplicates without using buffer
	public Node removeDuplicatesNoBuffer(Node head) {
		if (head.next == null) {
			return head;
		}
		Node current = head;
		Node next, prev;
		while(current.next != null) {
			next = current.next;
			prev = current;
			while (next != null) {
				if(current.data == next.data) {
					prev.next = next.next;
				}
				prev = prev.next;
				next = next.next;
			}
			current = current.next;
		}
		return head;
	}

	public int findKthLastElement(Node head, int k) {
		if (head == null || k <= 0) {
			return -1;
		}
		if (head.next == null) {
			return head.data;
		}
		Node current = head;
		int pos = k;
		while (current != null && pos > 0) {
			current = current.next;
			pos--;
		}
		while (current != null) {
			head = head.next;
			current = current.next;
		}
		return head.data;
	}

	public void deletefromMiddle(Node node) {
		if (node == null) {
			return;
		}
		if (node.next == null) {
			node = null;
			return;
		}
		Node current = node;
		Node temp = node.next;
		while (temp.next != null) {
			node.data = temp.data;
			node = temp;
			temp = temp.next;
		}
		node.data = temp.data;
		node.next = null;
		temp = null;
		//printList(current);
	}
	
	//todo partition a linked list around a value x
}
