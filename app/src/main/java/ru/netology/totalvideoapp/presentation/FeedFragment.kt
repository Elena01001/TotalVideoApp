package ru.netology.totalvideoapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
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

        binding.progressBar.isVisible = false

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory()
        )[MainViewModel::class.java]

        binding.incrementButton.setOnClickListener {
            viewModel.onIncrementButtonClicked()
        }

        binding.urlButton.setOnClickListener {
            viewModel.onUrlButtonClicked()

        }

        observeProgressBar()
        observeIncrementEvent()
        observeSuccessfulServerReply()
        observeErrors()

        return binding.root
    }

    private fun observeIncrementEvent() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.incrementFlow.collectLatest { number ->
                binding.incrementButton.text = number.toString()
            }
        }
    }

    private fun observeSuccessfulServerReply() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.successUrlDataFlow.collectLatest { resultString ->
                binding.urlReply.text = resultString
            }
        }
    }

    private fun observeErrors() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.singleShotFlow.collectLatest {
                val message = it
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeProgressBar() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.progressBarFlow.collectLatest {
                binding.progressBar.isVisible = it == true
            }
        }
    }
}