package com.example.androidacademyhomework.domain

import com.example.androidacademyhomework.models.Actor
import com.example.androidacademyhomework.models.Movie

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                1,
                "https://i114.fastpic.ru/big/2020/1204/d3/26ce231e64b492831917e2a12f0d31d3.png",
                false,
                13,
                "Action, Adventure, Drama",
                4,
                125,
                "Avengers: End Game",
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe.",
                137,
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        "https://i114.fastpic.ru/big/2020/1205/ef/00650b0691bed1d747f8a0bba3296def.png"
                    ),
                    Actor(
                        "Chris Evans",
                        "https://i114.fastpic.ru/big/2020/1205/bc/58b03935654d06bcda93b9e7546a24bc.png"
                    ),
                    Actor(
                        "Mark Ruffalo",
                        "https://i114.fastpic.ru/big/2020/1205/64/2b9b8a0f553ae6737d95bf12b73d6764.png"
                    ),
                    Actor(
                        "Chris Hemsworth",
                        "https://i114.fastpic.ru/big/2020/1205/72/afca4f1084ff2c57cc42d83ae6a3a872.png"
                    )
                )
            ),
            Movie(
                2,
                "https://i114.fastpic.ru/big/2020/1204/f0/8936aa154e283308a7d84878ccbeb3f0.png",
                true,
                16,
                "Action, Sci-Fi, Thriller",
                5,
                98,
                "Tenet",
                "No story",
                97,
                listOf()
            ),
            Movie(
                3,
                "https://i114.fastpic.ru/big/2020/1204/94/88b9694ec44c1bf2c4bd6c2c17a08594.png",
                false,
                13,
                "Action, Adventure, Sci-Fi",
                4,
                38,
                "Black Window",
                "No story",
                102,
                listOf()
            ),
            Movie(
                4,
                "https://i114.fastpic.ru/big/2020/1204/6a/768b4c0cda60d266bb5f0b9ff448d66a.png",
                false,
                13,
                "Action, Adventure, Fantasy",
                5,
                74,
                "Wonder Woman 1984",
                "No story",
                120,
                listOf()
            )
        )
    }
}