//package pl.patecki.timeline.presentation;
//
//import java.util.List;
//
//import android.util.Log;
//
//public class LogarythmicAdjustableTimeConverter implements TimeConverter {
//
//	private int viewSize;
//	private long minimalTimeDistance;
//	private double ratio;
//	private boolean hasAllData;
//	private boolean log;
//	
//	private static final String TAG = "LogarythmicTimeConverter";
//	
//	@Override
//	public TimeConverter getInstance() {
//		return new LogarythmicAdjustableTimeConverter();
//	}
//
//	@Override
//	public long getConvertedTime(long timeDistance) {
//		if (timeDistance > 0)
//			return (long)Math.max(1 , Math.log(timeDistance) );
//		return 0;
//	}
//	
//	/**
//	 * @param strength between 0 - 100. Towards 0 more constant distances between events, towards 100 - distances more proportional to time
//	 */
//	@Override
//	public void setNormalizationData(int viewSize, long minimalTimeDistance, long maximalTimeDistance, int strength){
//		
//		this.viewSize = viewSize;
//		this.minimalTimeDistance = minimalTimeDistance;
//		ratio = ((double) viewSize / minimalTimeDistance);
//		if (log)
//			Log.d(TAG, "ratio " + ratio + " View size: " + viewSize + " Minimal time distance: " + minimalTimeDistance);
//		hasAllData = true;
//	}
//	
//	@Override
//	public int getNormalizedSize(long timeAfterPrevious){
//		return (int)(timeAfterPrevious * ratio);
//	}
//	
//	@Override
//	public int getMinimalLength(){
//		return viewSize;
//	}
//	
//	@Override
//	public double getRatio(){
//		return ratio;
//	}
//
//	@Override
//	public boolean hasAllData() {
//		return hasAllData;
//	}
//
//}
