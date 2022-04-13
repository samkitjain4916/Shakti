package com.example.firebasepractice.ui.developer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firebasepractice.databinding.FragmentDeveloperBinding;

public class DeveloperFragment extends Fragment
{
    public static com.example.firebasepractice.ui.mail.MailFragment newInstance() {
        return new com.example.firebasepractice.ui.mail.MailFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        com.example.firebasepractice.databinding.FragmentDeveloperBinding binding = FragmentDeveloperBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView link = binding.link;

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun();
            }
        });

        return root;
    }

    private void fun()
    {
        Uri uri = Uri.parse("https://www.linkedin.com/in/samkit-jain-9832b1196/?originalSubdomain=in");
        //startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}