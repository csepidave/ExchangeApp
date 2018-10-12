package exchangerates.com.csepi.exchangerates.presentation.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import exchangerates.com.csepi.exchangerates.R
import exchangerates.com.csepi.exchangerates.data.model.Rate
import exchangerates.com.csepi.exchangerates.presentation.presenter.ActualRatesPresenter
import exchangerates.com.csepi.exchangerates.presentation.view.adapter.ActualRatesAdapter
import exchangerates.com.csepi.exchangerates.presentation.view.interfaces.ActualRatesView
import kotlinx.android.synthetic.main.fragment_actual_rates.*

class ActualRatesFragment : Fragment(), ActualRatesView{

    private val actualRatesPresenter by lazy { ActualRatesPresenter(this) }
    private val actualRatesAdapter by lazy { ActualRatesAdapter() }
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_actual_rates, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        linearLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = actualRatesAdapter
        loadData()
    }

    fun loadData() {
        actualRatesPresenter.getActualRates()
    }

    override fun showExchangeRates(rates: List<Rate>) {
        actualRatesAdapter.setData(rates)
    }

    override fun showErrorSnackbar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
        actualRatesPresenter.clear()
    }

    companion object {
        fun newInstance(): ActualRatesFragment = ActualRatesFragment()
    }

}