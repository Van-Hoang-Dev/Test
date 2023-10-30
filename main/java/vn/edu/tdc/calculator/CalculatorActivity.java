package vn.edu.tdc.calculator;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    //Fields
    EditText edtResult;

    //Lấy kết quả từ màn hình Choose operator
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        String strResult = data.getStringExtra("result");
                        edtResult.setText(strResult);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        //Get view from form
        Button btnCalculate = findViewById(R.id.btn_calculate);
        EditText edtA = findViewById(R.id.edtA);
        EditText edtB = findViewById(R.id.edtB);
        edtResult = findViewById(R.id.edtResult);
        //Event processing
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Tạo bundle để chứa dữ liệu
                Bundle data = new Bundle();

                //Lấy dữ liệu trong hai thẻ input
                String a = edtA.getText().toString();
                String b = edtB.getText().toString();

                //Đưa dữ liệu vào bundle
                data.putString("a", a);
                data.putString("b", b);

                //Chuyển sang trang Chooseoperator dùng intent
                //Intent được tạo với mục tiêu là mở TargetActivity,
                // và có thể chuyển dữ liệu thông qua putExtra() trước khi gọi startActivity() để chuyển đến activity mới.
                Intent intent = new Intent(CalculatorActivity.this, ChooseOperatorActivity.class);

                //Đặt bundle vào intent
                intent.putExtra("data", data);
                launcher.launch(intent);
                //startActivity(intent);

            }
        });
    }
}