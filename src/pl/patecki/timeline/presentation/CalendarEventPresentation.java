package pl.patecki.timeline.presentation;

import pl.patecki.timeline.contract.CalendarEvent;

/** examine if we can marges this class with view. Probably not because of lifecycle */
public class CalendarEventPresentation {

	private CalendarEvent calendarEvent;
	private long timeAfterPrevious;
	
	/**
	 * @param currentEvent - please mind the order
	 * @param previousCalendarEvent - please mind the order
	 */
	public CalendarEventPresentation(CalendarEvent currentEvent, CalendarEvent previousCalendarEvent){
		
		this.calendarEvent = currentEvent;
		timeAfterPrevious = (previousCalendarEvent != null )? currentEvent.getTime() - previousCalendarEvent.getTime() : 0;
	}
	
	public CalendarEvent getCalendarEvent() {
		return calendarEvent;
	}

	public long getTimeAfterPrevious() {
		return timeAfterPrevious;
	}
	
}
