package pl.patecki.timeline.data;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Years;

public class TimeUnitUtility {
	
	public static final int MILISECOND = 0;
	public static final int SECOND = 1;
	public static final int MINUTE = 2;
	public static final int HOUR = 3;
	public static final int DAY = 4;
	public static final int MONTH = 5;
	public static final int YEAR = 6;
	
	private static final int YEAR_TRESHOLD = 365000;
	private static final int MONTHS_TRESHOLD = 365000;
	private static final int DAYS_TRESHOLD = 365000;
	private static final int HOURS_TRESHOLD = 365000;
	private static final int MINUTE_TRESHOLD = 365000;
	private static final int SECOND_TRESHOLD = 365000;
//	private static final int MILISECOND_TRESHOLD = 365000;
	
	private static final String TAG = "TimeUnitUtility";
	
	private TimeDistanceStrategy timeDistanceStrategy;
	
	public int chooseTimeUnit(List<DateTime> dateTimes){
		
		if (dateTimes == null || dateTimes.size() < 2){
			return -1;
		}
		return chooseTimeUnit(dateTimes.get(0), dateTimes.get(dateTimes.size() - 1));
	}

	public int chooseTimeUnit(DateTime first, DateTime last){
		
		if ( Years.yearsBetween(first, last).getYears() > YEAR_TRESHOLD ){
			timeDistanceStrategy = new YearTimeDistance();
			return YEAR;
		}
		
		if ( Months.monthsBetween(first, last).getMonths() > MONTHS_TRESHOLD ){
			timeDistanceStrategy = new MonthTimeDistance();
			return MONTH;
		}
		
		if ( Days.daysBetween(first, last).getDays() > DAYS_TRESHOLD ){
			timeDistanceStrategy = new DayTimeDistance();
			return DAY;
		}
		
		if ( Hours.hoursBetween(first, last).getHours() > HOURS_TRESHOLD ){
			timeDistanceStrategy = new HourTimeDistance();
			return HOUR;
		}
			
		if ( Minutes.minutesBetween(first, last).getMinutes() > MINUTE_TRESHOLD ){
			timeDistanceStrategy = new MinutesTimeDistance();
			return MINUTE;
		}
		
		if ( Seconds.secondsBetween(first, last).getSeconds() > SECOND_TRESHOLD ){
			timeDistanceStrategy = new SecondsTimeDistance();
			return SECOND;
		}
		
		return DAY;		
	}
	
	public int getDistance(DateTime first, DateTime second){
		return timeDistanceStrategy.getDistance(first, second);
	}
	
	private interface TimeDistanceStrategy{
		
		/**
		 * 
		 * @return -1 - means events should me merged together.
		 */
		int getDistance(DateTime first, DateTime second);
	}
	
	private class YearTimeDistance implements TimeDistanceStrategy{
		
		public int getDistance(DateTime first, DateTime second){
			
			if (first == null || second == null)
				return 0;
			return Years.yearsBetween(first, second).getYears();
		}
	}
	
	private class MonthTimeDistance implements TimeDistanceStrategy{
		
		public int getDistance(DateTime first, DateTime second){
			
			if (first == null || second == null)
				return 0;
			return Months.monthsBetween(first, second).getMonths();
		}
	}
	
	private class DayTimeDistance implements TimeDistanceStrategy{
		
		public int getDistance(DateTime first, DateTime second){
			
			if (first == null || second == null)
				return 0;
			return Days.daysBetween(first, second).getDays();
		}
	}
	
	private class HourTimeDistance implements TimeDistanceStrategy{
		
		public int getDistance(DateTime first, DateTime second){
			
			if (first == null || second == null)
				return 0;
			return Hours.hoursBetween(first, second).getHours();
		}
	}
	
	private class MinutesTimeDistance implements TimeDistanceStrategy{
		
		public int getDistance(DateTime first, DateTime second){
			
			if (first == null || second == null)
				return 0;
			return Minutes.minutesBetween(first, second).getMinutes();
		}
	}
	
	private class SecondsTimeDistance implements TimeDistanceStrategy{
		
		public int getDistance(DateTime first, DateTime second){
			
			if (first == null || second == null)
				return 0;
			return Seconds.secondsBetween(first, second).getSeconds();
		}
	}

}
