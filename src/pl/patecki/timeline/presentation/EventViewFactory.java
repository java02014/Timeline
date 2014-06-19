package pl.patecki.timeline.presentation;

import java.text.SimpleDateFormat;

import org.joda.time.format.DateTimeFormat;

import pl.patecki.timeline.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventViewFactory {

	private TimeConverter timeConverter;
	private int rowViewID;
	private int timelineViewID;

	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private LayoutInflater inflater;
	private Context context;

	/**
	 * @param rowViewID - Negative value means use dafault layout
	 * @param shrostestEventDistance - used for normalization
	 */
	public EventViewFactory(Context context, TimeConverter timeConverter, int rowViewID, int timelineViewID) {

		this.timeConverter = timeConverter;
		this.rowViewID = rowViewID;
		this.timelineViewID = timelineViewID;
		this.context = context;
	}

	public View getEventView(CalendarEventPresentation eventPresentation, View convertView) {

		if (convertView == null)
			convertView = getInflater().inflate(rowViewID, null);

		TextView time = (TextView) convertView.findViewById(R.id.time_field);
		time.setText(eventPresentation.getCalendarEvent().getDateTime().toString(DateTimeFormat.forPattern("YYY - MM - d")));
		TextView title = (TextView) convertView.findViewById(R.id.title_field);
		title.setText(eventPresentation.getCalendarEvent().getLabel());
//		View marker = convertView.findViewById(R.id.marker);
		
//		RelativeLayout.LayoutParams markerParams = (RelativeLayout.LayoutParams) marker.getLayoutParams();
//		if (markerParams == null)
//			markerParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		markerParams.setMargins(0, 0, (int) ((timeConverter.getMinimalLength() / 2) / 1.5), 0);
//		marker.setLayoutParams(markerParams);

		long timeAfterPrevious = eventPresentation.getTimeAfterPrevious();
		long convertedTime = timeConverter.getConvertedTime(timeAfterPrevious);
		int length = (int) timeConverter.getNormalizedSize(convertedTime); 
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(  (length > timeConverter.getMinimalLength() )? length : timeConverter.getMinimalLength() , LayoutParams.MATCH_PARENT);
		convertView.setLayoutParams(layoutParams);
		return convertView;
	}
	
	public View getTimelineView(CalendarEventPresentation eventPresentation, View convertView) {

		if (convertView == null)
			convertView = getInflater().inflate(timelineViewID, null);

		ScaleLineView lineView = (ScaleLineView)convertView.findViewById(R.id.lineView);
		
		long timeAfterPrevious = eventPresentation.getTimeAfterPrevious();
		long convertedTime = timeConverter.getConvertedTime(timeAfterPrevious);
		int length = (int) timeConverter.getNormalizedSize(convertedTime); 
		lineView.setup(eventPresentation.getTimeMarks());
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(  (length > timeConverter.getMinimalLength() )? length : timeConverter.getMinimalLength() , LayoutParams.MATCH_PARENT);
		convertView.setLayoutParams(layoutParams);
		return convertView;
	}
	
	private LayoutInflater getInflater() {

		if (inflater == null)
			inflater = LayoutInflater.from(context);
		return inflater;
	}
}
