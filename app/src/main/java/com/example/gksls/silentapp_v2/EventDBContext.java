package com.example.gksls.silentapp_v2;

/**
 * Created by gksls on 20.12.2017.
 *
 *     Integer event_id;
 Integer eventId_start;
 Integer eventId_off;
 String eventName;
 Integer vibrate_start_hours;
 Integer vibrate_start_minute;
 Integer vibrate_off_hours;
 Integer vibrate_off_minute;
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



public class EventDBContext extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "eventss.db";

    private static final String TABLE_NAME = "EVENTS";
    private static final String COLUMN_NAME_EVENT_ID = "EVENTID";
    private static final String COLUMN_NAME_PZTT = "PZTT";
    private static final String COLUMN_NAME_SALT = "SALT";
    private static final String COLUMN_NAME_CART = "CART";
    private static final String COLUMN_NAME_PERT = "PERT";
    private static final String COLUMN_NAME_CUMT = "CUMT";
    private static final String COLUMN_NAME_CMTT = "CMTT";
    private static final String COLUMN_NAME_PAZT = "PAZT";
    private static final String COLUMN_NAME_PZT_START = "PZT_START";
    private static final String COLUMN_NAME_PZT_OFF = "PZT_OFF";
    private static final String COLUMN_NAME_SAL_START = "SAL_START";
    private static final String COLUMN_NAME_SAL_OFF = "SAL_OFF";
    private static final String COLUMN_NAME_CAR_START = "CAR_START";
    private static final String COLUMN_NAME_CAR_OFF = "CAR_OFF";
    private static final String COLUMN_NAME_PER_START = "PER_START";
    private static final String COLUMN_NAME_PER_OFF = "PER_OFF";
    private static final String COLUMN_NAME_CUM_START = "CUM_START";
    private static final String COLUMN_NAME_CUM_OFF = "CUM_OFF";
    private static final String COLUMN_NAME_CMT_START = "CMT_START";
    private static final String COLUMN_NAME_CMT_OFF = "CMT_OFF";
    private static final String COLUMN_NAME_PAZ_START = "PAZ_START";
    private static final String COLUMN_NAME_PAZ_OFF = "PAZ_OFF";


    private static final String COLUMN_NAME_EVENT_NAME = "ENAME";
    private static final String COLUMN_NAME_VIBSTART_H = "STARTH";
    private static final String COLUMN_NAME_VIBSTART_M = "STARTM";
    private static final String COLUMN_NAME_VIBOFF_H = "OFFH";
    private static final String COLUMN_NAME_VIBOFF_M = "OFFM";


    public EventDBContext(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        CREATE TABLE IF NOT EXISTS EMPLOYEE (
//                ID      INTEGER PRIMARY KEY AUTOINCREMENT,
//                NAME    TEXT,
//                SURNAME TEXT,
//                AGE     INTEGER,
//                SALARY  DOUBLE,
//                GENDER  TEXT
//        );

        String create_table_command = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                COLUMN_NAME_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_PZTT + " INTEGER, " +
                COLUMN_NAME_SALT + " INTEGER, " +
                COLUMN_NAME_CART + " INTEGER, " +
                COLUMN_NAME_PERT + " INTEGER, " +
                COLUMN_NAME_CUMT + " INTEGER, " +
                COLUMN_NAME_CMTT + " INTEGER, " +
                COLUMN_NAME_PAZT + " INTEGER, " +
                COLUMN_NAME_PZT_START + " INTEGER, " +
                COLUMN_NAME_PZT_OFF + " INTEGER, " +
                COLUMN_NAME_SAL_START + " INTEGER, " +
                COLUMN_NAME_SAL_OFF + " INTEGER, " +
                COLUMN_NAME_CAR_START + " INTEGER, " +
                COLUMN_NAME_CAR_OFF + " INTEGER, " +
                COLUMN_NAME_PER_START + " INTEGER, " +
                COLUMN_NAME_PER_OFF + " INTEGER, " +
                COLUMN_NAME_CUM_START + " INTEGER, " +
                COLUMN_NAME_CUM_OFF + " INTEGER, " +
                COLUMN_NAME_CMT_START + " INTEGER, " +
                COLUMN_NAME_CMT_OFF + " INTEGER, " +
                COLUMN_NAME_PAZ_START + " INTEGER, " +
                COLUMN_NAME_PAZ_OFF + " INTEGER, " +
                COLUMN_NAME_EVENT_NAME  +  " TEXT, " +
                COLUMN_NAME_VIBSTART_H + " INTEGER, " +
                COLUMN_NAME_VIBSTART_M + " INTEGER, " +
                COLUMN_NAME_VIBOFF_H+ " INTEGER, " +
                COLUMN_NAME_VIBOFF_M + " INTEGER " +
                " ); ";

        db.execSQL(create_table_command);
    }


    public void AddEvent(Event newEvent){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_PZTT, newEvent.getPzt_true() );
        values.put(COLUMN_NAME_SALT, newEvent.getSal_true() );
        values.put(COLUMN_NAME_CART, newEvent.getCar_true() );
        values.put(COLUMN_NAME_PERT, newEvent.getPer_true() );
        values.put(COLUMN_NAME_CUMT, newEvent.getCum_true() );
        values.put(COLUMN_NAME_CMTT, newEvent.getCmt_true() );
        values.put(COLUMN_NAME_PAZT, newEvent.getPaz_true() );
        values.put(COLUMN_NAME_PZT_START, newEvent.getPzt_start() );
        values.put(COLUMN_NAME_PZT_OFF, newEvent.getPzt_off() );
        values.put(COLUMN_NAME_SAL_START, newEvent.getSal_start() );
        values.put(COLUMN_NAME_SAL_OFF, newEvent.getSal_off() );
        values.put(COLUMN_NAME_CAR_START, newEvent.getCar_start() );
        values.put(COLUMN_NAME_CAR_OFF, newEvent.getCat_off() );
        values.put(COLUMN_NAME_PER_START, newEvent.getPer_start() );
        values.put(COLUMN_NAME_PER_OFF, newEvent.getPer_off() );
        values.put(COLUMN_NAME_CUM_START, newEvent.getCum_start());
        values.put(COLUMN_NAME_CUM_OFF, newEvent.getCum_off() );
        values.put(COLUMN_NAME_CMT_START, newEvent.getCmt_start() );
        values.put(COLUMN_NAME_CMT_OFF, newEvent.getCmt_off() );
        values.put(COLUMN_NAME_PAZ_START, newEvent.getPaz_start() );
        values.put(COLUMN_NAME_PAZ_OFF, newEvent.getPaz_off() );
        values.put(COLUMN_NAME_EVENT_NAME, newEvent.getEventName());
        values.put(COLUMN_NAME_VIBSTART_H, newEvent.getVibrate_start_hours());
        values.put(COLUMN_NAME_VIBSTART_M, newEvent.getVibrate_start_minute() );
        values.put(COLUMN_NAME_VIBOFF_H, newEvent.getVibrate_off_hours() );
        values.put(COLUMN_NAME_VIBOFF_M, newEvent.getVibrate_off_minute() );


        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void DeleteEvent(Event deleteEvent){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_NAME_EVENT_ID + " = ? ", new String[] { deleteEvent.getEvent_id().toString() } );
        db.close();
    }

    public void UpdateEvent(Event updateEvent){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_PZTT, updateEvent.getPzt_true() );
        values.put(COLUMN_NAME_SALT, updateEvent.getSal_true() );
        values.put(COLUMN_NAME_CART, updateEvent.getCar_true() );
        values.put(COLUMN_NAME_PERT, updateEvent.getPer_true() );
        values.put(COLUMN_NAME_CUMT, updateEvent.getCum_true() );
        values.put(COLUMN_NAME_CMTT, updateEvent.getCmt_true() );
        values.put(COLUMN_NAME_PAZT, updateEvent.getPaz_true() );
        values.put(COLUMN_NAME_PZT_START, updateEvent.getPzt_start() );
        values.put(COLUMN_NAME_PZT_OFF, updateEvent.getPzt_off() );
        values.put(COLUMN_NAME_SAL_START, updateEvent.getSal_start());
        values.put(COLUMN_NAME_SAL_OFF, updateEvent.getSal_off() );
        values.put(COLUMN_NAME_CAR_START, updateEvent.getCar_start());
        values.put(COLUMN_NAME_CAR_OFF, updateEvent.getCat_off() );
        values.put(COLUMN_NAME_PER_START, updateEvent.getPer_start());
        values.put(COLUMN_NAME_PER_OFF, updateEvent.getPer_off() );
        values.put(COLUMN_NAME_CUM_START, updateEvent.getCum_start());
        values.put(COLUMN_NAME_CUM_OFF, updateEvent.getCum_off() );
        values.put(COLUMN_NAME_CMT_START, updateEvent.getCmt_start());
        values.put(COLUMN_NAME_CMT_OFF, updateEvent.getCmt_off() );
        values.put(COLUMN_NAME_PAZ_START, updateEvent.getPaz_start());
        values.put(COLUMN_NAME_PAZ_OFF, updateEvent.getPaz_off() );
        values.put(COLUMN_NAME_EVENT_NAME, updateEvent.getEventName());
        values.put(COLUMN_NAME_VIBSTART_H, updateEvent.getVibrate_start_hours());
        values.put(COLUMN_NAME_VIBSTART_M, updateEvent.getVibrate_start_minute() );
        values.put(COLUMN_NAME_VIBOFF_H, updateEvent.getVibrate_off_hours() );
        values.put(COLUMN_NAME_VIBOFF_M, updateEvent.getVibrate_off_minute() );


        db.update(TABLE_NAME,values, COLUMN_NAME_EVENT_ID + " = ? ", new String[] { updateEvent.getEvent_id().toString() });

        db.close();
    }

    public Event GetEvent(Long id){
        //SELECT * FROM TABLE_NAME WHERE ID = id
        String select_command = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_EVENT_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Event event = null;

        if(cursor.moveToFirst()){
            Integer eventID  = cursor.getInt( cursor.getColumnIndex(COLUMN_NAME_EVENT_ID) );
            Integer pzt_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PZTT));
            Integer sal_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SALT));
            Integer car_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CART));
            Integer per_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PERT));
            Integer cum_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CUMT));
            Integer cmt_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CMTT));
            Integer paz_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PAZT));

            Integer pzt_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PZT_START));
            Integer pzt_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PZT_OFF));
            Integer sal_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SAL_START));
            Integer sal_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SAL_OFF));
            Integer car_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CAR_START));
            Integer car_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CAR_OFF));
            Integer per_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PER_START));
            Integer per_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PER_OFF));
            Integer cum_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CUM_START));
            Integer cum_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CUM_OFF));
            Integer cmt_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CMT_START));
            Integer cmt_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CMT_OFF));
            Integer paz_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PAZ_START));
            Integer paz_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PAZ_OFF));
            String eventname = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EVENT_NAME));
            Integer vib_starth = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBSTART_H));
            Integer vib_startm = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBSTART_M));
            Integer vib_offh = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBOFF_H));
            Integer vib_offm = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBOFF_M));

            event = new Event(eventID,pzt_t,sal_t,car_t,per_t,cum_t,cmt_t,paz_t,pzt_start,pzt_off,sal_start,sal_off,car_start,car_off,per_start,per_off,cum_start,cum_off,cmt_start,cmt_off,paz_start,paz_off,eventname,vib_starth,vib_startm,vib_offh,vib_offm);
        }

        cursor.close();
        db.close();


        return event;


    }

    public ArrayList<Event> GetAllEvents(){

        //SELECT * FROM TABLE_NAME
        String select_all_command = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(select_all_command,null);

        ArrayList<Event> eventList = new ArrayList<>();

        while(cursor.moveToNext()){

            Integer eventID  = cursor.getInt( cursor.getColumnIndex(COLUMN_NAME_EVENT_ID) );
            Integer pzt_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PZTT));
            Integer sal_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SALT));
            Integer car_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CART));
            Integer per_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PERT));
            Integer cum_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CUMT));
            Integer cmt_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CMTT));
            Integer paz_t = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PAZT));

            Integer pzt_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PZT_START));
            Integer pzt_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PZT_OFF));
            Integer sal_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SAL_START));
            Integer sal_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SAL_OFF));
            Integer car_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CAR_START));
            Integer car_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CAR_OFF));
            Integer per_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PER_START));
            Integer per_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PER_OFF));
            Integer cum_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CUM_START));
            Integer cum_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CUM_OFF));
            Integer cmt_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CMT_START));
            Integer cmt_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_CMT_OFF));
            Integer paz_start = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PAZ_START));
            Integer paz_off = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PAZ_OFF));
            String eventname = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EVENT_NAME));
            Integer vib_starth = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBSTART_H));
            Integer vib_startm = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBSTART_M));
            Integer vib_offh = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBOFF_H));
            Integer vib_offm = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_VIBOFF_M));

            Event event = new Event(eventID,pzt_t,sal_t,car_t,per_t,cum_t,cmt_t,paz_t,pzt_start,pzt_off,sal_start,sal_off,car_start,car_off,per_start,per_off,cum_start,cum_off,cmt_start,cmt_off,paz_start,paz_off,eventname,vib_starth,vib_startm,vib_offh,vib_offm);

            eventList.add(event);
        }

        cursor.close();
        db.close();

        return eventList;
    }

    public void RemoveAllEvents(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);

        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}