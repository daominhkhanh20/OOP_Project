package sample.Test;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class kgk {
    public static void main(String[] args) throws Exception {

        Scanner scanner=new Scanner(System.in);
        hash hashPass= new hash();
        String pass=scanner.nextLine();
        String temp=hashPass.hashPassword(pass);
        System.out.println(temp.length());
        System.out.println(temp);
    }
}
