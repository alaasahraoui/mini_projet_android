package com.example.myapp6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {


    private ImageView  nextbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextbtn= findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuizAct();
            }
        });
    }




    public void openActivity3(){
//transition.setDuration(10000);
//transition.setInterpolator(new DecelerateInterpolator());
//getWindow().setExitTransition(transition);
        // ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle()

        Intent int1 = new Intent(this,Activity3.class);


        startActivity(int1);


    }



    public void openQuizAct(){
//transition.setDuration(10000);
//transition.setInterpolator(new DecelerateInterpolator());
//getWindow().setExitTransition(transition);
        // ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle()

        Intent int1 = new Intent(this,QuizActivity.class);


        startActivity(int1);


    }

}
