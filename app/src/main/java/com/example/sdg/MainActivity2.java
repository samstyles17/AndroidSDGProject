package com.example.sdg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity
{

    ConstraintLayout constraintLayout;
    Button thanksButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        constraintLayout = findViewById(R.id.constraintLayout);
        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("Name");
        Toast.makeText(getApplicationContext(),"Welcome: "+value1,Toast.LENGTH_LONG).show();

        thanksButton = findViewById(R.id.thankyou);
        thanksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Thank You","Thank you for visiting US!");
            }
        });

    }

    private void showAlertDialog(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void gotoActon1(View view)
    {
        String websiteUrl = "https://stories.undp.org/building-bricks-for-a-new-afghanistan";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        startActivity(intent);

    }
}