package com.omsakti.foodrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Food_Category_Activity extends AppCompatActivity {
    RecyclerView recycler_category;
    ArrayList<model_category> arrayList_category;
    ArrayList<String> parent_arraylist;
    CategoryAdapter adapter;
    String str_food_name, parent;
    TextView txt_title;
    ImageView img_back;

    DatabaseReference rootRef, categoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);

        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        str_food_name = getIntent().getStringExtra("food_name");
        txt_title = findViewById(R.id.txt_title);
        txt_title.setText(str_food_name);

        recycler_category = findViewById(R.id.recycler_category);
        recycler_category.setLayoutManager(new GridLayoutManager(this, 2));

        arrayList_category = new ArrayList<>();
        adapter = new CategoryAdapter(Food_Category_Activity.this, arrayList_category, new CategoryAdapter.Click_main() {
            @Override
            public void itemclick(CardView v, int position) {
                Intent intent = new Intent(Food_Category_Activity.this, Food_Recipe_Activity.class);
                intent.putExtra("category_name", arrayList_category.get(position).getStr_name());
                intent.putExtra("food_name", str_food_name);
                startActivity(intent);
            }
        });
        recycler_category.setAdapter(adapter);

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
        categoryRef = rootRef.child("Category").child(str_food_name);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    arrayList_category.clear();
                    parent_arraylist = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        parent = dataSnapshot.getKey();

                        model_category category = dataSnapshot.getValue(model_category.class);
                        category.setStr_image(dataSnapshot.child("image").getValue(String.class));
                        category.setStr_name(dataSnapshot.child("name").getValue(String.class));
                        arrayList_category.add(category);
                        recycler_category.setAdapter(adapter);
                        parent_arraylist.add(parent);
                    }
                    adapter.notifyDataSetChanged();
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        categoryRef.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}