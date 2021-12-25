import java.io.*;

public class Compare {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		
		final int numLines = 3000;
			
		
		int highest = 0;
		int outer = 0, inner = 0;
		for(int i = 0; i < numLines; i++) {
			for(int j = 0; j < numLines; j++) {
				if(i !=j) {
					int comp = compare(dict.getWord(i),dict.getWord(j));
					if(comp > highest && comp != 64) {
						highest = comp;
						outer = i;
						inner = j;
					}
				}
			}
		}
		System.out.println(highest + " " + (outer+1) + " " + (inner+1));
		
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

}
