package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import com.example.travelhelper.mvp.contract.RegistrationContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private final RegistrationContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public RegistrationPresenter(RegistrationContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onRegistrationButtonClicked(Users user) {
        assert view != null;
        if(user.Firstname.isEmpty() || user.Lastname.isEmpty() || user.Login.isEmpty() || user.Email.isEmpty() || user.Phone.isEmpty() || user.Password.isEmpty())
            view.onEmptyFields();
        else{
            mDisposable.add(repository.createUser(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        Log.i(Constants.appLog, s);
                        if(s.contains("User already exists"))
                            view.onRegistrationFailed();
                        else
                            view.onRegistrationSuccess();
                    }, throwable -> {
                        Log.e(Constants.appLog, throwable.getMessage());
                    }));
        }
    }
}
