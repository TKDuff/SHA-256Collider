import java.security.MessageDigest;
public class CS210project {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		QueueLinkedList shaList = new QueueLinkedList();		//create linked lists to store sha values and English sentences
		QueueLinkedList engList = new QueueLinkedList();
		final int totalWord = 1700811; 							//word count of text file book
		System.out.println("Running...");
		
		String sentence = ""; 		//store words in book until fullstop	
		int countStart = 1;			//keep track of number of words in sentence string
		for(int i = 0; i < totalWord; i++) {		//for each word in the book								
			String current = dict.getWord(i);		
			if(current.charAt(current.length()-1) == '.') { 	//if current word contains full stop	
				if(countStart >= 3 && countStart <= 10) {		//if between 3 and 10 words
					engList.insert(sentence + current);			//insert into queue storing English sentences
					shaList.insert(sha256(sentence + current));	//insert into queue storing SHA values
				}
				sentence = "";									//if not between 3 and 10 words but at full stop begin new sentence, (why && not used for conditional above on line 22)
				countStart = 0;									//also restart counter of number of words in sentence
			} else {											
				sentence += current + " ";						//if word not contain full stop add it to the sentence string
			}
			countStart++;										//increments number of words so far after full stop
		}  		
		
		
		int highest = 0;						//to store highest number of comparisons so far
		String outer = "", inner = "";			//to store the two English sentences who have the highest comparison number
				
		Link currentSha = shaList.first;				//begin at start of queue
		Link currentSent = engList.first;
		
		  while(currentSha != null) {				//while queue not empty
			  shaList.remove();						//since tops of queues is stored in "current" variables they can be removed 	
			  engList.remove();
			  Link comparedSha = shaList.first;			//comparedSha variable (new top of queue) stores sha to be compared with "currentSha" (old top of queue), 
			  Link comparedSent = engList.first;
			  
			  while( comparedSha != null) { 									//while there are still values to be compared to current value
				  int comp = compare(currentSha.data, comparedSha.data);		//compare both sha values, storing the result
				  if(comp > highest && comp != 64) {					//if this result is greater than the highest so far, and the two compared Sha values are not the same
					  highest = comp;									//result is new highest value
					  outer = currentSent.data;								
					  inner = comparedSent.data;						//stores both current link (outer loop) and compared link (inner loop) English sentences to be displayed at the end
				  }
				  comparedSha = comparedSha.next;				//move onto next link in list to compare with currentSha
				  comparedSent = comparedSent.next;		
			  }
			  currentSha = shaList.first;					//after current is compared with each value in the list, it moves onto the next link, which is now at the top of the queue since we removed the prevous top at the start
			  currentSent = engList.first; 
		  }
			
		System.out.println(highest + "\n" + outer + "\n" + inner);
		
	}
	
	/*compare method*/
	private static int compare(String s1, String s2) { 
    	int count = 0;										//stores number of common characters in same position
    	int pos = 0;
    	while(pos <= 63) {									//for each character in a sha value
    		if ( s1.charAt(pos)  == s2.charAt(pos)) {		//if both characters the same as the same position in their sha value
    			count++;									//increment counter to be returned
    		}
    		pos++;											//move onto next position
    	}
    	return count;
    	
	}
	/*Moodle SHA-256 code*/
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

/*singly linked list queue code*/
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
		first = first.next;					//remove top of queue by setting first link to equal next link after it
	}
	
	public void insert(String data) {
		Link newLink = new Link(data); 		//create new link
		if(first == null) {					//if list is empty set first and last values to null
			first = newLink;
			last = newLink;
		} else {
		last.next = newLink;				//old last link point to new link
		last = newLink;						//update last link object to point to new node
		
		}
	}	
}

