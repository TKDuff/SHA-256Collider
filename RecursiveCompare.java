import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RecursiveCompare {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		
		String s1 = "7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d7a55526885a87a6e0d71c99c843d74fe2e4a419a44871864f606063c6e6c937d";
		String s2 = "e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42e1b952d8cfa8eb6f4d534095818174102e4d6b59a4d71b143d0f22c5d873bb42";
		
		//QueueLinkedList myList = new QueueLinkedList();
		long startTime = System.currentTimeMillis();
		System.out.println(f(s1, s2));
		long endTime   = System.currentTimeMillis();
		System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds\n");
		
		
		startTime = System.currentTimeMillis();
		System.out.println(compare(s1, s2));
		endTime   = System.currentTimeMillis();
		System.out.print("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}

	private static int f(String s1, String s2) {
		if(s1.length() == 0) {
			return 0;
		} else {
			if(s1.charAt(s1.length()-1) == s2.charAt(s2.length()-1)) {
				return f(s1.substring(0, s1.length()-1), s2.substring(0, s1.length()-1)) + 1;
			} else {
				return f(s1.substring(0, s1.length()-1), s2.substring(0, s1.length()-1)) + 0;
			}
		}
		
	}
	
	private static int compare(String s1, String s2) {
    	int count = 0;
    	int pos = 0;
    	while(pos <= 511) {
    		if ( s1.charAt(pos)  == s2.charAt(pos)) {
    			count++;
    		}
    		pos++;
    	}
    	return count;
    	
	}

}
