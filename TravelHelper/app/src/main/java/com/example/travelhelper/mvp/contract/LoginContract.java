package com.example.travelhelper.mvp.contract;

public interface LoginContract {
    interface View{
        void onUserFound(String role);
        void onUserIncorrectPass();
        void onUserNotFound();
    }
    interface Presenter {
        void onLoginButtonClicked(String login, String password);
    }
}
