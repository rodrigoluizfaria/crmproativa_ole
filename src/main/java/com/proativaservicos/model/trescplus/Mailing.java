package com.proativaservicos.model.trescplus;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Mailing {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("original_name")
    public String originalName;
    @SerializedName("dial")
    public int dial;
    @SerializedName("redial")
    public int redial;

    @SerializedName("answered")
    public int answered;

    @SerializedName("converted")
    public int converted;

    @SerializedName("dmc")
    public int dmc;

    @SerializedName("unknown")
    public int unknown;

    @SerializedName("total")
    public int total;

    @SerializedName("dialed")
    public int dialed;

    @SerializedName("dialed_percentage")
    public double dialedPercentage;

    @SerializedName("completed")
    public int completed;

    @SerializedName("completed_percentage")
    public double completedPercentage;

    @SerializedName("asr")
    public double asr;

    @SerializedName("asr_percentage")
    public double asrPercentage;

    @SerializedName("answered_percentage")
    public double answeredPercentage;

    @SerializedName("weight")
    public int weight;

    @SerializedName("dial_percentage")
    public double dialPercentage;

    @SerializedName("redial_percentage")
    public double redialPercentage;

    @SerializedName("converted_percentage")
    public double convertedPercentage;

    @SerializedName("dmc_percentage")
    public double dmcPercentage;

    @SerializedName("unknown_percentage")
    public int unknownPercentage;

    @SerializedName("failed")
    public int failed;

    @SerializedName("not_answered")
    public int notAnswered;

    @SerializedName("not_answered_due_progress_amd")
    public int notAnsweredDueProgressAmd;

    @SerializedName("mailbox")
    public int mailbox;

    @SerializedName("abandoned")
    public int abandoned;

    @SerializedName("failed_percentage")
    public double failedPercentage;

    @SerializedName("not_answered_percentage")
    public double notAnsweredPercentage;

    @SerializedName("not_answered_due_progress_amd_percentage")
    public double notAnsweredDueProgressAmdPercentage;

    @SerializedName("mailbox_percentage")
    public double mailboxPercentage;

    @SerializedName("abandoned_percentage")
    public double abandonedPercentage;

    @SerializedName("ura_name")
    public String uraName;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("importing")
    public boolean importing;

    @SerializedName("recycle_process")
    public boolean recycleProcess;

    @SerializedName("recycled_times")
    public int recycledTimes;

    @SerializedName("recycle_filters")
    public ArrayList<Object> recycleFilters;

    @SerializedName("pro_mode")
    public boolean proMode;

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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public int getDial() {
        return dial;
    }

    public void setDial(int dial) {
        this.dial = dial;
    }

    public int getRedial() {
        return redial;
    }

    public void setRedial(int redial) {
        this.redial = redial;
    }

    public int getAnswered() {
        return answered;
    }

    public void setAnswered(int answered) {
        this.answered = answered;
    }

    public int getConverted() {
        return converted;
    }

    public void setConverted(int converted) {
        this.converted = converted;
    }

    public int getDmc() {
        return dmc;
    }

    public void setDmc(int dmc) {
        this.dmc = dmc;
    }

    public int getUnknown() {
        return unknown;
    }

    public void setUnknown(int unknown) {
        this.unknown = unknown;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDialed() {
        return dialed;
    }

    public void setDialed(int dialed) {
        this.dialed = dialed;
    }

    public double getDialedPercentage() {
        return dialedPercentage;
    }

    public void setDialedPercentage(double dialedPercentage) {
        this.dialedPercentage = dialedPercentage;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public double getCompletedPercentage() {
        return completedPercentage;
    }

    public void setCompletedPercentage(double completedPercentage) {
        this.completedPercentage = completedPercentage;
    }

    public double getAsr() {
        return asr;
    }

    public void setAsr(double asr) {
        this.asr = asr;
    }

    public double getAsrPercentage() {
        return asrPercentage;
    }

    public void setAsrPercentage(double asrPercentage) {
        this.asrPercentage = asrPercentage;
    }

    public double getAnsweredPercentage() {
        return answeredPercentage;
    }

    public void setAnsweredPercentage(double answeredPercentage) {
        this.answeredPercentage = answeredPercentage;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getDialPercentage() {
        return dialPercentage;
    }

    public void setDialPercentage(double dialPercentage) {
        this.dialPercentage = dialPercentage;
    }

    public double getRedialPercentage() {
        return redialPercentage;
    }

    public void setRedialPercentage(double redialPercentage) {
        this.redialPercentage = redialPercentage;
    }

    public double getConvertedPercentage() {
        return convertedPercentage;
    }

    public void setConvertedPercentage(double convertedPercentage) {
        this.convertedPercentage = convertedPercentage;
    }

    public double getDmcPercentage() {
        return dmcPercentage;
    }

    public void setDmcPercentage(double dmcPercentage) {
        this.dmcPercentage = dmcPercentage;
    }

    public int getUnknownPercentage() {
        return unknownPercentage;
    }

    public void setUnknownPercentage(int unknownPercentage) {
        this.unknownPercentage = unknownPercentage;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getNotAnswered() {
        return notAnswered;
    }

    public void setNotAnswered(int notAnswered) {
        this.notAnswered = notAnswered;
    }

    public int getNotAnsweredDueProgressAmd() {
        return notAnsweredDueProgressAmd;
    }

    public void setNotAnsweredDueProgressAmd(int notAnsweredDueProgressAmd) {
        this.notAnsweredDueProgressAmd = notAnsweredDueProgressAmd;
    }

    public int getMailbox() {
        return mailbox;
    }

    public void setMailbox(int mailbox) {
        this.mailbox = mailbox;
    }

    public int getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(int abandoned) {
        this.abandoned = abandoned;
    }

    public double getFailedPercentage() {
        return failedPercentage;
    }

    public void setFailedPercentage(double failedPercentage) {
        this.failedPercentage = failedPercentage;
    }

    public double getNotAnsweredPercentage() {
        return notAnsweredPercentage;
    }

    public void setNotAnsweredPercentage(double notAnsweredPercentage) {
        this.notAnsweredPercentage = notAnsweredPercentage;
    }

    public double getNotAnsweredDueProgressAmdPercentage() {
        return notAnsweredDueProgressAmdPercentage;
    }

    public void setNotAnsweredDueProgressAmdPercentage(double notAnsweredDueProgressAmdPercentage) {
        this.notAnsweredDueProgressAmdPercentage = notAnsweredDueProgressAmdPercentage;
    }

    public double getMailboxPercentage() {
        return mailboxPercentage;
    }

    public void setMailboxPercentage(double mailboxPercentage) {
        this.mailboxPercentage = mailboxPercentage;
    }

    public double getAbandonedPercentage() {
        return abandonedPercentage;
    }

    public void setAbandonedPercentage(double abandonedPercentage) {
        this.abandonedPercentage = abandonedPercentage;
    }

    public String getUraName() {
        return uraName;
    }

    public void setUraName(String uraName) {
        this.uraName = uraName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isImporting() {
        return importing;
    }

    public void setImporting(boolean importing) {
        this.importing = importing;
    }

    public boolean isRecycleProcess() {
        return recycleProcess;
    }

    public void setRecycleProcess(boolean recycleProcess) {
        this.recycleProcess = recycleProcess;
    }

    public int getRecycledTimes() {
        return recycledTimes;
    }

    public void setRecycledTimes(int recycledTimes) {
        this.recycledTimes = recycledTimes;
    }

    public ArrayList<Object> getRecycleFilters() {
        return recycleFilters;
    }

    public void setRecycleFilters(ArrayList<Object> recycleFilters) {
        this.recycleFilters = recycleFilters;
    }

    public boolean isProMode() {
        return proMode;
    }

    public void setProMode(boolean proMode) {
        this.proMode = proMode;
    }

    @Override
    public String toString() {
        return "MailingResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", dial=" + dial +
                ", redial=" + redial +
                ", answered=" + answered +
                ", converted=" + converted +
                ", dmc=" + dmc +
                ", unknown=" + unknown +
                ", total=" + total +
                ", dialed=" + dialed +
                ", dialedPercentage=" + dialedPercentage +
                ", completed=" + completed +
                ", completedPercentage=" + completedPercentage +
                ", asr=" + asr +
                ", asrPercentage=" + asrPercentage +
                ", answeredPercentage=" + answeredPercentage +
                ", weight=" + weight +
                ", dialPercentage=" + dialPercentage +
                ", redialPercentage=" + redialPercentage +
                ", convertedPercentage=" + convertedPercentage +
                ", dmcPercentage=" + dmcPercentage +
                ", unknownPercentage=" + unknownPercentage +
                ", failed=" + failed +
                ", notAnswered=" + notAnswered +
                ", notAnsweredDueProgressAmd=" + notAnsweredDueProgressAmd +
                ", mailbox=" + mailbox +
                ", abandoned=" + abandoned +
                ", failedPercentage=" + failedPercentage +
                ", notAnsweredPercentage=" + notAnsweredPercentage +
                ", notAnsweredDueProgressAmdPercentage=" + notAnsweredDueProgressAmdPercentage +
                ", mailboxPercentage=" + mailboxPercentage +
                ", abandonedPercentage=" + abandonedPercentage +
                ", uraName='" + uraName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", importing=" + importing +
                ", recycleProcess=" + recycleProcess +
                ", recycledTimes=" + recycledTimes +
                ", recycleFilters=" + recycleFilters +
                ", proMode=" + proMode +
                '}';
    }
}
