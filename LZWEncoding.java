//Maya Doyle and Anneliese Ardizzone
//Optimized by Jasmine Wang and Sally Ho



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LZWEncoding{
	public static void main (String[] args){
		//read in a txt file and dump it into a string
		Scanner keyboard = new Scanner(System.in);
		System.out.print ("Please give me the name of the file you wish to encode: ");
		String theFileName = keyboard.nextLine();



		try {
			//reading in a text file and creating print writer
			FileReader fr = new FileReader (theFileName);
			BufferedReader br = new BufferedReader(fr);
			PrintWriter pw = new PrintWriter ("encoded.txt");
			String previousCharacter = ""+(char)(br.read());
			
			//create a hashmap as the dictionary that will hold all additional entries after 255
			HashMap <String, String> theDictionary = new HashMap<String, String>();

			//counter will keep track of what the code for each new entry should be
			int counter = 256;
			while (br.ready())	
			{
				String currentCharacter = ""+ (char)br.read();
				String previousPlusCurrent = previousCharacter+currentCharacter;
				
				//if previousCurrent is already in the theDictionary or if it's in the ascii table, update previous
				if (theDictionary.containsKey(previousPlusCurrent) || previousPlusCurrent.length() == 1)
				{
					previousCharacter = previousPlusCurrent;
				}
				//print out value for previous character
				else
				{
					//print previous character if it's a single character
					if (previousCharacter.length()==1)
					{
						pw.print((int)previousCharacter.charAt(0) + "\n");
					}
					//print previous if it's not a single character
					else 
					{
						pw.print(theDictionary.get(previousCharacter) + "\n");
					}
					//add previous + current to dictionary 
					if (theDictionary.size()<=1000)
					{
					
						theDictionary.put(previousPlusCurrent, ""+counter);
						counter++;
					}
					previousCharacter= "" + currentCharacter;
				}
			}
			
			//edge case for last entry 
			if (previousCharacter.length() == 1 )
			{
				pw.print((int)previousCharacter.charAt(0)+ "\n");
			}
			else
			{
				pw.print(theDictionary.get(previousCharacter) + "\n");
			}
			pw.close();
			br.close();
			fr.close();
		}

			catch (IOException e)
			{
				System.out.println ("can't read");
			}
	}
}
		
		

/*
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
			
		String previousCharacter = "";
		String currentCharacter = "";
		String previousPlusCurrent = ""; 
		for(int k=0; k<fileToEncode.length(); k++) {
			currentCharacter = String.valueOf(fileToEncode.charAt(k));
			previousPlusCurrent = previousCharacter + currentCharacter;
			
			//if found
			if(previousPlusCurrent.length () == 1 || theDictionary.contains(previousPlusCurrent)) {
				previousCharacter = previousCharacter + currentCharacter;
			}

			//if not found
			else {
				
				//adds P+C to dictionary 
				theDictionary.add(previousPlusCurrent);
			
				//output code for previousCharacter to the code stream 
				if(previousCharacter.length()==1) {
					encodedValues.add((int)previousCharacter.charAt(0));
				}

				else if (previousCharacter.length() > 1){ //there was a problem in which the previous character would be added to the dictionary if it was "". "" has length 0, so the "if length() > 1" eliminates the problem
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
			FileWriter writeToFile = new FileWriter("output1.txt");
			
			for (int i = 0; i < encodedValues.size(); i++){
				writeToFile.write(encodedValues.get(i) + "\n");
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
*/