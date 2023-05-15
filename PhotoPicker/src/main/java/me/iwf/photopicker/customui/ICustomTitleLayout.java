package me.iwf.photopicker.customui;

import android.content.Context;
import android.view.View;

public interface ICustomTitleLayout {

    View genTitleLayout(Context context, ISelectedActionListener iSelectedActionListener);
    void setTitleCount(String coutDes);

    interface ISelectedActionListener{
        void done();
        void back();
    }
}
