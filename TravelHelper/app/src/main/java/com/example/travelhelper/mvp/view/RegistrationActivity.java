package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelhelper.databinding.ActivityRegistrationBinding;
import com.example.travelhelper.mvp.contract.RegistrationContract;
import com.example.travelhelper.mvp.presenter.RegistrationPresenter;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.utils.Extensions;

import android.content.Intent;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {

    RegistrationPresenter presenter;
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new RegistrationPresenter(this);
        binding.registerBtn.setOnClickListener(v -> {
            presenter.registrationButtonClicked(
                    new User(binding.firstname.getText().toString(),
                             binding.lastname.getText().toString(),
                             binding.email.getText().toString(),
                             binding.phone.getText().toString(),
                             binding.login.getText().toString(),
                             binding.password.getText().toString())
            );
        });
    }

    @Override
    public void onRegistrationSuccess() {
        Extensions.successToast("Регистрация успешна");
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onRegistrationFailed() {
        Extensions.errorToast("Заполните все поля");
    }
}