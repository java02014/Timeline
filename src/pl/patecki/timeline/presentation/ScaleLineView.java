package pl.patecki.timeline.presentation;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ScaleLineView extends View {
	
	private Float[] timeMarks;
	private Paint paint;
	private float markerThickness = 2;
	private float lineLength = 30;

	public ScaleLineView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public ScaleLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public ScaleLineView(Context context) {
		super(context);
		init();
	}
	
	public void setup(Float[] timeMarks){
		
		this.timeMarks = timeMarks;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.d("draw","draw");
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		for (Float timeMark : timeMarks){
			
			float position = timeMark * width;
			float safePosition = keepBounds(position, markerThickness, width);
			canvas.drawLine(safePosition, height, safePosition, height - lineLength, paint);
		}
	}
	
	private void init(){
		setupPaint();
	}

	private void setupPaint() {
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(markerThickness);
	}
	
	/**
	 * @return make sure that no clipping occurs to lines
	 */
	private float keepBounds(float position, float thickness, int width){
		
		if (position - (thickness / 2) < 0){
			return thickness/2;
		} else if ( position + (thickness / 2) > width) {
			return width - (thickness / 2);
		}
		return position;
	}
}
