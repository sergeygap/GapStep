package ru.sergeygap.gapstep.presentation.habit_create

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.androidbroadcast.vbpd.viewBinding
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.FragmentCreateHabitBinding
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.set
import androidx.core.graphics.createBitmap

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
        binding.radioGroupHabitType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioUseful -> {
                }

                R.id.radioNotUseful -> {
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
            for (x in 0 until width) {
                val hue = 360f * x / (width - 1)
                val color = Color.HSVToColor(floatArrayOf(hue, 1f, 1f))
                for (y in 0 until height) {
                    gradientBitmap[x, y] = color
                }
            }
            colorContainer.background = gradientBitmap.toDrawable(resources)
        }
        for (color in colorList) {
            val squareSize = resources.getDimensionPixelSize(R.dimen.square_size)
            val margin = (squareSize * 0.25).toInt()
            val squareView = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(squareSize, squareSize).apply {
                    rightMargin = margin
                }
                setBackgroundColor(color)
                setOnClickListener {
                    updateSelectedColor(color)
                }
            }

            colorContainer.addView(squareView)
        }
    }

    private fun updateSelectedColor(color: Int) {
        binding.selectedColorView.setBackgroundColor(color)
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        binding.textRgbValue.text = String.format("", r, g, b)
        val hsv = FloatArray(3)
        Color.RGBToHSV(r, g, b, hsv)
        val h = hsv[0].toInt()
        val s = (hsv[1] * 255).toInt()
        val v = (hsv[2] * 255).toInt()
        binding.textHsvValue.text = "HSV: ($h, $s, $v)"
    }
}