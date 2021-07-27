package com.omsakti.foodrecipe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Food_Recipe_Activity extends AppCompatActivity {
    String str_category_name, str_food_name, parent;
    TextView txt_title, txt_recipe, txt_thing;
    ImageView img_back, img_play;
    WebView videoView;

    DatabaseReference rootRef, recipeRef;

    String str_video_path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recipe);

        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        str_category_name = getIntent().getStringExtra("category_name");
        str_food_name = getIntent().getStringExtra("food_name");
        txt_title = findViewById(R.id.txt_title);
        txt_title.setText(str_category_name);

        videoView = findViewById(R.id.videoView);
        txt_recipe = findViewById(R.id.txt_recipe);
        txt_thing = findViewById(R.id.txt_thing);
        img_play = findViewById(R.id.img_play);

        Thread myThread = new Thread(new MyRunnable());
        myThread.start();

    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                LoadCategory();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void LoadCategory() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        recipeRef = rootRef.child("Category").child(str_food_name).child(str_category_name);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    str_video_path = snapshot.child("video").getValue(String.class);
                    txt_recipe.setText(snapshot.child("recipe").getValue(String.class));
                    txt_thing.setText(snapshot.child("thing").getValue(String.class));

                    videoView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            return false;
                        }
                    });

                    String str_video = "https://www.youtube.com/embed/" + snapshot.child("video").getValue(String.class);
//                    String videoStr = "<html><body>Airtel 5G<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/dEIyR-A-oLU\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
                    String final_str = "<html><body style=margin: 0; padding: 0><iframe  width=100% height=100% src=" + str_video + " type=text/html frameborder=0></iframe><body><html>";
                    Log.d("str_main", final_str);
                    WebSettings ws = videoView.getSettings();
                    ws.setJavaScriptEnabled(true);

                    videoView.loadData(final_str, "text/html", "utf-8");
//                    videoView.loadUrl(str_video);
//                    videoView.loadUrl(str_video);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        recipeRef.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}