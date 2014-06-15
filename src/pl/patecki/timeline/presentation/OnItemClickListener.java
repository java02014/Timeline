package pl.patecki.timeline.presentation;

import pl.patecki.timeline.contract.CalendarEvent;

public interface OnItemClickListener {
	void onItemClicked(int position, CalendarEvent calendarEvent);
}
