package pl.patecki.timeline.contract;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;

public class CalendarEvent implements Comparable<CalendarEvent> {

	private DateTime dateTime;

	private String label;
	private String description;
	private String media;
	
	/**
	 * @param timeMilis 
	 * @param label - null is accepted
	 * @param description - null is accepted
	 * @param media - null is accepted
	 */
	public CalendarEvent(String label, String description,
			String media) {
		super();
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
	public CalendarEvent(DateTime dateTime, String label, String description,
			String media) {
		super();
		this.dateTime = dateTime;
		this.label = label;
		this.description = description;
		this.media = media;
	}
	
	public CalendarEvent(DateTime dateTime, String[] details) {
		super();
		this.dateTime = dateTime;
		this.label = details[0];
		this.description = details[1];
		this.media = details[2];
	}
	
	public CalendarEvent(){}

	public DateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
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
		
		return DateTimeComparator.getDateOnlyInstance().compare(this.dateTime, another.dateTime);
	}
}
