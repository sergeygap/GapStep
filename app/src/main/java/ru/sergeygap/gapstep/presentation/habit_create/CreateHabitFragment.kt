package ru.sergeygap.gapstep.presentation.habit_create

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.set
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.androidbroadcast.vbpd.viewBinding
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.FragmentCreateHabitBinding

class CreateHabitFragment : Fragment(R.layout.fragment_create_habit) {

    private val binding: FragmentCreateHabitBinding by viewBinding(FragmentCreateHabitBinding::bind)
    private val viewModel: CreateHabitViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        setupRadioButtons()
        setupColorPicker()
    }

    private fun setupRadioButtons() {
        binding.radioGroupHabitType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioUseful -> {
                    // TODO: Обработка для "Полезная"
                }

                R.id.radioNotUseful -> {
                    // TODO: Обработка для "Неполезная"
                }
            }
        }
    }

    private fun setupSpinner() {
        val priorityOptions = listOf("Низкий", "Средний", "Высокий")
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, priorityOptions)
        binding.priorityAutoComplete.setAdapter(adapter)
    }

    private fun setupColorPicker() {
        val colorContainer = binding.colorContainer
        colorContainer.post {
            val width = colorContainer.width
            val height = colorContainer.height

            val gradientBitmap = createBitmap(width, height)
            (0 until width).forEach { x ->
                val hue = 360f * x / (width - 1)
                val color = Color.HSVToColor(floatArrayOf(hue, 1f, 1f))
                for (y in 0 until height) gradientBitmap[x, y] = color
            }
            colorContainer.background = gradientBitmap.toDrawable(resources)
        }

        val squaresCount = 16
        val step = 360f / squaresCount
        val strokeWidth = resources.getDimensionPixelSize(R.dimen.square_stroke_width)

        (0 until squaresCount).forEach { i ->
            val hue = i * step
            val color = Color.HSVToColor(floatArrayOf(hue, 1f, 1f))
            val squareSize = resources.getDimensionPixelSize(R.dimen.square_size)
            val margin = (squareSize * 0.25).toInt()

            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(color)
                setStroke(strokeWidth, Color.WHITE)
            }

            val squareView = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(squareSize, squareSize).apply {
                    rightMargin = margin
                }
                background = drawable
                setOnClickListener { updateSelectedColor(color) }
            }

            colorContainer.addView(squareView)
        }
    }

    private fun updateSelectedColor(color: Int) {
        binding.selectedColorView.isVisible = true
        binding.textRgbValue.isVisible = true
        binding.textHsvValue.isVisible = true

        binding.selectedColorView.setBackgroundColor(color)

        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        binding.textRgbValue.text = getString(R.string.rgb_format, r, g, b)

        val hsv = FloatArray(3)
        Color.RGBToHSV(r, g, b, hsv)
        val h = hsv[0].toInt()
        val s = (hsv[1] * 255).toInt()
        val v = (hsv[2] * 255).toInt()
        binding.textHsvValue.text = getString(R.string.hsv_format, h, s, v)
    }
}
