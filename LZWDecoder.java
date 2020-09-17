import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LZWDecoder {
	
	public static void main (String[]args) throws IOException
	{
		//determine what text file the user wants to decode
		String fileName;
		Scanner keyboard = new Scanner (System.in);
		System.out.println ("What file do you want to decode? ");
		fileName = keyboard.nextLine();
		
		// create an arraylist that will be our dictionary (index of a string = its code)
		ArrayList<String> decodingDictionary = new ArrayList<String>();
		// initialize it by setting characters 0-255
		for (int i = 0; i < 256; i ++)
		{
			String letter = "" + (char)i;
			decodingDictionary.add(letter);
		}
		
		//read in the file to decode (assuming the file starts w the dictionary
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		//stores the int value of the current character
		int currentCharacterInt = br.read();
		
		//stores the phrase (set of chars) being read in
		String currentPhrase = "";
		
		//read through the file until you hit two returns/new lines in a row
		while (currentCharacterInt != 10 || !currentPhrase.equals(""))
		{
			//if there's a new line (and currentPhrase isn't empty), add the current phrase to the
			//next index of the dictionary array list
			if (currentCharacterInt == 10)
			{
				decodingDictionary.add(currentPhrase);
				currentPhrase = "";
			}
			//if it's not a new line, add the current character to the current phrase
			else
			{
				currentPhrase += (char)(currentCharacterInt);
				
			}
			
			//read in the next character + make that the current character
			currentCharacterInt = br.read();
		}

	}
}
