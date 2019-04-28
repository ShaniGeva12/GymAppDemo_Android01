package com.geva.shani.gymappdemo;

import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import android.annotation.SuppressLint;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.text.Layout;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView welcome_str;
    private EditText user_name;
    private Spinner mCategorySpinner;
    private RelativeLayout rl_name;
    private RelativeLayout rl_pic;
    private RelativeLayout rl_act;
    private LinearLayout rl_cls;
    private RadioGroup radioSexGroup ;
    private int visible_flag;
    private int s;
    private String msg_rate;


    private RatingBar ratingBar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome_str = findViewById(R.id.wlcm);
        user_name = findViewById(R.id.name_enter);

        rl_name = (RelativeLayout)findViewById(R.id.set_name_row1);
        rl_pic = (RelativeLayout)findViewById(R.id.header_img_row2);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

      radioSexGroup  = (RadioGroup)findViewById(R.id.sexRadioGroup);

        rl_act = (RelativeLayout)findViewById(R.id.activities_layout);
        rl_act.setVisibility(View.INVISIBLE);

        rl_cls = (LinearLayout)findViewById(R.id.classes_layout);
        rl_cls.setVisibility(View.INVISIBLE);


        visible_flag = 0;

        //widget - spinner
        //Spinner init
        mCategorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Categories, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mCategorySpinner.setAdapter(adapter);
        mCategorySpinner.setOnItemSelectedListener(this);

        Button listClass = findViewById(R.id.list_btn);
        listClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visible_flag ==0)
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.login_must), Toast.LENGTH_SHORT).show();
                else
                {
                    movePage(view);
                }

            }
        });


        Button addBtn = findViewById(R.id.ok_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //first - close keyboard
               closeKeyboard();

                //now - save the name and display msg with it
                String n = user_name.getText().toString();

               int selectedId = radioSexGroup.getCheckedRadioButtonId();
                switch(selectedId){
                    case R.id.male_checkbox:
                        // do operations specific to this selection
                        //s=0;
                        welcome_str.setText(getResources().getString(R.string.hello_male) + ", " + n + ". " + getResources().getString(R.string.wlcm_str_male));
                        break;
                    case R.id.female_checkbox:
                        // do operations specific to this selection
                       // s=1;
                        welcome_str.setText(getResources().getString(R.string.hello_female) + " " + n + ". " + getResources().getString(R.string.wlcm_str_female));
                        break;

                     default:
                        welcome_str.setText(getResources().getString(R.string.hello) + ", " + n + ". " + getResources().getString(R.string.wlcm_str));
                        break;
                }
                rl_name.setVisibility(View.INVISIBLE);
                rl_name.setGravity(0);
                rl_name.removeAllViews();
                rl_act.setVisibility(View.VISIBLE);
                rl_cls.setVisibility(View.VISIBLE);
                visible_flag = 1;
            }
        });


        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                msg_rate = getResources().getString(R.string.rating_tnx) + " " + String.valueOf(rating) + " " + getResources().getString(R.string.starts);
                Toast.makeText(MainActivity.this, msg_rate , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void movePage(View view)
    {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(MainActivity.this, "You chose: " + position, Toast.LENGTH_SHORT).show();

        switch (position) {

            case 0:
                rl_cls.removeAllViews();
                break;
            //1 = aerobic
            case 1:
                if(visible_flag == 1)
                    Toast.makeText(parent.getContext(), getResources().getString(R.string.a_class) , Toast.LENGTH_SHORT).show();

                rl_cls.removeAllViews();

                Button a1 = new Button(MainActivity.this);
                a1.setText(getResources().getString(R.string.class_aerobic_1));
                a1.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                a1.setPadding(4,4,4,4);
                a1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //Toast.makeText(MainActivity.this,((Button)view).getText(),Toast.LENGTH_SHORT).show();
                    }
                });

                Button a2 = new Button(MainActivity.this);
                a2.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                a2.setText(getResources().getString(R.string.class_aerobic_2));
                a2.setPadding(4,4,4,4);


                rl_cls.addView(a1);
                rl_cls.addView(a2);
                break;

            //2 = body shape
            case 2:
                if(visible_flag == 1)
                    Toast.makeText(parent.getContext(), getResources().getString(R.string.bs_class) , Toast.LENGTH_SHORT).show();
                rl_cls.removeAllViews();

                Button b1 = new Button(MainActivity.this);
                b1.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                b1.setPadding(4,4,4,4);
                b1.setText(getResources().getString(R.string.class_body_1));
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //Toast.makeText(MainActivity.this,((Button)view).getText(),Toast.LENGTH_SHORT).show();
                    }
                });

                Button b2 = new Button(MainActivity.this);
                b2.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                b2.setPadding(4,4,4,4);
                b2.setText(getResources().getString(R.string.class_body_2));

                Button b3 = new Button(MainActivity.this);
                b3.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                b3.setPadding(4,4,4,4);
                b3.setText(getResources().getString(R.string.class_body_3));

                rl_cls.addView(b1);
                rl_cls.addView(b2);
                rl_cls.addView(b3);
                break;

            //3 = muscle strains
            case 3:
                if(visible_flag == 1)
                    Toast.makeText(parent.getContext(), getResources().getString(R.string.ms_class) , Toast.LENGTH_SHORT).show();
                rl_cls.removeAllViews();

                Button m1 = new Button(MainActivity.this);
                m1.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                m1.setPadding(4,4,4,4);
                m1.setText(getResources().getString(R.string.class_muscle_1));

                Button m2 = new Button(MainActivity.this);
                m2.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                m2.setPadding(4,4,4,4);
                m2.setText(getResources().getString(R.string.class_muscle_2));

                Button m3 = new Button(MainActivity.this);
                m3.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                m3.setPadding(4,4,4,4);
                m3.setText(getResources().getString(R.string.class_muscle_3));

                Button m4 = new Button(MainActivity.this);
                m4.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                m4.setPadding(4,4,4,4);
                m4.setText(getResources().getString(R.string.class_muscle_4));

                rl_cls.addView(m1);
                rl_cls.addView(m2);
                rl_cls.addView(m3);
                rl_cls.addView(m4);
                break;

            //4 = weights
            case 4:
                if(visible_flag == 1)
                    Toast.makeText(parent.getContext(), getResources().getString(R.string.w_class) , Toast.LENGTH_SHORT).show();
                rl_cls.removeAllViews();

                Button w1 = new Button(MainActivity.this);
                w1.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w1.setPadding(4,4,4,4);
                w1.setText(getResources().getString(R.string.class_weights_1));

                Button w2 = new Button(MainActivity.this);
                w2.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w2.setPadding(4,4,4,4);
                w2.setText(getResources().getString(R.string.class_weights_2));

                Button w3 = new Button(MainActivity.this);
                w3.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w3.setPadding(4,4,4,4);
                w3.setText(getResources().getString(R.string.class_weights_3));

                Button w4 = new Button(MainActivity.this);
                w4.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w4.setPadding(4,4,4,4);
                w4.setText(getResources().getString(R.string.class_weights_4));

                Button w5 = new Button(MainActivity.this);
                w5.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w5.setPadding(4,4,4,4);
                w5.setText(getResources().getString(R.string.class_weights_5));

                Button w6 = new Button(MainActivity.this);
                w6.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w6.setPadding(4,4,4,4);
                w6.setText(getResources().getString(R.string.class_weights_6));

                Button w7 = new Button(MainActivity.this);
                w7.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w7.setPadding(4,4,4,4);
                w7.setText(getResources().getString(R.string.class_weights_7));

                Button w8 = new Button(MainActivity.this);
                w8.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                w8.setPadding(4,4,4,4);
                w8.setText(getResources().getString(R.string.class_weights_8));

                rl_cls.addView(w1);
                rl_cls.addView(w2);
                rl_cls.addView(w3);
                rl_cls.addView(w4);
                rl_cls.addView(w5);
                rl_cls.addView(w6);
                rl_cls.addView(w7);
                rl_cls.addView(w8);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
