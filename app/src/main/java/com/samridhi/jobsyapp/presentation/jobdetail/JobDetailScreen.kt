package com.samridhi.jobsyapp.presentation.jobdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.samridhi.jobsyapp.ui.theme.darkBlack
import com.samridhi.jobsyapp.ui.theme.gray70
import com.samridhi.jobsyapp.ui.theme.ht1
import com.samridhi.jobsyapp.ui.theme.ht2
import com.samridhi.jobsyapp.ui.theme.label
import com.samridhi.jobsyapp.ui.theme.lightBlack
import com.samridhi.jobsyapp.ui.theme.red
import com.samridhi.jobsyapp.ui.theme.subTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailScreen(
    viewModel: JobDetailsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(AppString.job_description),
                    style = MaterialTheme.typography.ht2.copy(
                        fontSize = 20.sp, color = darkBlack
                    )
                )
            },
                actions = {
                    Icon(
                        modifier = Modifier.clickable {
                            viewModel.onEvent(JobDetailsEvent.OnSaveOrUnSave)
                        },
                        painter = painterResource(id = if (viewModel.uiState.save) AppDrawable.save else AppDrawable.unsave),
                        contentDescription = ""
                    )
                }
            )
        }
    ) { innerPadding ->
        JobDetailScreenContent(
            modifier = Modifier.padding(innerPadding),
            uiState = viewModel.uiState
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JobDetailScreenContent(
    modifier: Modifier = Modifier,
    uiState: JobDetailsUiState
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = uiState.details?.title ?: "",
            style = MaterialTheme.typography.ht1.copy(color = lightBlack, fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = uiState.details?.companyName ?: "",
            style = MaterialTheme.typography.ht1.copy(color = lightBlack, fontSize = 12.sp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row {
            FlowRow(
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Chip(text = stringResource(AppString.Location, uiState.details?.location ?: ""))
                Chip(
                    text = stringResource(
                        AppString.Qualification,
                        uiState.details?.qualifications ?: ""
                    )
                )
                Chip(text = stringResource(AppString.Salary, uiState.details?.salary ?: ""))
            }

        }
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = red
            ),
            onClick = {
            }
        ) {
            Text(
                text = "Apply Now",
                style = MaterialTheme.typography.label.copy(
                    fontSize = 16.sp,
                    color = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Divider()
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.experience))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.experience ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.qualification))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.qualification ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.feescharged))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.feesCharged ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.openings_count))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.openingsCount ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.job_role))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.jobRole ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.job_hours))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.jobHours ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.job_category))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.jobCategory ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.custom_link))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.customLink ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.views))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.views ?: "")
            }
        })
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.subTitle.copy(
                    fontSize = 14.sp, color = gray70
                ).toSpanStyle()
            ) {
                append(stringResource(AppString.shares))
                append("-")
            }
            withStyle(
                style = MaterialTheme.typography.ht2.copy(
                    fontSize = 14.sp, color = lightBlack
                ).toSpanStyle()
            ) {
                append(uiState.details?.shares ?: "")
            }
        })

    }
}

@Composable
fun Chip(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(38.dp))
            .background(color = Color.Black)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.ht1.copy(
                color = Color.White,
                fontSize = 10.sp
            ),
            maxLines = 1,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewJobDetail() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(AppString.job_description),
                    style = MaterialTheme.typography.ht2.copy(
                        fontSize = 20.sp, color = darkBlack
                    )
                )
            },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp),
                        painter = painterResource(id = AppDrawable.back),
                        contentDescription = ""
                    )
                }
            )
        }
    ) { innerPadding ->
        JobDetailScreenContent(
            modifier = Modifier.padding(innerPadding),
            uiState = JobDetailsUiState()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Chip(
            text = ""
        )
    }

}