package com.app.thenhpattern.model.vo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.app.thenhpattern.BR;

public class User {

    //Register Request

    public static class RegisterRequest extends BaseObservable implements IRequest{
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String password;
        private String error;

        @Bindable
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {

            this.firstName = firstName;
            notifyPropertyChanged(BR.firstName);

        }
        @Bindable
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
            notifyPropertyChanged(BR.lastName);
        }
        @Bindable
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
            notifyPropertyChanged(BR.email);
        }
        @Bindable
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
            notifyPropertyChanged(BR.phone);
        }
        @Bindable
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            notifyPropertyChanged(BR.password);

        }

        @Bindable
        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
            notifyPropertyChanged(BR.error);
        }

    }

    //Login Request

    public static class LoginRequest extends BaseObservable implements IRequest{
        private String email;
        private String password;

        @Bindable
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
            notifyPropertyChanged(BR.email);
        }

        @Bindable
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            notifyPropertyChanged(BR.password);
        }
    }

    //Current User

    public static class CurrentUser{

        private String user_auth_token;
        private String user_firstName;
        private String user_lastName;
        private String user_name;
        private String user_email;
        private String user_phone_no;
        private String device_type;
        private String user_status;
        private String is_verified;

        public String getUser_firstName() {
            return user_firstName;
        }

        public void setUser_firstName(String user_firstName) {
            this.user_firstName = user_firstName;
        }

        public String getUser_lastName() {
            return user_lastName;
        }

        public void setUser_lastName(String user_lastName) {
            this.user_lastName = user_lastName;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_phone_no() {
            return user_phone_no;
        }

        public void setUser_phone_no(String user_phone_no) {
            this.user_phone_no = user_phone_no;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getIs_verified() {
            return is_verified;
        }

        public void setIs_verified(String is_verified) {
            this.is_verified = is_verified;
        }

        public String getUser_auth_token() {
            return user_auth_token;
        }

        public void setUser_auth_token(String user_auth_token) {
            this.user_auth_token = user_auth_token;
        }
    }


}
