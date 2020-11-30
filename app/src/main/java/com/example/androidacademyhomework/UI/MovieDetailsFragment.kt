package com.example.androidacademyhomework.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.androidacademyhomework.R

class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonViewBack).setOnClickListener {
            returnToMovieListFragment()
        }
    }

    private fun returnToMovieListFragment() {
       findNavController().navigate( R.id.action_movieDetailsFragment_to_movieListFragment)
    }
}
