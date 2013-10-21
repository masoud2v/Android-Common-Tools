package ir.seifolahi.androidcommen.debug;

import android.util.Log;

public class Logg {

	
	public static boolean IS_DEBUG = true;
	
	public static void i(String tag,String msg){
		
		if(!IS_DEBUG)
			return ;
		
		Log.i(tag, msg);
		
	}
	
	public static void i(String msg){
				
		if(!IS_DEBUG)
			return ;
		
		String tag = "";

		try {
			throw new Exception();
		} catch (Exception e) {
			StackTraceElement element = e.getStackTrace()[1];
			tag = element.getClassName() + " " + element.getLineNumber();
		}
		
		i(tag,msg);
		
	}
	
	
	public static void printStackTrace(Throwable t){
		
		if(!IS_DEBUG)
			return ;
		
		t.printStackTrace();
	}
	

	
}
