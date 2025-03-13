package com.example.common.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

@Component
public class RSAUtils {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException("初始化RSA密钥对失败", e);
        }
    }
    
    public String encrypt(String data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("RSA加密失败", e);
        }
    }
    
    public String decrypt(String data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (Exception e) {
            throw new RuntimeException("RSA解密失败", e);
        }
    }
} 