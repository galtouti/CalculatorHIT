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
        result.setText("");
    }

    // פונקציה ללחיצה על מספרים
    public void numFunc(View view) {
        Button button = (Button) view;
        result.append(button.getText().toString());
    }

    // פונקציה ללחיצה על אופרטורים
    public void funcCh(View view) {
        Button button = (Button) view;
        operator = button.getText().toString(); // שומר את האופרטור
        num1 = Double.parseDouble(result.getText().toString()); // שומר את המספר הראשון
        result.setText(""); // מנקה את המסך לקליטת המספר השני
    }

    // פונקציה לחישוב התוצאה
    public void calculate(View view) {
        num2 = Double.parseDouble(result.getText().toString()); // שומר את המספר השני

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
                    result.setText("Error"); // טיפול בחלוקה באפס
                    return;
                }
                break;
        }

        result.setText(String.valueOf(resultValue)); // מציג את התוצאה
    }

    // פונקציה לאיפוס כל הנתונים (לחיצה על AC)
    public void resetCalculator(View view) {
        result.setText("");
        num1 = 0;
        num2 = 0;
        operator = "";
    }
}
