package com.altimetrick.gpslog.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.altimetrick.gpslog.R

abstract class BaseActivity<out T : ViewDataBinding,  out V : BaseViewModel>: AppCompatActivity() {

    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null

    abstract fun getContentView(): Int

    abstract fun getViewModel(): V?

    fun getViewDataBinding(): T? {
        return mViewDataBinding
    }

    abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding(){
        mViewDataBinding = DataBindingUtil.setContentView(this, getContentView())
        mViewModel = getViewModel()
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false, tag: String){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContentView, fragment)
        if (addToBackStack) fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }

    fun showInfoAlert(message: String){
        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setMessage(message)
        builder.setPositiveButton("OKAY", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })
        builder.setCancelable(false)
        builder.create().show()
    }
}