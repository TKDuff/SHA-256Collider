import java.util.*;
public class LinkedListQueue {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int num = Integer.parseInt(in.nextLine());
		Link[] array = new Link[num];
		
		for(int i = 0; i < num; i++) {
			array[i] = new Link(in.nextLine());
		}

		
		for(int i = 0; i < num; i++) {
			int select = i;
			int previous = i-1;
			int next = i+1;
			if(previous != -1) {
				array[select].previous = array[previous];
			}
			if(next != num) {
				array[select].next=array[next];
			}
		}
		
		LinkedList myList = new LinkedList();
		
		if(num>0) {
			myList.first=array[0];
			myList.last=array[num-1];
		}
		
		myList.insertTail("Test");
		
		Link current = myList.first;
		
		for(int i = 0; i < num+1; i++) {
			System.out.println(current.data +" ");
			current = current.next;
		}
		
	}
		
}

class Link{
	public String data;
	public Link next;
	public Link previous;
	
	public Link(String input) {
		data = input;
		next = null;
		previous =null;
	}
	
	
}

class LinkedList{
	public Link first;
	public Link last;
	
	public LinkedList() {
		first = null;
	}
	
	public boolean isEmpty() {
		return (first==null);
	}
	
	
	public void removeHead() {
		first = first.next;
	}
	
	public void insertTail(String data) {
		Link newLink = new Link(data); 		//create new link
		last.next = newLink;				//old last link point to new link
		last = newLink;						//update last link object to point to new node
		
		
	}
	
}

