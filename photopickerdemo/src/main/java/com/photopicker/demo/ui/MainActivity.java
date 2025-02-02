package com.photopicker.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import com.photopicker.demo.R;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

public class MainActivity extends AppCompatActivity {

  private PhotoAdapter photoAdapter;

  private ArrayList<String> selectedPhotos = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    photoAdapter = new PhotoAdapter(this, selectedPhotos);

    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
    recyclerView.setAdapter(photoAdapter);

    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setGridColumnCount(4)
                .start(MainActivity.this);
      }
    });

    findViewById(R.id.button_no_camera).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PhotoPicker.builder()
                .setPhotoCount(7)
                .setShowCamera(false)
                .setPreviewEnabled(false)
                .start(MainActivity.this);
      }
    });

    findViewById(R.id.button_one_photo).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setPreviewEnabled(true)
                .setGridColumnCount(3)
                .setCustomTitleView(new CustomTitleView())
                .setPageBgColorRes(R.color.__picker_color_white) //R.color.__picker_color_red
                .start(MainActivity.this);
      }
    });

    findViewById(R.id.button_photo_gif).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PhotoPicker.builder()
                .setShowCamera(true)
                .setShowGif(true)
                .start(MainActivity.this);
      }
    });

    findViewById(R.id.btn_ui).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setGridColumnCount(4)
                .setCustomTitleView(new CustomTitleView())
                .start(MainActivity.this);
      }
    });

    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
            new RecyclerItemClickListener.OnItemClickListener() {
      @Override
      public void onItemClick(View view, int position) {
        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
          PhotoPicker.builder()
                  .setPhotoCount(PhotoAdapter.MAX)
                  .setShowCamera(true)
                  .setPreviewEnabled(false)
                  .setSelected(selectedPhotos)
                  .start(MainActivity.this);
        } else {
          PhotoPreview.builder()
                  .setPhotos(selectedPhotos)
                  .setCurrentItem(position)
                  .start(MainActivity.this);
        }
      }
    }));
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK &&
        (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

      List<String> photos = null;
      if (data != null) {
        photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
      }
      selectedPhotos.clear();

      if (photos != null) {

        selectedPhotos.addAll(photos);
      }
      photoAdapter.notifyDataSetChanged();
    }
  }

}
