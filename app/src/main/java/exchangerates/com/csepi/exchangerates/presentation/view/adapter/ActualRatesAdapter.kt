package exchangerates.com.csepi.exchangerates.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import exchangerates.com.csepi.exchangerates.R
import exchangerates.com.csepi.exchangerates.data.model.Rate

class ActualRatesAdapter : RecyclerView.Adapter<ActualRatesAdapter.ActualRatesItemViewHolder>(){

    private var rates: List<Rate>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActualRatesItemViewHolder {
        val view = LayoutInflater.from(parent.context)?.inflate(R.layout.item_actual_rates, parent,
                false)
        return ActualRatesItemViewHolder(view!!)
    }

    fun setData(rates: List<Rate>?) {
        this.rates = rates
        notifyDataSetChanged()
    }

    override fun getItemCount() = rates?.size ?: 0

    override fun onBindViewHolder(holder: ActualRatesItemViewHolder, position: Int) {
        rates?.let {
            val rate = it[position]
            holder.currency.text = rate.currency
            holder.value.text = rate.rate.toString()
        }
    }

    inner class ActualRatesItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val currency = view.findViewById(R.id.currency) as TextView
        val value = view.findViewById(R.id.value) as TextView
    }
}