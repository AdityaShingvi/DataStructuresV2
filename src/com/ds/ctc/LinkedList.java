package com.ds.ctc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LinkedList {
	//LinkedList list = new LinkedList();
	
	public static void main(String[] args) {
		LinkedList list2 = new LinkedList();
		
		//create a list 4 -> 10 -> 5 -> 6 -> 2 -> 19 -> 11 -> 1
//		Node n1 = new Node(10, null);
//		Node n2 = new Node(5, n1);
//		Node n3 = new Node(3, n2);
//		Node n4 = new Node(6, n3);
//		Node n5 = new Node(5, n4);
//		Node n6 = new Node(6, n5);
//		Node n7 = new Node(0, n6);
		
		//Node head = list.removeDuplicatesNoBuffer(n7);
		//list.printList(head);
		
		//int data = list.findKthLastElement(n7, 0);
		//System.out.println(data);
		
//		list.deletefromMiddle(n4);
//		list.printList(n7);
		
		//list2.partitionList(n7, 6);
//		Node m = new Node(7, null);
//		Node m1 = new Node(1, m);
//		Node m2 = new Node(6, m1);
//		Node o = new Node(5, m2);
//		Node o1 = new Node(8, o);
//		Node o2 = new Node(6, o1);
//		m1.next = o;
		//list2.printList(list2.listAddition(m2, o2));
		//System.out.println((list2.detectLoop(o2)));
		
//		Node m = new Node(4, null);
//		Node m1 = new Node(1, m);
//		Node m2 = new Node(4, m1);
		//System.out.println(list2.isPalindrome(m2));
		
		
		
		
		////////////////
		
		Node head1 = new Node('7');
		head1.setNext(new Node('6'));
		head1.next.setNext(new Node('.'));
		head1.next.next.setNext(new Node('5'));
		head1.next.next.next.setNext(new Node('5'));
		head1.next.next.next.next.setNext(new Node('6'));
		Node head2 = new Node('8');
		head2.next.setNext(new Node('4'));
		head2.next.next.setNext(new Node('1'));
		head2.next.next.next.setNext(new Node('7'));
		head2.next.next.next.next.setNext(new Node('6'));
		head2.next.next.next.next.next.setNext(new Node('.'));
		head2.next.next.next.next.next.next.setNext(new Node('2'));
		CompareDecimalNumbers(head1, head2);
		
		
	}
	
	//print the complete list
	public void printList(Node head) {
		if (head != null) {
			Node temp = head;
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
			System.out.println();
		}
	}
	
	public int length(Node head) {
		int cnt = 0;
		while (head != null) {
			head = head.next;
			cnt++;
		}
		return cnt;
	}

	//remove duplicates USING BUFFER
	public Node removeDuplicates(Node head) {
		Map<Character, Integer> map = new HashMap<>();
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
	
	// solution 1 - partition a linked list around a value x
	// solution 2 - Add new nodes to head of new lists instead of adding at end to get rid of extra 2 pointers
	public void partitionList(Node head, int partition) {
		LinkedList list = new LinkedList();
		Node smallerHead = null, smaller = null;
		Node greaterHead = null, greater = null;
		int data;
		list.printList(head);
		while(head != null) {
			data = head.data;
			if (data >= partition) {
				if (greater == null) {
					greater = head;
					greaterHead = greater;
				} else {
					//Node node = new Node(data, null);
					greater.next = head;
					greater = greater.next;
				}
			} else {
				if (smaller == null) {
					smaller = head;
					smallerHead = smaller;
				} else {
					//Node node = new Node(data, null);
					smaller.next = head;
					smaller = smaller.next;
				}
			}
			head = head.next;
		}
		//merge the 2 lists
		smaller.next = greaterHead;
		list.printList(smallerHead);
		//object.printList(greaterHead);
	}
	
//	public Node listAddition(Node head1, Node head2) {
//		Node newHead = null, current = null;
//		if (head1 != null || head2 != null) {
//			int carry = 0, remainder = 0, sum = 0;
//			if (true) {
//				while (head1 != null || head2 != null) {
//					if (head1 == null) {
//						sum = head2.data + carry;
//					} else if (head2 == null) {
//						sum = head1.data + carry;
//					} else {
//						sum = head1.data + head2.data + carry;
//					}
//					carry = 0;
//					if (head1 != null && head2 != null) {
//						if (sum > 9) {
//							carry = sum / 10;
//							remainder = sum % 10;
//							sum = remainder;
//						}
//					}
//					if (newHead == null) {
//						newHead = new Node(sum, null);
//						current = newHead;
//					} else {
//						Node newNode = new Node(sum, null);
//						current.next = newNode;
//						current = newNode;
//					}
//					head1 = head1 == null ? head1 : head1.next;
//					head2 = head2 == null ? head2 : head2.next;
//				}
//			}
//		}
//		return newHead;
//	}
	
	// detect start of loop in linked list
	public Node detectLoop(Node head) {
		Node fast = head, slow = head;
		
		while (fast != null ) {
			slow = slow.next;
			fast = fast.next.next;
			if (fast == slow)
				break;
		}
		slow = head;
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
			if (fast == slow)
				break;
		}
		return fast;
	}
	
//	public boolean isPalindrome(Node head) {
//		if (head == null) {
//			return false;
//		}
//		if (head.next == null) {
//			return true;
//		}
//		Stack<Integer> stack = new Stack<Integer>();
//		Node fast = head,slow = head;
//		boolean isPalindrome = true;
//		while (fast.next != null) {
//			stack.push(slow.data);
//			slow = slow.next;
//			fast = fast.next.next;
//		}
//		while (slow.next != null) {
//			slow = slow.next;
//			if (stack.pop() != slow.data) {
//				isPalindrome = false;
//				break;
//			}
//		}
//		return isPalindrome;
//	}
	
	public void mergeContacts(List<List<String>> contacts) {
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		String[] arr;
//		for (List<String> contactList : contacts) {
//			//<adityashingvi13@gmail.com, aditya@gmail.com, shingvi@gmail.com>
//			for (String s: contactList) {
//				
//			}
//		}
		
		
		
	}
static int CompareDecimalNumbers(Node head1, Node head2){

	String first ="";
	String second = "";
	while(head1.next != null || head2.next!= null){
	if(head1.data == '.' && head2.data != '.')
	break;	
	if(head2.data =='.' && head1.data != '.')
	break;
	first = first + head1.data;
	head1 = head1.next;
	second = second + head2.data;
	head2 = head2.next;
	}
	if(Integer.parseInt(first) > Integer.parseInt(second)){
	while(head1.next!= null)
	first = first + head1.data;
	return Integer.parseInt(first);
	}	
	else{
	while(head2.next != null)
	second = second + head2.data;
	return Integer.parseInt(second);

}
	}

}

