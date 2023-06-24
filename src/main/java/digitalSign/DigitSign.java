package digitalSign;

import java.io.Serializable;

public class DigitSign implements Serializable {

    private static final long serialVersionUID = 1L;

    private String letter;
    private byte[] digitSign; 
    private String publicKeyFile;

    public DigitSign(String letter, byte[] digitSign, String publicKeyFile) {
        this.letter = letter;
        this.digitSign = digitSign;
        this.publicKeyFile = publicKeyFile;
    }

    public String getLetter() {
        return letter;
    }

    public byte[] getSign() {
        return digitSign;
    }

    public String getPublicKey() {
        return publicKeyFile;
    }
}