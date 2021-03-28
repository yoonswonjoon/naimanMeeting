package com.example.rodem.a0Common.a11ViewControl

import android.graphics.Rect
import android.view.ViewTreeObserver
import android.view.Window

//        //키보드 클릭시 리사이클러뷰올라가도록 만들었는데 인터넷보고 따라 만들었음
//        https://googry.tistory.com/43, https://pspdfkit.com/blog/2016/keyboard-handling-on-android/
class KeyboardVisibilityUtils(
    private val window: Window,
    private val onShowKeyboard: ((keyboardHeight: Int) -> Unit)? = null,
    private val onHideKeyboard: (() -> Unit)? = null
) {

    private val minKeyboardHeightPx = 150

    private val windowVisibleDisplayFrame = Rect()
    private var lastVisibleDecorViewHeight: Int = 0


    private val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        window.decorView.getWindowVisibleDisplayFrame(windowVisibleDisplayFrame)
        val visibleDecorViewHeight = windowVisibleDisplayFrame.height()

        // Decide whether keyboard is visible from changing decor view height.
        if (lastVisibleDecorViewHeight != 0) {
            if (lastVisibleDecorViewHeight > visibleDecorViewHeight + minKeyboardHeightPx) {
                // Calculate current keyboard height (this includes also navigation bar height when in fullscreen mode).
                val currentKeyboardHeight = window.decorView.height - windowVisibleDisplayFrame.bottom
                // Notify listener about keyboard being shown.
                onShowKeyboard?.invoke(currentKeyboardHeight)
            } else if (lastVisibleDecorViewHeight + minKeyboardHeightPx < visibleDecorViewHeight) {
                // Notify listener about keyboard being hidden.
                onHideKeyboard?.invoke()
            }
        }
        // Save current decor view height for the next call.
        lastVisibleDecorViewHeight = visibleDecorViewHeight
    }

    init {
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    fun detachKeyboardListeners() {
        window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
    }
}