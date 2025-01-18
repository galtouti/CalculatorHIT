package com.example.calculatorhit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView result;
    double num1 = 0;
    double num2 = 0;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = findViewById(R.id.textViewResult);
        result.setText("0");
    }

    // פונקציה ללחיצה על מספרים
    public void numFunc(View view) {
        try {
            Button button = (Button) view;
            if (result.getText().toString().equals("0")) {
                result.setText(button.getText().toString()); // מחליף את 0 במספר שנלחץ
            } else {
                result.append(button.getText().toString());
            }
        } catch (Exception e) {
            result.setText("Error");
        }
    }

    // פונקציה ללחיצה על אופרטורים
    public void funcCh(View view) {
        try {
            Button button = (Button) view;
            operator = button.getText().toString(); // שומר את האופרטור
            num1 = Double.parseDouble(result.getText().toString()); // שומר את המספר הראשון
            result.append(" " + operator + " "); // מוסיף את האופרטור לטקסט הקיים
        } catch (Exception e) {
            result.setText("Error");
        }
    }

    // פונקציה לחישוב התוצאה
    public void calculate(View view) {
        try {
            String[] parts = result.getText().toString().split(" "); // מפריד בין המספרים לאופרטור
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid input");
            }
            num1 = Double.parseDouble(parts[0]); // המספר הראשון
            num2 = Double.parseDouble(parts[2]); // המספר השני

            double resultValue = 0;

            switch (operator) {
                case "+":
                    resultValue = num1 + num2;
                    break;
                case "-":
                    resultValue = num1 - num2;
                    break;
                case "x":
                    resultValue = num1 * num2;
                    break;
                case "÷":
                    if (num2 != 0) {
                        resultValue = num1 / num2;
                    } else {
                        throw new ArithmeticException("Divide by zero");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected operator");
            }

            if (resultValue % 1 == 0) {
                result.setText(String.valueOf((int) resultValue)); // מציג את התוצאה
            } else {
                result.setText(String.valueOf(resultValue));
            }
            num1 = resultValue; // מאתחל את המספר הראשון כדי להמשיך בחישוב חדש
            operator = ""; // מאתחל את האופרטור
        } catch (Exception e) {
            result.setText("Error");
        }
    }

    // פונקציה לאיפוס כל הנתונים (לחיצה על AC)
    public void resetCalculator(View view) {
        try {
            result.setText("0");
            num1 = 0;
            num2 = 0;
            operator = "";
        } catch (Exception e) {
            result.setText("Error");
        }
    }
}