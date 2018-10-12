package exchangerates.com.csepi.exchangerates.data.parser

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import exchangerates.com.csepi.exchangerates.data.model.Rates
import java.lang.reflect.Type

class RatesParser : JsonDeserializer<Rates> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Rates {
        val gson = Gson()
        var map: Map<String, Double> = HashMap()
        map = gson.fromJson(json, map.javaClass) as Map<String, Double>
        return Rates(map)
    }
}