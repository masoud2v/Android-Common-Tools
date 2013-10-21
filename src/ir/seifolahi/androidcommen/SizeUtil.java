package ir.seifolahi.androidcommen;

import android.content.Context;
import android.util.TypedValue;

public class SizeUtil {

	Context mContext;

	public SizeUtil(Context context) {
		mContext = context;
	}

	public int getScreenWidth() {
		return mContext.getResources().getDisplayMetrics().widthPixels;
	}

	public int getScreenHeight() {
		return mContext.getResources().getDisplayMetrics().heightPixels;
	}

	public float getPxFromDp(int value) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics());
	}

	public int getRootPxFromDp(int value) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics());
	}
}
