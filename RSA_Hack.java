package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSA_Hack {


    public static List<Integer> primeFactorization(int n){

        List<Integer> factors = new ArrayList<>();

        for(int i = 2; i <= n; i++){
            if(n % i == 0){
                factors.add(i);
            }
            while(n % i == 0){
                n = n / i;
            }
        }

        return factors;
    }

    public static int squareAndMultiply(int x, int n, int d){

        char[] bin = Integer.toBinaryString(d).toCharArray();
        int z = 1;

        for(int i = 0; i < bin.length; i++){
            z = z * z;
            z = z % n;

            if(bin[i] == '1'){
                z = z * x;
                z = z % n;
            }
        }

        return z;
    }

    public static int phiN(int p, int q){

        return (p - 1) * (q - 1);
    }

    public static String HackRSA(int n, int b, String ciphertext){

        String[] split = ciphertext.split(",");
        int[] cipher = new int[split.length];

        // convert the ciphertext given as string into an int array,
        // storing each number which represents a character for our plaintext
        for(int i = 0; i < split.length; i++){
            cipher[i] = Integer.parseInt(split[i]);
        }

        // find the decryption key
        // decompose n in prime factors
        // n is composed by multiplication of two large prime numbers =>
        // primeFactors will contain only 2 elements
        List<Integer> primeFactors = primeFactorization(n);
        int p = primeFactors.get(0);
        int q = primeFactors.get(1);

        // computing phi of n
        int phi = phiN(p, q);

        // computing d(private key)
        // b * ? mod phi = 1
        int d = 0;
        for(int i = 0; i < phi; i++){
            if((b * i) % phi == 1){
                d = i;
                break;
            }
        }

        System.out.println("private key<" + d + ">");

        // decrypting each number from cipher
        // c^d mod n
        int[] plain = new int[cipher.length];
        for(int i = 0; i < cipher.length; i++){
            plain[i] = squareAndMultiply(cipher[i], n, d);
        }

        // computing plaintext
        StringBuilder plaintext = new StringBuilder();
        for(int i : plain){
            plaintext.append((char) (i + 65));
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Insert ciphertext (comma separated values (ex: 382,412,...): ");
        String ciphertext = sc.nextLine();

        System.out.println("Insert n: ");
        int n = sc.nextInt();

        System.out.println("Insert b: ");
        int b = sc.nextInt();

        System.out.println("Plaintext: " + HackRSA(n, b, ciphertext));
    }
}