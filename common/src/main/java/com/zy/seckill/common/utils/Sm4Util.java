package com.zy.seckill.common.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

public class Sm4Util {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static final String SYS_SECRET_KEY = "gsis20232023gsis";
    public static final String SYS_IV = "9na3v13cy9bt39vu";

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 64;


    //(2).SM4-CBC加密模式---算法/模式/补码方式(PKCS7Padding-填充大小可以在1-255bytes之间）
    public static final String SM4_CBC_PKCS7 = "SM4/CBC/PKCS7Padding";

    /**
     * 生成ECB暗号
     * @explain ECB模式（电子密码本模式：Electronic codebook）
     * @param algorithmName
     *            算法名称
     * @param mode
     *            模式
     * @param key
     * @return
     * @throws Exception
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }


    /**

     * 自动生成密钥

     * @explain

     */

    public static byte[] generateKey() throws Exception {
        return generateKey(DEFAULT_KEY_SIZE);

    }

    /**

     * @explain

     * @param keySize

     * @return

     * @throws Exception

     */

    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);

        kg.init(keySize, new SecureRandom());

        return kg.generateKey().getEncoded();

    }


    /**
     * 1.sm4加密
     * @explain 加密模式：ECB
     *          密文长度不固定，会随着被加密字符串长度的变化而变化
     * @param hexKey
     *            16进制密钥（忽略大小写）
     * @param paramStr
     *            待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     */
    public static String encryptEcb(String hexKey, String paramStr) throws Exception {
        String cipherText = "";
        // 16进制字符串-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String-->byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
        // byte[]-->hexString
        cipherText = ByteUtils.toHexString(cipherArray);
        return cipherText;
    }

    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }


    /**
     * 2.sm4解密
     * @explain 解密模式：采用ECB
     * @param hexKey
     *            16进制密钥
     * @param cipherText
     *            16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptEcb(String hexKey, String cipherText) throws Exception {
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
        // byte[]-->String
        decryptStr = new String(srcData, ENCODING);
        return decryptStr;
    }

    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }


    /**
     * 3.校验加密前后的字符串是否为同一数据
     * @explain
     * @param hexKey
     *            16进制密钥（忽略大小写）
     * @param cipherText
     *            16进制加密后的字符串
     * @param paramStr
     *            加密前的字符串
     * @return 是否为同一数据
     * @throws Exception
     */
    public static boolean verifyEcb(String hexKey, String cipherText, String paramStr) throws Exception {
        // 用于接收校验结果
        boolean flag = false;
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // 将16进制字符串转换成数组
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
        // 将原字符串转换成byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 判断2个数组是否一致
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }



    /**
     * 获取生成CBC暗号
     * @explain CBC模式
     * @param mode 1:加密(Cipher.ENCRYPT_MODE)，2：解密(Cipher.DECRYPT_MODE)
     * @param key 秘钥
     * @param iv 初始向量
     * @return
     * @throws Exception
     */
    private static Cipher generateCbcCipher(int mode,String key,String iv) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), SM4_CBC_PKCS7);
        Cipher cipher = Cipher.getInstance(SM4_CBC_PKCS7);
        IvParameterSpec spec = new IvParameterSpec(iv.getBytes());
        cipher.init(mode,secretKey,spec);
        return cipher;
    }


    /**
     * 2-1.sm4加密_CBC模式
     * @explain 加密模式：CBC(相比ECB,需要初始向量iv，增加算法强度)
     *          密文长度不固定，会随着被加密字符串长度的变化而变化
     * @param key 16进制密钥（忽略大小写）
     * @param data 待加密字符串
     * @param iv 初始向量
     * @return 返回16进制的加密字符串
     * @throws Exception
     */
    public static String encryptCbc(String key, String iv, String data) {

        //(1).将数据转为字节数组
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        //(2).获取cipher并加密获得加密后的数组
        byte[] encryptBytes;
        try {
            Cipher cipher = generateCbcCipher(Cipher.ENCRYPT_MODE,key,iv);
            encryptBytes = cipher.doFinal(dataBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //(3).加密数组编码为base64字符串
        String encryptStr = Base64.getEncoder().encodeToString(encryptBytes);

        return encryptStr;
    }

    /**
     * 2-2.sm4解密_CBC模式
     * @explain 解密模式：采用CBC(相比ECB,需要初始向量iv，增加算法强度)
     * @param key 16进制密钥
     * @param encryptStr 待解密的字符串
     * @param iv 初始向量
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decryptCbc(String key,String iv,String encryptStr) {

        //(1).将待解密的字符串通过base64解码成字节数组
        byte[] encryptBytes = Base64Utils.decode(encryptStr.getBytes());

        //(2).获取cipher并解密字节数组
        byte[] decryptBytes = new byte[0];
        try {
            Cipher cipher = generateCbcCipher(Cipher.DECRYPT_MODE,key,iv);
            decryptBytes = cipher.doFinal(encryptBytes);
        }  catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //(3).解密后的字节数组转为字符串
        String decryptStr = null;
        try {
            decryptStr = new String(decryptBytes, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return decryptStr;
    }

    public static void main(String[] args) {
        try {

            String json = "{\"name\":\"zhang\",\"phone\":\"13558810102\"}";
            // 自定义的32位16进制密钥
            //String key = "ve5hOdTeEtj28vFK";
            //String key = "UISwD9fW6cFh9SNS";
//            String key = "86C63180C2806ED1F47B859DE501215B";
            String cipher = encryptCbc(SYS_SECRET_KEY,SYS_IV, "1619963219510333440");
            String cipherDes = decryptCbc(SYS_SECRET_KEY,SYS_IV, cipher);
            System.out.println("cipher-->"+cipher);
            System.out.println("cipherDes-->"+cipherDes);

//            String cip = decryptEcb(key, "8e87c86353912f6d8d309f4938215157");
//            System.out.println("cip-->"+cip);
            //String c = SmUtil.sm4(key.getBytes()).encryptHex(json);
            // System.out.println(verifyEcb(key, cipher, json));// true
            //String abc = decryptEcb(key, "4ncw+RSEdPY/gnet0Usv0LEtCGYrxBzm6zSzXrLScUA=");
            //String cipherDes = SmUtil.sm4(key.getBytes()).decryptStr(c);

            //String name = SmUtil.sm4(key.getBytes()).decryptStr("V8kWlHpdBHQeQMQoj83tMA==");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
