package pl.patecki.timeline.presentation;

import java.util.List;

import org.joda.time.DateTime;

import android.util.Log;

public class LogarythmicTimeConverter implements TimeConverter {

	private int viewSize;
	private long minimalTimeDistance;
	private double ratio;
	private boolean hasAllData;
	private boolean log = true;
	
	private static final String TAG = "LogarythmicTimeConverter";
	
	@Override
	public long getConvertedTime(long timeDistance) {
		if (timeDistance > 0)
			return (long)Math.max(1 , Math.log(timeDistance) );
		return 0;
	}
	
	@Override
	public void setNormalizationData(int viewSize, long minimalTimeDistance, long maximalTimeDistance, int additionalField){
		
		this.viewSize = viewSize;
		this.minimalTimeDistance = minimalTimeDistance;
		ratio = ((double) viewSize / this.minimalTimeDistance);
		if (log)
			Log.d(TAG, "ratio " + ratio + " View size: " + viewSize + " Minimal time distance: " + minimalTimeDistance);
		hasAllData = true;
	}
	
	@Override
	public int getNormalizedSize(long timeAfterPrevious){
		return (int)(timeAfterPrevious * ratio);
	}
	
	@Override
	public int getMinimalLength(){
		return viewSize;
	}
	
	@Override
	public double getRatio(){
		return ratio;
	}

	@Override
	public boolean hasAllData() {
		return hasAllData;
	}

	@Override
	public void setNormalizationData(int viewSize, List<DateTime> allEvents) {
		
	}

}
