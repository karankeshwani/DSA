package com.sunbeam;

import java.util.Scanner;

class SinglyCircularList {
	static class Node 
	{
		private int data;
		private Node next;
		Node() {
			data = 0;
			next = null;
		}
		Node(int val) {
			data = val;
			next = null;
		}
	}
	
	private Node head;
	public SinglyCircularList() {
		head = null;
	}
	// time: O(n)
	public void display() {
		System.out.print("List: ");
		if(head != null) {
			Node trav = head;
			do {
				System.out.print(trav.data + ", ");
				trav = trav.next;
			}while(trav != head);
		}
		System.out.println();
	}
	// time: O(n)
	public void addLast(int val) {
		// create and initialize the node
		Node nn = new Node(val);
		// if list is empty, add node in head and make it circular
		if(head == null) {
			head = nn;
			nn.next = head;
		}
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != head)
				trav = trav.next;
			// add the node after trav i.e. at end
			nn.next = head;
			trav.next = nn;
		}
	}
	// time: O(n)
	public void addFirst(int val) {
		// create and initialize the node
		Node nn = new Node(val);
		// if list is empty, add node in head and make it circular
		if(head == null) {
			head = nn;
			nn.next = head;
		}
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != head)
				trav = trav.next;
			// add the node after trav i.e. at end
			nn.next = head;
			trav.next = nn;
			// head to new node
			head = nn;
		}
	}
	// time: O(pos)
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
				// if pos is beyond number of nodes, then stop traversing on last node (so that node get added after it)
				if(trav.next == head)
					break;
				trav = trav.next;
			}
			// add the node after trav
			nn.next = trav.next;
			trav.next = nn;
		}
	}
	// time: O(n)
	public void delFirst() {
		// if list is empty, do nothing
		if(head == null)
			return;
		// if list has single node, then make list empty
		if(head.next == head)
			head = null;
		else {
			// traverse till last node
			Node trav = head;
			while(trav.next != head)
				trav = trav.next;
			// take head to the next (second) node
			head = head.next;
			// last node's next to the new head
			trav.next = head;
		}
	}
	// time: O(1) -- Java
	public void delAll() {
		head = null;
	}
	// time: O(n)
	public void delLast() {
		// if list is empty or list has single node, then call delFirst()
		if(head == null || head.next == head)
			delFirst();
		else {
			// traverse till last node along with a prev pointer
			Node trav = head, prev = null;
			while(trav.next != head) {
				prev = trav;
				trav = trav.next;
			}
			// when trav is last node, prev is node behind it. Make prev's next null, so that trav node will be GC.
			prev.next = head;
		}
	}
	// time: O(pos)
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
				if(trav.next == head)
					return;
				prev = trav;
				trav = trav.next;
			}
			// in prev node's next keep trav's next (so that trav will be GC)
			prev.next = trav.next;
		}
	}
}

public class SinglyCircularListMain {

	public static void main(String[] args) {
		SinglyCircularList list = new SinglyCircularList();
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
