package com.example.litelife.ui.plans.tabLayoutPlans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.litelife.data.adapter.ExerciseAdapter
import com.example.litelife.databinding.FragmentListActivityTrackerBinding
import com.example.litelife.ui.plans.PlansViewModel

class FoodFragment : Fragment() {
    private lateinit var viewModel: PlansViewModel
    private lateinit var binding: FragmentListActivityTrackerBinding
    private lateinit var adapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListActivityTrackerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PlansViewModel::class.java]

        adapter = ExerciseAdapter()

        binding.rvList.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvList.adapter = adapter

        val activity = ""
        viewModel.getExercise(activity)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        viewModel.listExercise.observe(viewLifecycleOwner) { exercise ->
            exercise?.let {
                adapter.setListUser(it)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    companion object {
        const val ARG_NAME = "exercise"
    }
}