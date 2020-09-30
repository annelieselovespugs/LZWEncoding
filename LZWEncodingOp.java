//Maya Doyle and Anneliese Ardizzone
//LZW Compression
/*Is the string P + C present in the theDictionary?
    - If it is:    
          - P = concat(P,C);
    - if Not:    
           - output the code word which denotes P to the codestream
           - add the string P+C) to the theDictionary
           - P = C 
 */


import java.util.*;
import java.io.*;

public class LZWEncodingOp{
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
			String currentCharacter = ""+(char)(br.read());
			String previousPlusCurrent = previousCharacter+currentCharacter;

			//set up a ArrayList<String> thetheDictionary = new ArrayList<String>();
			//characters 0-255 are pre-existing ASCII characters, so the indexes have to start at 256
			//we don't ACTUALLY have to start at 256, we can start at 0 and just add 256 whenever using that number
			//it could also be an arraylist of lists (String code, int index), but we can figure out the index based on the code's place in the list (256+indexOf(code)) so there's no need
			ArrayList<String> theDictionary = new ArrayList<String>();

			while (br.ready())
				
			{
				currentCharacter = ""+ (char)br.read();
				previousPlusCurrent = previousCharacter+currentCharacter;
				//theDictionary excludes characters 0-255 in the ascii table
				//if previousCurrent is already in the theDictionary or if it's in the ascii table
				if (theDictionary.indexOf(previousPlusCurrent) >= 0 || previousPlusCurrent.length() == 1)
				{
					previousCharacter = previousPlusCurrent;
				}
				//print out value for previous character
				else
				{
					//print previous character if it's a single character
					if (previousCharacter.length()==1)
					{
						pw.print((int)previousCharacter.charAt(0) + " ");
					}
					//print previous if it's not a single character
					else 
					{
						pw.print(256+theDictionary.indexOf(previousCharacter) + " ");
					}
					//add previous + current to dictionary 
					if (theDictionary.size()<=1000)
					{
						theDictionary.add(previousPlusCurrent);
					}
					previousCharacter= "" + currentCharacter;
				}
			}
			
			//edge case
			if (previousCharacter.length() == 1 )
			{
				pw.print((int)previousCharacter.charAt(0)+ " ");
			}
			else
			{
				pw.print(256+theDictionary.indexOf(previousCharacter) + " ");
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
		
			//--------------------------------------------------------------------------------------




/*
			//set up 3 word variables: previous character, current character, and previous + current character
			//the third variable is probably optional because it's just (previous + current)
			//but let's have it for the sake of keeping code clean and not having to add variables over and over
			//though then again, it could be easier to just always add them because we're doing a ton of adding later

			for(int k=0; k<fileToEncode.length(); k++) {
				currentCharacter = String.valueOf(fileToEncode.charAt(k));
				previousPlusCurrent = previousCharacter + currentCharacter;

				//if found
				if(thetheDictionary.contains(previousPlusCurrent)) {
					previousCharacter = previousCharacter + currentCharacter;
				}

				//if not found
				else {
					//the below if statement is to avoid adding single characters ("a", "b", "c", etc.) to the theDictionary
					if (previousPlusCurrent.length() > 1){
						//System.out.println("Adding to dict: " + previousPlusCurrent);
						thetheDictionary.add(previousPlusCurrent);
					}
					//System.out.println("previousCurrent Length: " + previousCharacter.length());

					if(previousCharacter.length()==1) {
						//System.out.println("Adding to encoded values: " + (char)previousCharacter.charAt(0));
						encodedValues.add((int)previousCharacter.charAt(0));
					}

					else if (previousCharacter.length() > 1){ //there was a problem in which the previous character would be added to the theDictionary if it was "". "" has length 0, so the "if length() > 1" eliminates the problem
						encodedValues.add(thetheDictionary.indexOf(previousCharacter)+256);
					}
					previousCharacter = currentCharacter;
				}
			}

			//adding on the last current character so we don't lose it
			if(currentCharacter.length()==1) {
				encodedValues.add((int)currentCharacter.charAt(0));
			}

			else if (previousCharacter.length() > 1){
				encodedValues.add(thetheDictionary.indexOf(currentCharacter)+256);
			}

			try {
				FileWriter writeToFile = new FileWriter("output.txt");

				/*for (int i = 0; i < thetheDictionary.size (); i++)
			{
				writeToFile.write(thetheDictionary.get(i) + "\n");
			}
			writeToFile.write("\n");*/
		/*
				for (int i = 0; i < encodedValues.size(); i++){
					writeToFile.write(encodedValues.get(i) + "\n");
				}
				writeToFile.close();
			}
			catch (IOException e) {
				System.out.println("Uh oh :(");
			}
		

			//--------------------------------------------------------------------------------------


			//Look for (previous + current) in the theDictionary
			//a sorting algorithm could be nice but skip it if speed doesn't matter
			//isn't .sort(list) a thing?
			//IF FOUND
			//P = concatination(P,C) (which means previous = previous + current ?) concatenation

			//IF NOT FOUND:
			//"output the code which denotes P to the codestream" (which means yeet it into the encodedValues array ?)
			//add the string concatination(previous + current) to the theDictionary
			//set previous = current
			 * 
			 */



		
	
