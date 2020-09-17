import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Decoder 
{
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
		
		//creates a scanner to scan in the dictionary and encoded file 
		Scanner scanner = new Scanner (new File (fileName)); 
		
		//This String keeps track of which letter we're on to ensure we only scan in the dictionary at first 
		String current = " "; 
		
		while (!current.equals ("end of dictionary"))
		{
			current = scanner.nextLine (); 
			decodingDictionary.add(current); 
		}
		
		//This line removes the blank String from the dictionary that was used to separate the dictionary and encoded values 
		decodingDictionary.remove(decodingDictionary.size () -1); 
				
		FileWriter writer = new FileWriter ("decodedCode.txt"); 
		
		//scans the next line, converts to int, the gets String at the index of the int and prints it to decodedCode.txt
		while (scanner.hasNextLine())
		{
			int index = Integer.parseInt(scanner.nextLine ());
			writer.write (decodingDictionary.get (index)); 
		}
		scanner.close();
		writer.close();
	}
}

