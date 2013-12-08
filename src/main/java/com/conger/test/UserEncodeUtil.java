package com.conger.test;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class UserEncodeUtil {
	public String encryptUserID(String originalUserID) {
		if (StringUtils.isEmpty(originalUserID)) {
			return originalUserID;
		}

		StringBuilder encryptedUserBuffer = new StringBuilder();
		for (int i = 0; i < originalUserID.length(); i++) {
			encryptedUserBuffer.append("-").append(
					fromCharToHexString(originalUserID.charAt(i), i));
		}
		return encryptedUserBuffer.substring(1).toString();

	}

	public String decodeUserID(String encryptedUserID) {
		if (StringUtils.isEmpty(encryptedUserID)) {
			return encryptedUserID;
		}

		StringBuilder decodedUserBuffer = new StringBuilder();
		try {
			String[] parts = encryptedUserID.split("-");
			for (int i = 0; i < parts.length; ++i) {
				int octValue = Integer.parseInt(parts[i], 16);
				int ascii = octValue + 13 - i - i % 3;
				char c = (char) ascii;

				decodedUserBuffer.append(c);
			}
		} catch (Exception e) {

		}

		return decodedUserBuffer.toString();
	}

	public Date getCurrentTime() {
		return new Date();
	}

	/*
	 * hexString = hex_string_of((ascii_code_of(character) - 13 +
	 * character_index + character_index%3))
	 */
	private String fromCharToHexString(char character, int indexOfCharacter) {
		int asciiOfC = (int) character; // Get ASCII code of character.
		int transformedCode = asciiOfC - 13 + indexOfCharacter + indexOfCharacter % 3; 
		return Integer.toHexString(transformedCode);
	}
	
	public static void main(String[] args) {
	    System.out.println(new UserEncodeUtil().encryptUserID("Yusuke Nagakura"));
	}
}
