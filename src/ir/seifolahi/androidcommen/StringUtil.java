package ir.seifolahi.androidcommen;

import java.security.MessageDigest;
import java.util.Formatter;

public class StringUtil {

	public static String format(String formatString, Object... args) {
		Formatter formatter = new Formatter();
		String formatedString = formatter.format(formatString, args).toString();
		formatter.close();
		return formatedString;
	}

	public static String MD5(String str) {
		return hash(str, "MD5");
	}

	public static String SHA1(String str) {
		return hash(str, "SHA-1");
	}

	public static String hash(String text, String algorithm) {

		try {

			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			byte[] hashBytes = md.digest();
			return convertToHex(hashBytes);

		} catch (Exception e) {
		}

		return "";

	}

	private static String convertToHex(byte[] data) {
		StringBuilder buf = new StringBuilder();
		for (byte b : data) {
			int halfbyte = (b >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
				halfbyte = b & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

}
