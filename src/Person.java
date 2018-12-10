import java.util.Random;

public class Person {
	
	private long mE, mM, mD, mN;
		
	/**
	 * Generates a person with a public and private key.
	 * @author Anthony Macchia
	 */
	public Person() {	
		Random rand = new Random();
		long p = RSA.randPrime(20000, 65000, rand);
		long q = RSA.randPrime(20000, 65000, rand);
		
		mM = p * q;
		mN = (p -1) * (q -1);
		
		mE = RSA.relPrime(mN, rand);
		
		mD = RSA.inverse(mE, mN);
	}
	
	/**
	 * Encrypts a plain text message to a given person.
	 * @author Anthony Macchia
	 * @return An array of longs, which is the cipher text.
	 */
	public long[] encryptTo(String msg, Person person) {
		
		long[] cipher = new long[msg.length()];
		for (int i = 0, j = 0; i < msg.length(); i += 2) {
			// Get the block to encr
			long block = RSA.toLong(msg, i);
			
			// Encr the block
			cipher[j] = RSA.modPower(block, person.getE(), person.getM());
			j++;
		}
		
		return cipher;
	}
	
	/**
	 * Decrypts cipher text recieved by a person.
	 * @author Anthony Macchia
	 * @return The plain text message.
	 */
	public String decrypt(long[] cipher) {
		StringBuilder plain = new StringBuilder();
		for (long l : cipher) {
			l = RSA.modPower( l , mD, mM);
			plain.append(RSA.longTo2Chars(l));
		}
		return plain.toString();
	}
	
	/**
	 * Accessor for a person's public encryption exponent.
	 * @author Anthony Macchia
	 * @return the person's public encryption exponent.
	 */
	public long getE() { return mE; }
	
	/**
	 * Accessor for a person's public modulus.
	 * @author Anthony Macchia
	 * @return the person's public modulus
	 */
	public long getM() { return mM; }
}
