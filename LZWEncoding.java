//Maya Doyle and Anneliese Ardizzone
//LZW Compression
/*Is the string P + C present in the dictionary?
    - If it is:    
          - P = concat(P,C);
    - if Not:    
           - output the code word which denotes P to the codestream
           - add the string P+C) to the dictionary
           - P = C 
*/


import java.util.*;
import java.io.*;


public class LZWEncoding{
	public static void main (String[] args){
		//read in a txt file and dump it into a string
		Scanner keyboard = new Scanner(System.in);
    	System.out.print ("Please give me the name of the file you wish to encode: ");
    	String theFileName = keyboard.nextLine();
    	
		String fileToEncode = ""; 
		try{
			FileReader fileRead = new FileReader (theFileName);
			char currentLetterInFileToEncode = (char)(fileRead.read());
			while ((int)currentLetterInFileToEncode < 256){
				fileToEncode += currentLetterInFileToEncode;
				currentLetterInFileToEncode = (char)(fileRead.read());
			}
			fileRead.close();
		}
		catch(IOException ex){
			System.out.println ("Something went wrong. :("); 
		}


		//--------------------------------------------------------------------------------------


		//set up a ArrayList<String> theDictionary = new ArrayList<String>();
			//characters 0-255 are pre-existing ASCII characters, so the indexes have to start at 256
				//we don't ACTUALLY have to start at 256, we can start at 0 and just add 256 whenever using that number
			//it could also be an arraylist of lists (String code, int index), but we can figure out the index based on the code's place in the list (256+indexOf(code)) so there's no need
		ArrayList<String> theDictionary = new ArrayList<String>();

		//set up an ArrayList<Integer> encodedValues = new ArrayList<Integer>();
			//this is where the numbers get yeeted in the loop if they're not found
			//using the completed version of this array, you can essentially remake the whole P, C, P+C table
		ArrayList<Integer> encodedValues = new ArrayList<Integer>();

		//set up 3 word variables: previous character, current character, and previous + current character
			//the third variable is probably optional because it's just (previous + current)
			//but let's have it for the sake of keeping code clean and not having to add variables over and over
			//though then again, it could be easier to just always add them because we're doing a ton of adding later
		String previousCharacter = "";
		String currentCharacter = "";
		String previousPlusCurrent = ""; 
		for(int k=0; k<fileToEncode.length(); k++) {
			currentCharacter = String.valueOf(fileToEncode.charAt(k));
			previousPlusCurrent = previousCharacter + currentCharacter;
			
			//if found
			if(theDictionary.contains(previousPlusCurrent)) {
				previousCharacter = previousCharacter + currentCharacter;
			}

			//if not found
			else {
				//the below if statement is to avoid adding single characters ("a", "b", "c", etc.) to the dictionary
				if (previousPlusCurrent.length() > 1){
					//System.out.println("Adding to dict: " + previousPlusCurrent);
					theDictionary.add(previousPlusCurrent);
				}
				//System.out.println("PC Length: " + previousCharacter.length());

				if(previousCharacter.length()==1) {
					//System.out.println("Adding to encoded values: " + (char)previousCharacter.charAt(0));
					encodedValues.add((int)previousCharacter.charAt(0));
				}

				else if (previousCharacter.length() > 1){ //there was a problem in which the previous character would be added to the dictionary if it was "". "" has length 0, so the "if length() > 1" eliminates the problem
					//System.out.println("Adding to encoded values uwu: " + (int)(theDictionary.indexOf(previousCharacter)));
					encodedValues.add(theDictionary.indexOf(previousCharacter)+256);
				}
				previousCharacter = currentCharacter;
			}
		}

		//adding on the last current character so we don't lose it
		if(currentCharacter.length()==1) {
			encodedValues.add((int)currentCharacter.charAt(0));
		}

		else if (previousCharacter.length() > 1){
			encodedValues.add(theDictionary.indexOf(currentCharacter)+256);
		}


		

		try {
			FileWriter writeToFile = new FileWriter("output.txt");
			for (i = 0; i < encodedValues.size(); i++){
				writeToFile.write(encodedValues.get(i) + "\n")
			}
			writeToFile.close();
		    } 
		catch (IOException e) {
			System.out.println("Uh oh :(");
		}


		
		//--------------------------------------------------------------------------------------


		//Look for (previous + current) in the dictionary
			//a sorting algorithm could be nice but skip it if speed doesn't matter
				//isn't .sort(list) a thing?
			//IF FOUND
				//P = concatination(P,C) (which means previous = previous + current ?) concatenation

			//IF NOT FOUND:
				//"output the code which denotes P to the codestream" (which means yeet it into the encodedValues array ?)
				//add the string concatination(previous + current) to the dictionary
				//set previous = current
	
		

	}
}