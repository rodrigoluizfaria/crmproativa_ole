package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

public class Campanha {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("paused")
    private boolean paused;

    @SerializedName("amd_enabled")
    private boolean amdEnabled;

    @SerializedName("acw_timeout")
    private int acwTimeout;

    @SerializedName("is_on_active_time")
    private boolean isOnActiveTime;

    @SerializedName("caller_id")
    private String callerId;

    @SerializedName("limit_call_per_agent")
    private int limitCallPerAgent;

    @SerializedName("asr")
    private String asr;

    @SerializedName("ura_limit")
    private int uraLimit;

    @SerializedName("is_predictive")
    private boolean isPredictive;

    @SerializedName("agent_dashboard")
    private boolean agentDashboard;

    @SerializedName("active_list_notify")
    private boolean activeListNotify;

    @SerializedName("allows_manual")
    private boolean allowsManual;

    @SerializedName("limit_call_time")
    private int limitCallTime;

    @SerializedName("exit_manual_mode")
    private int exitManualMode;

    @SerializedName("copy_identifier")
    private boolean copyIdentifier;

    @SerializedName("ivr_after_call_id")
    private Object ivrAfterCallId;

    @SerializedName("route_limit_exceeded")
    private boolean routeLimitExceeded;

    @SerializedName("route_landline_id")
    private int routeLandlineId;

    @SerializedName("route_mobile_id")
    private int routeMobileId;

    @SerializedName("route_group_landline_id")
    private int routeGroupLandlineId;

    @SerializedName("route_group_mobile_id")
    private int routeGroupMobileId;

    @SerializedName("check_smart_filter")
    private boolean checkSmartFilter;

    @SerializedName("filter_calls")
    private boolean filterCalls;

    @SerializedName("check_dnd")
    private boolean checkDnd;

    @SerializedName("check_blacklist")
    private boolean checkBlacklist;

    @SerializedName("check_ddd")
    private boolean checkDdd;

    @SerializedName("should_complete_failed_call")
    private boolean shouldCompleteFailedCall;

    @SerializedName("update_mailing_data")
    private boolean updateMailingData;

    @SerializedName("behavior")
    private String behavior;
    @SerializedName("ivr_after_call_status")
    private boolean ivrAfterCallStatus;

    @SerializedName("horizontal_dial")
    private boolean horizontalDial;
    @SerializedName("min_idle_time")
    private int minIdleTime;

    @SerializedName("dialer_settings")
    private DialerSettings dialerSettings;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isAmdEnabled() {
        return amdEnabled;
    }

    public void setAmdEnabled(boolean amdEnabled) {
        this.amdEnabled = amdEnabled;
    }

    public int getAcwTimeout() {
        return acwTimeout;
    }

    public void setAcwTimeout(int acwTimeout) {
        this.acwTimeout = acwTimeout;
    }

    public boolean isOnActiveTime() {
        return isOnActiveTime;
    }

