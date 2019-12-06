package com.magody.androidejemploplacetopay.model.place_to_pay.business_class;

import java.util.Date;

class Recurring {

    private String periodicity;
    private int interval;
    private Date nextPayment;
    private int maxPeriods;
    private Date dueDate;
    private String notificationUrl;

    public Recurring(String periodicity, int interval, Date nextPayment, int maxPeriods, Date dueDate, String notificationUrl) {
        this.periodicity = periodicity;
        this.interval = interval;
        this.nextPayment = nextPayment;
        this.maxPeriods = maxPeriods;
        this.dueDate = dueDate;
        this.notificationUrl = notificationUrl;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Date getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(Date nextPayment) {
        this.nextPayment = nextPayment;
    }

    public int getMaxPeriods() {
        return maxPeriods;
    }

    public void setMaxPeriods(int maxPeriods) {
        this.maxPeriods = maxPeriods;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    @Override
    public String toString() {
        return "Recurring{" +
                "periodicity='" + periodicity + '\'' +
                ", interval=" + interval +
                ", nextPayment=" + nextPayment +
                ", maxPeriods=" + maxPeriods +
                ", dueDate=" + dueDate +
                ", notificationUrl='" + notificationUrl + '\'' +
                '}';
    }
}
