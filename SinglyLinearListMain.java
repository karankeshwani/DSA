package com.sunbeam;

import java.util.Scanner;

class SinglyLinearList {
	static class Node
	{
		private int data;
		private Node next;
		public Node() {
			this.data = 0;
			this.next = null;	
		}
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node head;
	public SinglyLinearList() {
		head = null;
	}
	// time: O(n)
	public void display() {
		System.out.print("List: ");
		Node trav = head;
		while(trav != null) {
			System.out.print(trav.data + ", ");
			trav = trav.next;
		}
		System.out.println();
	}
	// time: O(n)
	public void addLast(int val) {
		// create and initialize the node
		Node nn = new Node(val);
		// if list is empty, then add node at the start
		if(head == null)
			head = nn;
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != null)
				trav = trav.next;
			// add new node into next of last node
			trav.next = nn;
		}
	}
	// time: O(1)
	public void addFirst(int val) {
		// create and initialize the node
		Node nn = new Node(val);
		// new node next to head
		nn.next = head;
		// head to newnode
		head = nn;
	}
	// time: O(pos) -- best case: O(1), worst case: O(n)
	public void addAtPos(int pos, int val) {
		// if list is empty or pos is 1/before, add the node at the start
		if(head == null || pos <= 1)
			addFirst(val);
		else {
			// create and initialize the node
			Node nn = new Node(val);
			// traverse till pos-1
			Node trav = head;
			for(int i=1; i<pos-1; i++) {
				// if pos is beyond number of nodes, then stop traversing on last node (so than node get added after it)
				if(trav.next == null)
					break;
				trav = trav.next;
			}
			// add the node after trav
			nn.next = trav.next;
			trav.next = nn;
		}
	}
	// time: O(1)
	public void delFirst() {
		// if list is not empty, take head to the next (second) node
		if(head != null)
			head = head.next;
	}
	// time: O(1) -- in Java
	public void delAll() {
		head = null;
			// all nodes will be garbage collected
	}
	// time: O(n)
	public void delLast() {
		// if list is empty or list has single node, then call delFirst()
		if(head == null || head.next == null)
			delFirst();
		else {
			// traverse till last node along with a prev pointer
			Node trav = head, prev = null;
			while(trav.next != null) {
				prev = trav;
				trav = trav.next;
			}
			// when trav is last node, prev is node behind it. Make prev's next null, so that trav node will be GC.
			prev.next = null;
		}
	}
	// time: O(pos) -- best case: O(1), worst case: O(n)
	public void delAtPos(int pos) {
		// if list is empty or node to be deleted from first pos, then call delFirst()
		if(head == null || pos == 1)
			delFirst();
		// if pos is before 1, do nothing
		else if(pos < 1)
			return;
		else {
			// traverse till node at given pos along with a prev pointer
			Node trav = head, prev = null;
			for(int i=1; i<pos; i++) {
				// if pos is beyond number of nodes, then do nothing
				if(trav.next == null)
					return;
				prev = trav;
				trav = trav.next;
			}
			// in prev node's next keep trav's next (so that trav will be GC)
			prev.next = trav.next;
		}
	}
}

public class SinglyLinearListMain {
	public static void main(String[] args) {
		SinglyLinearList list = new SinglyLinearList();
		Scanner sc = new Scanner(System.in);
		int choice, val, pos;
		do {
			System.out.print("\n1. Display\n2. Add Last\n3. Add First\n4. Add at Pos\n5. Del All\n6. Del First\n7. Del Last\n8. Del At Pos\nEnter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // display
				list.display();
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
