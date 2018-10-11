package exchangerates.com.csepi.exchangerates

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import exchangerates.com.csepi.exchangerates.presentation.view.ActualRatesFragment
import exchangerates.com.csepi.exchangerates.presentation.view.HistoricalRatesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    private val actualRatesFragment: ActualRatesFragment by lazy { ActualRatesFragment.newInstance() }
    private val historicalRatesFragment: HistoricalRatesFragment by lazy { HistoricalRatesFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_actual -> {
                toolbar.title = applicationContext.resources.getString(R.string.title_actual)
                openFragment(actualRatesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_historical -> {
                toolbar.title = applicationContext.resources.getString(R.string.title_historical)
                openFragment(historicalRatesFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
