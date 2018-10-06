package com.example.gksls.silentapp_v2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;



public class MainActivity extends AppCompatActivity {

    ArrayList<Event> events = new ArrayList<>();

    EventAdaptor eventAdaptor;

    EventDBContext eventDBContext;

    EditText etName, etSurname, etAge, etSalary, etGpa, etEmail;
    Spinner spGender;

    Button btnDelete, btnUpdate,btnAdd;
    AlarmManager alarmManagerON;
    AlarmManager alarmManagerON1,alarmManagerON2;
    Intent intent,intent2,intent3,intent4,intent5,intent6,intent7,intent8,intent9,intent10,intent11,intent12,intent13,intent14;
    PendingIntent pendingIntent,pendingIntent2,pendingIntent3,pendingIntent4,pendingIntent5,pendingIntent6,pendingIntent7,pendingIntent8,pendingIntent9,pendingIntent10,pendingIntent11,pendingIntent12,pendingIntent13,pendingIntent14;
    AlarmManager alarmManagerOFF;
    AlarmManager alarmManagerOFF1,alarmManagerOFF2;


    int start_hour;
    int start_minute;
    int off_hour;
    int off_minute;

    ListView lvEvents;
    int selectedEventPosition = 0;
    int s_id,of_id;
    int pzt_s_id,pzt_of_id,sal_s_id,sal_of_id,car_s_id,car_of_id,per_s_id,per_of_id,cum_s_id,cum_of_id,cmt_s_id,cmt_of_id,paz_s_id,paz_of_id;
    int pzt_t,sal_t,car_t,per_t,cum_t,cmt_t,paz_t;

    Button btnON,btnOFF;
    Button custumEkle,customGeri;
    Button mShowDialog,btnPicker,btnPicker2;
    ImageButton mEkle,getmVazgec,mGuncelle;

    View mView;


    AlertDialog dialog;


