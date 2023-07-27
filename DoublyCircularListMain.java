package com.sunbeam;

class DoublyCircularList {
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
	public DoublyCircularList() {
		head = null;
	}
	public boolean isEmpty() {
		return (head == null);
	}
	// time: O(n)
	public void fwdDisplay() {
		System.out.print("Fwd List: ");
		if(!isEmpty()) {
			Node trav = head;
			do {
				System.out.print(trav.data + ", ");
				trav = trav.next;
			}while(trav != head);
		}
		System.out.println();
	}
	// time: O(n)
	public void revDisplay() {
		System.out.print("Rev List: ");
		if(!isEmpty()) {
			Node trav = head.prev;
			do {
				System.out.print(trav.data + ", ");
				trav = trav.prev;
			}while(trav != head.prev);
		}
		System.out.println();		
	}
	// time: O(1)
	public void addLast(int val) {
		// create and initialize a new node
		Node nn = new Node(val);
		if(isEmpty()) {
			// nn is first node and make it circular
			head = nn;
			nn.next = head;
			nn.prev = head;
		}
		else {
			// get address of last node
			Node tail = head.prev;
			// add new node after the last node
			nn.next = head;
			nn.prev = tail;
			tail.next = nn;
			head.prev = nn;
		}
	}
	// time: O(1)
//doubt
	public void addFirst(int val) {
		addLast(val);
		head = head.prev;
	}
	// time: O(1)
	public void delFirst() {
		// if list is empty, do nothing
		if(isEmpty())
			return;
		// if list has single node, delete it
		if(head == head.next)
			head = null;
		else {
			// get addr of last node
			Node tail = head.prev;
			// take head to the second node
			head = head.next;
			// last node's next to first node
			tail.next = head;
			// first node's prev to last node
			head.prev = tail;
		}
	}
	// time: O(1)
	public void delLast() {
		// homework
	}
}

public class DoublyCircularListMain {

	public static void main(String[] args) {
		DoublyCircularList list = new DoublyCircularList();
		list.addFirst(11);
		list.addLast(22);
		list.addLast(33);
		list.addLast(44);
		list.addFirst(55);
		list.fwdDisplay();
		list.revDisplay();
		list.delFirst();
		list.fwdDisplay();
		list.revDisplay();
	}

}
