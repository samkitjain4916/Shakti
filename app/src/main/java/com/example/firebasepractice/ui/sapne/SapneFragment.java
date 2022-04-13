package com.example.firebasepractice.ui.sapne;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.firebasepractice.databinding.FragmentSapneBinding;

public class SapneFragment extends Fragment
{

    TextView sapneWebsite;

    public static com.example.firebasepractice.ui.mail.MailFragment newInstance() {
        return new com.example.firebasepractice.ui.mail.MailFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        com.example.firebasepractice.databinding.FragmentSapneBinding binding = FragmentSapneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sapneWebsite = binding.sapneWebsite;

        sapneWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_website();
            }
        });

        return root;
    }

    private void open_website()
    {
        Uri uri = Uri.parse("https://www.sapne.org.in/");
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


}