package pl.patecki.timeline.presentation;

public interface TimeConverter {
	
	TimeConverter getInstance();

	long getConvertedTime(long source);
	
}
