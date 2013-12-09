package com.conger.test.r1;

import java.net.URI;

public class URLTest {

	
	public static final void main(String[] args) throws Exception {
		URI uri = new URI("file://jiji/fcitx-socket-:0");
		System.out.println(uri.toURL());
	}
}
