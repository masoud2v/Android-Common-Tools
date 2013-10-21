package ir.seifolahi.androidcommen.debug;

import java.util.HashMap;
import java.util.Map;

public class Chronometer {
	
	private static Map<Integer, Long> mChronometer = new HashMap<Integer, Long>();
	
	public static void startChronometer(){
		startChronometer(0);
	}
	
	public static long stopChronometer(){
		stopChronometer(0);
		return getTimeOfChronometer(0);
	}
	
	public static void startChronometer(int i){
		mChronometer.put(i, System.currentTimeMillis());
	}
	
	public static long stopChronometer(int i){
		if(getTimeOfChronometer(i)==0)throw new RuntimeException("you should call startChornometter before stopChornometter");
		
		long time = System.currentTimeMillis() - getTimeOfChronometer(i);
		mChronometer.put(i, time);
		
		Logg.i("Chornometter " + i, "time " + time + " ms");
		
		return time;
	}
	
	public static long getTimeOfChronometer(int i){
		return mChronometer.get(i);
	}

}
