package com.example.gksls.silentapp_v2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gksls on 18.12.2017.
 */

public class Event {

    Integer event_id;
    Integer pzt_true;
    Integer sal_true;
    Integer car_true;
    Integer per_true;
    Integer cum_true;
    Integer cmt_true;
    Integer paz_true;

    Integer pzt_start;
    Integer pzt_off;
    Integer sal_start;
    Integer sal_off;
    Integer car_start;
    Integer cat_off;
    Integer per_start;
    Integer per_off;
    Integer cum_start;
    Integer cum_off;
    Integer cmt_start;
    Integer cmt_off;
    Integer paz_start;
    Integer paz_off;





    //Integer eventId_start;
    //Integer eventId_off;
    String eventName;
    Integer vibrate_start_hours;
    Integer vibrate_start_minute;
    Integer vibrate_off_hours;
    Integer vibrate_off_minute;


    public Event(Integer pzt_true, Integer sal_true, Integer car_true, Integer per_true, Integer cum_true, Integer cmt_true, Integer paz_true, Integer pzt_start, Integer pzt_off, Integer sal_start, Integer sal_off, Integer car_start, Integer cat_off, Integer per_start, Integer per_off, Integer cum_start, Integer cum_off, Integer cmt_start, Integer cmt_off, Integer paz_start, Integer paz_off, String eventName, Integer vibrate_start_hours, Integer vibrate_start_minute, Integer vibrate_off_hours, Integer vibrate_off_minute) {
        this.pzt_true = pzt_true;
        this.sal_true = sal_true;
        this.car_true = car_true;
        this.per_true = per_true;
        this.cum_true = cum_true;
        this.cmt_true = cmt_true;
        this.paz_true = paz_true;
        this.pzt_start = pzt_start;
        this.pzt_off = pzt_off;
        this.sal_start = sal_start;
        this.sal_off = sal_off;
        this.car_start = car_start;
        this.cat_off = cat_off;
        this.per_start = per_start;
        this.per_off = per_off;
        this.cum_start = cum_start;
        this.cum_off = cum_off;
        this.cmt_start = cmt_start;
        this.cmt_off = cmt_off;
        this.paz_start = paz_start;
        this.paz_off = paz_off;
        this.eventName = eventName;
        this.vibrate_start_hours = vibrate_start_hours;
        this.vibrate_start_minute = vibrate_start_minute;
        this.vibrate_off_hours = vibrate_off_hours;
        this.vibrate_off_minute = vibrate_off_minute;
    }

