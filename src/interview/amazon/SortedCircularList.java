package interview.amazon;

import interview.amazon.CNode;

public class SortedCircularList {
	
	public static CNode insertSortedList(CNode start, int n){
		
		CNode current, temp = new CNode();
		
		if(start == null){
			//start = new CNode();
			start.value = n;
			start.next = start;
			return start;
		}
		current = start;
		if(n < current.value ) {
			temp.value = current.value;
			temp.next = current.next;
			start.value = n;
			start.next = temp;
			return start;
		}
		else {
			while(current.next != start){
				if(current.next.value < n){
					current = current.next;
					continue;
				}
				temp.value = n;
				temp.next = current.next;
				current.next = temp;
				return temp;
			}
			temp.value = n;
			temp.next = start;
			current.next = temp;
			return temp;
		}
		
	}

}
