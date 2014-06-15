package pl.patecki.timeline.presentation;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ScaleLineView extends View {
	
	private Float[] timeMarks;
	private Paint paint;

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
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		for (Float timeMark : timeMarks){
			
			float i = timeMark * width;
			canvas.drawLine(i, height, i, height-40, paint);
		}
	}
	
	private void init(){
		setupPaint();
	}

	private void setupPaint() {
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(4);
	}
}
