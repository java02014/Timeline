package pl.patecki.timeline.presentation;

import android.view.View;

public class EventViewFactory {
	
	private TimeConverter timeConverter;
	private int rowViewID;
	private int shortestEventDistance;
	
	/**
	 * @param rowViewID - Negative value means use dafault layout
	 * @param shrostestEventDistance - used for normalization
	 */
	public EventViewFactory(TimeConverter timeConverter, int rowViewID, int shrostestEventDistance){
		
		this.timeConverter = timeConverter;
		this.rowViewID = rowViewID;
		this.shortestEventDistance = shrostestEventDistance;
	}

	public View getView(CalendarEventPresentation eventPresentation, View convertView){
		
		return null;
	}
}
