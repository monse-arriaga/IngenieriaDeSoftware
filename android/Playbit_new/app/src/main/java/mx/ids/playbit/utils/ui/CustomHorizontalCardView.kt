package mx.ids.playbit.utils.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import mx.ids.playbit.databinding.CardGamesBinding
import mx.ids.playbit.viewmodel.CustomCardViewModel
/**
 * CustomHorizontalCardView used to handle data of the custom view for games
 * @author Leonardo Aguilar Rodríguez
 *  */
class CustomHorizontalCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CardGamesBinding = CardGamesBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setImageResource(resId: Int) {
        binding.ivCard.setImageResource(resId)
    }

    fun setText(text: String) {
        binding.tvCard.text = text
    }

    fun bindViewModel(viewModel: CustomCardViewModel, lifecycleOwner: LifecycleOwner) {
        viewModel.text.observe(lifecycleOwner, Observer { text ->
            binding.tvCard.text = text
        })
        viewModel.imageRes.observe(lifecycleOwner, Observer { resId ->
            binding.ivCard.setImageResource(resId)
        })
    }
}