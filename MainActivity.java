package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonCopyText;
    private Button buttonFindPhase;
    private Button buttonReset;
    private TextView textViewFoundPhase;
    private TextView textViewCopiedText;

    private String initialText;
    private String phrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCopyText = (Button) findViewById(R.id.buttonCopyText);
        buttonFindPhase = (Button) findViewById(R.id.buttonFindPhrase);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        textViewFoundPhase = (TextView) findViewById(R.id.textViewFoundPhrase);
        textViewCopiedText = (TextView) findViewById(R.id.textViewCopiedText);

        buttonCopyText.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String clip = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                if (clip != null) {
                    textViewCopiedText.setText(clip);
                    initialText = textViewCopiedText.getText().toString();
                } else {
                    textViewFoundPhase.setText("Clipboard is empty.");

                }

            }
        });


        buttonFindPhase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!textViewCopiedText.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Find the phrase.");

                    final EditText editTextPhrase = new EditText(MainActivity.this);
                    editTextPhrase.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(editTextPhrase);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            phrase = editTextPhrase.getText().toString();

                            if (phrase.length() > 0) {
                                textViewFoundPhase.setText(findPhrase(phrase));
                            } else {
                                textViewCopiedText.setText(initialText);
                                textViewFoundPhase.setText("Cannot find anything.");
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }

                    });
                    builder.show();

                } else {
                    testViewFoundPhrase.setText("Copy something at first.");
                }
            }

        });


    }

    }
}

