package com.weather.calculator20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton ac ,c, one , two , three , four , eight ,nine ,  mult , five , six , seven , plus , minus , divide , extra , zero , equals , dot;
    TextView output , input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.inputTv);
        output = findViewById(R.id.outputTv);

        assignid(R.id.one , one);
        assignid(R.id.two , two);
        assignid(R.id.three , three);
        assignid(R.id.four , four);
        assignid(R.id.five , five);
        assignid(R.id.six , six);
        assignid(R.id.seven , seven);
        assignid(R.id.eight , eight);
        assignid(R.id.nine , nine);
        assignid(R.id.zero , zero);
        assignid(R.id.dot , dot);
        assignid(R.id.ac , ac);
        assignid(R.id.plus , plus);
        assignid(R.id.minus , minus);
        assignid(R.id.divide , divide);
        assignid(R.id.arrow , extra);
        assignid(R.id.mult , mult);
        assignid(R.id.equals , equals);


    }

    void assignid(int id , MaterialButton btn){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String data = button.getText().toString();

        String datatocalc = input.getText().toString();

        input.setText(datatocalc+data);

        if (data.equals("ac")){
            input.setText(" ");
            output.setText("0");
            return;
        }
        else if(data.equals(".")){
            data = input.getText().toString();
            input.setText(data+"");
        }

        String finalresult = getresut(datatocalc);
        if(data.equals("=")){
            input.setText(datatocalc.substring(0, datatocalc.length()));
            output.setText(finalresult);
            return;
        }


    }
    String getresut(String data) {

        String finalresult = null;
        try {
            Context cont = Context.enter();
            cont.setOptimizationLevel(-1);
            Scriptable scriptable = cont.initStandardObjects();
            finalresult = cont.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if (finalresult.endsWith(".0")) {
                finalresult = finalresult.replace(".0", "");
            }

        } catch (Exception e) {
            output.setText("");
        }
        return finalresult;
    }
}

