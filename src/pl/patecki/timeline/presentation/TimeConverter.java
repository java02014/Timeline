package pl.patecki.timeline.presentation;

import java.util.List;

import org.joda.time.DateTime;

/**
 * This class works on arbitrary time unit
 */
public interface TimeConverter {
	
	/**
	 * Without normalization
	 * @return - time unit
	 */
	long getConvertedTime(long source);

	void setNormalizationData(int viewSize, long minimalTimeDistance, long maximalTimeDistance, int additionalField);
	
	/**
	 * @param viewSize
	 * @param allEvents - Events wont be kept, only necessary info will be extracted
	 */
	void setNormalizationData(int viewSize, List<DateTime> allEvents);

	int getMinimalLength();

	/**
	 * With normalization
	 * @return - length unit
	 */
	int getNormalizedSize(long timeAfterPrevious);
	
	double getRatio();
	
	boolean hasAllData();
	
	int getRatioForTimeUnit(long timeDistance, long timeUnit);
	
}
