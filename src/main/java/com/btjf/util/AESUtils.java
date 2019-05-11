package com.btjf.util;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by gfl on 2018/6/14 10:45
 * Email:gfl@cheok.com.
 */
public class AESUtils {

    //编码方式
    public static final String bm = "UTF-8";

    /**
     * 加密
     *
     * @return
     */
    public static String encrypt(String cleartext, String secretKey) {
        //加密方式： AES128(CBC/PKCS5Padding) + HEX, 私钥
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(secretKey.getBytes());
            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            //实例化加密类，参数为加密方式，要写全
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //PKCS5Padding比PKCS7Padding效率高，PKCS7Padding可支持IOS加解密
            //初始化，此方法可以采用三种方式，按加密算法要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            //加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE,7bit等等。此处看服务器需要什么编码方式
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));

            return HexUtil.encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static byte[] getIvParameterSpec(String secretKey) {
        return secretKey.substring(0,8).getBytes();
    }

    /**
     * 解密
     *
     * @return
     */
    public static String decrypt(String encrypted, String secretKey) {
        try {
            byte[] byteMi = HexUtil.decode(encrypted);
            IvParameterSpec zeroIv = new IvParameterSpec(secretKey.getBytes());
            SecretKeySpec key = new SecretKeySpec(
                    secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //与加密时不同MODE:Cipher.DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, bm);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String originalText = "hello java";
        String cipherText = encrypt(originalText, "aaaassssdddd");
        String decodeText = decrypt(cipherText, "aaaassssdddd");
        System.out.println("原文：" + originalText);
        System.out.println("密文：" + cipherText);
        System.out.println("解密：" + decodeText);
    }
}
