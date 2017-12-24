package com.example.gksls.silentapp_v2;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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


    CheckBox pzt,sal,car,per,cum,cmt,paz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btnOFF = (Button) findViewById(R.id.btnOFF);
         btnON = (Button) findViewById(R.id.btnON);
        //final Button btnAdd = (Button) findViewById(R.id.btnAdd);


        etName = (EditText) findViewById(R.id.etName);
        //etSurname = (EditText) findViewById(R.id.etSurname);
        //etAge = (EditText) findViewById(R.id.etAge);
        //etSalary = (EditText) findViewById(R.id.etSalary);
        //spGender = (Spinner) findViewById(R.id.spGender);
        //etEmail = (EditText) findViewById(R.id.mailText);
        //etGpa = (EditText) findViewById(R.id.tvGpa);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        lvEvents = (ListView) findViewById(R.id.lvEmployees);

        pzt= (CheckBox) findViewById(R.id.checkBox7);
        sal= (CheckBox) findViewById(R.id.checkBox6);
        car= (CheckBox) findViewById(R.id.checkBox5);
        per= (CheckBox) findViewById(R.id.checkBox4);
        cum= (CheckBox) findViewById(R.id.checkBox3);
        cmt= (CheckBox) findViewById(R.id.checkBox2);
        paz= (CheckBox) findViewById(R.id.checkBox);





        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedEventPosition = position;
                updateBoxesBySelectedEvent();
            }
        });
        final Calendar c = Calendar.getInstance();

        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Butona basınca alert dialog çıkmakta
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

//                        Burda seçilen zaman ile ilgili yapılacakları yazdık
//                        örneğin SharedRef e kaydedilmesi. Önceki alarmın iptali ve yeni alarmın tekrar oluşturulması


                        btnON.setText(selectedHour + ":" + selectedMinute);
                        start_hour=selectedHour;
                        start_minute=selectedMinute;

                    }
                },c.get(Calendar.HOUR_OF_DAY) , c.get(Calendar.MINUTE), true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        btnOFF.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {




                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {

                        btnOFF.setText(selectedHour + ":" + selectedMinute);
                        off_hour=selectedHour;
                        off_minute=selectedMinute;


                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        this.eventDBContext = new EventDBContext(getApplicationContext());

        this.updateListViewContent();




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

            //alarmı güne göre kurulumu


            //unique id

            //UUID myuuid = UUID.randomUUID();
            //int highbits = (int) myuuid.getLeastSignificantBits();






            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(getBaseContext(), AlarmReceiverON.class);
            //s_id = (int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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
            //alarmı güne göre kurulumu

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
/*
            DayResult dpaz=setAlarm(Calendar.SUNDAY);
            paz_s_id=dpaz.getStart1();
            paz_of_id=dpaz.getOff1();
            paz_t=1;
*/
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




/*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, start_hour);
        calendar.set(Calendar.MINUTE, start_minute);
        calendar.set(Calendar.SECOND, 0);
        alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(getBaseContext(), AlarmReceiverON.class);
        s_id = (int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(),s_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);




        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, off_hour);
        calendar2.set(Calendar.MINUTE, off_minute);
        calendar2.set(Calendar.SECOND, 0);
        alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
        of_id = (int) System.currentTimeMillis();
        pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(), of_id, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);


        Integer eventId_start = s_id;
        Integer eventId_off =of_id;

        */




    }

    public DayResult setAlarm(int day){
        //start
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, start_hour);
        calendar.set(Calendar.MINUTE, start_minute);
        calendar.set(Calendar.SECOND, 0);

        //alarmı güne göre kurulumu

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
        btnON.setText(event.getVibrate_start_hours()+":"+event.getVibrate_start_minute());
        btnOFF.setText(event.getVibrate_off_hours()+":"+event.getVibrate_off_minute());
        //etSurname.setText(employee.getSurname());
        //etAge.setText(employee.getAge().toString());
        //etSalary.setText(employee.getPhone());
        //etEmail.setText(employee.getEmail());
        //etGpa.setText(employee.getGpa());


    }

    public void BtnDeleteClick(View view) {

        Event event = events.get(selectedEventPosition);



        //delete broadcast

        if(event.getPzt_true()==1){
            delAlarm(event.getPzt_start(),event.getPzt_off());
        }
        if(event.getSal_true()==1){
            delAlarm(event.getSal_start(),event.getSal_off());
        }
        if(event.getCar_true()==1){
            delAlarm(event.getCar_start(),event.getCat_off());
            Toast.makeText(this, String.valueOf(event.getCar_start())+"tru:"+String.valueOf(event.getCar_true()+"of:"+String.valueOf(event.getCat_off())), Toast.LENGTH_SHORT).show();
        }
        if(event.getPer_true()==1){
            delAlarm(event.getPer_start(),event.getPer_off());
        }
        if(event.getCum_true()==1){
            Toast.makeText(this, String.valueOf(event.getCum_start())+"--"+String.valueOf(event.getCum_off()), Toast.LENGTH_SHORT).show();
            delAlarm(event.getCum_start(),event.getCum_off());
        }
        if(event.getCmt_true()==1){
            delAlarm(event.getCmt_start(),event.getCmt_off());
        }
        if(event.getPaz_true()==1){
            delAlarm(event.getPaz_start(),event.getPaz_off());
        }



/*
        intent = new Intent(getBaseContext(), AlarmReceiverON.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(),event.getEventId_start(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        alarmManagerON.cancel(pendingIntent);


        intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
        pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(),event.getEventId_off(), intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent2.cancel();
        alarmManagerOFF.cancel(pendingIntent2);
*/




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

            //alarmı güne göre kurulumu


            //unique id

            //UUID myuuid = UUID.randomUUID();
            //int highbits = (int) myuuid.getLeastSignificantBits();






            alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(getBaseContext(), AlarmReceiverON.class);
            //s_id = (int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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

            //alarmı güne göre kurulumu

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
            //alarmı güne göre kurulumu

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
/*
            DayResult dpaz=setAlarm(Calendar.SUNDAY);
            paz_s_id=dpaz.getStart1();
            paz_of_id=dpaz.getOff1();
            paz_t=1;
*/
        }



        /*
        //guncellenen broadcast alarm ayarlanır
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, start_hour);
        calendar.set(Calendar.MINUTE, start_minute);
        calendar.set(Calendar.SECOND, 0);
        alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(getBaseContext(), AlarmReceiverON.class);
        s_id = (int) System.currentTimeMillis();//iptal etmede bu id uzerinden gerceklesecek
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(),s_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);




        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, off_hour);
        calendar2.set(Calendar.MINUTE, off_minute);
        calendar2.set(Calendar.SECOND, 0);
        alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
        of_id = (int) System.currentTimeMillis();
        pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(), of_id, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);*/







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
    }

    public void BtnClearTable(View view) {
        eventDBContext.RemoveAllEvents();
        this.updateListViewContent();
    }






}
