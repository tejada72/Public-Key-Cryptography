import java.io.*;
import java.math.*;
import java.util.*;	// Random number generator

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




// ..........  place class methods here

}	