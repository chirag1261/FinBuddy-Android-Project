package com.example.finbuddy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.events.EventCommand;
import com.example.finbuddy.Activity.MainActivity;
import com.example.finbuddy.R;
import com.example.finbuddy.adapter.LoanAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EducationLoanFragment extends Fragment {
    List<String> l,l1;
    Context ctx;
    LoanAdapter adapter;
    RecyclerView rV, rV1;
    TextView tvBOI, tvSBI, tvIDBI, tvCanara;
    LinearLayout mainlinear;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education_loan, container, false);
        rV=view.findViewById(R.id.recycler_view);
        rV1=view.findViewById(R.id.recycler_view1);
        mainlinear = view.findViewById(R.id.main_linear);
        progressBar = view.findViewById(R.id.progress_bar);

        mainlinear.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        l = new ArrayList<>();
        l1 = new ArrayList<>();
        rV.setHasFixedSize(true);
        rV.setLayoutManager(new LinearLayoutManager(getActivity()));
        rV1.setHasFixedSize(true);
        rV1.setLayoutManager(new LinearLayoutManager(getActivity()));
        ctx=getContext();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        DocumentReference ref=db.collection("EducationLoan").document("Benefit");
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Map<String, Object> c =documentSnapshot.getData();
                    for(int i=0;i<c.size();i++){
                        String s=String.valueOf(i);
                        String a=String.valueOf(i+1);
                        l.add("• " + (String) c.get(s));
                    }
                    adapter = new LoanAdapter(l,ctx);
                    rV.setAdapter(adapter);
                }
            }
        });
        DocumentReference ref1=db.collection("EducationLoan").document("DocumentRequired");
        ref1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Map<String, Object> c =documentSnapshot.getData();
                    for(int i=0;i<c.size();i++){
                        String s=String.valueOf(i);
                        String a=String.valueOf(i+1);
                        l1.add("• " + (String) c.get(s));                    }
                    adapter = new LoanAdapter(l1,ctx);
                    rV1.setAdapter(adapter);
                    mainlinear.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvBOI = view.findViewById(R.id.tvBOI);
        tvSBI = view.findViewById(R.id.tvSBI);
        tvCanara = view.findViewById(R.id.tvCanara);
        tvIDBI = view.findViewById(R.id.tvIDBI);

        tvBOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.bankofindia.co.in/EducationLoan";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        tvSBI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://sbi.co.in/web/personal-banking/loans/education-loans";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        tvCanara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://canarabank.com/User_page.aspx?othlink=391";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        tvIDBI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.idbibank.in/education-loan.aspx";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
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
