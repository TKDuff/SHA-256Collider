import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
public class LinkedListQueue {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		Scanner in = new Scanner(System.in);
		
		
		long startTime = System.currentTimeMillis();

		QueueLinkedList myList = new QueueLinkedList();
		
		final int totalWord = 37907; 		//word count of each book
		int numLines = 0;
		
		
		String sentence = ""; 		
		int countStart = 0;				
		for(int i = 0; i < totalWord; i++) {						
			String current = dict.getWord(i);	
			if(current.charAt(current.length()-1) == '.' ) {	
				if(countStart >= 3 && countStart <= 10) {		
					myList.insert(sha256(sentence + current));
				}
				sentence = "";									
				countStart = 0;
			} else {											
				sentence += current + " ";						
			}
			countStart++;
			numLines++;
		}	
		
		
		int highest = 0;
		String outer = "", inner = "";
		Link current = myList.first;
		while(current != null) {
			Link compared = myList.first.next;		//ensure not comparing same sha
			while(compared != null) {
				int comp = compare(current.data, compared.data);
				if(comp > highest && comp != 64) {
					highest = comp;
					outer = current.data;
					inner = compared.data;
				}
				compared = compared.next;
			}
			current = current.next;
			myList.remove();
		}
		
		System.out.println(highest + " " + outer + " " + inner);
		
		
		
		long endTime   = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
			System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");

	}
	
	private static int compare(String s1, String s2) {
    	int count = 0;
    	int pos = 0;
    	while(pos <= 63) {
    		if ( s1.charAt(pos)  == s2.charAt(pos)) {
    			count++;
    		}
    		pos++;
    	}
    	return count;
    	
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

