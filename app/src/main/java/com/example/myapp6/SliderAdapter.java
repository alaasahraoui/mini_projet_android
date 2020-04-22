package com.example.myapp6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    LayoutInflater layoutFlater;
    Context context;
    public  SliderAdapter(Context context){

        this.context =context;
    }

    //arrays for slider
    public int[] slide_images={R.drawable.g1,R.drawable.g2,R.drawable.g3};
    //strings
    public String[] slide_headings={"ماهي مدرستي؟ ","كيف العب ؟","تعلم معنا "};

    public String[] slide_text={"مدرستي هو تطيب يسمح للاطفال بتعلم معلومات جديدة و مفيدة خلال  فترةالحجر الصحي ",
            "اختر الموضوع الذي يعجبك واجب عن الاسئلة و التمارين المطروحة و ستضهر لك النتيجة في النهاية ",
            "تابع التعلم يوميا و يمكنك مشاركة النتيجة مع اصدقائك ومعرفة مستواك يوميا . استمتع مع مدرستي   "};



    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(LinearLayout) o;
    }



    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutFlater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view =layoutFlater.inflate(R.layout.slider_layout,container,false);

        ImageView slideImageView=(ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading=(TextView) view.findViewById(R.id.slide_heading);
        TextView slideText=(TextView) view.findViewById(R.id.slide_text);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideText.setText(slide_text[position]);
        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object o) {
         container.removeView((LinearLayout) o);
    }



}
