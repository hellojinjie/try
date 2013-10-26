package com.conger.test;

import java.net.URL;

public class URLTest {

	
	public static final void main(String[] args) throws Exception {
		URL url = new URL("http://127.0.0.1/jiji");
		url.openStream();
	}
}