    CheckBox pzt,sal,car,per,cum,cmt,paz;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowDialog=(Button) findViewById(R.id.btnqq);
        //btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        lvEvents = (ListView) findViewById(R.id.lvEmployees);







        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
         mView =getLayoutInflater().inflate(R.layout.custom,null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();

        etName=(EditText)mView.findViewById(R.id.eventAdi);
        ImageButton mEkle=(ImageButton) mView.findViewById(R.id.buttonEkle);
        ImageButton mVazgec=(ImageButton) mView.findViewById(R.id.buttonVazgec);
        ImageButton mGuncelle=(ImageButton)mView.findViewById(R.id.buttonGuncelle);
        mEkle.setFocusable(false);
        mVazgec.setFocusable(false);


        pzt= (CheckBox) mView.findViewById(R.id.checkBox77);
        sal= (CheckBox) mView.findViewById(R.id.checkBox66);
        car= (CheckBox) mView.findViewById(R.id.checkBox55);
        per= (CheckBox) mView.findViewById(R.id.checkBox44);
        cum= (CheckBox) mView.findViewById(R.id.checkBox33);
        cmt= (CheckBox) mView.findViewById(R.id.checkBox22);
        paz= (CheckBox) mView.findViewById(R.id.checkBox11);
        btnPicker=(Button)mView.findViewById(R.id.btnON_cus);
        btnPicker2=(Button)mView.findViewById(R.id.btnOFF_cus);




        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                selectedEventPosition = position;
                updateBoxesBySelectedEvent();


            }
        });




        this.eventDBContext = new EventDBContext(getApplicationContext());

        this.updateListViewContent();




    }







    public void closeDialog (View view){

        dialog.dismiss();
    }
    public void dialogShow (View view){

        dialog.show();
    }

    public void btnOnfunction(View view) {


        final Calendar c = Calendar.getInstance();

            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {




                    btnPicker.setText(selectedHour + ":" + selectedMinute);
                    start_hour=selectedHour;
                    start_minute=selectedMinute;

                }
            },c.get(Calendar.HOUR_OF_DAY) , c.get(Calendar.MINUTE), true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        }


          public void btnOfffunction(View view)
          {

              final Calendar c = Calendar.getInstance();

            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener()
            {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                {

                    btnPicker2.setText(selectedHour + ":" + selectedMinute);
                    off_hour=selectedHour;
                    off_minute=selectedMinute;


                }
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        }




   public class DayResult {
        int start1;
        int off1;

        public DayResult (int start1,int off1){
            this.start1=start1;
            this.off1=off1;
        }

    public int getStart1(){
            return start1;
    }
    public int getOff1(){
        return off1;
    }


    }

    public static class IdGenerator {
        public static int generateUniqueId() {
            UUID idOne = UUID.randomUUID();
            int idOne1 = (int) idOne.getLeastSignificantBits();
            String str=""+idOne1;
            int uid=str.hashCode();
            String filterStr=""+uid;
            str=filterStr.replaceAll("-", "");
            return Integer.parseInt(str);
        }

    }

    private void updateListViewContent(){
        events = eventDBContext.GetAllEvents();

        eventAdaptor = new EventAdaptor(this,events);

        lvEvents.setAdapter(eventAdaptor);
    }

    public void BtnAddClick(View view) {







        //Integer eventId_start = etName.getText().toString();
        //Integer eventId_off = etSurname.getText().toString();
        String eventName = etName.getText().toString();
        Integer vibrate_start_hours = start_hour;
        Integer vibrate_start_minute = start_minute;
        Integer vibrate_off_hours = off_hour;
        Integer vibrate_off_minute = off_minute;



        if(pzt.isChecked()){
            pzt_t=1;

            //start
            Calendar calendar7 = Calendar.getInstance();
            calendar7.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar7.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar7.set(Calendar.MINUTE, start_minute);
            calendar7.set(Calendar.SECOND, 0);
            if (calendar7.getTimeInMillis() < System.currentTimeMillis()) {
                calendar7.add(Calendar.DAY_OF_YEAR, 7);
            }

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(getBaseContext(), AlarmReceiverON.class);
            //s_id = (int) System.currentTimeMillis();//
            pzt_s_id=IdGenerator.generateUniqueId();
            pendingIntent = PendingIntent.getBroadcast(getBaseContext(),pzt_s_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar7.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent);

            //off
            calendar7.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar7.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar7.set(Calendar.MINUTE, off_minute);
            calendar7.set(Calendar.SECOND, 0);
            if (calendar7.getTimeInMillis() < System.currentTimeMillis()) {
                calendar7.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            pzt_of_id = IdGenerator.generateUniqueId();//iptal etmede bu id uzerinden gerceklesecek
            pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(),pzt_of_id , intent2, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar7.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent2);



        /*DayResult dpzt=setAlarm(Calendar.MONDAY);
        pzt_s_id=dpzt.getStart1();
        pzt_of_id=dpzt.getOff1();
        pzt_t=1;
         */

        } else{
            pzt_t=0;
        }

        if(sal.isChecked()){
            sal_t=1;
            //start
            Calendar calendar6 = Calendar.getInstance();
            calendar6.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
            calendar6.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar6.set(Calendar.MINUTE, start_minute);
            calendar6.set(Calendar.SECOND, 0);
            if (calendar6.getTimeInMillis() < System.currentTimeMillis()) {
                calendar6.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent3 = new Intent(getBaseContext(), AlarmReceiverON.class);
            sal_s_id = IdGenerator.generateUniqueId();
            pendingIntent3 = PendingIntent.getBroadcast(getBaseContext(),sal_s_id, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar6.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent3);

            //off
            calendar6.set(Calendar.DAY_OF_WEEK,  Calendar.TUESDAY);
            calendar6.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar6.set(Calendar.MINUTE, off_minute);
            calendar6.set(Calendar.SECOND, 0);
            if (calendar6.getTimeInMillis() < System.currentTimeMillis()) {
                calendar6.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent4 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            sal_of_id =IdGenerator.generateUniqueId();
            pendingIntent4 = PendingIntent.getBroadcast(getBaseContext(),sal_of_id, intent4, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar6.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent4);
/*
            DayResult dsal=setAlarm(Calendar.TUESDAY);
            sal_s_id=dsal.getStart1();
            sal_of_id=dsal.getOff1();
            sal_t=1;
            */

        }else{
            sal_t=0;
        }

        if(car.isChecked()){
            car_t=1;
            //start
            Calendar calendar5 = Calendar.getInstance();
            calendar5.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
            calendar5.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar5.set(Calendar.MINUTE, start_minute);
            calendar5.set(Calendar.SECOND, 0);
            if (calendar5.getTimeInMillis() < System.currentTimeMillis()) {
                calendar5.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent5 = new Intent(getBaseContext(), AlarmReceiverON.class);
            car_s_id = IdGenerator.generateUniqueId();
            pendingIntent5 = PendingIntent.getBroadcast(getBaseContext(),car_s_id, intent5, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar5.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent5);

            //off
            calendar5.set(Calendar.DAY_OF_WEEK,  Calendar.WEDNESDAY);
            calendar5.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar5.set(Calendar.MINUTE, off_minute);
            calendar5.set(Calendar.SECOND, 0);
            if (calendar5.getTimeInMillis() < System.currentTimeMillis()) {
                calendar5.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent6 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            car_of_id = IdGenerator.generateUniqueId();
            pendingIntent6= PendingIntent.getBroadcast(getBaseContext(),car_of_id, intent6, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar5.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent6);
/*
            DayResult dcar=setAlarm(Calendar.WEDNESDAY);
            car_s_id=dcar.getStart1();
            car_of_id=dcar.getOff1();
            car_t=1;
            */

        }else{
            car_t=0;
        }

        if(per.isChecked()){
            per_t=1;
            //start
            Calendar calendar4 = Calendar.getInstance();
            calendar4.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
            calendar4.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar4.set(Calendar.MINUTE, start_minute);
            calendar4.set(Calendar.SECOND, 0);
            if (calendar4.getTimeInMillis() < System.currentTimeMillis()) {
                calendar4.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent7 = new Intent(getBaseContext(), AlarmReceiverON.class);
            per_s_id = IdGenerator.generateUniqueId();
            pendingIntent7 = PendingIntent.getBroadcast(getBaseContext(),per_s_id, intent7, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent7);

            //off
            calendar4.set(Calendar.DAY_OF_WEEK,  Calendar.THURSDAY);
            calendar4.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar4.set(Calendar.MINUTE, off_minute);
            calendar4.set(Calendar.SECOND, 0);
            if (calendar4.getTimeInMillis() < System.currentTimeMillis()) {
                calendar4.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent8 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            per_of_id = IdGenerator.generateUniqueId();
            pendingIntent8= PendingIntent.getBroadcast(getBaseContext(),per_of_id, intent8, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent8);
/*
            DayResult dper=setAlarm(Calendar.THURSDAY);
            per_s_id=dper.getStart1();
            per_of_id=dper.getOff1();
            per_t=1;
*/
        }else{
            per_t=0;
        }

        if(cum.isChecked()){
            cum_t=1;
            //start
            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            calendar3.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar3.set(Calendar.MINUTE, start_minute);
            calendar3.set(Calendar.SECOND, 0);
            if (calendar3.getTimeInMillis() < System.currentTimeMillis()) {
                calendar3.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent9 = new Intent(getBaseContext(), AlarmReceiverON.class);
            cum_s_id =IdGenerator.generateUniqueId();

                    //(int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
            pendingIntent9 = PendingIntent.getBroadcast(getBaseContext(),cum_s_id, intent9, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent9);

            //off
            calendar3.set(Calendar.DAY_OF_WEEK,  Calendar.FRIDAY);
            calendar3.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar3.set(Calendar.MINUTE, off_minute);
            calendar3.set(Calendar.SECOND, 0);
            if (calendar3.getTimeInMillis() < System.currentTimeMillis()) {
                calendar3.add(Calendar.DAY_OF_YEAR, 7);
            }

            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent10 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            cum_of_id =IdGenerator.generateUniqueId();
                    //(int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
            pendingIntent10= PendingIntent.getBroadcast(getBaseContext(),cum_of_id, intent10, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent10);


/*
            DayResult dcum=setAlarm(Calendar.FRIDAY);
            cum_s_id=dcum.getStart1();
            cum_of_id=dcum.getOff1();
            cum_t=1;
            */

        }else{
            cum_t=0;
        }

        if(cmt.isChecked()){
            cmt_t=1;
            //start
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            calendar2.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar2.set(Calendar.MINUTE, start_minute);
            calendar2.set(Calendar.SECOND, 0);
            if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
                calendar2.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent11 = new Intent(getBaseContext(), AlarmReceiverON.class);
            cmt_s_id = IdGenerator.generateUniqueId();
            pendingIntent11 = PendingIntent.getBroadcast(getBaseContext(),cmt_s_id, intent11, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON1.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent11);

            //off
            calendar2.set(Calendar.DAY_OF_WEEK,  Calendar.SATURDAY);
            calendar2.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar2.set(Calendar.MINUTE, off_minute);
            calendar2.set(Calendar.SECOND, 0);
            if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
                calendar2.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent12 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            cmt_of_id = IdGenerator.generateUniqueId();
            pendingIntent12= PendingIntent.getBroadcast(getBaseContext(),cmt_of_id, intent12, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF1.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent12);
/*
            DayResult dcmt=setAlarm(Calendar.SATURDAY);
            cmt_s_id=dcmt.getStart1();
            cmt_of_id=dcmt.getOff1();
            cmt_t=1;
*/
        }else{
            cmt_t=0;
        }

        if(paz.isChecked()){
            paz_t=1;
            //start
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar1.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar1.set(Calendar.MINUTE, start_minute);
            calendar1.set(Calendar.SECOND, 0);
            if (calendar1.getTimeInMillis() < System.currentTimeMillis()) {
                calendar1.add(Calendar.DAY_OF_YEAR, 7);
            }
            

            alarmManagerON2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent13 = new Intent(getBaseContext(), AlarmReceiverON.class);
            paz_s_id = IdGenerator.generateUniqueId();
            pendingIntent13 = PendingIntent.getBroadcast(getBaseContext(),paz_s_id, intent13, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON2.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent13);

            //off
            calendar1.set(Calendar.DAY_OF_WEEK,  Calendar.SUNDAY);
            calendar1.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar1.set(Calendar.MINUTE, off_minute);
            calendar1.set(Calendar.SECOND, 0);
            if (calendar1.getTimeInMillis() < System.currentTimeMillis()) {
                calendar1.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent14 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            paz_of_id = IdGenerator.generateUniqueId();
            pendingIntent14= PendingIntent.getBroadcast(getBaseContext(),paz_of_id, intent14, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF2.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent14);

        }else{
            paz_t=0;
        }





            Event newEvent = new Event(pzt_t,sal_t,car_t,per_t,cum_t,cmt_t,paz_t,
                pzt_s_id,pzt_of_id,sal_s_id,sal_of_id,car_s_id,car_of_id,per_s_id,per_of_id,cum_s_id,cum_of_id,cmt_s_id,cmt_of_id,paz_s_id,paz_of_id
                ,eventName,vibrate_start_hours,vibrate_start_minute,vibrate_off_hours,vibrate_off_minute);
        eventDBContext.AddEvent(newEvent);
        //employees.add(new Employee(name,surname,age,salary,gender));
        this.updateListViewContent();

        pzt_s_id=0;pzt_of_id=0;sal_s_id=0;sal_of_id=0;car_s_id=0;car_of_id=0;per_s_id=0;per_of_id=0;cum_s_id=0;cum_of_id=0;cmt_s_id=0;cmt_of_id=0;paz_s_id=0;paz_of_id=0;
        pzt_t=0;sal_t=0;car_t=0;per_t=0;cum_t=0;cmt_t=0;paz_t=0;





dialog.dismiss();
        Toast.makeText(this, "Etkinlik Eklendi...", Toast.LENGTH_SHORT).show();


    }

    public DayResult setAlarm(int day){
        //start
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, start_hour);
        calendar.set(Calendar.MINUTE, start_minute);
        calendar.set(Calendar.SECOND, 0);

      

        alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(getBaseContext(), AlarmReceiverON.class);
        s_id = (int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(),s_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent);

        //off
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, off_hour);
        calendar.set(Calendar.MINUTE, off_minute);
        calendar.set(Calendar.SECOND, 0);


        alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
        of_id = (int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
        pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(),of_id, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent2);



        DayResult dayResult1=new DayResult(s_id,of_id);

        return dayResult1;


    }

    public void delAlarm(int start_b,int of_b){
        intent = new Intent(getBaseContext(), AlarmReceiverON.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(),start_b, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        alarmManagerON.cancel(pendingIntent);


        intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
        pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(),of_b, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent2.cancel();
        alarmManagerOFF.cancel(pendingIntent2);



    }

    public void updateBoxesBySelectedEvent(){
        Event event = events.get(selectedEventPosition);
        etName.setText(event.getEventName());
        btnPicker.setText(event.getVibrate_start_hours()+":"+event.getVibrate_start_minute());
        btnPicker2.setText(event.getVibrate_off_hours()+":"+event.getVibrate_off_minute());
        if(event.getPzt_true()==1){
            pzt.setChecked(true);
        }
        if(event.getSal_true()==1){
            sal.setChecked(true);
        }
        if(event.getCar_true()==1){
            car.setChecked(true);
        }
        if(event.getPer_true()==1){
            per.setChecked(true);
        }
        if(event.getCum_true()==1){
            cum.setChecked(true);
        }
        if(event.getCmt_true()==1){
            cmt.setChecked(true);
        }
        if(event.getPaz_true()==1){
            paz.setChecked(true);
        }




    }

    public void BtnDeleteClick(View view) {
if(selectedEventPosition<0){
    Toast.makeText(this, "Önce silinecek etkinliği seçiniz", Toast.LENGTH_SHORT).show();
    return; 
}
        Event event = events.get(selectedEventPosition);
        //Toast.makeText(this, selectedEventPosition, Toast.LENGTH_SHORT).show();



        //delete broadcast

        if(event.getPzt_true()==1){
            delAlarm(event.getPzt_start(),event.getPzt_off());
        }
        if(event.getSal_true()==1){
            delAlarm(event.getSal_start(),event.getSal_off());
        }
        if(event.getCar_true()==1){
            delAlarm(event.getCar_start(),event.getCat_off());
        }
        if(event.getPer_true()==1){
            delAlarm(event.getPer_start(),event.getPer_off());
        }
        if(event.getCum_true()==1){
            delAlarm(event.getCum_start(),event.getCum_off());
        }
        if(event.getCmt_true()==1){
            delAlarm(event.getCmt_start(),event.getCmt_off());
        }
        if(event.getPaz_true()==1){
            delAlarm(event.getPaz_start(),event.getPaz_off());
        }


        Toast.makeText(this, "Etkinlik silindi...", Toast.LENGTH_SHORT).show();

        eventDBContext.DeleteEvent(event);
        //events.remove(employee);
        this.updateListViewContent();
        pzt_s_id=0;pzt_of_id=0;sal_s_id=0;sal_of_id=0;car_s_id=0;car_of_id=0;per_s_id=0;per_of_id=0;cum_s_id=0;cum_of_id=0;cmt_s_id=0;cmt_of_id=0;paz_s_id=0;paz_of_id=0;
        pzt_t=0;sal_t=0;car_t=0;per_t=0;cum_t=0;cmt_t=0;paz_t=0;
    }

    public void BtnUpdateClick(View view) {
            //guncellemeden once daha önceki broadcast silinir
        Event event = events.get(selectedEventPosition);

        //delete broadcast
/*
        if(event.getPzt_true()==1){
            delAlarm(event.getPzt_start(),event.getPzt_off());
        }
        if(event.getSal_true()==1){
            delAlarm(event.getSal_start(),event.getSal_off());
        }
        if(event.getCar_true()==1){
            delAlarm(event.getCar_start(),event.getCat_off());
        }
        if(event.getPer_true()==1){
            delAlarm(event.getPer_start(),event.getPer_off());
        }
        if(event.getCum_true()==1){
            delAlarm(event.getCum_start(),event.getCum_off());
        }
        if(event.getCmt_true()==1){
            delAlarm(event.getCmt_start(),event.getCmt_off());
        }
        if(event.getPaz_true()==1){
            delAlarm(event.getPaz_start(),event.getPaz_off());
        }
*/
        String eventName = etName.getText().toString();
        Integer vibrate_start_hours = start_hour;
        Integer vibrate_start_minute = start_minute;
        Integer vibrate_off_hours = off_hour;
        Integer vibrate_off_minute = off_minute;

        Integer eventId_starts = s_id;
        Integer eventId_offs =of_id;




        if(pzt.isChecked()){
            pzt_t=1;

            //start
            Calendar calendar7 = Calendar.getInstance();
            calendar7.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar7.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar7.set(Calendar.MINUTE, start_minute);
            calendar7.set(Calendar.SECOND, 0);
            if (calendar7.getTimeInMillis() < System.currentTimeMillis()) {
                calendar7.add(Calendar.DAY_OF_YEAR, 7);
            }





            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(getBaseContext(), AlarmReceiverON.class);
            //s_id = (int) System.currentTimeMillis();
            pzt_s_id=IdGenerator.generateUniqueId();
            pendingIntent = PendingIntent.getBroadcast(getBaseContext(),pzt_s_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar7.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent);

            //off
            calendar7.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar7.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar7.set(Calendar.MINUTE, off_minute);
            calendar7.set(Calendar.SECOND, 0);
            if (calendar7.getTimeInMillis() < System.currentTimeMillis()) {
                calendar7.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            pzt_of_id = IdGenerator.generateUniqueId();//iptal etmede bu id uzerinden gerceklesecek
            pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(),pzt_of_id , intent2, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar7.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent2);



        /*DayResult dpzt=setAlarm(Calendar.MONDAY);
        pzt_s_id=dpzt.getStart1();
        pzt_of_id=dpzt.getOff1();
        pzt_t=1;
         */

        }

        if(sal.isChecked()){
            sal_t=1;
            //start
            Calendar calendar6 = Calendar.getInstance();
            calendar6.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
            calendar6.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar6.set(Calendar.MINUTE, start_minute);
            calendar6.set(Calendar.SECOND, 0);
            if (calendar6.getTimeInMillis() < System.currentTimeMillis()) {
                calendar6.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent3 = new Intent(getBaseContext(), AlarmReceiverON.class);
            sal_s_id = IdGenerator.generateUniqueId();
            pendingIntent3 = PendingIntent.getBroadcast(getBaseContext(),sal_s_id, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar6.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent3);

            //off
            calendar6.set(Calendar.DAY_OF_WEEK,  Calendar.TUESDAY);
            calendar6.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar6.set(Calendar.MINUTE, off_minute);
            calendar6.set(Calendar.SECOND, 0);
            if (calendar6.getTimeInMillis() < System.currentTimeMillis()) {
                calendar6.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent4 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            sal_of_id =IdGenerator.generateUniqueId();
            pendingIntent4 = PendingIntent.getBroadcast(getBaseContext(),sal_of_id, intent4, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar6.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent4);
/*
            DayResult dsal=setAlarm(Calendar.TUESDAY);
            sal_s_id=dsal.getStart1();
            sal_of_id=dsal.getOff1();
            sal_t=1;
            */

        }

        if(car.isChecked()){
            car_t=1;
            //start
            Calendar calendar5 = Calendar.getInstance();
            calendar5.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
            calendar5.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar5.set(Calendar.MINUTE, start_minute);
            calendar5.set(Calendar.SECOND, 0);
            if (calendar5.getTimeInMillis() < System.currentTimeMillis()) {
                calendar5.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent5 = new Intent(getBaseContext(), AlarmReceiverON.class);
            car_s_id = IdGenerator.generateUniqueId();
            pendingIntent5 = PendingIntent.getBroadcast(getBaseContext(),car_s_id, intent5, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar5.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent5);

            //off
            calendar5.set(Calendar.DAY_OF_WEEK,  Calendar.WEDNESDAY);
            calendar5.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar5.set(Calendar.MINUTE, off_minute);
            calendar5.set(Calendar.SECOND, 0);
            if (calendar5.getTimeInMillis() < System.currentTimeMillis()) {
                calendar5.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent6 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            car_of_id = IdGenerator.generateUniqueId();
            pendingIntent6= PendingIntent.getBroadcast(getBaseContext(),car_of_id, intent6, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar5.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent6);
/*
            DayResult dcar=setAlarm(Calendar.WEDNESDAY);
            car_s_id=dcar.getStart1();
            car_of_id=dcar.getOff1();
            car_t=1;
            */

        }

        if(per.isChecked()){
            per_t=1;
            //start
            Calendar calendar4 = Calendar.getInstance();
            calendar4.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
            calendar4.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar4.set(Calendar.MINUTE, start_minute);
            calendar4.set(Calendar.SECOND, 0);
            if (calendar4.getTimeInMillis() < System.currentTimeMillis()) {
                calendar4.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent7 = new Intent(getBaseContext(), AlarmReceiverON.class);
            per_s_id = IdGenerator.generateUniqueId();
            pendingIntent7 = PendingIntent.getBroadcast(getBaseContext(),per_s_id, intent7, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent7);

            //off
            calendar4.set(Calendar.DAY_OF_WEEK,  Calendar.THURSDAY);
            calendar4.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar4.set(Calendar.MINUTE, off_minute);
            calendar4.set(Calendar.SECOND, 0);
            if (calendar4.getTimeInMillis() < System.currentTimeMillis()) {
                calendar4.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent8 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            per_of_id = IdGenerator.generateUniqueId();
            pendingIntent8= PendingIntent.getBroadcast(getBaseContext(),per_of_id, intent8, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent8);
/*
            DayResult dper=setAlarm(Calendar.THURSDAY);
            per_s_id=dper.getStart1();
            per_of_id=dper.getOff1();
            per_t=1;
*/
        }

        if(cum.isChecked()){
            cum_t=1;
            //start
            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            calendar3.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar3.set(Calendar.MINUTE, start_minute);
            calendar3.set(Calendar.SECOND, 0);
            if (calendar3.getTimeInMillis() < System.currentTimeMillis()) {
                calendar3.add(Calendar.DAY_OF_YEAR, 7);
            }

          

            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent9 = new Intent(getBaseContext(), AlarmReceiverON.class);
            cum_s_id =IdGenerator.generateUniqueId();
            //(int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
            pendingIntent9 = PendingIntent.getBroadcast(getBaseContext(),cum_s_id, intent9, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent9);

            //off
            calendar3.set(Calendar.DAY_OF_WEEK,  Calendar.FRIDAY);
            calendar3.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar3.set(Calendar.MINUTE, off_minute);
            calendar3.set(Calendar.SECOND, 0);
            if (calendar3.getTimeInMillis() < System.currentTimeMillis()) {
                calendar3.add(Calendar.DAY_OF_YEAR, 7);
            }

            alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent10 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            cum_of_id =IdGenerator.generateUniqueId();
            //(int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
            pendingIntent10= PendingIntent.getBroadcast(getBaseContext(),cum_of_id, intent10, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent10);


/*
            DayResult dcum=setAlarm(Calendar.FRIDAY);
            cum_s_id=dcum.getStart1();
            cum_of_id=dcum.getOff1();
            cum_t=1;
            */

        }

        if(cmt.isChecked()){
            cmt_t=1;
            //start
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            calendar2.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar2.set(Calendar.MINUTE, start_minute);
            calendar2.set(Calendar.SECOND, 0);
            if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
                calendar2.add(Calendar.DAY_OF_YEAR, 7);
            }

            

            alarmManagerON1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent11 = new Intent(getBaseContext(), AlarmReceiverON.class);
            cmt_s_id = IdGenerator.generateUniqueId();
            pendingIntent11 = PendingIntent.getBroadcast(getBaseContext(),cmt_s_id, intent11, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON1.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent11);

            //off
            calendar2.set(Calendar.DAY_OF_WEEK,  Calendar.SATURDAY);
            calendar2.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar2.set(Calendar.MINUTE, off_minute);
            calendar2.set(Calendar.SECOND, 0);
            if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
                calendar2.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent12 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            cmt_of_id = IdGenerator.generateUniqueId();
            pendingIntent12= PendingIntent.getBroadcast(getBaseContext(),cmt_of_id, intent12, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF1.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent12);
/*
            DayResult dcmt=setAlarm(Calendar.SATURDAY);
            cmt_s_id=dcmt.getStart1();
            cmt_of_id=dcmt.getOff1();
            cmt_t=1;
*/
        }

        if(paz.isChecked()){
            paz_t=1;
            //start
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar1.set(Calendar.HOUR_OF_DAY, start_hour);
            calendar1.set(Calendar.MINUTE, start_minute);
            calendar1.set(Calendar.SECOND, 0);
            if (calendar1.getTimeInMillis() < System.currentTimeMillis()) {
                calendar1.add(Calendar.DAY_OF_YEAR, 7);
            }
           

            alarmManagerON2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent13 = new Intent(getBaseContext(), AlarmReceiverON.class);
            paz_s_id = IdGenerator.generateUniqueId();
            pendingIntent13 = PendingIntent.getBroadcast(getBaseContext(),paz_s_id, intent13, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerON2.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent13);

            //off
            calendar1.set(Calendar.DAY_OF_WEEK,  Calendar.SUNDAY);
            calendar1.set(Calendar.HOUR_OF_DAY, off_hour);
            calendar1.set(Calendar.MINUTE, off_minute);
            calendar1.set(Calendar.SECOND, 0);
            if (calendar1.getTimeInMillis() < System.currentTimeMillis()) {
                calendar1.add(Calendar.DAY_OF_YEAR, 7);
            }


            alarmManagerOFF2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent14 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
            paz_of_id = IdGenerator.generateUniqueId();
            pendingIntent14= PendingIntent.getBroadcast(getBaseContext(),paz_of_id, intent14, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManagerOFF2.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent14);

        }





        event.setPzt_true(pzt_t);
        event.setSal_true(sal_t);
        event.setCar_true(car_t);
        event.setPer_true(per_t);
        event.setCum_true(cum_t);
        event.setCmt_true(cmt_t);
        event.setPaz_true(paz_t);

        event.setPzt_start(pzt_s_id);
        event.setPzt_off(pzt_of_id);
        event.setSal_start(sal_s_id);
        event.setSal_off(sal_of_id);
        event.setCar_start(car_s_id);
        event.setCat_off(car_of_id);
        event.setPer_start(per_s_id);
        event.setPer_off(per_of_id);
        event.setCum_start(cum_s_id);
        event.setCum_off(cum_of_id);
        event.setCmt_start(cmt_s_id);
        event.setCmt_off(cmt_of_id);
        event.setPaz_start(paz_s_id);
        event.setPaz_off(paz_of_id);

        event.setEventName(eventName);

        event.setVibrate_start_hours(vibrate_start_hours);
        event.setVibrate_start_minute(vibrate_start_minute);
        event.setVibrate_off_hours(vibrate_off_hours);
        event.setVibrate_off_minute(vibrate_off_minute);

        eventDBContext.UpdateEvent(event);

        this.updateListViewContent();
        pzt_s_id=0;pzt_of_id=0;sal_s_id=0;sal_of_id=0;car_s_id=0;car_of_id=0;per_s_id=0;per_of_id=0;cum_s_id=0;cum_of_id=0;cmt_s_id=0;cmt_of_id=0;paz_s_id=0;paz_of_id=0;
        pzt_t=0;sal_t=0;car_t=0;per_t=0;cum_t=0;cmt_t=0;paz_t=0;

        dialog.dismiss();
        Toast.makeText(this, "Etkinlik Guncellendi...", Toast.LENGTH_SHORT).show();
    }

    public void BtnClearTable(View view) {
        eventDBContext.RemoveAllEvents();
        this.updateListViewContent();
    }






}
