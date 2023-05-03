package com.loperilla.onboarding_domain.usecase.quote

import androidx.paging.Pager
import com.loperilla.model.quote.Quote
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding_domain.usecase.quote
 * Created By Manuel Lopera on 3/5/23 at 20:22
 * All rights reserved 2023
 */
class QuotePagingUseCase @Inject constructor(
    private val quotePagingSource: Pager<Int, Quote>
) {
    fun getRandomQuotes() = quotePagingSource.flow
}
