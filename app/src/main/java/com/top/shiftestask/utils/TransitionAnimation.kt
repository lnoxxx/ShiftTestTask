package com.top.shiftestask.utils

import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionSet

const val DEFAULT_TRANSITION_DURATION = 200L

fun getImageTransitionName(id: Int): String = "${id}_image"

fun Fragment.applyDefaultSharedElementTransition() {
    val changeBounds = ChangeBounds()
    changeBounds.duration = DEFAULT_TRANSITION_DURATION
    val changeTransform = ChangeTransform()
    changeTransform.duration = DEFAULT_TRANSITION_DURATION

    val sharedElementTransition =
        TransitionSet()
            .addTransition(changeBounds)
            .addTransition(changeTransform)
    sharedElementTransition.setPathMotion(ArcMotion())
    sharedElementTransition.interpolator = DecelerateInterpolator()

    sharedElementEnterTransition = sharedElementTransition
}

fun Fragment.applyDefaultExitEnterTransition() {
    val fadeShow = Fade()
    fadeShow.duration = DEFAULT_TRANSITION_DURATION * 2
    fadeShow.interpolator = DecelerateInterpolator()
    val slide = Slide()
    slide.duration = DEFAULT_TRANSITION_DURATION
    slide.interpolator = DecelerateInterpolator()
    exitTransition = slide
    returnTransition = slide
    enterTransition = fadeShow
}