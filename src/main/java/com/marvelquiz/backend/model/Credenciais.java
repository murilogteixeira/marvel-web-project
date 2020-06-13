package com.marvelquiz.backend.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;

public class Credenciais {
    private String privateKey = "ded1f16e4c678b8817e21f3b79fda2ea2153900c";
    private String publicKey = "ab7fe0ebc4b57fc4cfd8c5cc155ec01c";
    private Timestamp ts;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public String getHash() {
        String hash = ts + privateKey + publicKey;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(hash.getBytes(), 0, hash.length());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            return "error";
        }
    }
}