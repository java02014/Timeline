package pl.patecki.timeline.presentation;

import java.text.SimpleDateFormat;

import pl.patecki.timeline.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventViewFactory {

	private TimeConverter timeConverter;
	private LengthNormalizer lengthNormalizer;
	private int rowViewID;
	private int shortestEventDistance;

	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private LayoutInflater inflater;
	private Context context;

	/**
	 * @param rowViewID - Negative value means use dafault layout
	 * @param shrostestEventDistance - used for normalization
	 */
	public EventViewFactory(Context context, TimeConverter timeConverter, LengthNormalizer lengthNormalizer, int rowViewID, int shrostestEventDistance) {

		this.timeConverter = timeConverter;
		this.rowViewID = rowViewID;
		this.shortestEventDistance = shrostestEventDistance;
		this.context = context;
		this.lengthNormalizer = lengthNormalizer;
	}

	public View getView(CalendarEventPresentation eventPresentation, View convertView) {

		if (convertView == null)
			convertView = getInflater().inflate(rowViewID, null);

		TextView time = (TextView) convertView.findViewById(R.id.time_field);
		time.setText(sdf.format(eventPresentation.getCalendarEvent().getTime()));
		TextView title = (TextView) convertView.findViewById(R.id.title_field);
		title.setText(eventPresentation.getCalendarEvent().getLabel());
		
		int length = (int) lengthNormalizer.getNormalizedSize(timeConverter.getConvertedTime(eventPresentation.getTimeAfterPrevious()));
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams( (length != 0 )? length : lengthNormalizer.getMinimalLength(), LayoutParams.MATCH_PARENT);
		convertView.setLayoutParams(layoutParams);
		return convertView;
	}

	private LayoutInflater getInflater() {

		if (inflater == null)
			inflater = LayoutInflater.from(context);
		return inflater;
	}
}
