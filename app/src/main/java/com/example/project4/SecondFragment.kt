package com.example.project4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.Volley
import com.example.project4.databinding.FragmentSecondBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        var jokeType = arguments?.getString("jokeArg").toString().toLowerCase()
        println(jokeType)
        var queue = Volley.newRequestQueue(context)
        viewModel.currentJoke(queue,jokeType)

        val setupObserver = Observer<String> { setup -> binding.setup.text = setup }
        viewModel.getSetup().observe(viewLifecycleOwner, setupObserver)

        val punchlineObserver = Observer<String> { punchline -> binding.punchline.text = punchline }
        viewModel.getPunchline().observe(viewLifecycleOwner, punchlineObserver)

        val imageObserver = Observer<String> { icon -> Picasso.with(context).load(icon).into(binding.image) }
        viewModel.getImage().observe(viewLifecycleOwner, imageObserver)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}