package key;

import java.io.*;
import java.security.*;

public class MyKeyPair {
	public static String createKeyPair(String publicFileName, String privateFileName) throws NoSuchAlgorithmException {
		String result = "<br><br>";
		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();

        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();
        result += "생성된 공개키 정보: ";
        result += "키의 길이: " + publicKeyBytes.length;
        for (byte bytes : publicKeyBytes) {
            result += String.format("%02x", bytes) + "\t";
        }

        result += "<br>생성된 개인키 정보: ";
        result += "키의 길이: " + privateKeyBytes.length;
        for (byte bytes : privateKeyBytes) {
            result += String.format("%02x", bytes) + "\t";
        }
        result += "<br>";

        String publicFName = publicFileName;

        try (FileOutputStream fos = new FileOutputStream(publicFName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(publicKey);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String privateFName = privateFileName;

        try (FileOutputStream fos = new FileOutputStream(privateFName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(privateKey);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return result;
	}
}