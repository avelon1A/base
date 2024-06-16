package com.example.roomdatabase.Model

import androidx.annotation.DrawableRes
import com.example.roomdatabase.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val page = listOf(
    Page(
        title = "first page",
        description = "this is the first page for landing page",
        image = R.drawable.onboardingone
    ),
    Page(
        title = "second page",
        description = "this is the second page for landing page",
        image = R.drawable.onboarding4
    ),
    Page(
        title = "third page",
        description = "this is the third page for landing page",
        image = R.drawable.onboarding3
    )
)
