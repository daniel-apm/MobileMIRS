package mdb.project.mobilemirs.Interface;

public interface ILogin {
    void onLoginSuccess(String message, String fullName);
    void onLoginFailed(String message);
}
