import java.io.*;
import java.math.*;
import java.util.*;	// Random number generator
import java.lang.Long;

public class RSA
{
    public static void main (String args[])
    {
        //Person Alice = new Person();
        //Person Bob = new Person();

        String msg = "Bob, let's have lunch."; 	// message to be sent to Bob
        long []  cipher;
        //cipher =  Alice.encryptTo(msg, Bob);			// encrypted, with Bob's public key

        System.out.println ("Message is: " + msg);
        System.out.println ("Alice sends:");
        //show (cipher);

        //System.out.println ("Bob decodes and reads: " + Bob.decrypt (cipher));	// decrypted,
        // with Bob's private key.
        System.out.println ();

        msg = "No thanks, I'm busy";
        //cipher = Bob.encryptTo (msg, Alice);

        System.out.println ("Message is: " + msg);
        System.out.println ("Bob sends:");
        //show (cipher);

        //System.out.println ("Alice decodes and reads: " + Alice.decrypt (cipher));
    }

   /*
   @author:Wes Holman
   */
   public static long inverse(long e, long m){
   /*Find the multiplicative inverse of a long int
Returns:
The inverse of e, mod m. Uses the extended Eulidean Algorithm*/
      return 0;
   }
   
   /*
   @author:Wes Holman
   */
   public static long modPower(long b, long p, long m){
      /*Raise a number, b, to a power, p, modulo m
      Returns:
      bp mod m*/
      /*
      Decompose p into powers of 2 through repeated division while p>0
      keep track of first 1 digit, then start keeping track of 0s
      create an array of the same size
      first entry is b
      next n-1 entries are arr[i-1]*arr[i-1] % m
      REMEMBER ORDER
      result = 1
      for every element of numbers, multiply it by corresponding 0 or 1, multiply with result, mod m
      return result
      */
      long result = 1;
      String bits = Long.toBinaryString(p);
      bits = bits.substring(bits.indexOf('1'));
      long[] powers = new long[bits.length()];
      powers[0] = b;
      for(int i = 1; i < powers.length; i++){
         powers[i] = (powers[i-1]*powers[i-1]) % m;
      }
      for(int i = bits.length(); i >= 0; i--){
         if(bits.charAt(i) == '1'){
            result *= powers[bits.length() - i];
            result = result % m;
         }
      }
      return result;
   }

   //TODO stub
    /**
     * Converts a long to 2 chars
     * @param x type long to convert it into 2 chars.
     * @return The string made up two numeric digits representing x
     */
   public static String longTo2Chars(long x) {
       return "";
   }

   //TODO stub
    /**
     * Find a random prime number
     * @param m - Smallest value within the range
     * @param n - Highest value within the range
     * @param rand - Pseudo random generator from java.util
     * @return A random prime in the range m..n, using rand to generate the number
     */
    public static long randPrime(int m, int n, Random rand) {
       return 0L;
    }

    //TODO stub
    /**
     * Find a random number relatively prime to a given long int
     * @param n Prime number
     * @param rand Pseudo random generator from java.util
     * @return a random number relatively prime to n
     */
    public static long relPrime(long n, Random rand) {
        return 0L;
    }

    //TODO stub
    /**
     * Display an array of longs on stdout
     * @param cipher Array of longs to get printed out.
     */
    public static void show(long[] cipher) {
       System.out.println("Finish this method");
    }

    //TODO stub
    /**
     * Convert two numeric chars to long int
     * @param msg Message
     * @param p Position
     * @return the two digit number beginning at position p of msg as a long int.
     */
    public static long toLong(String msg, int p) {
       return 0L;
    }


// ..........  place class methods here

}	