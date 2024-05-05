package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView carListView;
    private TextView quantityTextView;
    private ImageView carImageView;
    private Button increaseButton;
    private Button decreaseButton;
    private Button addButton;
    public String[] carNames = {"Microlino", "Splinter", "Peel Trident на реактивной тяге"};
    public String[] carDescriptions = {"Это ... - BMW Isetta! Если быть совсем точным, то почти-что Isetta.", "Перед вами уважаемые друзья студенческий проект автомобиля, который был создан в Университете Северной Каролины. ", "Выше уважаемые читатели мы вам рассказали о мини автомобиле модели Peel P50, который по сей день считается пока самым маленьким легковым транспортным средством в мире. "};
    public int[] carImages = {R.drawable.car1, R.drawable.car2, R.drawable.car3};
    public int selectedItem = -1;
    public int quantity = 0;
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    private void addToCart() {
        if (selectedItem != -1) {
            CartItem selectedCartItem = new CartItem(carNames[selectedItem], carDescriptions[selectedItem], carImages[selectedItem], quantity);
            cartItems.add(selectedCartItem);
            Toast.makeText(this, "Товар добавлен в корзину.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, CarActivity.class);
            intent.putParcelableArrayListExtra("cartItems", cartItems);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carListView = findViewById(R.id.carListView);
        quantityTextView = findViewById(R.id.quantityTextView);
        carImageView = findViewById(R.id.carImageView);
        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);
        addButton = findViewById(R.id.addButton);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, carNames);
        carListView.setAdapter(adapter);
        carListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = position;
                updateUI();
            }
        });
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != -1) {
                    quantity++;
                    updateUI();
                }
            }
        });
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != -1 && quantity > 0) {
                    quantity--;
                    updateUI();
                }
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != -1) {
                    addToCart();
                }
            }
        });


    }

    private void updateUI() {
        if (selectedItem != -1) {
            carImageView.setImageResource(carImages[selectedItem]);
            quantityTextView.setText("Количество: " + quantity + "\nОписание: " + carDescriptions[selectedItem]);
        } else {
            carImageView.setImageResource(0);
            quantityTextView.setText("");
        }
    }
}
