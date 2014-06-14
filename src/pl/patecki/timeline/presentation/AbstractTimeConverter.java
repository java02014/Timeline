package pl.patecki.timeline.presentation;

import java.util.List;

import org.joda.time.DateTime;

public abstract class AbstractTimeConverter implements TimeConverter {

	private long minimalTimeDistance;
	private long maximalTimeDistance;
	
	@Override
	public void setNormalizationData(int viewSize, List<DateTime> allEvents) {
		
		
	}
	
}
