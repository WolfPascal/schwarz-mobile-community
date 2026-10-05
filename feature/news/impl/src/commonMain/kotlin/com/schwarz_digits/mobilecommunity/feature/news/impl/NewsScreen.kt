package com.schwarz_digits.mobilecommunity.feature.news.impl

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.schwarz_digits.mobilecommunity.core.model.NewsArticle
import com.schwarz_digits.mobilecommunity.core.ui.components.AsyncNetworkImage
import com.schwarz_digits.mobilecommunity.core.ui.components.EmptyStateView
import com.schwarz_digits.mobilecommunity.core.ui.components.ErrorStateView
import com.schwarz_digits.mobilecommunity.core.ui.components.LoadingStateView
import com.schwarz_digits.mobilecommunity.core.ui.resources.Res
import com.schwarz_digits.mobilecommunity.core.ui.resources.brand_schwarz_digits
import com.schwarz_digits.mobilecommunity.core.ui.resources.loading_news
import com.schwarz_digits.mobilecommunity.core.ui.resources.news_empty_subtitle
import com.schwarz_digits.mobilecommunity.core.ui.resources.news_empty_title
import com.schwarz_digits.mobilecommunity.core.ui.resources.news_headline
import com.schwarz_digits.mobilecommunity.core.ui.resources.news_read_more
import com.schwarz_digits.mobilecommunity.core.ui.resources.news_subtitle
import com.schwarz_digits.mobilecommunity.core.ui.resources.state_error_subtitle
import com.schwarz_digits.mobilecommunity.core.ui.resources.state_error_title
import com.schwarz_digits.mobilecommunity.core.ui.theme.DigitsColors
import com.schwarz_digits.mobilecommunity.feature.news.impl.presentation.NewsUiState
import com.schwarz_digits.mobilecommunity.feature.news.impl.presentation.NewsViewModel
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NewsScreen(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
) {
    NewsScreen(
        contentPadding = contentPadding,
        modifier = modifier,
        viewModel = viewModel { NewsViewModel() },
    )
}

@Composable
internal fun NewsScreen(
    contentPadding: PaddingValues,
    modifier: Modifier,
    viewModel: NewsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    val statusBarTop = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val topPadding =
        if (contentPadding.calculateTopPadding() > statusBarTop) {
            contentPadding.calculateTopPadding() + 16.dp
        } else {
            statusBarTop + 16.dp
        }

    LazyColumn(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        contentPadding =
            PaddingValues(
                top = topPadding,
                bottom = contentPadding.calculateBottomPadding() + 96.dp,
                start = 20.dp,
                end = 20.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier =
                            Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .background(DigitsColors.CyanPrimary, RoundedCornerShape(2.dp)),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(Res.string.brand_schwarz_digits),
                        style = MaterialTheme.typography.labelSmall,
                        color = DigitsColors.CyanPrimary,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(Res.string.news_headline),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(Res.string.news_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        when (val state = uiState) {
            is NewsUiState.Loading -> {
                item {
                    LoadingStateView(
                        message = stringResource(Res.string.loading_news),
                    )
                }
            }
            is NewsUiState.Empty -> {
                item {
                    EmptyStateView(
                        title = stringResource(Res.string.news_empty_title),
                        message = stringResource(Res.string.news_empty_subtitle),
                    )
                }
            }
            is NewsUiState.Error -> {
                item {
                    ErrorStateView(
                        title = stringResource(Res.string.state_error_title),
                        message = stringResource(Res.string.state_error_subtitle),
                        onRetry = { viewModel.loadNews() },
                    )
                }
            }
            is NewsUiState.Success -> {
                items(state.articles, key = { it.id }) { article ->
                    NewsArticleCard(article = article)
                }
            }
        }
    }
}

@Composable
private fun NewsArticleCard(article: NewsArticle) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column {
            if (!article.imageUrl.isNullOrBlank()) {
                AsyncNetworkImage(
                    url = article.imageUrl,
                    contentDescription = article.title,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                )
            }

            Column(
                modifier = Modifier.padding(20.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        color = DigitsColors.CyanPrimary.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(0.5.dp, DigitsColors.CyanPrimary.copy(alpha = 0.35f)),
                    ) {
                        Text(
                            text = article.tag,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = DigitsColors.CyanPrimary,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "${article.date} • ${article.readTime}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 20.sp,
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = article.author,
                        style = MaterialTheme.typography.labelMedium,
                        color = DigitsColors.Neutral400,
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = stringResource(Res.string.news_read_more),
                        style = MaterialTheme.typography.labelMedium,
                        color = DigitsColors.CyanPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
