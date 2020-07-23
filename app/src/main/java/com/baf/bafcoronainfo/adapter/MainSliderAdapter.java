package com.baf.bafcoronainfo.adapter;



import com.baf.bafcoronainfo.R;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("https://baf.mil.bd/website/assets/img/photo-album/yak-130/8-8.png");
//                viewHolder.bindImageSlide(R.drawable.banner_image);
                break;
            case 1:
//                viewHolder.bindImageSlide(R.drawable.banner_image2);
                viewHolder.bindImageSlide("https://baf.mil.bd/website/assets/img/photo-album/f-7bg2/9-9.png");
                break;
            case 2:
//                viewHolder.bindImageSlide(R.drawable.banner_image1);
                viewHolder.bindImageSlide("https://baf.mil.bd/website/assets/img/photo-album/f-7bg2/5-5.png");
                break;
        }
    }
}
