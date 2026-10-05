package com.schwarz_digits.mobilecommunity.feature.news.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarz_digits.mobilecommunity.core.model.NewsArticle
import com.schwarz_digits.mobilecommunity.core.ui.network.NetworkClientProvider
import com.schwarz_digits.mobilecommunity.feature.news.impl.data.NewsRepository
import com.schwarz_digits.mobilecommunity.feature.news.impl.data.NewsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * UI state representing the current screen presentation for News.
 */
sealed interface NewsUiState {
    data object Loading : NewsUiState

    data class Success(
        val articles: List<NewsArticle>,
    ) : NewsUiState

    data object Empty : NewsUiState

    data class Error(
        val message: String? = null,
    ) : NewsUiState
}

/**
 * ViewModel managing the asynchronous loading and state transitions of community news.
 */
class NewsViewModel(
    private val repository: NewsRepository =
        NewsRepository(
            NewsService(NetworkClientProvider.createClient()),
        ),
) : ViewModel() {
    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    init {
        loadNews()
    }

    fun loadNews() {
        _uiState.value = NewsUiState.Loading
        viewModelScope.launch {
            repository
                .getNewsArticles()
                .onSuccess { articles ->
                    _uiState.value =
                        if (articles.isEmpty()) {
                            NewsUiState.Empty
                        } else {
                            NewsUiState.Success(articles)
                        }
                }.onFailure { error ->
                    _uiState.value = NewsUiState.Error(error.message)
                }
        }
    }
}
