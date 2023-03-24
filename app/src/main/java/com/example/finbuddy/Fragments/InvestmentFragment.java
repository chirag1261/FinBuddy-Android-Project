package com.example.finbuddy.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.events.EventCommand;
import com.example.finbuddy.Activity.MainActivity;
import com.example.finbuddy.R;
import com.white.progressview.HorizontalProgressView;

import org.json.JSONException;
import org.json.JSONObject;

public class InvestmentFragment extends Fragment {

    HorizontalProgressView progress_fd, progress_equity, progress_mf, progress_real_estate, progress_gold;
    TextView btnSIPCalculator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_investment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        progress_fd = view.findViewById(R.id.progress_fd);
        progress_equity = view.findViewById(R.id.progress_equity);
        progress_mf = view.findViewById(R.id.progress_mf);
        progress_real_estate = view.findViewById(R.id.progress_real_estate);
        progress_gold = view.findViewById(R.id.progress_gold);

        progress_fd.setProgressInTime(0, 1000);
        progress_equity.setProgressInTime(0, 1200);
        progress_mf.setProgressInTime(0, 1100);
        progress_real_estate.setProgressInTime(0, 1000);
        progress_gold.setProgressInTime(0, 1050);

        btnSIPCalculator = view.findViewById(R.id.btnSIPCalculator);
        btnSIPCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_investmentFragment2_to_SIPCalculatorFragment2);
            }
        });


        NavController navController = Navigation.findNavController(view);


        AlanButton alanButton =  ((MainActivity)getActivity()).alanButton;

        AlanCallback alanCallback = new AlanCallback() {
            /// Handle commands from Alan Studio
            @Override
            public void onCommand(final EventCommand eventCommand) {
                try {
                    JSONObject command = eventCommand.getData();
                    String commandName = command.getJSONObject("data").getString("command");
                    if(commandName.equals("return home")){
                        navController.navigateUp();
                    }
                    Log.d("AlanButton", "onCommand: commandName: " + commandName);
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                }
            }
        };

/// Register callbacks
        alanButton.registerCallback(alanCallback);
    }
}
