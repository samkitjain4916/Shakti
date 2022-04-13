package com.example.firebasepractice.ui.mail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.firebasepractice.databinding.FragmentMailBinding;

public class MailFragment extends Fragment
{
    public static MailFragment newInstance() {
        return new MailFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        com.example.firebasepractice.databinding.FragmentMailBinding binding = FragmentMailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView email_sapne = binding.emailSapne;
        TextView email_shakti = binding.emailShakti;

        email_sapne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_mail("sapnethedreams@gmail.com");
            }
        });

        email_shakti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_mail("sapnekishakti@gmail.com");
            }
        });

        return root;
    }

    public void open_mail(String email)
    {
        Uri uri = Uri.parse("mailto:" + email);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}