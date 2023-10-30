package vn.edu.tdc.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseOperatorActivity extends AppCompatActivity {
    String a;
    String b;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_operator);


        //Get view from tag
        EditText edtA = findViewById(R.id.edtA);
        EditText edtB = findViewById(R.id.edtB);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);

        intent = getIntent();

        //Lay du lieu tu calculate sang
        Bundle data = intent.getBundleExtra("data");
        if(data != null){
            a = data.getString("a");
            b = data.getString("b");

            edtA.setText(a);
            edtB.setText(b);
        }

        //Get event
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = a + " + " + b + " = " + (Double.parseDouble(a) + Double.parseDouble(b));
                //Cách 1
                Intent intentPlus = new Intent(ChooseOperatorActivity.this, CalculatorActivity.class);
                intentPlus.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //startActivity(intentPlus);
                intentPlus.putExtra("result", result);
                setResult(Activity.RESULT_OK, intentPlus);

                //Cách 2
//                intent.putExtra("result", result);
                setResult(Activity.RESULT_OK, intentPlus);
                finish();

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = a + " - " + b + " = " + (Double.parseDouble(a) - Double.parseDouble(b));
                Intent intentMinus = new Intent(ChooseOperatorActivity.this, CalculatorActivity.class);
                intentMinus.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intentMinus.putExtra("result", result);
                setResult(Activity.RESULT_OK, intentMinus);
                finish();
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = a + " * " + b + " = " + (Double.parseDouble(a) * Double.parseDouble(b));
                Intent intentMul = new Intent(ChooseOperatorActivity.this, CalculatorActivity.class);
                intentMul.putExtra("result", result);
                intentMul.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                setResult(Activity.RESULT_OK, intentMul);
                finish();
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num1 = Double.parseDouble(a);
                double num2 = Double.parseDouble(b);
                 String result;
                if(num2 == 0){
                    result = "Tử không thể chia cho 0!";
                }
                else{
                    result = a + " / " + b + " = " + (num1 / num2);
                }
                Intent intentMul = new Intent(ChooseOperatorActivity.this, CalculatorActivity.class);
                intentMul.putExtra("result", result);
                intentMul.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                setResult(Activity.RESULT_OK, intentMul);
                finish();
            }
        });
    }
}