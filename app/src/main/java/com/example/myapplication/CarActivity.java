package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CarActivity extends MainActivity {
    private ListView cartListView;
    private ArrayList<CartItem> cartItems;
    private int selectedItem = -1;

    private void removeFromCart() {
        if (selectedItem != -1) {
            CartItem selectedCartItem = new CartItem(carNames[selectedItem], carDescriptions[selectedItem], carImages[selectedItem], quantity);
            if (cartItems.contains(selectedCartItem)) {
                cartItems.remove(selectedCartItem);
                Toast.makeText(this, "Товар удален из корзины.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartListView = findViewById(R.id.carListView);

        Intent intent = getIntent();
        cartItems = intent.getParcelableArrayListExtra("cartItems");

        CarAdapter cartAdapter = new CarAdapter(this, R.layout.car_item, cartItems);
        cartListView.setAdapter(cartAdapter);
    }
}
