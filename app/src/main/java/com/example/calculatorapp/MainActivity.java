package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText num1, num2, operate;
    private TextView resultText;
    private Button calc;
    private Toast toastError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.input1);
        num2 = findViewById(R.id.input2);
        operate = findViewById(R.id.operation);
        resultText = findViewById(R.id.txtResult);
        calc = findViewById(R.id.btnCalc);

        calc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        float input1, input2, result=0;
        String task;
        boolean correctOperation = true;
        try {
            input1 = Float.parseFloat(num1.getText().toString());
            input2 = Float.parseFloat(num2.getText().toString());
            task = operate.getText().toString();
            switch (task) {
                case "+":
                    result = input1 + input2;
                    break;
                case "-":
                    result = input1 - input2;
                    break;
                case "*":
                    result = input1 * input2;
                    break;
                case "/":
                    if (input2 == 0) throw new ArithmeticException();
                    result = input1 / input2;
                    break;
                default:
                    correctOperation = false;
                    break;
            }
        } catch (ArithmeticException e){
            int duration = Toast.LENGTH_SHORT;
            if (toastError!=null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.divide_zero, duration);
            toastError.show();
            return;
        } catch (NullPointerException e){
            int duration = Toast.LENGTH_SHORT;
            if (toastError!=null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.null_data, duration);
            toastError.show();
            return;
        } catch (NumberFormatException e) {
            int duration = Toast.LENGTH_SHORT;
            if (toastError!=null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.wrong_format, duration);
            toastError.show();
            return;
        }
        if (correctOperation) {
            resultText.setText(input1 + " " + task + " " + input2 + " " + "=" + " " + result);
        }
        else {
            int duration = Toast.LENGTH_SHORT;
            if (toastError!=null){
                toastError.cancel();
            }
            toastError = Toast.makeText(this, R.string.wrong_operation, duration);
            toastError.show();
            return;
        }
    }
}