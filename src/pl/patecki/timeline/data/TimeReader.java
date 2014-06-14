package pl.patecki.timeline.data;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import pl.patecki.timeline.TimelineContract;
import android.database.Cursor;

/**
 * Class responsible for reading various data types into DataTime used by this view
 */
public class TimeReader {
	
	public static final String[] COLUMNS = new String[]{ "year", "month", "day", "hour", "minute", "second", "milisecond"};
	private static enum InputType{NATURAL, MILLIS_OF_EPOCH, ARBITRARY};

	private int miliseconds_of_epoch = -1;
	
	private int milisecond = -1;
	private int second = -1;
	private int minute = -1;
	private int hour = -1;
	private int day = -1;
	private int month = -1;
	private int year = -1;
	private InputType inputType;
	
	/**
	 * 
	 * @param listDateTime - sorted in ascending order
	 * @return Duration object or null
	 */
	public static Duration getTimespan(List<DateTime> listDateTime){
		
		if (listDateTime.size() > 0)
			return new Interval(listDateTime.get(0), listDateTime.get(listDateTime.size())).toDuration();
		return null;
	}
	
	public static TimeReader getIntstance(){
		return new TimeReader();
	}
	
	private TimeReader(){}
	
	public void readColumnNumbers(Cursor cursor){
		
		year = cursor.getColumnIndex(TimelineContract.YEAR);
		month = cursor.getColumnIndex(TimelineContract.MONTH_OF_YEAR);
		day = cursor.getColumnIndex(TimelineContract.DAY_OF_MONTH);
		hour = cursor.getColumnIndex(TimelineContract.HOUR_OF_DAY);
		minute = cursor.getColumnIndex(TimelineContract.MINUTE_OF_HOUR);
		second = cursor.getColumnIndex(TimelineContract.SECOND_OF_MINUTE);
		milisecond = cursor.getColumnIndex(TimelineContract.MILLIS_OF_SECOND);
		
		miliseconds_of_epoch = cursor.getColumnIndex(TimelineContract.MILLIS_OF_EPOCH);
		if (miliseconds_of_epoch != -1)
			inputType = InputType.MILLIS_OF_EPOCH;
		else
			inputType = InputType.NATURAL;
	}
	
	public List<DateTime> readCursor(Cursor cursor){
		
		if (cursor == null || cursor.isClosed())
			return null;
		
		cursor.moveToFirst();
		List<DateTime> result = new ArrayList<DateTime>();

		switch (inputType) {
		case NATURAL:

			while(!cursor.isAfterLast()){
				
				DateTime dateTime = new DateTime(
						(year != -1)? cursor.getInt(year) : 0,
						(month != -1)? cursor.getInt(month) : 0,
						(day != -1)? cursor.getInt(day) : 0,	
						(hour != -1)? cursor.getInt(hour) : 0,
						(minute != -1)? cursor.getInt(minute) : 0,
						(second != -1)? cursor.getInt(second) : 0,
						(milisecond != -1)? cursor.getInt(milisecond) : 0
						);
				result.add(dateTime);
				cursor.moveToNext();
			}
			break;
		case MILLIS_OF_EPOCH:

			while (!cursor.isAfterLast()) {

				DateTime dateTime = new DateTime(cursor.getLong(miliseconds_of_epoch));
				result.add(dateTime);
				cursor.moveToNext();
			}
			break;

		default:
			break;
		}
		return result;
	}
	
	public long[] getMinimalMaximalDurations(List<DateTime> times){
		
		long[] result = new long[2];
		long min = Long.MAX_VALUE;
		long max = 0;
		
		for(int i = 0; i < times.size(); i++){
			
		}
		return null;
	}

}
