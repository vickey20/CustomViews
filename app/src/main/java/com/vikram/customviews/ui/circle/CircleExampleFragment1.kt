package com.vikram.customviews.ui.circle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vikram.customviews.R
import com.vikram.customviews.databinding.CircleExampleFragment1Binding

class CircleExampleFragment1 : Fragment() {
    private lateinit var binding: CircleExampleFragment1Binding

    companion object {
        fun newInstance() = CircleExampleFragment1()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = CircleExampleFragment1Binding.bind(inflater.inflate(R.layout.circle_example_fragment_1, container, false))
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.titleText.text = resources.getString(R.string.circle_example_1)

        binding.playButton.setOnClickListener {
            if (binding.circleView.play) {
                binding.circleView.play = false
                binding.playButton.text = resources.getText(R.string.play)
            } else {
                binding.circleView.play = true
                binding.circleView.play()
                binding.playButton.text = resources.getText(R.string.pause)
            }
        }
    }
}