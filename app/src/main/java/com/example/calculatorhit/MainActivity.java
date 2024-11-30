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
        Button button = (Button) view;
        if (result.getText().toString().equals("0")) {
            result.setText(button.getText().toString()); // מחליף את 0 במספר שנלחץ
        } else {
            result.append(button.getText().toString());
        }
    }


    // פונקציה ללחיצה על אופרטורים
    public void funcCh(View view) {
        Button button = (Button) view;
        operator = button.getText().toString(); // שומר את האופרטור
        num1 = Double.parseDouble(result.getText().toString()); // שומר את המספר הראשון
        result.append(" " + operator + " "); // מוסיף את האופרטור לטקסט הקיים
    }

    // פונקציה לחישוב התוצאה
    public void calculate(View view) {
        try {
            String[] parts = result.getText().toString().split(" "); // מפריד בין המספרים לאופרטור
            num1 = Double.parseDouble(parts[0]); // המספר הראשון
            num2 = Double.parseDouble(parts[2]); // המספר השני
        } catch (Exception e) {
            result.setText("Error");
            return;
        }

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
                    result.setText("NOOOOOOOOO DIVIDE 0"); // טיפול בחלוקה באפס
                    return;
                }
                break;
        }

        result.setText(String.valueOf(resultValue)); // מציג את התוצאה
        num1 = resultValue; // מאתחל את המספר הראשון כדי להמשיך בחישוב חדש
        operator = ""; // מאתחל את האופרטור
    }

    // פונקציה לאיפוס כל הנתונים (לחיצה על AC)
    public void resetCalculator(View view) {
        result.setText("0");
        num1 = 0;
        num2 = 0;
        operator = "";
    }
}
