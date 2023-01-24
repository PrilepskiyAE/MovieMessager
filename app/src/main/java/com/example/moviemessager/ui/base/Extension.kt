package com.example.moviemessager.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.flow.Flow

inline fun <reified T> Flow<T>.observeInLifecycle(
    lifecycleOwner: LifecycleOwner
) = FlowObserver(lifecycleOwner, this)

inline fun <reified F> getCurrentFragment(navHostFragment: NavHostFragment): F? {
    return if (navHostFragment.isAdded && navHostFragment.childFragmentManager.fragments.size > 0) {
        if (navHostFragment.childFragmentManager.fragments[0] is F) {
            navHostFragment.childFragmentManager.fragments[0] as F
        } else {
            null
        }
    } else null
}