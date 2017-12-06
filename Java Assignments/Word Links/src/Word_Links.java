import java.util.Arrays;
import java.util.Scanner;



public class Word_Links {
	
	public static void main (String[] args)
	{
		String[] dictionaryArray = readDictionary();
		boolean run = true;
		Scanner inputScanner = new Scanner(System.in);
		while(run)
		{
			System.out.println("Enter a list of words separated comma and a space (or an empty list to quit):");
			String wordList = inputScanner.nextLine();
			if(!wordList.isEmpty())		// checks for empty list to quit
			{
				String[] wordListArray = readWordList(wordList);

				if(isUniqueList(wordListArray))	// if list is unique and only English words
				{
					if(isWordChain (wordListArray, dictionaryArray))
					{
						System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
					}
					else
					{
						System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
					}
				}
				else
				{
					System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
				}
			}
			else
			{
				run = false;
				inputScanner.close();
			}
		}

				
	}
	
	public static String[] readDictionary()
	{
		In fileReader;				//initialize File Scanner
		String dictionary = null;	//initialize String
		try
		{
		fileReader = new In("words.txt");
		dictionary = new String(fileReader.readAll());
		}
		catch (Exception e)
		{
			System.out.println("Error: Reading file");
			System.exit(1);
		}
		String[] dictionaryArray = dictionary.split("\\r?\\n");		//splits string into an array using next line delimiters
		
		return dictionaryArray;
	}
	
	public static String[] readWordList(String wordList)
	{
		if(wordList == null)
		{
			System.out.println("wordList: null string exception in: readWordList" );
			System.exit(1);
		}
		String[] wordListArray = wordList.split("\\s*,\\s*");
		return wordListArray;
	}
	
	public static boolean isUniqueList(String[] wordListArray)
	{
		if(wordListArray==null)
		{
			System.out.println("wordListArray: null array exception in: isUniqueList" );
			System.exit(1);
			return false;
		}
		else
		{
			boolean isUniqueList = true;
			for( int i = 0; i<wordListArray.length-1; i++)			// moves though the word list
			{
				String testString =  wordListArray[i];				// initialize word to test
				for ( int j = i+1; j<wordListArray.length; j++)		// checks the word against the remaining words in the list
				{
					String compareString = wordListArray[j];		// initializes the word to compare the test word against
					if(testString.equals(compareString))			// checks if two words are the same 
					{
						isUniqueList = false;
					}
				}

			}
			return isUniqueList;
		}
	}

	public static boolean isEnglishWord (String word, String[] dictionaryArray)
	{
		if(word==null||dictionaryArray==null)
		{
			if(word==null)
			{
				System.out.println("word: null string exception in: isEnglishWord" );
				System.exit(1);
			}
			if(dictionaryArray==null)
			{
				System.out.println("dictionaryArray: null array exception in: isEnglishWord" );
				System.exit(1);
			}
			return false;
		}
		else
		{
			boolean isEnglishWord = false;
			Arrays.sort(dictionaryArray);
			
				int key = Arrays.binarySearch(dictionaryArray, word);		//checks to see if the word is in the dictionary 

				if(key >= 0)
				{
					String compareWord = dictionaryArray[key];

					if(word.equals(compareWord))			//ensures the words match
					{
						isEnglishWord = true;
					}
				}
			
			return isEnglishWord;
		}
	}
	
	
	
	public static boolean isDifferentByOne (String lastWord, String nextWord)
	{
		if(lastWord==null||nextWord==null)
		{
			if(lastWord==null)
			{
				System.out.println("wordListArray: null string exception in: isDifferentByOne" );
				System.exit(1);
			}
			if(nextWord==null)
			{
				System.out.println("dictionaryArray: null string exception in: isDifferentByOne" );
				System.exit(1);
			}
			return false;
		}
		else
		{
			boolean isDifferentByOne = true;
			if (lastWord.length() == nextWord.length())				// checks the word are the same length 
			{
				int differences = 0;
				for(int i = 0; i<lastWord.length(); i++)				// moves through the word one character at a time
				{
					if(lastWord.charAt(i) != nextWord.charAt(i)) 		// checks characters at the same index are the same
					{ 
						differences++;									// increases differences by one if characters are different
					}
					if(differences > 1)									// checks how many differences there are between the words
					{
						isDifferentByOne = false;
					}
				}
			}
			else
			{
				isDifferentByOne = false;
			}
			return isDifferentByOne;
		}
	}
	
	public static boolean isWordChain (String[] wordListArray, String[] dictionaryArray)
	{
		if(wordListArray==null)
		{
			System.out.println("wordListArray: null array exception in: isWordChain" );
			System.exit(1);
			return false;
		}
		else
		{
			int j = 0;
			boolean isWordChain = true;
			for (int i = 1; i<wordListArray.length; i++)					// moves through the word list 
			{
				if (!isEnglishWord(wordListArray[j], dictionaryArray))
				{
					isWordChain = false;
				}
				if(!isDifferentByOne(wordListArray[i-1], wordListArray[i]))	// checks each word only differs by one character from the last word
				{
					isWordChain = false;
				}
				j++;
			}
			return isWordChain;
		}
	}
}
