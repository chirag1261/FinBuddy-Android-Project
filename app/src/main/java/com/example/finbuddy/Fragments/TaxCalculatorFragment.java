package com.example.finbuddy.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finbuddy.R;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculatorFragment extends Fragment{
    Spinner taxType;
    EditText amount;
    Button calculate_tax;
    TextView tax_amount, net_salary;
    String item = "New Regime";
    int totalAmount=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tax_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        taxType = view.findViewById(R.id.taxType);
        List<String> categories = new ArrayList<String>();
        categories.add("New Regime");
        categories.add("Old Regime");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taxType.setAdapter(dataAdapter);
        taxType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        amount = view.findViewById(R.id.amount);

        calculate_tax = view.findViewById(R.id.calculate_tax);
        tax_amount = view.findViewById(R.id.tax_amount);
        net_salary = view.findViewById(R.id.net_salary);
        calculate_tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    public void calculate() {
        if (!amount.getText().toString().equals("")) {
            totalAmount = Integer.parseInt(amount.getText().toString());
        }
        else{
            Toast.makeText(getActivity(),"Please enter the amount!",Toast.LENGTH_SHORT).show();
            return ;
        }
        double tax = 0;
        if (item.equals("New Regime")) {
            if (totalAmount <= 250000)
                tax = 0;
            else if (totalAmount <= 500000) {
                double y = (double) (totalAmount - 250000);
                y = (double) (y * 5) / (double) 100;
                tax += y;
            } else if (totalAmount <= 750000) {
                tax += (double) 12500;
                double y = (double) (totalAmount - 500000);
                y = (double) (y * 10) / (double) 100;
                tax += y;
            } else if (totalAmount <= 1000000) {
                tax += (double) 38000;
                double y = (double) (totalAmount - 750000);
                y = (double) (y * 15) / (double) 100;
                tax += y;
            } else if (totalAmount <= 1250000) {
                tax += (double) 75500;
                double y = (double) (totalAmount - 1000000);
                y = (double) (y * 20) / (double) 100;
                tax += y;
            } else if (totalAmount <= 1500000) {
                tax += (double) 125500;
                double y = (double) (totalAmount - 1250000);
                y = (double) (y * 25) / (double) 100;
                tax += y;
            } else {
                tax += (double) 188000;
                double y = (double) (totalAmount - 1500000);
                y = (double) (y * 30) / (double) 100;
                tax += y;
            }
        } else {
            if (totalAmount <= 250000)
                tax = 0;
            else if (totalAmount <= 500000) {
                double y = (double) (totalAmount - 250000);
                y = (double) (y * 5) / (double) 100;
                tax += y;
            } else if (totalAmount <= 1000000) {
                tax += (double) 12500;
                double y = (double) (totalAmount - 500000);
                y = (double) (y * 20) / (double) 100;
                tax += y;
            } else {
                tax += (double) 112500;
                double y = (double) (totalAmount - 100000);
                y = (double) (y * 30) / (double) 100;
                tax += y;
            }
        }
        double s=tax*((double)104/(double)100);
        tax_amount.setText(String.format("Tax = ₹%,.2f", (tax+s)));
        net_salary.setText(String.format("Net Salary = ₹%,.2f", (totalAmount - (tax+s))));
    }
}