package digitalEnvelope;

import digitalSign.DigitSign;
import javax.crypto.*;
import java.io.*;
import java.security.*;
import java.util.Arrays;

public class Create {
    public static void createEnvelope(String letterFName, String content, String myPrivateFName, String myPublicFName, String mySecretFName, String yourPublicFName, String sigFName, String encryptedFName) {
        try (FileOutputStream fos = new FileOutputStream(letterFName)) {
            fos.write(content.getBytes());

            FileInputStream fis = new FileInputStream(myPrivateFName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            PrivateKey myPrivateKey = (PrivateKey) ois.readObject();
            ois.close();
            fis.close();

            byte[] originLetter = content.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(originLetter);
            if (originLetter != null) {
            	Arrays.fill(originLetter, (byte) 0);
            }

            byte[] originHash = messageDigest.digest();

            String algorithm = "SHA256withRSA";
            Signature sig = Signature.getInstance(algorithm);
            sig.initSign(myPrivateKey);
            sig.update(originHash);
            byte[] signature = sig.sign();

            FileOutputStream signatureFos = new FileOutputStream(sigFName);
            ObjectOutputStream signatureOos = new ObjectOutputStream(signatureFos);
            signatureOos.writeObject(signature);

            DigitSign digitSign = new DigitSign(letterFName, signature, myPublicFName);
            ByteArrayOutputStream digitSignBaos = new ByteArrayOutputStream();
            ObjectOutputStream digitSignOos = new ObjectOutputStream(digitSignBaos);
            digitSignOos.writeObject(digitSign);
            byte[] dataBytes = digitSignBaos.toByteArray();

            FileInputStream secretFis = new FileInputStream(mySecretFName);
            ObjectInputStream secretOis = new ObjectInputStream(secretFis);
            Key secretKey = (Key) secretOis.readObject();

            Cipher c1 = Cipher.getInstance("AES");
            c1.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypt = c1.doFinal(dataBytes);

            FileInputStream yourPublicFis = new FileInputStream(yourPublicFName);
            ObjectInputStream yourPublicOis = new ObjectInputStream(yourPublicFis);
            PublicKey yourPublicKey = (PublicKey) yourPublicOis.readObject();

            Cipher c2 = Cipher.getInstance("RSA");
            c2.init(Cipher.ENCRYPT_MODE, yourPublicKey);
            byte[] envelope = c2.doFinal(secretKey.getEncoded());

            signatureOos.close();
            signatureFos.close();
            digitSignOos.close();
            digitSignBaos.close();
            secretOis.close();
            secretFis.close();
            yourPublicOis.close();
            yourPublicFis.close();
            
            DigitEnvelope digitEnvelope = new DigitEnvelope(encrypt, envelope);
            try (FileOutputStream fos2 = new FileOutputStream(encryptedFName);
                    ObjectOutputStream oos = new ObjectOutputStream(fos2)) {
                   oos.writeObject(digitEnvelope);
            } catch (IOException e) {
               System.err.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException | ClassNotFoundException | InvalidKeyException | SignatureException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }
}
