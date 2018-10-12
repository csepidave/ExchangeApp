package exchangerates.com.csepi.exchangerates.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun String.Companion.empty() = ""

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}