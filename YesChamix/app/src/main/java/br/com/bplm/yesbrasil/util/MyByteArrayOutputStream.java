package br.com.bplm.yesbrasil.util;

import java.io.ByteArrayOutputStream;

public class MyByteArrayOutputStream extends ByteArrayOutputStream{
	
	@Override
	    public synchronized byte[] toByteArray() {
	        return buf;
	    }
}

