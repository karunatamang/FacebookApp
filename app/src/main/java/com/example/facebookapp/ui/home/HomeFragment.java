package com.example.facebookapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookapp.R;
import com.example.facebookapp.adapter.UserAdapter;
import com.example.facebookapp.api.ApiUser;
import com.example.facebookapp.model.User;
import com.example.facebookapp.url.Url;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView textView;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private List<User> usersList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                UserAdapter userAdapter = new UserAdapter(usersList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(userAdapter);
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersList = new ArrayList<>();
        showUser();
    }

    private void showUser() {
        ApiUser usersAPI = Url.getInstance().create(ApiUser.class);
        Call<List<User>> userlistCall = usersAPI.showUser();

        try {
            Response<List<User>> response = userlistCall.execute();
            usersList = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}