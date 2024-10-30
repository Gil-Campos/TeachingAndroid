package com.example.networking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FirstViewModel>()
    private lateinit var postAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        viewModel.getAllPosts()
    }

    private fun observers() {
        viewModel.allPost.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                initRecyclerView(it)
            }
        }
    }

    private fun initRecyclerView(post: List<PostStructure>) {
        binding.rvPosts.apply {
            postAdapter = RecyclerAdapter(post)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}