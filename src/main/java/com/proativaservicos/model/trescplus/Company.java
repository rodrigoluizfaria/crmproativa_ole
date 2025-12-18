package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Company {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("currency")
    private String currency;

    @SerializedName("balance")
    private double balance;

    @SerializedName("domain")
    private String domain;

    @SerializedName("logo_name")
    private String logoName;

    @SerializedName("logo_image_link")
    private String logoImageLink;

    @SerializedName("socket_channel")
    private String socketChannel;

    @SerializedName("max_agents_login")
    private int maxAgentsLogin;

    @SerializedName("webphone_licenses")
    private int webphoneLicenses;

    @SerializedName("ura_licenses")
    private int uraLicenses;

    @SerializedName("ura_limit")
    private int uraLimit;

    @SerializedName("credit_limit")
    private String creditLimit;

    @SerializedName("is_partner")
    private boolean isPartner;
    @SerializedName("integration_enabled")
    private boolean integrationEnabled;
    @SerializedName("partner_id")
    private Object partnerId;
    @SerializedName("limit_call_per_agent")
    private String limitCallPerAgent;
    @SerializedName("access_bi")
    private boolean accessBi;
    @SerializedName("value_sms")
    private double valueSms;
    @SerializedName("credit_sms")
    private int creditSms;
    @SerializedName("route_landline_id")
    private int routeLandlineId;

    @SerializedName("route_mobile_id")
    private int routeMobilId;
    @SerializedName("route_group_mobile_id")
    private int routeGroupMobileId;
    @SerializedName("limit_unproductive_calls")
    private int limitUnproductiveCalls;
    @SerializedName("used_3c_plus_route")
    private boolean used3cPlusRoute;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("plan")
    private int plan;

    @SerializedName("new_front_end")
    private boolean newFrontEnd;

    @SerializedName("whatsapp_licenses")
    private int whatsappLicenses;

    @SerializedName("url")
    private Object url;

    @SerializedName("blocked_at")
    private Object blockedAt;

    @SerializedName("blocked_sms")
    private boolean blockedSms;

    @SerializedName("whatsapp_max_concurrent_logins")
    private int whatsappMaxConcurrentLogins;

    @SerializedName("company_financial_status")
    private CompanyFinancialStatus companyFinancialStatus;

    @SerializedName("enabled_download_audios_in_batch")
    private boolean enabledDownloadAudiosInBatch;

    @SerializedName("enabled_recordings_in_stereo")
    private boolean enabledRecordingsInStereo;

    @SerializedName("pro_mode")
    private boolean proMode;

    @SerializedName("record_audio_agent")
    private boolean recordAudioAgent;


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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getLogoImageLink() {
        return logoImageLink;
    }

    public void setLogoImageLink(String logoImageLink) {
        this.logoImageLink = logoImageLink;
    }

    public String getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(String socketChannel) {
        this.socketChannel = socketChannel;
    }

    public int getMaxAgentsLogin() {
        return maxAgentsLogin;
    }

    public void setMaxAgentsLogin(int maxAgentsLogin) {
        this.maxAgentsLogin = maxAgentsLogin;
    }

    public int getWebphoneLicenses() {
        return webphoneLicenses;
    }

    public void setWebphoneLicenses(int webphoneLicenses) {
        this.webphoneLicenses = webphoneLicenses;
    }

    public int getUraLicenses() {
        return uraLicenses;
    }

    public void setUraLicenses(int uraLicenses) {
        this.uraLicenses = uraLicenses;
    }

    public int getUraLimit() {
        return uraLimit;
    }

    public void setUraLimit(int uraLimit) {
        this.uraLimit = uraLimit;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public boolean isPartner() {
        return isPartner;
    }

    public void setPartner(boolean partner) {
        isPartner = partner;
    }

    public boolean isIntegrationEnabled() {
        return integrationEnabled;
    }

    public void setIntegrationEnabled(boolean integrationEnabled) {
        this.integrationEnabled = integrationEnabled;
    }

    public Object getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Object partnerId) {
        this.partnerId = partnerId;
    }

    public String getLimitCallPerAgent() {
        return limitCallPerAgent;
    }

    public void setLimitCallPerAgent(String limitCallPerAgent) {
        this.limitCallPerAgent = limitCallPerAgent;
    }

    public boolean isAccessBi() {
        return accessBi;
    }

    public void setAccessBi(boolean accessBi) {
        this.accessBi = accessBi;
    }

    public double getValueSms() {
        return valueSms;
    }

    public void setValueSms(double valueSms) {
        this.valueSms = valueSms;
    }

    public int getCreditSms() {
        return creditSms;
    }

    public void setCreditSms(int creditSms) {
        this.creditSms = creditSms;
    }

    public int getRouteLandlineId() {
        return routeLandlineId;
    }

    public void setRouteLandlineId(int routeLandlineId) {
        this.routeLandlineId = routeLandlineId;
    }



    public int getRouteMobilId() {
        return routeMobilId;
    }

    public void setRouteMobilId(int routeMobilId) {
        this.routeMobilId = routeMobilId;
    }

    public int getRouteGroupMobileId() {
        return routeGroupMobileId;
    }

    public void setRouteGroupMobileId(int routeGroupMobileId) {
        this.routeGroupMobileId = routeGroupMobileId;
    }

    public int getLimitUnproductiveCalls() {
        return limitUnproductiveCalls;
    }

    public void setLimitUnproductiveCalls(int limitUnproductiveCalls) {
        this.limitUnproductiveCalls = limitUnproductiveCalls;
    }

    public boolean isUsed3cPlusRoute() {
        return used3cPlusRoute;
    }

    public void setUsed3cPlusRoute(boolean used3cPlusRoute) {
        this.used3cPlusRoute = used3cPlusRoute;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public boolean isNewFrontEnd() {
        return newFrontEnd;
    }

    public void setNewFrontEnd(boolean newFrontEnd) {
        this.newFrontEnd = newFrontEnd;
    }

    public int getWhatsappLicenses() {
        return whatsappLicenses;
    }

    public void setWhatsappLicenses(int whatsappLicenses) {
        this.whatsappLicenses = whatsappLicenses;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(Object blockedAt) {
        this.blockedAt = blockedAt;
    }

    public boolean isBlockedSms() {
        return blockedSms;
    }

    public void setBlockedSms(boolean blockedSms) {
        this.blockedSms = blockedSms;
    }

    public int getWhatsappMaxConcurrentLogins() {
        return whatsappMaxConcurrentLogins;
    }

    public void setWhatsappMaxConcurrentLogins(int whatsappMaxConcurrentLogins) {
        this.whatsappMaxConcurrentLogins = whatsappMaxConcurrentLogins;
    }

    public CompanyFinancialStatus getCompanyFinancialStatus() {
        return companyFinancialStatus;
    }

    public void setCompanyFinancialStatus(CompanyFinancialStatus companyFinancialStatus) {
        this.companyFinancialStatus = companyFinancialStatus;
    }

    public boolean isEnabledDownloadAudiosInBatch() {
        return enabledDownloadAudiosInBatch;
    }

    public void setEnabledDownloadAudiosInBatch(boolean enabledDownloadAudiosInBatch) {
        this.enabledDownloadAudiosInBatch = enabledDownloadAudiosInBatch;
    }

    public boolean isEnabledRecordingsInStereo() {
        return enabledRecordingsInStereo;
    }

    public void setEnabledRecordingsInStereo(boolean enabledRecordingsInStereo) {
        this.enabledRecordingsInStereo = enabledRecordingsInStereo;
    }

    public boolean isProMode() {
        return proMode;
    }

    public void setProMode(boolean proMode) {
        this.proMode = proMode;
    }

    public boolean isRecordAudioAgent() {
        return recordAudioAgent;
    }

    public void setRecordAudioAgent(boolean recordAudioAgent) {
        this.recordAudioAgent = recordAudioAgent;
    }

    public class CompanyFinancialStatus{

        @SerializedName("status")
        public String status;

        @SerializedName("days")
        public int days;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", domain='" + domain + '\'' +
                ", logoName='" + logoName + '\'' +
                ", logoImageLink='" + logoImageLink + '\'' +
                ", socketChannel='" + socketChannel + '\'' +
                ", maxAgentsLogin=" + maxAgentsLogin +
                ", webphoneLicenses=" + webphoneLicenses +
                ", uraLicenses=" + uraLicenses +
                ", uraLimit=" + uraLimit +
                ", creditLimit='" + creditLimit + '\'' +
                ", isPartner=" + isPartner +
                ", integrationEnabled=" + integrationEnabled +
                ", partnerId=" + partnerId +
                ", limitCallPerAgent='" + limitCallPerAgent + '\'' +
                ", accessBi=" + accessBi +
                ", valueSms=" + valueSms +
                ", creditSms=" + creditSms +
                ", routeLandlineId=" + routeLandlineId +
                ", routeMobilId=" + routeMobilId +
                ", routeGroupMobileId=" + routeGroupMobileId +
                ", limitUnproductiveCalls=" + limitUnproductiveCalls +
                ", used3cPlusRoute=" + used3cPlusRoute +
                ", createdAt=" + createdAt +
                ", plan=" + plan +
                ", newFrontEnd=" + newFrontEnd +
                ", whatsappLicenses=" + whatsappLicenses +
                ", url=" + url +
                ", blockedAt=" + blockedAt +
                ", blockedSms=" + blockedSms +
                ", whatsappMaxConcurrentLogins=" + whatsappMaxConcurrentLogins +
                ", companyFinancialStatus=" + companyFinancialStatus +
                ", enabledDownloadAudiosInBatch=" + enabledDownloadAudiosInBatch +
                ", enabledRecordingsInStereo=" + enabledRecordingsInStereo +
                ", proMode=" + proMode +
                ", recordAudioAgent=" + recordAudioAgent +
                '}';
    }
}
