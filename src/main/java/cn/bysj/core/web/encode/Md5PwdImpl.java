package cn.bysj.core.web.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Md5PwdImpl implements Md5Pwd {

	public  String encode(String password) {
		String algorithm = "MD5";

		// 加盐

		MessageDigest instance = null;

		try {
			instance = MessageDigest.getInstance(algorithm);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 加密
		byte[] digest = instance.digest(password.getBytes());

		// 十六进制加密
		char[] encodeHex = Hex.encodeHex(digest);

		String s = new String(encodeHex);

		return s;

	}

}
