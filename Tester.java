import java.io.*;
import java.security.MessageDigest;
public class Tester {

	public static void main(String[] args) throws IOException {
		Dictionary dict = new Dictionary();
		File file1 = new File("output1.txt"); //file destination to write to
		FileWriter fw1 = new FileWriter(file1,true); //boolean set to true in order to only append to file, note wipe and rewrite lines to file each time
		PrintWriter pw1 = new PrintWriter(fw1); //send printed strings to file
		
		File file2 = new File("output2.txt"); //file destination to write to
		FileWriter fw2 = new FileWriter(file2,true); //boolean set to true in order to only append to file, note wipe and rewrite lines to file each time
		PrintWriter pw2 = new PrintWriter(fw2); //send printed strings to file
		
		
		
		final int totalWord = 562482; 		//word count of each book
				
		String sentence = ""; 			//keep track sentences made between each fullstop
		int countStart = 0;				//counts number of words after full stop, track if under 10
		for(int i = 0; i < totalWord; i++) {						//for each word (line in txt file)
			String current = dict.getWord(i);	
			if(current.charAt(current.length()-1) == '.' ) {	//if current words contains full stop and
				if(countStart >= 3 && countStart <= 10) {		//if number of words (counted so far) after full stop enough to make proper sentence
					pw1.println(sha256(sentence + current)); 			//write sentence so far and current word (one containing full stop) to file
					pw2.println(sentence + current); 
				}
				sentence = "";									//reset to start over after the full stop
				countStart = 0;
			} else {											//if current not contain full stop
				sentence += current + " ";						//add current to sentence so far
			}
			countStart++;		
		}
		pw1.close(); 		//close PrintWriter
		pw2.close();
		
		
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

/*
https://github.com/TKDuff/SHA-256Collider.git
 
git remote add origin https://github.com/TKDuff/SHA-256Collider.git
git branch -M main
git push -u origin main

*/
