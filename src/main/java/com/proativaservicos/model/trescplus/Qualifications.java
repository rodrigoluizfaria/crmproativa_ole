package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proativaservicos.util.DateUtil;

import java.util.Date;

public class Qualifications {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("behavior")
    public int behavior;

    @SerializedName("campaign_id")
    public int campaignId;

    @SerializedName("created_at")
    public Date createdAt;

    @SerializedName("updated_at")
    public Date updatedAt;

    @SerializedName("list_id")
    public int listId;

    @SerializedName("pagination")
    public String emoji;

    @SerializedName("color")
    public String color;

    @SerializedName("conversion")
    public boolean conversion;

    @SerializedName("order")
    public int order;

    @SerializedName("allow_schedule")
    public boolean allowSchedule;

    @SerializedName("allow_schedule_to_another_number")
    public boolean allowScheduleToAnotherNumber;

    @SerializedName("days_limit")
    public int daysLimit;

    @SerializedName("dmc")
    public boolean dmc;

    @SerializedName("deleted_at")
    public Date deletedAt;

    @SerializedName("should_insert_blacklist")
    public boolean shouldInsertBlacklist;

    @SerializedName("unknown")
    public boolean unknown;

    @Expose
    public Date dataAgendamento;
    @SerializedName("blacklist_blocking_days")
    public String blacklistBlockingDays;

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

    public int getBehavior() {
        return behavior;
    }

    public void setBehavior(int behavior) {
        this.behavior = behavior;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isConversion() {
        return conversion;
    }

    public void setConversion(boolean conversion) {
        this.conversion = conversion;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isAllowSchedule() {
        return allowSchedule;
    }

    public void setAllowSchedule(boolean allowSchedule) {
        this.allowSchedule = allowSchedule;
    }

    public boolean isAllowScheduleToAnotherNumber() {
        return allowScheduleToAnotherNumber;
    }

    public void setAllowScheduleToAnotherNumber(boolean allowScheduleToAnotherNumber) {
        this.allowScheduleToAnotherNumber = allowScheduleToAnotherNumber;
    }

    public int getDaysLimit() {
        return daysLimit;
    }

    public void setDaysLimit(int daysLimit) {
        this.daysLimit = daysLimit;
    }

    public boolean isDmc() {
        return dmc;
    }

    public void setDmc(boolean dmc) {
        this.dmc = dmc;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isShouldInsertBlacklist() {
        return shouldInsertBlacklist;
    }

    public void setShouldInsertBlacklist(boolean shouldInsertBlacklist) {
        this.shouldInsertBlacklist = shouldInsertBlacklist;
    }

    public boolean isUnknown() {
        return unknown;
    }

    public void setUnknown(boolean unknown) {
        this.unknown = unknown;
    }

    public String getBlacklistBlockingDays() {
        return blacklistBlockingDays;
    }

    public void setBlacklistBlockingDays(String blacklistBlockingDays) {
        this.blacklistBlockingDays = blacklistBlockingDays;
    }

    public String getDataAgendamento() {
        if (this.dataAgendamento != null)
            return DateUtil.builder(dataAgendamento).formatarDataParaString("yyyy-MM-dd HH:mm:ss").getDataTexto();
        return null;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    @Override
    public String toString() {
        return "Qualifications{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", behavior=" + behavior +
                ", campaignId=" + campaignId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", listId=" + listId +
                ", emoji='" + emoji + '\'' +
                ", color='" + color + '\'' +
                ", conversion=" + conversion +
                ", order=" + order +
                ", allowSchedule=" + allowSchedule +
                ", allowScheduleToAnotherNumber=" + allowScheduleToAnotherNumber +
                ", daysLimit=" + daysLimit +
                ", dmc=" + dmc +
                ", deletedAt=" + deletedAt +
                ", shouldInsertBlacklist=" + shouldInsertBlacklist +
                ", unknown=" + unknown +
                ", blacklistBlockingDays='" + blacklistBlockingDays + '\'' +
                '}';
    }
}
