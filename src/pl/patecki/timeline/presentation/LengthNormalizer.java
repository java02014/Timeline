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
	
	public int getNormalizedSize(long timeAfterPrevious){
		
		return (int)(timeAfterPrevious * ratio);
	}
	
	public int getMinimalLength(){
		return viewSize;
	}
}