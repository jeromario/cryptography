package br.com.jefferson.cryptography.service;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoService {

    private static final StrongTextEncryptor encryptor;

    static {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword("123");
    }
    
    public static String encrypt(String rawText){
        return encryptor.encrypt(rawText);
    }

    public static String decrypt(String encryptedText){
        return encryptor.decrypt(encryptedText);
    }
}
