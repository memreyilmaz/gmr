package com.gmr.android.di

import com.gmr.android.ui.GameDetailFragment
import com.gmr.android.ui.GameListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): GameListFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): GameDetailFragment
}