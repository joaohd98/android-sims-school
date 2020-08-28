package screens.logged.scores.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresClassesBinding

class ScoresClassesView: ConstraintLayout {
    lateinit var binding: ViewScoresClassesBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_scores_classes, this, true)
        }
        else {
            binding = ViewScoresClassesBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }
}