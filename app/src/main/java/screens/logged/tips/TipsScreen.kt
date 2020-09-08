package screens.logged.tips

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenTipsBinding
import utils.getPixels
import kotlin.math.cos
import kotlin.math.sin

class TipsScreen : Fragment() {
    lateinit var binding: ScreenTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.screen_tips, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.screenTipsImage.clipToOutline = true
    }
}

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

    private lateinit var circlePaint: Paint
    private lateinit var separatorPaint: Paint

    private fun init() {
        circlePaint = Paint()
        circlePaint.color = ContextCompat.getColor(context, R.color.blue)
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
        val points = 2

        val width = 10f
        circlePaint.strokeWidth = width
        separatorPaint.strokeWidth = width

        canvas?.drawCircle(w / 2, h / 2, radiusPart - width, circlePaint)

        val pointAngle = 360 / points
        var angle = 0

        while(angle < 360) {
            val x = (cos(Math.toRadians(angle.toDouble())) * radiusPart).toFloat()
            val y = (sin(Math.toRadians(angle.toDouble())) * radiusPart).toFloat()
            canvas?.drawLine(radiusPart, radiusPart, x + radiusPart, y + radiusPart, separatorPaint)

            angle += pointAngle
        }

        canvas?.rotate(180f)
    }
}