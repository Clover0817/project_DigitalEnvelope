package digitalEnvelope;

import digitalSign.DigitSign;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;

public class Verify {
	public static boolean verifyEnvelope(String envelopeFName, String privateFName) {
	    PrivateKey privateKey;
	    try (FileInputStream fis = new FileInputStream(privateFName)) {
	        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
	            Object obj = ois.readObject();
	            privateKey = (PrivateKey) obj;
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        throw new RuntimeException(e);
	    }

	    try {
	        Path path = (new File(envelopeFName)).toPath();
	        byte[] recBytes = Files.readAllBytes(path);

	        DigitEnvelope digitEnvelope;
	        try (ByteArrayInputStream bais = new ByteArrayInputStream(recBytes);
	             ObjectInputStream ois = new ObjectInputStream(bais)) {
	            Object obj = ois.readObject();
	            digitEnvelope = (DigitEnvelope) obj;
	        } catch (IOException | ClassNotFoundException e) {
	            throw new RuntimeException(e);
	        }

	        byte[] encrypt = digitEnvelope.getEncryptedData();
	        byte[] envelope = digitEnvelope.getEnvelope();

	        Cipher c1 = Cipher.getInstance("RSA");
	        c1.init(Cipher.DECRYPT_MODE, privateKey);
	        byte[] decrypt = c1.doFinal(envelope);
	        SecretKey secretKey = new SecretKeySpec(decrypt, "AES");
	        
	        Cipher c2 = Cipher.getInstance("AES");
	        c2.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] bytes = c2.doFinal(encrypt);
	        

	        DigitSign ds;
	        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	             ObjectInputStream ois = new ObjectInputStream(bais)) {
	            Object obj = ois.readObject();
	            ds = (DigitSign) obj;
	        } catch (IOException | ClassNotFoundException e) {
	            throw new RuntimeException(e);
	        }

	        String letter = ds.getLetter();
	        byte[] digitSign = ds.getSign();
	        String publicKeyFile = ds.getPublicKey();

	        byte[] originLetter;
	        try (FileInputStream fis = new FileInputStream(letter)) {
	            originLetter = fis.readAllBytes();
	        } catch (FileNotFoundException e) {
	            throw new RuntimeException(e);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }

	        PublicKey publicKey;
	        try (FileInputStream fileInputStream = new FileInputStream(publicKeyFile)) {
	            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
	                Object obj = objectInputStream.readObject();
	                publicKey = (PublicKey) obj;
	            }
	        } catch (IOException | ClassNotFoundException e) {
	            throw new RuntimeException(e);
	        }

	        Signature sig = Signature.getInstance("SHA256withRSA");
	        sig.initVerify(publicKey);

	        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
	        messageDigest.update(originLetter);
	        byte[] originHash = messageDigest.digest();
	        sig.update(originHash);

	        return sig.verify(digitSign);

	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}