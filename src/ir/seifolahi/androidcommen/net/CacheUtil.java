package ir.seifolahi.androidcommen.net;

import ir.seifolahi.androidcommen.StringUtil;
import ir.seifolahi.androidcommen.debug.Logg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

public class CacheUtil {

	long mDeadline = 1 * 24 * 60 * 60 * 1000;				//default deadline is 1 day

	/**
	 * 
	 * @param deadline is age of the cache
	 *            
	 * 0 means permanent caching
	 * default deadline is 1 day
	 * 
	 */
	public CacheUtil(long deadline) {
		mDeadline = deadline;
	}
	
	public CacheUtil() {
	}

	/**
	 * 
	 * @param context
	 * @param url
	 * @return the bitmap from cache or return null when cached bitmap is old or not available
	 * 
	 */
	public Bitmap getBitmap(Context context, String url) {

		File cacheFile = getFile(context, url);

		if (cacheFile == null)
			return null;

		Bitmap image = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());

		return image;
	}

	/**
	 * 
	 * @param context
	 * @param url
	 * @return the string from cache or return null when cached string is old or not available
	 * 
	 */
	public String getString(Context context, String url) {

		File cacheFile = getFile(context, url);

		if (cacheFile == null)
			return null;

		try {
			StringBuffer fileData = new StringBuffer();
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			char[] buf = new char[1024];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
			}
			reader.close();

			return fileData.toString();
		} catch (IOException e) {
		}

		return null;

	}

	public byte[] getBytes(Context context, String url) {

		File cacheFile = getFile(context, url);

		if (cacheFile == null)
			return null;

		try {
			RandomAccessFile file = new RandomAccessFile(cacheFile.getAbsolutePath(), "r");
			byte[] buffer = new byte[(int) file.length()];
			file.read(buffer);
			file.close();
			return buffer;
		} catch (IOException e) {
		}

		return null;

	}

	private String getFileAddress(Context context, String url) {
		return context.getCacheDir() + "/" + StringUtil.SHA1(url);
	}

	private File getFile(Context context, String url) {
		File cacheFile = new File(getFileAddress(context, url));

		if (cacheFile.exists() && mDeadline == 0)
			return cacheFile;
		
		if (cacheFile.exists() && (cacheFile.lastModified() + mDeadline) > System.currentTimeMillis())
			return cacheFile;
		

		return null;
	}

	public void save(Bitmap image, Context context, String url) {
		try {
			File icon = new File(getFileAddress(context, url));

			if (!icon.getParentFile().exists())
				icon.getParentFile().mkdirs();

			icon.createNewFile();
			FileOutputStream fos = new FileOutputStream(icon);
			image.compress(CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (IOException e) {
			Logg.printStackTrace(e);
		}
	}

	public void save(String str, Context context, String url) {
		try {
			File icon = new File(getFileAddress(context, url));

			if (!icon.getParentFile().exists())
				icon.getParentFile().mkdirs();

			icon.createNewFile();
			
			PrintWriter out = new PrintWriter(icon);
			out.print(str);
			out.close();
		} catch (IOException e) {
			Logg.printStackTrace(e);
		}
	}

}
