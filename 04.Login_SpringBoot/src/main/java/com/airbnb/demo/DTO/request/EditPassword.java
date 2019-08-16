package com.airbnb.demo.DTO.request;

public class EditPassword {
    private String newPass;
    private String oldPass;

    public EditPassword(String newPass, String oldPass) {
        this.newPass = newPass;
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }
}
