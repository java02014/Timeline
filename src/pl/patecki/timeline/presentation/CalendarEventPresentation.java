package pl.patecki.timeline.presentation;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import android.util.Log;

import pl.patecki.timeline.contract.CalendarEvent;

public class CalendarEventPresentation implements Comparable<CalendarEventPresentation> {

	private CalendarEvent calendarEvent;
	private long timeAfterPrevious;
	private Float[] timeMarks;
	
	public CalendarEventPresentation(long timeAfterPreious, DateTime dateTime, String[] eventDescription){
		
		this.timeAfterPrevious = timeAfterPreious;
		this.calendarEvent = new CalendarEvent(dateTime, eventDescription); 
		calculateTimeMarks();
	}
	
	public CalendarEventPresentation(long timeAfterPreious, CalendarEvent calendarEvent){
		
		this.timeAfterPrevious = timeAfterPreious;
		this.calendarEvent = calendarEvent;
		calculateTimeMarks();
	}
	
	public CalendarEvent getCalendarEvent() {
		return calendarEvent;
	}

	public long getTimeAfterPrevious() {
		return timeAfterPrevious;
	}

	@Override
	public int compareTo(CalendarEventPresentation another) {

		return this.getCalendarEvent().compareTo(another.getCalendarEvent());
	}
	
	private void calculateTimeMarks(){
		
		DateTime dateTime = calendarEvent.getDateTime();
		DateTime monthStartDateTime = dateTime.withDayOfMonth(1);
		DateTime previousDateTime = dateTime.minusDays((int)this.timeAfterPrevious);
		int isOutsideRange = monthStartDateTime.compareTo(previousDateTime);
		
		if (isOutsideRange <= 0){
			// there is not enougth space to place marks
			Log.d("months", "months between 0");
			timeMarks = new Float[0];
			return;
		}
		
		int monthsDistance = Months.monthsBetween(previousDateTime, dateTime.withDayOfMonth(1)).getMonths() + 1;
		timeMarks = new Float[monthsDistance ];
		Log.d("months", "months between " + monthsDistance);
		
		DateTime currentTime = monthStartDateTime.minusMonths(monthsDistance);
		for (int i = 0; i < monthsDistance; i++){
			currentTime = currentTime.plusMonths(1);
			int daysDistance = Days.daysBetween(currentTime, dateTime).getDays();
			timeMarks[i] = 1 - (float)daysDistance / timeAfterPrevious;
			
		}
		for (Float l : timeMarks){
			Log.d("month","month " + l);
		}
////			timeMarks[i] = 1 - getProportional( i * 30 + dateTime.getDayOfMonth(), );
	}
	
	public Float[] getTimeMarks() {
		return timeMarks;
	}
}
