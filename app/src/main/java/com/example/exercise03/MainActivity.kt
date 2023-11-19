package com.example.exercise03

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.Fragment
import com.example.exercise03.ui.theme.Exercise03Theme

class MainActivity : AppCompatActivity(), StaticFragment.OnSelectListener {
    private var frag1: Fragment1? = null
    private var frag2: Fragment2? = null
    private var myTrans: FragmentTransaction? = null
    private val TAG_F1 = "Fragment1"
    private val TAG_F2 = "Fragment2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        frag1 = Fragment1().newInstance()
//        frag2 = Fragment2().newInstance()
        if (savedInstanceState == null) {
            frag1 = Fragment1().newInstance()
            frag2 = Fragment2().newInstance()
            myTrans = supportFragmentManager.beginTransaction()
            myTrans!!.add(R.id.dfcontainer, frag1!!, TAG_F1)
            myTrans!!.detach(frag1!!)
            myTrans!!.add(R.id.dfcontainer, frag2!!, TAG_F2)
            myTrans!!.detach(frag2!!)
            myTrans!!.commit()
        } else {
            frag1 = supportFragmentManager.findFragmentByTag(TAG_F1) as Fragment1?
            frag2 = supportFragmentManager.findFragmentByTag(TAG_F2) as Fragment2?
        }

    }

    override fun onSelect(option: Int) {
        val myTrans = supportFragmentManager.beginTransaction();
        Log.d("myTag", "onSelect: option = $option")
        when (option) {
            1 -> {
                myTrans.detach(frag2!!)
                myTrans.attach(frag1!!)
            }
            2 -> {
                myTrans.detach(frag1!!)
                myTrans.attach(frag2!!)
            }
        }
        myTrans.commit()
        this.myTrans = myTrans
    }
}
