package com.example.androidacademyhomework.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.androidacademyhomework.R

class MovieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ConstraintLayout>(R.id.constraintLayoutMovieOne).setOnClickListener {
            goToMovieDetailFragment()
        }
    }

    private fun goToMovieDetailFragment(){
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailsFragment)
    }
}
