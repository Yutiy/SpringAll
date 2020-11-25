package com.springboot.shiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
	private static final String SALT = "yutiy";
	private static final String ALGORITHM_NAME = "md5";
	private static final int HASH_ITERATIONS = 2;

	public static void main(String[] args) {
		System.out.println(MD5Utils.encrypt("test", "123456"));
	}

	public static String encrypt(String pwd) {
		String newPassword = new SimpleHash(ALGORITHM_NAME, pwd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pwd) {
		return new SimpleHash(ALGORITHM_NAME, pwd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
	}
}
