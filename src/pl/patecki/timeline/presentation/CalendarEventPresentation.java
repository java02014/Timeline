package pl.patecki.timeline.presentation;

import org.joda.time.DateTime;

import pl.patecki.timeline.contract.CalendarEvent;

/** examine if we can marges this class with view. Probably not because of lifecycle */
public class CalendarEventPresentation {

	private CalendarEvent calendarEvent;
	private long timeAfterPrevious;
	
//	/**
//	 * @param currentEvent - please mind the order
//	 * @param previousCalendarEvent - please mind the order
//	 */
//	public CalendarEventPresentation(CalendarEvent currentEvent, CalendarEvent previousCalendarEvent){
//		
//		this.calendarEvent = currentEvent;
//		if (previousCalendarEvent != null){
//			
//			if (currentEvent.getDateTime() != null && previousCalendarEvent.getDateTime() != null){
//				
//				timeAfterPrevious = Days.daysBetween(previousCalendarEvent.getDateTime(), currentEvent.getDateTime()).getDays();
//				timePeriodType = "days";
//			} else {
//				timeAfterPrevious = currentEvent.getTime() - previousCalendarEvent.getTime();
//				timePeriodType = "milis";
//			}
//		} else
//			timeAfterPrevious = 0;
//	}
	
	public CalendarEventPresentation(long timeAfterPreious, DateTime dateTime, String[] eventDescription){
		
		this.timeAfterPrevious = timeAfterPreious;
		this.calendarEvent = new CalendarEvent(dateTime, eventDescription); 
	}
	
	public CalendarEventPresentation(long timeAfterPreious, CalendarEvent calendarEvent){
		
		this.timeAfterPrevious = timeAfterPreious;
		this.calendarEvent = calendarEvent;
	}
	
	public CalendarEvent getCalendarEvent() {
		return calendarEvent;
	}

	public long getTimeAfterPrevious() {
		return timeAfterPrevious;
	}
	
}
