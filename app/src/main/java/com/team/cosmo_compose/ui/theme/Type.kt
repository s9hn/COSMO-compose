package com.team.cosmo_compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.team.cosmo_compose.R


// Set of Material typography styles to start with
private val PretendardRegular =
    FontFamily(Font(resId = R.font.pretendard_regular, weight = FontWeight.W400))
private val PretendardMedium =
    FontFamily(Font(resId = R.font.pretendard_medium, weight = FontWeight.W500))
private val PretendardSemibold =
    FontFamily(Font(resId = R.font.pretendard_semibold, weight = FontWeight.W600))

val Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 24.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = PretendardSemibold,
        fontSize = 20.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 12.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 10.sp,
    ),
)
