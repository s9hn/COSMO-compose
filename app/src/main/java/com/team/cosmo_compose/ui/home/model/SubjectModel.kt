package com.team.cosmo_compose.ui.home.model

import androidx.compose.runtime.Stable
import com.team.cosmo_compose.R

@Stable
data class SubjectModel(
    val subjectTitle: String,
    val subjectIcon: Int,
)

@Stable
val subjects: List<SubjectModel> = listOf(
    SubjectModel(
        "운영체제", R.drawable.ic_os,
    ),
    SubjectModel(
        "자료구조", R.drawable.ic_data_structure,
    ),
    SubjectModel(
        "알고리즘", R.drawable.ic_algorithm,
    ),
    SubjectModel(
        "네트워크", R.drawable.ic_network,
    ),
    SubjectModel(
        "데이터베이스", R.drawable.ic_database,
    ),
)
