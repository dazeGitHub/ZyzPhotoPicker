package me.iwf.photopicker.customui

import android.content.Context
import android.view.View


/**
 * created at 2018/11/29
 * author wangxiangle
 * email wang_x_le@163.com
 **/

interface ICustomTitleLayout {

    fun genTitleLayout(context: Context, selectedAction: ISelectedActionListener): View?

    fun setTitleCount(coutDes:String)
}


interface ISelectedActionListener{
    fun done()
    fun back()
}

object ActivityData {
    var customTitleView: ICustomTitleLayout? = null
}