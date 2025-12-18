package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Agent {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("active")
    public int active;

    @SerializedName("api_token")
    public String apiToken;

    @SerializedName("confirmed")
    public int confirmed;

    @SerializedName("confirmation_code")
    public Object confirmationCode;

    @SerializedName("extension_password")
    public String extensionPassword;

    @SerializedName("telephony_id")
    public String telephonyId;

    @SerializedName("used_3c_plus_route")
    public boolean webphone;

    @SerializedName("user_document")
    public Object userDocument;

    @SerializedName("last_login")
    public String lastLogin;

    @SerializedName("last_nps")
    public Object lastNps;

    @SerializedName("created_at")
    public Date createdAt;

    @SerializedName("password_updated_at")
    public Object passwordUpdatedAt;

    @SerializedName("extension")
    public Extension extension;

    @SerializedName("settings")
    public Settings settings;

    @SerializedName("role")
    public Role role;

    @SerializedName("company")
    public Company company;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public Object getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(Object confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getExtensionPassword() {
        return extensionPassword;
    }

    public void setExtensionPassword(String extensionPassword) {
        this.extensionPassword = extensionPassword;
    }

    public String getTelephonyId() {
        return telephonyId;
    }

    public void setTelephonyId(String telephonyId) {
        this.telephonyId = telephonyId;
    }

    public boolean isWebphone() {
        return webphone;
    }

    public void setWebphone(boolean webphone) {
        this.webphone = webphone;
    }

    public Object getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(Object userDocument) {
        this.userDocument = userDocument;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Object getLastNps() {
        return lastNps;
    }

    public void setLastNps(Object lastNps) {
        this.lastNps = lastNps;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Object getPasswordUpdatedAt() {
        return passwordUpdatedAt;
    }

    public void setPasswordUpdatedAt(Object passwordUpdatedAt) {
        this.passwordUpdatedAt = passwordUpdatedAt;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", apiToken='" + apiToken + '\'' +
                ", confirmed=" + confirmed +
                ", confirmationCode=" + confirmationCode +
                ", extensionPassword='" + extensionPassword + '\'' +
                ", telephonyId='" + telephonyId + '\'' +
                ", webphone=" + webphone +
                ", userDocument=" + userDocument +
                ", lastLogin='" + lastLogin + '\'' +
                ", lastNps=" + lastNps +
                ", createdAt=" + createdAt +
                ", passwordUpdatedAt=" + passwordUpdatedAt +
                ", extension=" + extension +
                ", settings=" + settings +
                ", role=" + role +
                ", company=" + company +
                '}';
    }

    public class Extension{

        @SerializedName("id")
        public int id;
        @SerializedName("extension_number")
        public int extensionNumber;

        @SerializedName("type")
        public String type;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getExtensionNumber() {
            return extensionNumber;
        }

        public void setExtensionNumber(int extensionNumber) {
            this.extensionNumber = extensionNumber;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Extension{" +
                    "id=" + id +
                    ", extensionNumber=" + extensionNumber +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
