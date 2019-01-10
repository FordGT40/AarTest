package com.wisdom.aartest

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.wisdom.androidinterfacemiddleware.method.InterfaceAuthenticated
import kotlinx.android.synthetic.main.activity_main.*

//import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureDSA.SHA256


/**
 * @ProjectName project： AarTest
 * @class package：com.wisdom.aartest
 * @class describe：
 * @author HanXueFeng
 * @time 2019/1/9 15:28
 * @change
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            if (!(et_content.text == null || "" == et_content.text.toString())) {
//                val str = AESUtil().encode(et_content.text.toString())
//                tv_result.text = str
                encopy()
            } else {
                Toast.makeText(this, "请填写要加密内容", Toast.LENGTH_SHORT).show()
            }

        }
    }


    @SuppressLint("SetTextI18n")
    private fun encopy() {
        tv_result_1.text = "加密结果：${InterfaceAuthenticated(this)
            .getAccessKey(this, et_content.text.toString())}"
    }


}