package pl.patecki.timeline.presentation;

public class LogarythmicTimeConverter implements TimeConverter {

	@Override
	public TimeConverter getInstance() {
		return new LogarythmicTimeConverter();
	}

	@Override
	public long getConvertedTime(long timeDistance) {
		if (timeDistance > 0)
			return (long)Math.max(1 , Math.log(timeDistance));
		return 0;
	}
	
}
