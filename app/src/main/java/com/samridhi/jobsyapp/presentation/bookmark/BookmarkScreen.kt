package com.samridhi.jobsyapp.presentation.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.samridhi.jobsyapp.alias.AppString
import com.samridhi.jobsyapp.ui.theme.gray70
import com.samridhi.jobsyapp.ui.theme.ht1
import com.samridhi.jobsyapp.ui.theme.ht2
import com.samridhi.jobsyapp.ui.theme.lightBlack
import com.samridhi.jobsyapp.ui.theme.subTitle

@Composable
fun BookmarksScreen(
    viewModel: BookmarkScreenViewModel = hiltViewModel()
) {
    Scaffold {
        BookmarksContentScreen(
            modifier = Modifier.padding(it),
            uiState = viewModel.uiState
        )
    }
}

@Composable
fun BookmarksContentScreen(
    modifier: Modifier,
    uiState: BookmarkUiState
) {
   LazyColumn(
       modifier = modifier
   ) {
       items(uiState.items) {
         BookMarkItems(data = it)
       }
   }
}

@Composable
fun BookMarkItems(
    data : BookmarkInfo
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp, shape = RoundedCornerShape(8.dp)
            )
            .background(color = Color.White)
            .padding(16.dp)

    ) {
        Text(
            text = data.title,
            style = MaterialTheme.typography.ht1.copy(color = lightBlack, fontSize = 12.sp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 10.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.location))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 10.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(data.location)
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 10.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.salary))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 10.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(data.salary)
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 10.sp, color = gray70
                ).toSpanStyle()
            ) {
                append("experience")
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 10.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(data.experience)
            }
        })
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        BookMarkItems(
//        )
    }
}