package exchangerates.com.csepi.exchangerates.data.model

import android.os.Parcelable
import exchangerates.com.csepi.exchangerates.utils.empty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiResponseModel(
        val success: Boolean,
        val historical: Boolean? = null,
        val base: String = String.empty(),
        val date: String = String.empty(),
        val rates: Rates? = null,
        val error: ErrorModel? = null
) : Parcelable