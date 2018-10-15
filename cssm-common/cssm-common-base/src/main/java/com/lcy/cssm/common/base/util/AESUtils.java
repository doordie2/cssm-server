package com.lcy.cssm.common.base.util;

import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


/**
* AES操作基础类
* @author administration
*
*/

public class AESUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(AESUtils.class);
    
	/**
	 * 禁止实例化
	 */
	private AESUtils() {
	}
	
	/**
	 * http://free4wp.com/%E8%A7%A3%E5%86%B3linux%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F%E4%B8%8Baes%E8%A7%A3%E5%AF%86%E5%A4%B1%E8%B4%A5%E7%9A%84%E9%97%AE%E9%A2%98.html
	现象描述：
	windows上加解密正常，linux上加密正常，解密时发生如下异常：

	javax.crypto.BadPaddingException: Given final block not properly padded
	       at com.sun.crypto.provider.SunJCE_f.b(DashoA13*..)
	       at
	com.sun.crypto.provider.SunJCE_f.b(DashoA13*..)
	       at
	com.sun.crypto.provider.AESCipher.engineDoFinal(DashoA13*..)
	       at
	javax.crypto.Cipher.doFinal(DashoA13*..)
	       at
	chb.test.crypto.AESUtils.crypt(AESUtils.java:386)
	       at
	chb.test.crypto.AESUtils.AesDecrypt(AESUtils.java:254)
	       at
	chb.test.crypto.AESUtils.main(AESUtils.java:40) 
	解决方法：
	经过检查之后，定位在生成KEY的方法上，如下：

	 public static SecretKey getKey (String strKey) {
	 try {
	   KeyGenerator _generator=KeyGenerator.getInstance("AES");
	   _generator.init(128, new SecureRandom(strKey.getBytes()));
	   return _generator.generateKey();
	 }  catch (Exception e) {
	     throw new RuntimeException("初始化密钥出现异常");
	 }
	} 

	修改到如下方式，问题解决：
	  public static SecretKey getKey(String strKey) {
	     try {
	      KeyGenerator _generator=KeyGenerator.getInstance("AES");
	      SecureRandom secureRandom=
	      SecureRandom.getInstance("SHA1PRNG");
	      secureRandom.setSeed(strKey.getBytes());
	      _generator.init(128,secureRandom);
	      return _generator.generateKey();
	    }  catch (Exception e) {
	        throw new RuntimeException("初始化密钥出现异常");
	    }
	  } 

	原因分析：
	SecureRandom 实现完全随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；
	该实现在 windows 上每次生成的 key 都相同，但是在 solaris 或部分 linux 系统上则不同。
    */
	
	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return 
	 */
	public static byte[] encrypt(final String content, final String password) 
        throws Exception {  
        final KeyGenerator kgen = KeyGenerator.getInstance("AES");
        final SecureRandom secureRandom = SecureRandom
                .getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes("UTF-8"));
        kgen.init(128, secureRandom);
        final SecretKey secretKey = kgen.generateKey();
        final byte[] enCodeFormat = secretKey.getEncoded();
        final SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		// 创建密码器
        final Cipher cipher = Cipher.getInstance("AES");
        final byte[] byteContent = content.getBytes("UTF-8");
		// 初始化
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(byteContent);
		// 加密
        return result;
	}  
	
	/**解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 */  
	public static byte[] decrypt(final byte[] content, final String password) 
        throws Exception {
        final KeyGenerator kgen = KeyGenerator.getInstance("AES");
        final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes("UTF-8"));
        kgen.init(128, secureRandom);
        final SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        final SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		// 创建密码器
        final Cipher cipher = Cipher.getInstance("AES");
		// 初始化
        cipher.init(Cipher.DECRYPT_MODE, key);
        final byte[] result = cipher.doFinal(content);
		// 加密
        return result;
	}
	
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}  
	
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1) {
				return null;
			}
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}  
	//加密   
	public static String encryptAndEncodeAsBase64(final String content,final String key){
		   byte [] byteMi = null ;  
		   String strMi = null;
		   try {  
		       byteMi = encrypt(content, key); 
		       strMi =  BaseEncoding.base64().encode(byteMi);
		   } catch (Exception e) {  
	            LOG.warn("exception when aesEncryption {}, detail: {}", content,
	                    e.getMessage());
		   } finally {  
		       byteMi = null ;  
		   }  
		   return strMi;  
	}
	
	//解密   
	public static String decodeAsBase64AndDecrypt(final String content, final String key) {
	       byte [] byteMing = null ;  
	       byte [] byteMi = null ;
	       String strMing = null;  
	       try {
	           byteMi = BaseEncoding.base64().decode(content);
	           byteMing = decrypt(byteMi,key);  
	           strMing = new String(byteMing, "UTF-8");  
	       } catch (Exception e) {  
	            LOG.warn("exception when aesDecryption {}, detail: {}", content,e.getMessage());
	       } finally {   
	           byteMing = null ;  
	           byteMi = null ;  
	       }  
	       return strMing;  
	}
	
	public static void main(String[] args) {
		String content = "gQHX8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0ZVaVpMcnJtRW9WcmZ3M2RlV1RwAAIEjZzNVQMEAAAAAA==";
		String password = "1234567890";
    	String contentTTT = "http://192.168.1.106:8080/ydd_market/startDownloadYdd.do?" + AESUtils.encryptAndEncodeAsBase64(content, password);
    	System.out.println("加密前contentTTT：" + contentTTT);
    	final String encoded = AESUtils.encryptAndEncodeAsBase64(content,password);
		System.out.println("加密前：" + encoded);
		System.out.println("解密后：" + AESUtils.decodeAsBase64AndDecrypt(encoded,password));
	}
}
