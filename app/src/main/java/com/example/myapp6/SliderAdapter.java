package com.example.myapp6;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    LayoutInflater layoutFlater;
    Context context;
    public  SliderAdapter(Context context){

        this.context =context;
    }

    //arrays for slider
    public int[] slide_images={R.drawable.g1,R.drawable.g2,R.drawable.g3};
    //strings
    public String[] slide_headings={"Welcome 1 ","Welcome 2","Welcome 3 "};

    public String[] slide_text={"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa ",
            "aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam",
            "dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipi  "};



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
