package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class RSA_Encryption {

    public static int squareAndMultiply(int x, int n, int b){

        char[] bin = Integer.toBinaryString(b).toCharArray();
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

    // encrypt plaintext using public key <b, n>
    public static int[] encryption(int n, int b, String plaintext){

        char[] plain = plaintext.toUpperCase().toCharArray();
        int[] cipher = new int[plain.length];

        // each letter of the plaintext is encrypted
        // using the squareAndMultiply algorithm
        // c = x^b mod n; x - current letter;
        for(int i = 0; i < plain.length; i++){
            int x = (plain[i] - 65);
            cipher[i] = squareAndMultiply(x, n, b);
        }

        return cipher;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Insert plaintext: ");
        String plaintext = sc.nextLine();

        System.out.println("Insert q: ");
        int q = sc.nextInt();

        System.out.println("Insert p: ");
        int p = sc.nextInt();

        System.out.println("Insert b: ");
        int b = sc.nextInt();

        int n = q * p;

        System.out.println("Public key: <" + b + ", " + n + ">");
        System.out.println("Ciphertext: " + Arrays.toString(encryption(n, b, plaintext)));
    }
}
