package com.example.mycoroutineapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycoroutineapplication.databinding.FragmentPostBinding


class PostFragment : Fragment() {

    var binding : FragmentPostBinding ?= null
    val viewModel: PostsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.posts.observe(viewLifecycleOwner){
            binding?.txtNumPosts?.text = it.size.toString()
            binding?.recPosts?.adapter?.notifyDataSetChanged()
        }

        binding?.btnRetrieve?.setOnClickListener{
            viewModel.retrievePosts()
        }

        binding?.recPosts?.layoutManager = LinearLayoutManager(context)
        binding?.recPosts?.adapter = PostListAdapter(viewModel.posts)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}