package com.samridhi.jobsyapp.presentation.job

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.samridhi.jobsyapp.alias.AppDrawable
import com.samridhi.jobsyapp.alias.AppString
import com.samridhi.jobsyapp.navigation.HomeScreenActions
import com.samridhi.jobsyapp.presentation.common.ErrorMessage
import com.samridhi.jobsyapp.presentation.common.HorizontalProgressLoader
import com.samridhi.jobsyapp.presentation.common.ProgressLoader
import com.samridhi.jobsyapp.ui.theme.darkBlack
import com.samridhi.jobsyapp.ui.theme.gray70
import com.samridhi.jobsyapp.ui.theme.ht1
import com.samridhi.jobsyapp.ui.theme.ht2
import com.samridhi.jobsyapp.ui.theme.lightBlack
import com.samridhi.jobsyapp.ui.theme.subTitle
import com.samridhi.jobsyapp.util.isScrolledToTheEnd

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobScreen(
    viewModel: JobScreenViewModel = hiltViewModel(),
    onAction: (HomeScreenActions) -> Unit
) {

    when (viewModel.uiState.screenState) {
        ScreenState.LOADING -> {
            ProgressLoader()
        }

        ScreenState.EMPTY -> {
            ErrorMessage(errorMessage = stringResource(id = AppString.no_data_available))
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            text = stringResource(AppString.jobs_opening),
                            style = MaterialTheme.typography.ht2.copy(
                                fontSize = 20.sp, color = darkBlack
                            )
                        )
                    }
                    )
                })
            { innerPadding ->
                JobScreenContent(
                    modifier = Modifier.padding(innerPadding),
                    uiState = viewModel.uiState,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }

    LaunchedEffect(key1 = viewModel.uiSideEffect) {
        handleSideEffect(
            effects = viewModel.uiSideEffect,
            onAction = onAction
        )

        viewModel.resetSideEffects()
    }
}

@Composable
fun JobScreenContent(
    modifier: Modifier = Modifier,
    uiState: JobScreenUiState,
    onEvent: (JobScreenEvent) -> Unit
) {

    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(uiState.items) {
            JobsItem(
                onClick = {
                    onEvent(JobScreenEvent.OnClickJobItem(it))
                },
                data = it
            )
        }
        item {
            if (uiState.isLoadingMore) {
                HorizontalProgressLoader()
            }
        }
    }
    if (listState.isScrolledToTheEnd()) {
        onEvent(JobScreenEvent.OnLoadMore)
    }
}

@Composable
fun JobsItem(
    data: JobInfo,
    onClick: (String) -> Unit
) {
    Column(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
        .shadow(
            elevation = 8.dp, shape = RoundedCornerShape(8.dp)
        )
        .background(color = Color.White)
        .clickable {
            onClick(data.id)
        }
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
                append(stringResource(AppString.phone_data))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 10.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(data.phoneData)
            }
        })
    }
}

fun handleSideEffect(
    effects: JobScreenSideEffects,
    onAction: (HomeScreenActions) -> Unit
) {
    when (effects) {
        JobScreenSideEffects.NoEffect -> {

        }

        is JobScreenSideEffects.OpenJobDetailScreen -> {
            onAction(HomeScreenActions.OpenJobScreen(effects.id))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun JobScreenPreview() {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "Jobs Opening",
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 20.sp, color = darkBlack
                )
            )
        }, navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                painter = painterResource(id = AppDrawable.back),
                contentDescription = ""
            )
        })
    })
    { innerPadding ->
        JobScreenContent(
            modifier = Modifier.padding(innerPadding),
            uiState = JobScreenUiState(),
            onEvent = {}
        )
    }

}