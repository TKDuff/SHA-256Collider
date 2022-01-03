import java.security.MessageDigest;
import java.util.*;
public class LinkedListQueue {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		Scanner in = new Scanner(System.in);

		QueueLinkedList myList = new QueueLinkedList();		
		
		myList.insert("One");
		myList.insert("Two");
		myList.insert("Three");
		myList.insert("Four");
		myList.insert("Five");
		
		
		int highest = 0;
		
		Link current = myList.first;
		while(current != null) {
			Link compared = myList.first.next;		//ensure not comparing same sha
			while(compared != null) {
				System.out.println(current.data + " compared to " + compared.data);
				compared = compared.next;
			}
			current = current.next;
			myList.remove();
		}
	}
	
	
	public static String sha256(String input){
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        }catch(Exception e){
            return(e.toString());
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

