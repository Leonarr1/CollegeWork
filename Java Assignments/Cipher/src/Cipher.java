import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cipher 
{
	public static void main(String[] args) 
	{
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
							'n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
		
		System.out.println("Enter the message you would like to encrypte in plain text(i.e. no punctuation). ");
		Scanner inputScanner = new Scanner(System.in);
		String message = inputScanner.next();
		char[] messageArray = message.toCharArray();
		encrypt(messageArray, alphabet, createCipher(alphabet));
		printCipher(createCipher(alphabet), messageArray);

	}
	
	public static char[] createCipher( char[] alphabet )
	{
		char cipherKey[] = new char[alphabet.length];
		System.arraycopy(alphabet, 0, cipherKey, 0, alphabet.length);
		if (alphabet!=null)
		{
			Random generator = new Random();
			for (int index=0; index<cipherKey.length; index++ )
			{
				int otherIndex = generator.nextInt(cipherKey.length);
				char temp = cipherKey[index];
				cipherKey[index] = cipherKey[otherIndex];
				cipherKey[otherIndex] = temp;
			}
		}
		return cipherKey;
	}
	public static void encrypt(char[] message, char[] alphabet, char[] cipher)
	{
		for( int messageIndex = 0; messageIndex < message.length; messageIndex++)
		{
			int alphabetplace = 0;
			for( int alphabetIndex = 0; alphabetIndex < alphabet.length; alphabetIndex++ )
			{
				if (message[messageIndex] == alphabet[alphabetIndex] ) 
				{
					alphabetplace = alphabetIndex;
				}
				
			}
			message[messageIndex] = cipher[alphabetplace];
			
		}
		
		
	}
	public static void printCipher( char[] key, char[] message )
	{
			System.out.println(Arrays.toString(key));
			String encryptedMessage = new String(message);
			System.out.println(encryptedMessage);
	}
}
