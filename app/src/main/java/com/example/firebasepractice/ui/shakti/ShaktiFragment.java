package com.example.firebasepractice.ui.shakti;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.firebasepractice.databinding.FragmentShaktiBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class ShaktiFragment extends Fragment
{
    private FragmentShaktiBinding binding;
    private String text_name,text_skill,text_email,text_age,text_number,text_address,text_request;
    private EditText name,skill,email,age,number,address,request;
    private FirebaseFirestore fStore;
    private DocumentReference documentReference;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentShaktiBinding.inflate(inflater,container, false);
        View root = binding.getRoot();

        fStore  = FirebaseFirestore.getInstance();

        name    = binding.name;
        skill   = binding.skill;
        email   = binding.email;
        age     = binding.ageNumber;
        number  = binding.mobileNumber;
        address = binding.address;
        request = binding.request;
        Button button_submit = binding.submit;

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                text_name    = name.getText().toString();
                text_skill   = skill.getText().toString();
                text_email   = email.getText().toString();
                text_age     = age.getText().toString();
                text_number  = number.getText().toString();
                text_address = address.getText().toString();
                text_request = request.getText().toString();


                if(TextUtils.isEmpty(text_name) || TextUtils.isEmpty(text_skill) || TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_age) || TextUtils.isEmpty(text_number))
                {
                    Toast.makeText(getContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Map<String,Object> map = new HashMap<>();
                    map.put("timestamp", FieldValue.serverTimestamp());
                    map.put("userName",text_name);
                    map.put("userSkill",text_skill);
                    map.put("userEmail",text_email);
                    map.put("userAge",text_age);
                    map.put("userNumber",text_number);
                    map.put("userAddress",text_address);
                    map.put("userRequest",text_request);

                    documentReference = fStore.collection("shaktiRegistrations").document();
                    documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void unused)
                        {
                            Toast.makeText(getContext(),"Successfully registered for SEP", Toast.LENGTH_SHORT).show();
                            if(getContext()!=null)
                            hideKeyboard(getContext());
                            if(getActivity()!=null)
                            getActivity().onBackPressed();
                        }

                    });

                }
            }
        });

        return root;
    }

    public static void hideKeyboard(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) mContext).getWindow()
                .getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}