package com.omsakti.foodrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    /*RecyclerView recycler_category;
    ArrayList<model_category> arrayList_category;
    CategoryAdapter adapter;

    DatabaseReference rootRef, categoryRef;*/
    CardView card_chinese, card_gujarati, card_italian, card_north_indian, card_panjabi, card_south_indian;
    TextView txt_chinese, txt_gujarati, txt_italian, txt_north_indian, txt_panjabi, txt_south_indian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_chinese = findViewById(R.id.card_chinese);
        card_gujarati = findViewById(R.id.card_gujarati);
        card_italian = findViewById(R.id.card_italian);
        card_north_indian = findViewById(R.id.card_north_indian);
        card_panjabi = findViewById(R.id.card_panjabi);
        card_south_indian = findViewById(R.id.card_south_indian);

        txt_chinese = findViewById(R.id.txt_chinese);
        txt_gujarati = findViewById(R.id.txt_gujarati);
        txt_italian = findViewById(R.id.txt_italian);
        txt_north_indian = findViewById(R.id.txt_north_indian);
        txt_panjabi = findViewById(R.id.txt_panjabi);
        txt_south_indian = findViewById(R.id.txt_south_indian);
        /*recycler_category = findViewById(R.id.recycler_category);
        recycler_category.setLayoutManager(new GridLayoutManager(this, 2));

        arrayList_category = new ArrayList<>();
        adapter = new CategoryAdapter(MainActivity.this, arrayList_category);

        Thread myThread = new Thread(new MyRunnable());
        myThread.start();*/

        card_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food_Category_Activity.class);
                intent.putExtra("food_name", txt_chinese.getText().toString());
                startActivity(intent);
            }
        });

        card_gujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food_Category_Activity.class);
                intent.putExtra("food_name", txt_gujarati.getText().toString());
                startActivity(intent);
            }
        });

        card_italian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food_Category_Activity.class);
                intent.putExtra("food_name", txt_italian.getText().toString());
                startActivity(intent);
            }
        });

        card_north_indian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food_Category_Activity.class);
                intent.putExtra("food_name", txt_north_indian.getText().toString());
                startActivity(intent);
            }
        });

        card_panjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food_Category_Activity.class);
                intent.putExtra("food_name", txt_panjabi.getText().toString());
                startActivity(intent);
            }
        });

        card_south_indian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food_Category_Activity.class);
                intent.putExtra("food_name", txt_south_indian.getText().toString());
                startActivity(intent);
            }
        });


    }
/*
    class MyRunnable implements Runnable {
        @Override
        public void run()
        {
            try {
                LoadCategory();
            } catch (Exception ex) {
                Log.e("my app", ex.toString());
            }
        }
    }

    private void LoadCategory() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        categoryRef = rootRef.child("Category");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    model_category category = dataSnapshot.getValue(model_category.class);
                    category.setStr_image(dataSnapshot.child("image").getValue(String.class));
                    category.setStr_name(dataSnapshot.child("name").getValue(String.class));
                    arrayList_category.add(category);
                    recycler_category.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        categoryRef.addListenerForSingleValueEvent(eventListener);

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}