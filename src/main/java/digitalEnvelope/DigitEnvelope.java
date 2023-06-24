package digitalEnvelope;

import java.io.Serializable;

public class DigitEnvelope implements Serializable {
    private static final long serialVersionUID = 1L;

    byte[] encryptedData;
    byte[] envelope; 

    public DigitEnvelope(byte[] encryptedData, byte[] envelope) {
        this.encryptedData = encryptedData;
        this.envelope = envelope;
    }

    public byte[] getEncryptedData() {
        return encryptedData;
    }

    public byte[] getEnvelope() {
        return envelope;
    }
}