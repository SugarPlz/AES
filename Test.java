package AES;

public class Test {
    public static void main(String[] args) {
        byte[] plain = {
                (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01,
                (byte) 0x01, (byte) 0xa1, (byte) 0x98, (byte) 0xaf,
                (byte) 0xda, (byte) 0x78, (byte) 0x17, (byte) 0x34,
                (byte) 0x86, (byte) 0x15, (byte) 0x35, (byte) 0x66
        };
        byte[] key = {
                (byte) 0x00, (byte) 0x01, (byte) 0x20, (byte) 0x01,
                (byte) 0x71, (byte) 0x01, (byte) 0x98, (byte) 0xae,
                (byte) 0xda, (byte) 0x79, (byte) 0x17, (byte) 0x14,
                (byte) 0x60, (byte) 0x15, (byte) 0x35, (byte) 0x94
        };
        Word[] plaintext = toWordArr(plain);
        System.out.println("明文：" + wordArrStr(plaintext));
        Word[] cipherKey = toWordArr(key);
        System.out.println("密钥：" + wordArrStr(cipherKey));
        Word[] cipherText = AES.encrypt(plaintext, cipherKey);
        System.out.println("密文：" + wordArrStr(cipherText));
        Word[] newPlainText = AES.decrypt(cipherText, cipherKey);
        System.out.println("明文：" + wordArrStr(newPlainText));
    }

    static Word[] toWordArr(byte[] b) {
        int len = b.length / 4;
        if (b.length % 4 != 0) {
            len++;
        }
        Word[] w = new Word[len];
        for (int i = 0; i < len; i++) {
            byte[] c = new byte[4];
            if (i * 4 < b.length) {
                System.arraycopy(b, i * 4, c, 0, 4);
            }
            w[i] = new Word(c);
        }
        return w;
    }

    static String wordArrStr(Word[] w) {
        StringBuilder str = new StringBuilder();
        for (Word word : w) {
            str.append(word);
        }
        return str.toString();
    }
}