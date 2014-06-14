package pl.patecki.timeline.data;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import pl.patecki.timeline.contract.CalendarEvent;
import pl.patecki.timeline.presentation.CalendarEventPresentation;
import android.database.Cursor;
import android.util.Log;

public class PresentationEventsData {

	private List<CalendarEventPresentation> presentationEvents = new ArrayList<CalendarEventPresentation>();
	
	private TimeReader timeReader = TimeReader.getIntstance();
	
	private EventReader eventReader = EventReader.getIntstance();
	
	private long minTimeDistance = Long.MAX_VALUE;
	
	private long maxTimeDistance = -1;
	
	private boolean hasData = false;

	public void setData(List<CalendarEvent> calendarEvents, boolean isSorted){
		
		reset();
		for (int i = 0 ; i < calendarEvents.size() ; i++ ){
			long afterPrevious = (i == 0)? 0 : Days.daysBetween(calendarEvents.get(i-1).getDateTime(), calendarEvents.get(i).getDateTime()).getDays();
			if (afterPrevious > 0)
				minTimeDistance = Math.min(minTimeDistance, afterPrevious);
			maxTimeDistance = Math.max(maxTimeDistance, afterPrevious);
			CalendarEventPresentation currentEvent = new CalendarEventPresentation(afterPrevious, calendarEvents.get(i));
			this.presentationEvents.add(currentEvent);
		}
		Log.d("minmax","minmax " + minTimeDistance + " " + maxTimeDistance);
		hasData = true;
	}
	
	public void setData(Cursor cursor, boolean isSorted){
		
		reset();
		if (cursor == null || cursor.isClosed()){
			return;
		}
		timeReader.readColumnNumbers(cursor);
		List<DateTime> dateTimes = timeReader.readCursor(cursor);
		List<String[]> dateEventDetails = eventReader.readCursor(cursor);
		
		for (int i = 0 ; i < dateTimes.size() ; i++ ){
			
			long afterPrevious = (i == 0)? 0 : Days.daysBetween(dateTimes.get(i-1), dateTimes.get(i)).getDays();
			minTimeDistance = Math.min(minTimeDistance, afterPrevious);
			maxTimeDistance = Math.max(maxTimeDistance, afterPrevious);
			CalendarEventPresentation event = new CalendarEventPresentation(afterPrevious, dateTimes.get(i), dateEventDetails.get(i));
			presentationEvents.add(event);
		}
		hasData = true;
		Log.d("minmax","minmax " + minTimeDistance + " " + maxTimeDistance);
//		if (!isSorted)
//		Collections.sort(dateTimes);
	}
	
	private void reset(){
		
		presentationEvents.clear();
		minTimeDistance = Long.MAX_VALUE;
		maxTimeDistance = -1;
		hasData = false;
	}
	
	public List<CalendarEventPresentation> getPresentationEvents(){
		
		return (hasData)? presentationEvents : null;
	}
	
	public long getMaxTimeDistance() {
		
		return maxTimeDistance;
	}
	
	public long getMinTimeDistance() {
		return minTimeDistance;
	}

}
