package pl.patecki.timeline;

import java.util.List;

import pl.patecki.timeline.contract.CalendarEvent;
import android.database.Cursor;

public interface CalendarDataInput {

	/**
	 * @param isSorted - if set to true events are already sorted
	 * @return true if data is correct to display, false otherwise
	 */
	boolean setData(Cursor cursor, boolean isSorted);
	
	/**
	 * @param isSorted - if set to true events are already sorted
	 * @return true if data is correct to display, false otherwise
	 */
	boolean setData(List<CalendarEvent> eventList, boolean isSorted);
	
}
