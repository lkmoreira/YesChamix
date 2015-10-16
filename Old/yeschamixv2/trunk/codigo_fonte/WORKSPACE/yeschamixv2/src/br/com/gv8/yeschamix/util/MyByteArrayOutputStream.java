package br.com.gv8.yeschamix.util;

import java.io.ByteArrayOutputStream;

public class MyByteArrayOutputStream extends ByteArrayOutputStream{
	
	@Override
	    public synchronized byte[] toByteArray() {
	        return buf;
	    }
}

