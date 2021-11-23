package com.example.carolina_coffee;

import android.os.Handler;
import android.widget.ImageView;

public class LoadingScreen {private ImageView loading;

    LoadingScreen(ImageView loading) {
        this.loading = loading;
    }

    public void setLoadScreen(){
        final Integer[] loadingImages = {R.drawable.walletcover, R.drawable.walletcover, R.drawable.walletcover, R.drawable.walletcover};
        final Handler loadingHandler = new Handler();
        Runnable runnable = new Runnable() {
            int loadingImgIndex = 0;
            public void run() {
                loading.setImageResource(loadingImages[loadingImgIndex]);
                loadingImgIndex++;
                if (loadingImgIndex >= loadingImages.length)
                    loadingImgIndex = 0;
                loadingHandler.postDelayed(this, 500);
            }
        };
        loadingHandler.postDelayed(runnable, 500);
    }}