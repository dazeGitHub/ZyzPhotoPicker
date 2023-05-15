package com.photopicker.demo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.photopicker.demo.R
import me.iwf.photopicker.customui.ICustomTitleLayout
import me.iwf.photopicker.customui.ISelectedActionListener


/**
 * created at 2018/11/29
 * author wangxiangle
 * email wang_x_le@163.com
 **/

class CustomTitleView : ICustomTitleLayout {
    var mTvTitle: TextView? = null
    var mTvDone: TextView? = null
    var mIvBack:ImageView?=null

    override fun genTitleLayout(context: Context, selectedActionListener: ISelectedActionListener): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_photo_select_cust_title, null)
        mTvDone = view.findViewById<TextView>(R.id.tv_right)
        mTvTitle = view.findViewById<TextView>(R.id.tv_title)
        mIvBack = view.findViewById<ImageView>(R.id.iv_left)

        mTvTitle?.visibility =View.VISIBLE
        mTvDone?.visibility = View.VISIBLE

        mTvTitle?.text = "选择图片"
        mIvBack?.setOnClickListener { selectedActionListener.back() }
        mTvDone?.setOnClickListener { selectedActionListener.done() }
        return view
    }

    override fun setTitleCount(countStr: String) {
        mTvDone?.text = countStr
    }
 }