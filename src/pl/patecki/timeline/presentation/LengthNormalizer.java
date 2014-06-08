package pl.patecki.timeline.presentation;

import android.util.Log;

public class LengthNormalizer{
	
	private int viewSize;
	private long minimalTimeDistance;
	private double ratio;
	
	// px / TimeUnit
	public LengthNormalizer(int viewSize, long minimalTimeDistance) {
		
		this.viewSize = viewSize;
		this.minimalTimeDistance = minimalTimeDistance;
		ratio = ((double)viewSize / (double)minimalTimeDistance);
		Log.d("ratio","ratio " + ratio + " " + viewSize + " " + minimalTimeDistance);
	}
	
	public long getNormalizedSize(long timeAfterPrevious){
		
		return (long)(timeAfterPrevious * ratio);
	}
}