package com.example.sdg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ViewSwitcher;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity
{
    private boolean switchValue = false;
    Switch switchWidget;
    Editable nameField;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    Button exploreButton,visitButton,okButton;
    ImageSwitcher imageSwitcher;
    FloatingActionButton nextButton,prevButton;
    int imageSwitcherImages[]={R.drawable.goal1,R.drawable.goal2,R.drawable.goal4,R.drawable.goal5,R.drawable.goal8};
    int switcherImageLength = imageSwitcherImages.length;
    int counter=0;
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = findViewById(R.id.imageSwitcher);
        nextButton = findViewById(R.id.floatingActionButton2);
        prevButton = findViewById(R.id.floatingActionButton);
        textInputLayout = findViewById(R.id.filledTextField);
        textInputEditText = findViewById(R.id.edit_text);
        exploreButton = findViewById(R.id.explore);
        visitButton = findViewById(R.id.visit);
        okButton = findViewById(R.id.ok);
        switchWidget = findViewById(R.id.switch1);

        switchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchValue = isChecked;
            }
        });


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(MainActivity.this);
            }
        });

        showImage(counter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextImage();
            }
        });


        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousImage();
            }
        });

        exploreButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nameField = textInputEditText.getText();
                if(nameField!=null)
                {
                    name = nameField.toString().trim();
                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    i.putExtra("Name",name);
                    startActivity(i);
                }

            }
        });

        visitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String websiteUrl = "https://www.undp.org/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
                 startActivity(intent);

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Value Confirmation","Confirm the values");
            }
        });
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(getOrderSummary());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the 'OK' button click (if needed)
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private String getOrderSummary() {
        String values = "Order Summary:\n";

        values+= name+"\n";

        // Append selected ImageSwitcher image
        values += "Selected Image: ";
        switch (counter) {
            case 0:
               values += "No Poverty";
                break;
            case 1:
                values += "Zero Hunger";
                break;
            case 2:
                values += "Dry";
                break;
        }
        values += "\n";

        values += "Willing to help: " + (switchValue ? "Yes" : "No") + "\n";



        return values;
    }

    public void showPreviousImage() {
        counter = (counter - 1 + switcherImageLength) % switcherImageLength;
        showImage(counter);
    }

    public void showNextImage() {
        counter = (counter+ 1) % switcherImageLength;
        showImage(counter);
    }

    private void showImage(int index) {
        imageSwitcher.setImageResource(imageSwitcherImages[index]);
    }
}