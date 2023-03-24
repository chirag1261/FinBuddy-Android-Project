package com.example.finbuddy.Fragments;

import static java.lang.Math.pow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.finbuddy.R;

public class SIPCalculatorFragment extends Fragment {
    EditText basic_amount, duration, interest_rate;
    Button calculate_sip;
    TextView sip_tax;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sip_calculator, container, false);
        basic_amount = view.findViewById(R.id.basic_amount);
        duration = view.findViewById(R.id.duration);
        interest_rate = view.findViewById(R.id.interest_rate);
        calculate_sip = view.findViewById(R.id.calculate_sip);
        sip_tax = view.findViewById(R.id.sip_tax);
        calculate_sip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
        return view;
    }

    public void calculate() {
        if (basic_amount.getText().toString().equals("") || duration.getText().toString().equals("") ||
                interest_rate.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Please fill the necessary details!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            double ans = 0;
            int n = Integer.parseInt(duration.getText().toString());
            double i = Double.parseDouble(interest_rate.getText().toString());
            int p = Integer.parseInt(basic_amount.getText().toString());
            double y = ((double) i / (double) (n * 100));
            ans = (double) p * (((pow((1 + y), (double) n) - 1) / y)) * (y + 1);
            sip_tax.setText(String.format("SIP = ₹%,.2f", (ans)));
        }
    }
}
