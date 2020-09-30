import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LZWDecoder {
	
	public static void main (String[]args) throws IOException
	{
		//determine what text file the user wants to decode by asking them
		String fileName;
		Scanner keyboard = new Scanner (System.in);
		System.out.print ("What file do you want to decode? ");
		fileName = keyboard.nextLine();
		

		//read in the file to decode
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		//create the file writer to produce the decoded file
		FileWriter file = new FileWriter ("decodedText.txt");
		
		
		// create an hashmap that will be our dictionary (index of a string = its code)
		HashMap<String, String> decodingDictionary = new HashMap<String, String>();
		// initialize it by setting characters 0-255 with the ascii table
		for (int i = 0; i < 256; i ++)
		{
			String letter = "" + (char)i;
			decodingDictionary.put(""+i, letter);
		}
		
		//represents the past code from the input file
		String past;
		
		//represents the current code from the input file
		String current;
		
		//read in the next number
			//this string will hold one line from the input file (ie, a number representing the code)
		String num = "";
			//read in the next character
		int currentChar = br.read();
			//continue reading until you hit a line break (indicating the number is over)
		while (currentChar!=10)
		{
			num += (char)(currentChar);
			currentChar = br.read();
		}
		//turn the string into the number (ie the code) and set it as the "past" code
		past = "" + num;
		
		//output the corresponding char to that number 
		file.write(decodingDictionary.get(past));
		
				
		while (br.ready() && decodingDictionary.size() < 10000)
		{
			//read in the next number using the same method i described above (just copy pasted)
			num = "";
			currentChar = br.read();
			while (currentChar!=10)
			{
				num += (char)(currentChar);
				currentChar = br.read();
			}
			
			//set that number as the "current" code
			current = "" + num;
			
			//check if the "current" code is in the dictionary
			if (decodingDictionary.containsKey (current))
			{
				//if it is, output the corresponding phrase to the output file
				file.write(decodingDictionary.get(current));
				//add the phrase corresponding to the past code + the first letter of the current code to the dictionary
				String stringToAdd = decodingDictionary.get(past) + decodingDictionary.get(current).substring(0,1);
				decodingDictionary.put(""+decodingDictionary.size(), stringToAdd);
			}
			
			else
			{
				//if the "current" code isn't in the dictionary
				//since encoder is always one step ahead,
				//current code is not in the dictionary yet 
				//this only occurs if there's a pattern where the first letter of the last "phrase" directly
				//follows it (cuz the decompression is one "step" behind)
				//therefore, we can determine the phrase corresponding to the unknown code by
				//adding the first letter of the previous code to the end of the previous code
				String outputString = decodingDictionary.get(past) + decodingDictionary.get(past).substring(0,1);
				file.write(outputString);
				
				//then add the corresponding phrase to the current code to the dictionary
				decodingDictionary.put(""+decodingDictionary.size(), outputString);
			}
			
			//make the current code the past code
			past = current;
		}
		
		//close the buffered reader
		br.close();
		
		
		file.close();
	}
}
