package key;

import javax.crypto.KeyGenerator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class MySecretKey {
    public static String createKey(String fileName) throws NoSuchAlgorithmException {
        String rslt = "";
        
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        Key secretKey = kg.generateKey();

        byte[] secretKeyBytes = secretKey.getEncoded();
        rslt += "키의 길이 (bytes): " + secretKeyBytes.length;

        for (byte bytes : secretKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        rslt += "\n";

        String fName = fileName;

        try (FileOutputStream fos = new FileOutputStream(fName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(secretKey);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return rslt;
    }
}