package com.samridhi.jobsyapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samridhi.jobsyapp.alias.AppFont

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
object Fonts {
    val poppinsBold = FontFamily(
        Font(AppFont.poppins_bold, FontWeight.Bold)
    )

    val poppinsRegular = FontFamily(
        Font(AppFont.poppins_regular, FontWeight.Normal)
    )

    val poppinsMedium = FontFamily(
        Font(AppFont.poppins_medium, FontWeight.Medium)
    )
    val poppinsSemiBold = FontFamily(
        Font(AppFont.poppins_semibold, FontWeight.SemiBold)
    )
    val poppinsLight = FontFamily(
        Font(AppFont.poppins_light, FontWeight.Light)
    )
}

val Typography.ht1: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.poppinsBold,
        fontSize = 28.sp,
        color = lightBlack
    )

val Typography.subTitle: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.poppinsRegular,
        fontSize = 14.sp,
        color = black70
    )

val Typography.label: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.poppinsMedium,
        fontSize = 14.sp,
        color = black70
    )

val Typography.m1: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.poppinsLight,
        fontSize = 11.sp,
        color = lightBlack
    )
val Typography.ht2: TextStyle
    get() = TextStyle(
        fontFamily = Fonts.poppinsSemiBold,
        fontSize = 14.sp,
        color = gray70
    )