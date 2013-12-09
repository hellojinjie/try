package com.conger.test.r1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserIdTest {

	public static void main(String[] args) throws Exception {
		UserEncodeUtil util = new UserEncodeUtil();
		BufferedReader br = new BufferedReader(new FileReader(
				new File("/home/jj/user_id")));
		String line = null;
		Map<String, List<String>> duplicate = new HashMap<String, List<String>>();
		Map<String, String> userMap = new HashMap<String, String>();
		while ((line = br.readLine()) != null) {
			line = util.decodeUserID(line);
			if (userMap.containsKey(line.toLowerCase())) {
				List<String> sameUsers = duplicate.get(line.toLowerCase());
				if (sameUsers == null) {
					sameUsers = new ArrayList<String>(2);
					sameUsers.add(line);
					sameUsers.add(userMap.get(line.toLowerCase()));
					duplicate.put(line.toLowerCase(), sameUsers);
				} else {
					sameUsers.add(line);
				}
			} else {
				userMap.put(line.toLowerCase(), line);
			}
		}
		br.close();
		for (Map.Entry<String, List<String>> entry : duplicate.entrySet()) {
			for (String s : entry.getValue()) {
				System.out.println(s);
			}
		}
	}
}
