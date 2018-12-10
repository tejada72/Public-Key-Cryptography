import java.util.*;	// Random number generator
import java.lang.Long;

public class RSA
{
    public static void main (String args[])
    {
    
        //Wes's method testing
        System.out.println("11: " + inverse(7, 19));
        System.out.println("4: " + modPower(2, 200, 19));
        System.out.println("5956885: " + modPower(1234, 9876, 9999999));
        System.out.println("738256: " + modPower(65536, 999888, 1500600));
        //End of Wes's unit tests
        
        Person Alice = new Person();
        Person Bob = new Person();

        String msg = "Bob, let's have lunch."; 	// message to be sent to Bob
        long []  cipher;
        cipher =  Alice.encryptTo(msg, Bob);			// encrypted, with Bob's public key

        System.out.println ("Message is: " + msg);
        System.out.println ("Alice sends:");
        show (cipher);

        System.out.println ("Bob decodes and reads: " + Bob.decrypt (cipher));	// decrypted,
        // with Bob's private key.
        System.out.println ();

        msg = "No thanks, I'm busy";
        cipher = Bob.encryptTo (msg, Alice);

        System.out.println ("Message is: " + msg);
        System.out.println ("Bob sends:");
        show (cipher);

        System.out.println ("Alice decodes and reads: " + Alice.decrypt (cipher));
        
    }

   /*
   @author:Wes Holman
   */
   public static long inverse(long e, long m){
   /*Find the multiplicative inverse of a long int
Returns:
The inverse of e, mod m. Uses the extended Eulidean Algorithm*/
      long[] r = {e, (m % e)};
      long q = m / e;
      long[] u = {0, 1};
      long carry;
      while(r[1] != 1){
         carry = r[0];
         r[0] = r[1];
         r[1] = carry % r[1];
         q = carry / r[0];
         carry = u[0];
         u[0] = u[1];
         u[1] = carry - u[0]*q;
         System.out.println(r[0] + " "+r[1]+ " "+q+" "+u[0]+" "+u[1]);
      }
      return (u[1]+m) % m;//Add m to remove negative values
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
      for(int i = bits.length()-1; i >= 0; i--){
         if(bits.charAt(i) == '1'){
            result *= powers[bits.length() - i - 1];
            result = result % m;
         }
      }
      return result;
   }

    /**
     * Converts a long to 2 chars
     * @param x type long to convert it into 2 chars.
     * @author Alex Tejada
     * @return The string made up two numeric digits representing x
     */
   public static String longTo2Chars(long x) {
       String result = "";
       result += (char)(x>>8);
       x = (long) (x % Math.pow(2, 8));
       result += (char) ((int) x);
       return result;
   }


    /**
     * Find a random prime number
     * @param m - Smallest value within the range
     * @param n - Highest value within the range
     * @param rand - Pseudo random generator from java.util
     * @author Alex Tejada
     * @return A random prime in the range m..n, using rand to generate the number
     */
    public static long randPrime(int m, int n, Random rand) {
        int initialValue =rand.nextInt(n - m) + m;

        if((initialValue % 2) == 0) {
            initialValue += 1;
        }

        int p = 0;

        for(int num = initialValue; p < 2; num = (num + 2)) {
            if(num > n) {
                if(m % 2 == 0)
                    num = m+1;
                else
                    num = m;
            }

            if(num == initialValue)
                p++;

            if(num == 3)
                return num;

            for(int i = 3; i < num; i+=2) {
                if(num % i == 0)
                    break;
                else if(i == num - 2)
                    return num;
            }
        }
        return -1;
    }

    /**
     * Find a random number relatively prime to a given long int
     * @param n Prime number
     * @param rand Pseudo random generator from java.util
     * @author Alex Tejada
     * @return a random number relatively prime to n
     */
    public static long relPrime(long n, Random rand) {
        long result = rand.nextLong();
        if(result < 0) {
            result *= -1;
        }
        result %= n;
        if(result == 0) {
            result++;
        }

        while(GCD(n,result)>1) {
            result += 1%n;
        }
        return result;
    }

    public static long GCD(long a, long b) {
        long remainder;
        while (!(b == 0)) {
            remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }

    /**
     * Display an array of longs on stdout
     * @param cipher Array of longs to get printed out.
     * @author Alex Tejada
     */
    public static void show(long[] cipher) {
        System.out.println(Arrays.toString(cipher));
    }

    /**
     * Convert two numeric chars to long int
     * @param msg Message
     * @param p Position
     * @author Alex Tejada
     * @return the two digit number beginning at position p of msg as a long int.
     */
    public static long toLong(String msg, int p) {

        StringBuilder binary = new StringBuilder();
        int i = p;
        while (i < p+2)
        {
            if(i < msg.length()) {
                byte b = (byte) msg.charAt(i);
                int val = b;
                for (int y = 0; y < 8; y++) {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            //binary.append(' ');
            i++;
        }

        long result = Long.parseUnsignedLong(binary.toString(),2);
        return result;
    }


// ..........  place class methods here

}