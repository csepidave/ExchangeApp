package exchangerates.com.csepi.exchangerates.utils

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun String.Companion.empty() = ""

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun View.showErrorSnackbarWithAction(description: String, @StringRes retryBtnResId: Int,
                                     retryClickListener: View.OnClickListener) {
    val snackbar = Snackbar.make(this, description, Snackbar.LENGTH_INDEFINITE)
            .setAction(retryBtnResId, retryClickListener)
    val snackBarTextView = snackbar.view.findViewById(android.support.design.R.id.snackbar_text) as TextView
    snackBarTextView.setSingleLine(false)
    snackbar.show()
}