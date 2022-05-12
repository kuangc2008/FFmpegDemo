package com.example.myapplication.jetpack.sharedviewmodel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.FragmentJetpackOutputBinding

class OutputFragment : Fragment() {
    private lateinit var binding : FragmentJetpackOutputBinding
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentJetpackOutputBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         *  create view model in activity scope
         */

        activity?.let {
            observeInput(sharedViewModel)
        }
    }

    private fun observeInput(sharedViewModel: SharedViewModel) {
        sharedViewModel.inputNumber.observe(viewLifecycleOwner) {

            Log.i("kcc", "3333")

            it?.let {
                binding.tvOutput.text = "2 x $it  =  ${2 * it}"
            }
        }
    }

}