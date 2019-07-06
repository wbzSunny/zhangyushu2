//package com.lzqs.zhangyushu.safety;
//
//import org.apache.shiro.crypto.hash.Sha256Hash;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.apache.shiro.util.ByteSource;
//
//public class Encrypt {
//
//    public static String passwordEncrypt(String password, String salt) {
//        String hashAlgorithmName = Sha256Hash.ALGORITHM_NAME;//加密方式
//        ByteSource bytes = ByteSource.Util.bytes(salt);
//        int hashIterations = 1024;//加密1024次
//        Object result = new SimpleHash(hashAlgorithmName, password, bytes, hashIterations);
//        return result.toString();
//    }
//
//    public static void main(String[] args) {
//        String jerry = passwordEncrypt("123456", "13190185507");
//        System.out.println(jerry);
//    }
//}
