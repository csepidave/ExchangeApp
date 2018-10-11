package exchangerates.com.csepi.exchangerates.presentation.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import exchangerates.com.csepi.exchangerates.R
import kotlinx.android.synthetic.main.activity_main.*
import com.afollestad.materialdialogs.MaterialDialog


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    private val actualRatesFragment by lazy { ActualRatesFragment.newInstance() }
    private val historicalRatesFragment by lazy { HistoricalRatesFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onBackPressed() {
        applicationCloseWarningDialog()
    }

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_actual -> {
                toolbar.title = applicationContext.resources.getString(R.string.title_actual)
                navigatesTo(actualRatesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_historical -> {
                toolbar.title = applicationContext.resources.getString(R.string.title_historical)
                navigatesTo(historicalRatesFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun navigatesTo(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun init() {
        toolbar = supportActionBar!!
        toolbar.title = applicationContext.resources.getString(R.string.title_actual)
        navigatesTo(actualRatesFragment)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun applicationCloseWarningDialog() {
        val dialogBuilder = MaterialDialog.Builder(this)
                .title(R.string.exit_alert_title)
                .content(R.string.exit_confirm)
                .negativeText(R.string.cancel)
                .negativeColorRes(R.color.colorPrimaryDark)
                .positiveText(R.string.yes)
                .positiveColorRes(R.color.colorPrimary)
                .autoDismiss(false)
                .cancelable(false)
                .onNegative { materialDialog, _ ->
                    materialDialog.dismiss() }
                .onPositive { materialDialog, _ ->
                    materialDialog.dismiss()
                    this.finishAffinity()
                }
        dialogBuilder.build().show()
    }
}
