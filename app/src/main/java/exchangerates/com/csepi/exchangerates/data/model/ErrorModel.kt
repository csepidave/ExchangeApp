package exchangerates.com.csepi.exchangerates.data.model

import android.os.Parcelable
import exchangerates.com.csepi.exchangerates.utils.empty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorModel(
        val code: Int = 0,
        val type: String = String.empty()
) : Parcelable