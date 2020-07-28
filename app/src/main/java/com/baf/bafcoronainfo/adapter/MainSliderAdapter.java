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
                viewHolder.bindImageSlide("https://skyapi.website/storage/images/image1.jpg");
//                viewHolder.bindImageSlide(R.drawable.banner_image);
                break;
            case 1:
//                viewHolder.bindImageSlide(R.drawable.banner_image2);
                viewHolder.bindImageSlide("https://skyapi.website/storage/images/image2.jpg");
                break;
            case 2:
//                viewHolder.bindImageSlide(R.drawable.banner_image1);
                viewHolder.bindImageSlide("https://skyapi.website/storage/images/image3.jpg");
                break;
        }
    }
}
