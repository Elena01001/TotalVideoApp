package ru.netology.totalvideoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import ru.netology.totalvideoapp.databinding.FeedFragmentBinding

class FeedFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    private var _binding: FeedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory()
        )[MainViewModel::class.java]

        binding.incrementButton.setOnClickListener {
            /*viewModel.onIncrementButtonClicked(count)
            count++
            binding.incrementButton.text = "$count"*/
            val count = binding.incrementButton.text.toString()
            viewModel.onIncrementButtonClicked(count.toInt())
        }

        binding.urlButton.setOnClickListener {
            val login = binding.putLogin.text.toString()
            val password = binding.putPassword.text.toString()
            viewModel.onUrlButtonClicked(login, password)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.incrementFlow.collect { number ->
                binding.incrementButton.text = number.toString()
            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.urlDataFlow.collect { resultString ->
                binding.urlReply.text = resultString
            }

        }

        return binding.root
    }
}