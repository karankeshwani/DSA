package com.sunbeam;

import java.util.Scanner;

class DoublyLinearList {
	static class Node {
		private int data;
		private Node next;
		private Node prev;
		public Node() {
			data = 0;
			next = null;
			prev = null;
		}
		public Node(int val) {
			data = val;
			next = null;
			prev = null;
		}
	}
	
	private Node head;
	public DoublyLinearList() {
		head = null;
	}
	// time: O(n)
	public void fwdDisplay() {
		System.out.print("Fwd List: ");
		Node trav = head;
		while(trav != null) {
			System.out.print(trav.data + ", ");
			trav = trav.next;
		}
		System.out.println();
	}
	// time: O(n) -- iterations: 2*n
	public void revDisplay() {
		System.out.print("Rev List: ");
		if(head != null) {
			// traverse till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			// traverse in reverse order and print data of each node
			while(trav != null) {
				System.out.print(trav.data + ", ");
				trav = trav.prev;
			}
		}
		System.out.println();
	}
	// time: O(n)
	public void addLast(int val) {
		// create and initialize new node
		Node nn = new Node(val);
		// if list is empty, add node at the start (head)
		if(head == null)
			head = nn;
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			// add new node after it (last node)
			nn.prev = trav;
			trav.next = nn;
		}
	}
	// time: O(1)
	public void addFirst(int val) {
		// create and initialize new node
		Node nn = new Node(val);
		// if list is empty, add node at the start (head)
		if(head == null)
			head = nn;
		else {
			// add new node before head
			nn.next = head;
			head.prev = nn;
			// change head to new node
			head = nn;
		}		
	}
	// time: O(pos)
	public void addAtPos(int pos, int val) {
		// if list is empty, or adding node on pos 1/before, then add first
		if(head == null || pos <= 1)
			addFirst(val);
		else {
			// create and initialize new node
			Node nn = new Node(val);
			// traverse till pos along with a prev pointer
			Node trav = head, prev = null;
			for(int i=1; i<pos; i++) {
				// if adding beyond the list elements, then stop traversing on null (so that element can be added after last node)
				if(trav == null)
					break;
				prev = trav;
				trav = trav.next;
			}
			// add node after prev and before trav
			nn.next = trav;
			nn.prev = prev;
			prev.next = nn;
			if(trav != null) // if add last case, trav will be null
				trav.prev = nn;
		}
	}
	// time: O(1)
	public void delFirst() {
		// if list is empty, do nothing
		if(head == null)
			return;
		// if list has single element, make head null -- the first node will be GC
		if(head.next == null)
			head = null;
		// take head to the next node and make new head's prev null
		else {
			head = head.next;
			head.prev = null;
		}
	}
	// time: O(1)
	public void delAll() {
		head = null;
	}
	// time: O(pos)
	public void delAtPos(int pos) {
		// if list is empty or delete at first pos, then call delFirst()
		if(head == null || pos == 1)
			delFirst();
		// if deleting before pos 1, then do nothing
		else if(pos < 1)
			return;
		else {
			// traverse till the pos
			Node trav = head;
			for(int i=1; i<pos; i++) {
				// if deleting beyond list elements, do nothing
				if(trav == null)
					return;
				trav = trav.next;
			}
			// connect trav's prev node to trav's next
			trav.prev.next = trav.next;
			// connect trav's next node to trav's prev
			if(trav.next != null) // if delete at last pos, trav's next will be null
				trav.next.prev = trav.prev;
		}
	}
	// time: O(n)
	public void delLast() {
		// homework
	}
}

public class DoublyLinearListMain {

	public static void main(String[] args) {
		DoublyLinearList list = new DoublyLinearList();
		Scanner sc = new Scanner(System.in);
		int choice, val, pos;
		do {
			System.out.print("\n1. Display\n2. Add Last\n3. Add First\n4. Add at Pos\n5. Del All\n6. Del First\n7. Del Last\n8. Del At Pos\nEnter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // display
				list.fwdDisplay();
				list.revDisplay();
				break;
			case 2: // add last
				System.out.print("Enter value: ");
				val = sc.nextInt();
				list.addLast(val);
				break;
			case 3: // add first
				System.out.print("Enter value: ");
				val = sc.nextInt();
				list.addFirst(val);
				break;			
			case 4: // add at pos
				System.out.print("Enter value: ");
				val = sc.nextInt();
				System.out.print("Enter position: ");
				pos = sc.nextInt();
				list.addAtPos(pos, val);
				break;
			case 5: // del all
				list.delAll();
				break;
			case 6: // del first
				list.delFirst();
				break;
			case 7: // del last
				list.delLast();
				break;
			case 8: // del at pos
				System.out.print("Enter position: ");
				pos = sc.nextInt();
				list.delAtPos(pos);
				break;
			}
		}while(choice != 0);
	}

}
