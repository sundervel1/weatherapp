package com.abc.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtility {

	public static byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outStream = new ByteArrayOutputStream(data.length);
		byte[] temp = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(temp);
			outStream.write(temp, 0, size);
		}
		try {
			outStream.close();
		} catch (IOException e) {
		}

		return outStream.toByteArray();
	}

	public static byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(data.length);
		byte[] temp = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(temp);
				outStream.write(temp, 0, count);
			}
			outStream.close();
		} catch (Exception e) {
		}
		return outStream.toByteArray();
	}

}
