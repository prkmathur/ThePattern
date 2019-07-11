package com.app.thenhpattern.model.vo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.app.thenhpattern.BR;

import java.util.Map;

public class UserVerification {

    public static class Request extends BaseObservable implements IRequest{

        private String userId;
        private String OTP;

        @Bindable
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
            notifyPropertyChanged(BR.userId);

        }
        @Bindable
        public String getOTP() {
            return OTP;
        }

        public void setOTP(String OTP) {
            this.OTP = OTP;
            notifyPropertyChanged(BR.oTP);
        }

        @Override
        public Map<String, String> getRequestMap() {
            return null;
        }
    }

    public static class Response{

        private boolean verificationStatus;

        public boolean isVerificationStatus() {
            return verificationStatus;
        }

        public void setVerificationStatus(boolean verificationStatus) {
            this.verificationStatus = verificationStatus;
        }
    }

}
