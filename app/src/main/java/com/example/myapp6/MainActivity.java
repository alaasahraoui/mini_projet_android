package com.example.myapp6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.text.Html;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Slide;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

private ViewPager mySlideViewPager;
private LinearLayout myDotsLayout;
private SliderAdapter sliderAdapter;
private TextView[] dots;
private Button skipbtn;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setContentView(R.layout.loading_screen);


         mySlideViewPager =(ViewPager) findViewById(R.id.SlideviewPager);
         myDotsLayout=(LinearLayout) findViewById(R.id.dots);

         sliderAdapter=new SliderAdapter(this);
         mySlideViewPager.setAdapter(sliderAdapter);
        addDots(0);

        mySlideViewPager.addOnPageChangeListener(viewListner);
   skipbtn=findViewById(R.id.btnskip);

 skipbtn.setOnClickListener(new View.OnClickListener() {
     public void onClick(View v) {
         Intent intt1 = new Intent(MainActivity.this,login.class);


         startActivity(intt1);

     }
 });
    }
     public  void addDots(int position){

        dots=new TextView[3];
        myDotsLayout.removeAllViews();

        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.blue_100));
            myDotsLayout.addView(dots[i]);


        }
         if (dots.length>0){dots[position].setTextColor(getResources().getColor(R.color.points));}
    }

    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {


            addDots(i);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };





    public void startlogin(View view) {
//transition=new Explode();

        openlogin();




    }

public void openActivity3(){

    Intent int1 = new Intent(this,Activity3.class);


    startActivity(int1);


 }

    public void openlogin(){

        Intent int1 = new Intent(this,login.class);


        startActivity(int1);


    }





}