    public void setOnActiveTime(boolean onActiveTime) {
        isOnActiveTime = onActiveTime;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public int getLimitCallPerAgent() {
        return limitCallPerAgent;
    }

    public void setLimitCallPerAgent(int limitCallPerAgent) {
        this.limitCallPerAgent = limitCallPerAgent;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public int getUraLimit() {
        return uraLimit;
    }

    public void setUraLimit(int uraLimit) {
        this.uraLimit = uraLimit;
    }

    public boolean isPredictive() {
        return isPredictive;
    }

    public void setPredictive(boolean predictive) {
        isPredictive = predictive;
    }

    public boolean isAgentDashboard() {
        return agentDashboard;
    }

    public void setAgentDashboard(boolean agentDashboard) {
        this.agentDashboard = agentDashboard;
    }

    public boolean isActiveListNotify() {
        return activeListNotify;
    }

    public void setActiveListNotify(boolean activeListNotify) {
        this.activeListNotify = activeListNotify;
    }

    public boolean isAllowsManual() {
        return allowsManual;
    }

    public void setAllowsManual(boolean allowsManual) {
        this.allowsManual = allowsManual;
    }

    public int getLimitCallTime() {
        return limitCallTime;
    }

    public void setLimitCallTime(int limitCallTime) {
        this.limitCallTime = limitCallTime;
    }

    public int getExitManualMode() {
        return exitManualMode;
    }

    public void setExitManualMode(int exitManualMode) {
        this.exitManualMode = exitManualMode;
    }

    public boolean isCopyIdentifier() {
        return copyIdentifier;
    }

    public void setCopyIdentifier(boolean copyIdentifier) {
        this.copyIdentifier = copyIdentifier;
    }

    public Object getIvrAfterCallId() {
        return ivrAfterCallId;
    }

    public void setIvrAfterCallId(Object ivrAfterCallId) {
        this.ivrAfterCallId = ivrAfterCallId;
    }

    public boolean isRouteLimitExceeded() {
        return routeLimitExceeded;
    }

    public void setRouteLimitExceeded(boolean routeLimitExceeded) {
        this.routeLimitExceeded = routeLimitExceeded;
    }

    public int getRouteLandlineId() {
        return routeLandlineId;
    }

    public void setRouteLandlineId(int routeLandlineId) {
        this.routeLandlineId = routeLandlineId;
    }

    public int getRouteMobileId() {
        return routeMobileId;
    }

    public void setRouteMobileId(int routeMobileId) {
        this.routeMobileId = routeMobileId;
    }

    public int getRouteGroupLandlineId() {
        return routeGroupLandlineId;
    }

    public void setRouteGroupLandlineId(int routeGroupLandlineId) {
        this.routeGroupLandlineId = routeGroupLandlineId;
    }

    public int getRouteGroupMobileId() {
        return routeGroupMobileId;
    }

    public void setRouteGroupMobileId(int routeGroupMobileId) {
        this.routeGroupMobileId = routeGroupMobileId;
    }

    public boolean isCheckSmartFilter() {
        return checkSmartFilter;
    }

    public void setCheckSmartFilter(boolean checkSmartFilter) {
        this.checkSmartFilter = checkSmartFilter;
    }

    public boolean isFilterCalls() {
        return filterCalls;
    }

    public void setFilterCalls(boolean filterCalls) {
        this.filterCalls = filterCalls;
    }

    public boolean isCheckDnd() {
        return checkDnd;
    }

    public void setCheckDnd(boolean checkDnd) {
        this.checkDnd = checkDnd;
    }

    public boolean isCheckBlacklist() {
        return checkBlacklist;
    }

    public void setCheckBlacklist(boolean checkBlacklist) {
        this.checkBlacklist = checkBlacklist;
    }

    public boolean isCheckDdd() {
        return checkDdd;
    }

    public void setCheckDdd(boolean checkDdd) {
        this.checkDdd = checkDdd;
    }

    public boolean isShouldCompleteFailedCall() {
        return shouldCompleteFailedCall;
    }

    public void setShouldCompleteFailedCall(boolean shouldCompleteFailedCall) {
        this.shouldCompleteFailedCall = shouldCompleteFailedCall;
    }

    public boolean isUpdateMailingData() {
        return updateMailingData;
    }

    public void setUpdateMailingData(boolean updateMailingData) {
        this.updateMailingData = updateMailingData;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public boolean isIvrAfterCallStatus() {
        return ivrAfterCallStatus;
    }

    public void setIvrAfterCallStatus(boolean ivrAfterCallStatus) {
        this.ivrAfterCallStatus = ivrAfterCallStatus;
    }

    public boolean isHorizontalDial() {
        return horizontalDial;
    }

    public void setHorizontalDial(boolean horizontalDial) {
        this.horizontalDial = horizontalDial;
    }

    public int getMinIdleTime() {
        return minIdleTime;
    }

    public void setMinIdleTime(int minIdleTime) {
        this.minIdleTime = minIdleTime;
    }

    public DialerSettings getDialerSettings() {
        return dialerSettings;
    }

    public void setDialerSettings(DialerSettings dialerSettings) {
        this.dialerSettings = dialerSettings;
    }


    @Override
    public String toString() {
        return "Campanha{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", paused=" + paused +
                ", amdEnabled=" + amdEnabled +
                ", acwTimeout=" + acwTimeout +
                ", isOnActiveTime=" + isOnActiveTime +
                ", callerId='" + callerId + '\'' +
                ", limitCallPerAgent=" + limitCallPerAgent +
                ", asr='" + asr + '\'' +
                ", uraLimit=" + uraLimit +
                ", isPredictive=" + isPredictive +
                ", agentDashboard=" + agentDashboard +
                ", activeListNotify=" + activeListNotify +
                ", allowsManual=" + allowsManual +
                ", limitCallTime=" + limitCallTime +
                ", exitManualMode=" + exitManualMode +
                ", copyIdentifier=" + copyIdentifier +
                ", ivrAfterCallId=" + ivrAfterCallId +
                ", routeLimitExceeded=" + routeLimitExceeded +
                ", routeLandlineId=" + routeLandlineId +
                ", routeMobileId=" + routeMobileId +
                ", routeGroupLandlineId=" + routeGroupLandlineId +
                ", routeGroupMobileId=" + routeGroupMobileId +
                ", checkSmartFilter=" + checkSmartFilter +
                ", filterCalls=" + filterCalls +
                ", checkDnd=" + checkDnd +
                ", checkBlacklist=" + checkBlacklist +
                ", checkDdd=" + checkDdd +
                ", shouldCompleteFailedCall=" + shouldCompleteFailedCall +
                ", updateMailingData=" + updateMailingData +
                ", behavior='" + behavior + '\'' +
                ", ivrAfterCallStatus=" + ivrAfterCallStatus +
                ", horizontalDial=" + horizontalDial +
                ", minIdleTime=" + minIdleTime +
                ", dialerSettings=" + dialerSettings +
                '}';
    }
}
