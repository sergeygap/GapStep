package ru.sergeygap.gapstep.presentation.habit_create

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.set
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.androidbroadcast.vbpd.viewBinding
import ru.sergeygap.gapstep.R
import ru.sergeygap.gapstep.databinding.FragmentCreateHabitBinding
import ru.sergeygap.gapstep.domain.entity.Habit

class CreateHabitFragment : Fragment(R.layout.fragment_create_habit) {

    private val binding: FragmentCreateHabitBinding by viewBinding(FragmentCreateHabitBinding::bind)
    private val viewModel: CreateHabitViewModel by viewModels()
    private var selectedColor: Int? = null
    private var selectedHabitType = ""
    private var habit: Habit? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkHabitPassing()

        setupTextWatchers()
        setupViewsElements()
        setupSpinner()
        setupRadioButtons()
        setupColorPicker()
    }

    private fun checkHabitPassing() {
        arguments?.let {
            val args = CreateHabitFragmentArgs.fromBundle(it)
            if (args.habitId == -1) return
            habit = viewModel.getHabitById(args.habitId)
            habit?.let { fillHabitFields(it) }
            binding.btnDeleteHabit.isVisible = true
            binding.btnAddHabit.text = getString(R.string.save)
        }
    }

    private fun fillHabitFields(habit: Habit) {
        binding.editTextUsername.setText(habit.name)
        binding.editTextDescription.setText(habit.description)
        if (habit.type == getString(R.string.useful)) {
            binding.radioGroupHabitType.check(R.id.radioUseful)
            selectedHabitType = getString(R.string.useful)
        } else {
            binding.radioGroupHabitType.check(R.id.radioNotUseful)
            selectedHabitType = getString(R.string.not_useful)
        }
        binding.priorityAutoComplete.setText(habit.priority, false)
        binding.editTextRepeats.setText(habit.count.toString())
        binding.editTextGoal.setText(habit.period.toString())
        updateSelectedColor(habit.color)
    }

    private fun setupViewsElements() {
        binding.btnAddHabit.isEnabled = false
        selectedHabitType = getString(R.string.useful)
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_createHabitFragment_to_habitListFragment)
        }
        binding.btnAddHabit.setOnClickListener {
            val newHabit = Habit(
                id = habit?.id ?: -1,
                name = binding.editTextUsername.text.toString().trim(),
                description = binding.editTextDescription.text.toString().trim(),
                type = selectedHabitType,
                priority = binding.priorityAutoComplete.text.toString().trim(),
                count = binding.editTextRepeats.text.toString().trim().toInt(),
                period = binding.editTextGoal.text.toString().trim().toInt(),
                color = selectedColor ?: requireContext().getColor(R.color.md_theme_primary),
            )
            if (habit != null) viewModel.updateHabit(newHabit) else viewModel.addHabit(newHabit)
            findNavController().navigate(R.id.action_createHabitFragment_to_habitListFragment)
        }
        binding.btnDeleteHabit.setOnClickListener {
            habit?.let { viewModel.deleteHabit(it) }
            findNavController().navigate(R.id.action_createHabitFragment_to_habitListFragment)
        }
    }

    private fun setupRadioButtons() {
        binding.radioGroupHabitType.setOnCheckedChangeListener { _, checkedId ->
            selectedHabitType = when (checkedId) {
                R.id.radioUseful -> getString(R.string.useful)
                R.id.radioNotUseful -> getString(R.string.not_useful)
                else -> getString(R.string.useful)

            }
            hideKeyboard()
            updateButtonState()
        }
    }

    private fun setupSpinner() {
        val priorityOptions = listOf(PriorityState.Low, PriorityState.Medium, PriorityState.High)
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, priorityOptions)
        binding.priorityAutoComplete.setAdapter(adapter)
        binding.priorityAutoComplete.setOnItemClickListener { _, _, _, _ ->
            hideKeyboard()
        }
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
        selectedColor = color
        updateButtonState(true)
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

    private fun setupTextWatchers() {
        binding.editTextUsername.doAfterTextChanged { updateButtonState() }
        binding.editTextDescription.doAfterTextChanged { updateButtonState() }
        binding.priorityAutoComplete.doAfterTextChanged { updateButtonState() }
        binding.editTextRepeats.doAfterTextChanged { updateButtonState() }
        binding.editTextGoal.doAfterTextChanged { updateButtonState() }
    }

    private fun updateButtonState(editMode: Boolean = false) {
        val isUsernameValid = binding.editTextUsername.text?.trim().isNullOrEmpty().not()
        val isDescriptionValid = binding.editTextDescription.text?.trim().isNullOrEmpty().not()
        val isPriorityValid = binding.priorityAutoComplete.text?.trim().isNullOrEmpty().not()
        val isRepeatsValid = binding.editTextRepeats.text?.trim().isNullOrEmpty().not()
        val isGoalValid = binding.editTextGoal.text?.trim().isNullOrEmpty().not()
        val isRadioSelected = binding.radioGroupHabitType.checkedRadioButtonId != -1

        val isFormValid =
            isUsernameValid && isDescriptionValid &&
                    isPriorityValid && isRepeatsValid && isGoalValid && isRadioSelected

        if (editMode) binding.btnAddHabit.isEnabled = true

        binding.btnAddHabit.isEnabled = isFormValid
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}
