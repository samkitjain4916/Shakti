package com.example.firebasepractice;

public class User
{
    String userName,userSkill,userEmail,userAge,userNumber,userAddress,userRequest;

    public User() {}

    public User(String userName, String userSkill, String userEmail, String userAge, String userNumber, String userAddress, String userRequest) {
        this.userName = userName;
        this.userSkill = userSkill;
        this.userEmail = userEmail;
        this.userAge = userAge;
        this.userNumber = userNumber;
        this.userAddress = userAddress;
        this.userRequest = userRequest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSkill() {
        return userSkill;
    }

    public void setUserSkill(String userSkill) {
        this.userSkill = userSkill;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }
}
