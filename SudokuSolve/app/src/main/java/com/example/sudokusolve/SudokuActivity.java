package com.example.sudokusolve;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SudokuActivity extends AppCompatActivity {

    private Puzzle puzzle;
    private Button solveBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        solveBtn = findViewById(R.id.solveBtn);
        solveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPuzzle();
                puzzle.solve();
                updateDisplay();

            }
        });

        resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<9; i++){
                    for(int j=0; j<9; j++){
                        EditText curText = (EditText) findViewById(getResources().getIdentifier("editText" + i + j, "id", getPackageName()));
                        curText.setText("");
                        curText.setTextColor(Color.parseColor("#000000"));
                    }
                }
            }
        });

    }



    private void createPuzzle(){
        int[][] arr = new int[9][9];

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                EditText curText = (EditText) findViewById(getResources().getIdentifier("editText" + i + j, "id", getPackageName()));

                if(curText.getText().toString().equals("")){
                    arr[i][j] = 0;
                }else{
                    arr[i][j] = Integer.parseInt(curText.getText().toString());
                }
            }
        }
        puzzle = new Puzzle(arr);
        puzzle.display();
    }

    private void updateDisplay(){
        int[][] arr = puzzle.getArray();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                EditText curText = (EditText) findViewById(getResources().getIdentifier("editText" + i + j, "id", getPackageName()));

                if(!curText.getText().toString().equals("")){
                    curText.setTextColor(Color.parseColor("#FF0000"));
                }else{
                    curText.setText(Integer.toString(arr[i][j]));
                }

            }
        }
    }
}
