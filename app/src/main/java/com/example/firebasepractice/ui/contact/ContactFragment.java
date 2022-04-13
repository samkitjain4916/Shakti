package com.example.firebasepractice.ui.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.firebasepractice.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment
{
    ImageView image_fb,image_insta,image_tweet,image_yt,image_in;

    public static com.example.firebasepractice.ui.contact.ContactFragment newInstance() {
        return new com.example.firebasepractice.ui.contact.ContactFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        com.example.firebasepractice.databinding.FragmentContactBinding binding = FragmentContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        image_insta = binding.imageInsta;
        image_tweet = binding.imageTweet;
        image_yt    = binding.imageYt;
        image_fb    = binding.imageFb;
        image_in    = binding.imageIn;

        image_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fun("https://www.instagram.com/sapnethedreams/?hl=en");
            }
        });

        image_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fun("https://www.facebook.com/Sapnethedreams/");
            }
        });

        image_tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fun("https://twitter.com/sapne07");
            }
        });

        image_yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fun("https://www.youtube.com/channel/UCZUECOx6Z66xnUjVEi57fSw");
            }
        });

        image_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun("https://www.linkedin.com/company/sapne-ngo/?originalSubdomain=in");
            }
        });


        return root;
    }

    private void fun(String string_url)
    {
        Uri uri = Uri.parse(string_url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}