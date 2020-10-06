/*
* Praise be to Ms. Kaufman and Computer Science A teachers.
* They spoke the truth when they spoke of handwritten code and BlueJ.
*/

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
		System.out.print("Please give me the name of the file you wish to encode: ");
		String theFileName = keyboard.nextLine();
		int prevPlusCurLen = 0;

		try {
			//reading in a text file and creating print writer
			FileReader fR = new FileReader(theFileName);
			BufferedReader bR = new BufferedReader(fR);
			PrintWriter pW = new PrintWriter("encoded.txt");
			String previousCharacter = ""+(char)(bR.read());
			
			//create a hashmap as the dictionary that will hold all additional entries after 255
			HashMap <String, String> theDictionary = new HashMap<String, String>();

			//counter will keep track of what the code for each new entry should be
			int counter = 256;
			while (bR.ready()){
				String currentCharacter = ""+ (char)bR.read();
				String previousPlusCurrent = previousCharacter+currentCharacter;
				
				/*if previousCurrent is already in the theDictionary or 
				 * if it's in the ascii table, update previous*/
				prevPlusCurLen = previousPlusCurrent.length();
				if (theDictionary.containsKey(previousPlusCurrent) || prevPlusCurLen == 1){
					previousCharacter = previousPlusCurrent;
				}
				//print out value for previous character
				else{
					//print previous character if it's a single character
					if (previousCharacter.length()==1){
						pW.print((int)previousCharacter.charAt(0) + "\n");
					}
					//print previous if it's not a single character
					else{
						pW.print(theDictionary.get(previousCharacter) + "\n");
					}
					//add previous + current to dictionary 
					if (theDictionary.size()<=1000){
						theDictionary.put(previousPlusCurrent, ""+counter);
						counter++;
					}
					previousCharacter= "" + currentCharacter;
				}
			}
			
			//edge case for last entry 
			if (previousCharacter.length() == 1 ){
				pW.print((int)previousCharacter.charAt(0)+ "\n");
			}
			else{
				pW.print(theDictionary.get(previousCharacter) + "\n");
			}
			pW.close();
			bR.close();
			fR.close();
		}

		catch (IOException error){
			System.out.println ("can't read");
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