    public Event(Integer eventID,Integer pzt_true, Integer sal_true, Integer car_true, Integer per_true, Integer cum_true, Integer cmt_true, Integer paz_true, Integer pzt_start, Integer pzt_off, Integer sal_start, Integer sal_off, Integer car_start, Integer cat_off, Integer per_start, Integer per_off, Integer cum_start, Integer cum_off, Integer cmt_start, Integer cmt_off, Integer paz_start, Integer paz_off, String eventName, Integer vibrate_start_hours, Integer vibrate_start_minute, Integer vibrate_off_hours, Integer vibrate_off_minute ) {
        this.event_id = eventID;
        this.pzt_true = pzt_true;
        this.sal_true = sal_true;
        this.car_true = car_true;
        this.per_true = per_true;
        this.cum_true = cum_true;
        this.cmt_true = cmt_true;
        this.paz_true = paz_true;
        this.pzt_start = pzt_start;
        this.pzt_off = pzt_off;
        this.sal_start = sal_start;
        this.sal_off = sal_off;
        this.car_start = car_start;
        this.cat_off = cat_off;
        this.per_start = per_start;
        this.per_off = per_off;
        this.cum_start = cum_start;
        this.cum_off = cum_off;
        this.cmt_start = cmt_start;
        this.cmt_off = cmt_off;
        this.paz_start = paz_start;
        this.paz_off = paz_off;
        this.eventName = eventName;
        this.vibrate_start_hours = vibrate_start_hours;
        this.vibrate_start_minute = vibrate_start_minute;
        this.vibrate_off_hours = vibrate_off_hours;
        this.vibrate_off_minute = vibrate_off_minute;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Integer getPzt_true() {
        return pzt_true;
    }

    public void setPzt_true(Integer pzt_true) {
        this.pzt_true = pzt_true;
    }

    public Integer getSal_true() {
        return sal_true;
    }

    public void setSal_true(Integer sal_true) {
        this.sal_true = sal_true;
    }

    public Integer getCar_true() {
        return car_true;
    }

    public void setCar_true(Integer car_true) {
        this.car_true = car_true;
    }

    public Integer getPer_true() {
        return per_true;
    }

    public void setPer_true(Integer per_true) {
        this.per_true = per_true;
    }

    public Integer getCum_true() {
        return cum_true;
    }

    public void setCum_true(Integer cum_true) {
        this.cum_true = cum_true;
    }

    public Integer getCmt_true() {
        return cmt_true;
    }

    public void setCmt_true(Integer cmt_true) {
        this.cmt_true = cmt_true;
    }

    public Integer getPaz_true() {
        return paz_true;
    }

    public void setPaz_true(Integer paz_true) {
        this.paz_true = paz_true;
    }

    public Integer getPzt_start() {
        return pzt_start;
    }

    public void setPzt_start(Integer pzt_start) {
        this.pzt_start = pzt_start;
    }

    public Integer getPzt_off() {
        return pzt_off;
    }

    public void setPzt_off(Integer pzt_off) {
        this.pzt_off = pzt_off;
    }

    public Integer getSal_start() {
        return sal_start;
    }

    public void setSal_start(Integer sal_start) {
        this.sal_start = sal_start;
    }

    public Integer getSal_off() {
        return sal_off;
    }

    public void setSal_off(Integer sal_off) {
        this.sal_off = sal_off;
    }

    public Integer getCar_start() {
        return car_start;
    }

    public void setCar_start(Integer car_start) {
        this.car_start = car_start;
    }

    public Integer getCat_off() {
        return cat_off;
    }

    public void setCat_off(Integer cat_off) {
        this.cat_off = cat_off;
    }

    public Integer getPer_start() {
        return per_start;
    }

    public void setPer_start(Integer per_start) {
        this.per_start = per_start;
    }

    public Integer getPer_off() {
        return per_off;
    }

    public void setPer_off(Integer per_off) {
        this.per_off = per_off;
    }

    public Integer getCum_start() {
        return cum_start;
    }

    public void setCum_start(Integer cum_start) {
        this.cum_start = cum_start;
    }

    public Integer getCum_off() {
        return cum_off;
    }

    public void setCum_off(Integer cum_off) {
        this.cum_off = cum_off;
    }

    public Integer getCmt_start() {
        return cmt_start;
    }

    public void setCmt_start(Integer cmt_start) {
        this.cmt_start = cmt_start;
    }

    public Integer getCmt_off() {
        return cmt_off;
    }

    public void setCmt_off(Integer cmt_off) {
        this.cmt_off = cmt_off;
    }

    public Integer getPaz_start() {
        return paz_start;
    }

    public void setPaz_start(Integer paz_start) {
        this.paz_start = paz_start;
    }

    public Integer getPaz_off() {
        return paz_off;
    }

    public void setPaz_off(Integer paz_off) {
        this.paz_off = paz_off;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getVibrate_start_hours() {
        return vibrate_start_hours;
    }

    public void setVibrate_start_hours(Integer vibrate_start_hours) {
        this.vibrate_start_hours = vibrate_start_hours;
    }

    public Integer getVibrate_start_minute() {
        return vibrate_start_minute;
    }

    public void setVibrate_start_minute(Integer vibrate_start_minute) {
        this.vibrate_start_minute = vibrate_start_minute;
    }

    public Integer getVibrate_off_hours() {
        return vibrate_off_hours;
    }

    public void setVibrate_off_hours(Integer vibrate_off_hours) {
        this.vibrate_off_hours = vibrate_off_hours;
    }

    public Integer getVibrate_off_minute() {
        return vibrate_off_minute;
    }

    public void setVibrate_off_minute(Integer vibrate_off_minute) {
        this.vibrate_off_minute = vibrate_off_minute;
    }
}
