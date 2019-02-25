package com.ssywbj.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Wbj on 2016/3/20.
 */
public class FileUtil {

	/**
	 * 读取文件数据
	 *
	 * @param file 读取的文件
	 * @return byte（字节）数组
	 * @throws Exception
	 */
	public static byte[] readByte(File file) throws Exception {
		return readByte(new FileInputStream(file));
	}

	/**
	 * 从输入流中读取数据
	 *
	 * @param inputStream 输入流
	 * @param isReset     是否重置流：true表示重置，重置后可再次利用，但注意要在其它地方关闭流；false表示直接关闭流。
	 * @return byte（字节）数组
	 * @throws Exception
	 */
	public static byte[] readByte(InputStream inputStream) throws Exception {
		int len;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4 * 1024];
		while ((len = inputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, len);
		}
		byte[] data = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();
		inputStream.close();
		return data;
	}

	/**
	 * 从输入流中读取内容
	 *
	 * @param inputStream 输入流
	 * @return 文字内容
	 * @throws Exception
	 */
	public static String readString(InputStream inputStream) throws Exception {
		return new String(readByte(inputStream));
	}

	/**
	 * 从文件中读取内容
	 *
	 * @param file 读取的文件
	 * @return 文件内容
	 * @throws Exception
	 */
	public static String readString(File file) throws Exception {
		return new String(readByte(file));
	}

	public static void saveInfoInFile(File file, byte[] data) {
		try {
			OutputStream out = new FileOutputStream(file);
			out.write(data);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveInfoInFile(File file,InputStream inputStream) throws Exception {
		int len;
		byte[] buffer = new byte[4 * 1024];
		OutputStream outputStream = new FileOutputStream(file);
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		outputStream.close();
		inputStream.close();
	}
	
	/**
	 * 复制文件
	 *
	 * @param source 被复制的文件
	 * @param dest   复制到的文件
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void copyFile(File source, File dest) throws Exception {
		FileChannel inputChannel = new FileInputStream(source).getChannel();
		FileChannel outputChannel = new FileOutputStream(dest).getChannel();
		outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		inputChannel.close();
		outputChannel.close();
	}

}
