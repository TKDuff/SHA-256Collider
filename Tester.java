import java.util.*;
public class Tester {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
				
		String sentence = "";
		int countStart = 0;
		for(int i = 0; i < 19653; i++) {
			String current = dict.getWord(i);
			if(current.charAt(current.length()-1) == '.' ) {
				if(countStart < 10 ) {
					System.out.println(sentence + current  +  countStart);
				}
				sentence = "";
				countStart = 0;
			} else {
				sentence += current + " ";
			}
			countStart++;
		}
		
	}
}
/*
https://github.com/TKDuff/SHA-256Collider.git
 
git remote add origin https://github.com/TKDuff/SHA-256Collider.git
git branch -M main
git push -u origin main

*/