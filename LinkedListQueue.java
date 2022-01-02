import java.util.*;
public class LinkedListQueue {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		QueueLinkedList myList = new QueueLinkedList();
				
		myList.insert("One");	
		myList.insert("Two");
		myList.insert("Three");
		myList.remove();
		Link current = myList.first;
		
		for(int i = 0; i < 2; i++) {
			System.out.println(current.data + " ");
			current = current.next;
		}
		
	}
		
}

class Link{
	public String data;
	public Link next;
	
	public Link(String input) {
		data = input;
		next = null;
	}
}

class QueueLinkedList{
	public Link first;
	public Link last;
	
	public QueueLinkedList() {
		first = null;
	}
	
	public void remove() {
		first = first.next;
	}
	
	public void insert(String data) {
		Link newLink = new Link(data); 		//create new link
		if(first == null) {
			first = newLink;
			last = newLink;
		} else {
		last.next = newLink;				//old last link point to new link
		last = newLink;						//update last link object to point to new node
		
		}
	}	
}

