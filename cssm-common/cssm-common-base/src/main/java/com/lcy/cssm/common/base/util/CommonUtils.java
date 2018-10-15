package com.lcy.cssm.common.base.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * 通用工具类(提供一些常用而不好归类的方法)
 * @author 王培
 * @create 2017-04-21 23:36
 **/
public class CommonUtils {
	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
	/**
	 * 获取随机数字
	 * @param length 获取数字长度
	 * @return
	 */
	public static String randomNumber(final int length) {
		char[] chars = "1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 将分为单位的转换为元 （除100）
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(String amount) throws Exception{
		if(!amount.matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}
		return String.format("%.2f", BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)));
	}
	/**
	 * 获取tokenId
	 * @param id 对象id
	 * @return
	 */
	public static String genTokenId(String id) {
		String prefix = randomNumber(5);
		String tokenId = prefix;
		id = base64Encode(id);
		tokenId += id.length() + id + randomLetter(5);
		String mill = String.format("%d", System.currentTimeMillis());
		tokenId += mill;
		String suffix = randomLetterAndNumber(Integer.parseInt(genMinMaxRandom(10, 20)));
		tokenId += suffix;
		if (tokenId.length() > 64) {
			tokenId = tokenId.substring(0, 64);
		}
		return tokenId;
	}

	public static String getHostNameByUrl(String url){
		if(StringUtils.isNotBlank(url)){
			String[] split = url.split(".com");
			if(split[0]!=null){
				return split[0]+".com";
			}
		}
		return  null;
	}

	public static String getPathByUrl(String url){
		if(StringUtils.isNotBlank(url)){
			String[] split = url.split(".com");
			if(split[1]!=null){
				String[] split1 = split[1].split("\\?");
				if(split1[0]!=null){
					return split1[0];
				}
			}
		}
		return  null;
	}

	/**
	 * 获取指定范围内随机数
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public static String genMinMaxRandom(int min, int max) {
		int rand = (int) (min + Math.random() * max);
		return String.format("%d", rand);
	}

	/**
	 * 获取随机字符串（大小写字母）
	 * @param length 需要获取的字符串的长度
	 * @return
	 */
	public static String randomLetter(final int length) {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 获取随机字符串（大小写字母，数字）
	 * @param length 获取的字符串的长度
	 * @return
	 */
	public static String randomLetterAndNumber(final int length) {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"
				.toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Base64加密
	 * @param token 需要加密的字符串
	 * @return
	 */
	public static String base64Encode(String token) {
		byte[] bytesEncoded = Base64.encodeBase64(token.getBytes());
		return new String(bytesEncoded, Charset.forName("UTF-8"));
	}

}
