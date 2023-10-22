package com.oznyigit.tipcalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oznyigit.tipcalculatorapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun calculateTipButtonCallback()
    {
        val cost = mBinding.costOfServiceEdittext.text.toString().toDoubleOrNull()

        if (cost == null) {
            mBinding.tvTipAmount.text = ""
            return
        }

        val tipPercent = when (mBinding.rgTipOptions.checkedRadioButtonId) {
            R.id.rbOptionTwentyPercent -> 0.20
            R.id.rbOptionEighteenPercent -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercent

        val roundUp = mBinding.switchRoundUp.isChecked

        if (roundUp) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        mBinding.tvTipAmount.text = getString(R.string.textview_tip_amount, formattedTip)
    }

    private fun initViews()
    {
        mBinding.buttonCalculate.setOnClickListener { calculateTipButtonCallback() }
    }

    private fun initialize()
    {
        initBinding()
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}