package components.circle_status

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.joao.simsschool.R
import kotlin.math.cos
import kotlin.math.sin

class CircleStatus: View {
    constructor(context: Context) : super(context) { init() }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){ init() }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private var isLoading: Boolean = true
    private var points: Int = 1
    private lateinit var circlePaint: Paint
    private lateinit var separatorPaint: Paint

    private fun init() {
        circlePaint = Paint()
        circlePaint.isAntiAlias = true
        circlePaint.style = Paint.Style.STROKE

        separatorPaint = Paint()
        separatorPaint.color = ContextCompat.getColor(context, R.color.background)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val radiusPart = w / 2

        val width = when {
            points <= 20 -> 10f
            points <= 40 -> 5f
            else -> 2.5f
        }

        circlePaint.strokeWidth = width
        separatorPaint.strokeWidth = width

        circlePaint.color = ContextCompat.getColor(context, if(isLoading) R.color.skeleton else R.color.blue)
        canvas?.drawCircle(w / 2, h / 2, radiusPart - width, circlePaint)

        if (points <= 1) {
            return
        }

        val pointAngle = 360 / points
        var angle = 0

        for(index in 0 until points) {
            val x = (cos(Math.toRadians(angle.toDouble())) * radiusPart).toFloat()
            val y = (sin(Math.toRadians(angle.toDouble())) * radiusPart).toFloat()

            canvas?.drawLine(radiusPart, radiusPart, x + radiusPart, y + radiusPart, separatorPaint)

            angle += pointAngle
        }
    }

    fun drawnStatus(points: Int) {
        this.points = points
        this.isLoading = false

        invalidate()
    }
}