package exchangerates.com.csepi.exchangerates.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rates(val map: Map<String, Double>) : Parcelable {

    fun getRate(currency: String): Double? {
        if (map.containsKey(currency)) {
            return map[currency]
        }
        return null
    }

    fun getRates() : List<Rate> {
        return map.toList().sortedBy { it.first }.map {
            return@map Rate(it.first, it.second)
        }
    }
}

