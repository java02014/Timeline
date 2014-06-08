package pl.patecki.timeline.contract;

import java.util.Calendar;

public class CalendarEvent implements Comparable<CalendarEvent> {

	private long timeMilis;
	private String label;
	private String description;
	private String media;
	
	/**
	 * @param timeMilis 
	 * @param label - null is accepted
	 * @param description - null is accepted
	 * @param media - null is accepted
	 */
	public CalendarEvent(long timeMilis, String label, String description,
			String media) {
		super();
		this.timeMilis = timeMilis;
		this.label = label;
		this.description = description;
		this.media = media;
	}
	
	/**
	 * @param timeMilis 
	 * @param label - null is accepted
	 * @param description - null is accepted
	 * @param media - null is accepted
	 */
	public CalendarEvent(Calendar date, String label, String description,
			String media) {
		super();
		this.timeMilis = date.getTimeInMillis();
		this.label = label;
		this.description = description;
		this.media = media;
	}
	
	public CalendarEvent(){}

	public long getTime() {
		return timeMilis;
	}
	public void setTime(long time) {
		this.timeMilis = time;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}

	@Override
	public int compareTo(CalendarEvent another) {
		
		if (this.timeMilis > another.timeMilis)
			return 1;
		else if (this.timeMilis < another.timeMilis)
			return -1;
		return 0;
	}
}
