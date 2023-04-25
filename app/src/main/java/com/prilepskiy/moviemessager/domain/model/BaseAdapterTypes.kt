package com.prilepskiy.moviemessager.domain.model

import com.prilepskiy.moviemessager.core.DiffUtilModel

open class BaseAdapterTypes : DiffUtilModel<Long>() {
    override val id: Long = 0L
}
