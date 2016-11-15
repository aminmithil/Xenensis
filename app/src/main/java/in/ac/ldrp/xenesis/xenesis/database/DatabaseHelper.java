package in.ac.ldrp.xenesis.xenesis.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import in.ac.ldrp.xenesis.xenesis.Constants;
import in.ac.ldrp.xenesis.xenesis.R;
import in.ac.ldrp.xenesis.xenesis.cd;
import in.ac.ldrp.xenesis.xenesis.holder.Event;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "xenesis.db";
    public String CREATE_EVENT = "create table " + DatabaseSchema.Events.TABLE_NEAME
            + "(" + DatabaseSchema.Events.EVENT_ID + " text, "
            + DatabaseSchema.Events.EVENT_CODE + " text, "
            + DatabaseSchema.Events.DEPARTMENT_ID + " text, "
            + DatabaseSchema.Events.EVENT_NAME + " text, "
            + DatabaseSchema.Events.EVENT_DESCRIPTION + " text, "
            + DatabaseSchema.Events.EVENT_PRICE + " text, "
            + DatabaseSchema.Events.EVENT_DATE + " text, "
            + DatabaseSchema.Events.EVENT_TIME + " text, "
            + DatabaseSchema.Events.COORDINATE_NAME + " text, "
            + DatabaseSchema.Events.COORDINATE_MOBILE_NUM + " text, "
            + DatabaseSchema.Events.IMAGE_NAME + " integer" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_EVENT);
            addData(db);
        } catch (SQLiteException e) { e.printStackTrace(); }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1 :
                try {
                    db.update(DatabaseSchema.Events.TABLE_NEAME, getUpdatedEventValues("CI3", Constants.Department.COMPUTER_ID, "X-Militia", cd.d.c3, " Rs 100 per team", "1st April", "10:00 AM", " Devraj Patel ", "8141132587", R.drawable.x_militia), DatabaseSchema.Events.EVENT_ID + "=3", null);
                    db.update(DatabaseSchema.Events.TABLE_NEAME, getUpdatedEventValues("MA6", Constants.Department.MECHANICAL_ID, "X-Bird", cd.me.c6, " Rs 600 per person", "1st April", " 11:00 AM", "Shalin Ponoly", "8488089400", R.drawable.x_bird), DatabaseSchema.Events.EVENT_ID + "=29", null);
                } catch (SQLiteException e) { e.printStackTrace(); }
        }
    }

    ContentValues getUpdatedEventValues(String eventCode, String deptId, String name, String description, String price, String date, String time,String coordinateName, String coordinateNum, int imageName) {
        ContentValues values = new ContentValues();

        values.put(DatabaseSchema.Events.EVENT_CODE, eventCode);
        values.put(DatabaseSchema.Events.DEPARTMENT_ID, deptId);
        values.put(DatabaseSchema.Events.EVENT_NAME, name);
        values.put(DatabaseSchema.Events.EVENT_DESCRIPTION, description);
        values.put(DatabaseSchema.Events.EVENT_PRICE, price);
        values.put(DatabaseSchema.Events.EVENT_DATE, date);
        values.put(DatabaseSchema.Events.EVENT_TIME, time);
        values.put(DatabaseSchema.Events.COORDINATE_NAME, coordinateName);
        values.put(DatabaseSchema.Events.COORDINATE_MOBILE_NUM, coordinateNum);
        values.put(DatabaseSchema.Events.IMAGE_NAME, imageName);

        return values;
    }

    public boolean updateEvent(String eventId, String eventCode, String deptId, String name, String description, String price, String date, String time,String coordinateName, String coordinateNum, int imageName){
        try{
            ContentValues values = new ContentValues();

            values.put(DatabaseSchema.Events.EVENT_ID, eventId);
            values.put(DatabaseSchema.Events.EVENT_CODE, eventCode);
            values.put(DatabaseSchema.Events.DEPARTMENT_ID, deptId);
            values.put(DatabaseSchema.Events.EVENT_NAME, name);
            values.put(DatabaseSchema.Events.EVENT_DESCRIPTION, description);
            values.put(DatabaseSchema.Events.EVENT_PRICE, price);
            values.put(DatabaseSchema.Events.EVENT_DATE, date);
            values.put(DatabaseSchema.Events.EVENT_TIME, time);
            values.put(DatabaseSchema.Events.COORDINATE_NAME, coordinateName);
            values.put(DatabaseSchema.Events.COORDINATE_MOBILE_NUM, coordinateNum);
            values.put(DatabaseSchema.Events.IMAGE_NAME, imageName);

            getWritableDatabase().update(DatabaseSchema.Events.TABLE_NEAME, values, DatabaseSchema.Events.EVENT_ID + "=" + eventId, null);
            return true;
        } catch (SQLiteConstraintException e) { e.printStackTrace(); return false; }
    }
    public boolean addEvent (SQLiteDatabase db, String eventId, String eventCode, String deptId, String name, String description, String price, String date, String time,String coordinateName, String coordinateNum, int imageName){
        try{
            ContentValues values = new ContentValues();

            values.put(DatabaseSchema.Events.EVENT_ID, eventId);
            values.put(DatabaseSchema.Events.EVENT_CODE, eventCode);
            values.put(DatabaseSchema.Events.DEPARTMENT_ID, deptId);
            values.put(DatabaseSchema.Events.EVENT_NAME, name);
            values.put(DatabaseSchema.Events.EVENT_DESCRIPTION, description);
            values.put(DatabaseSchema.Events.EVENT_PRICE, price);
            values.put(DatabaseSchema.Events.EVENT_TIME, time);
            values.put(DatabaseSchema.Events.EVENT_DATE, date);
            values.put(DatabaseSchema.Events.COORDINATE_NAME, coordinateName);
            values.put(DatabaseSchema.Events.COORDINATE_MOBILE_NUM, coordinateNum);
            values.put(DatabaseSchema.Events.IMAGE_NAME, imageName);

            db.insert(DatabaseSchema.Events.TABLE_NEAME, null, values);
            return true;
        } catch (SQLiteConstraintException e) { e.printStackTrace(); return false; }
    }

    public List<Event> events(List<String> constraints, String[] values) {
        try{
            List<Event> events = new ArrayList<>();
            String conString = constraints.get(0) + "=?";
            if(constraints.size()>1) {
                for(int i=0; i < constraints.size() ; i++){
                    conString = conString.concat(" and " + constraints.get(i) + "=?");
                }
            }
            Cursor cursor = getReadableDatabase().query(DatabaseSchema.Events.TABLE_NEAME, new String[]{"*"}, conString, values, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Event object = new Event(cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_CODE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.DEPARTMENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_PRICE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DATE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_TIME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.COORDINATE_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.COORDINATE_MOBILE_NUM)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseSchema.Events.IMAGE_NAME)));

                cursor.moveToNext();
                events.add(object);
            }
            cursor.close();
            return events;
        } catch (CursorIndexOutOfBoundsException e) { e.printStackTrace(); return null; }
    }

    public Event events(String eventId) {
        try{

            Cursor cursor = getReadableDatabase().rawQuery("select * from " + DatabaseSchema.Events.TABLE_NEAME + " where " + DatabaseSchema.Events.EVENT_ID + "=" + eventId, null);
            cursor.moveToFirst();

                Event object = new Event(cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_CODE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.DEPARTMENT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_PRICE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_DATE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.EVENT_TIME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.COORDINATE_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.Events.COORDINATE_MOBILE_NUM)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseSchema.Events.IMAGE_NAME)));

            cursor.close();
            return object;
        } catch (CursorIndexOutOfBoundsException e) { e.printStackTrace(); return null; }
    }
/*
    public int checkData (){
        try{
            Cursor cursor = getReadableDatabase().rawQuery("select * from " + DatabaseSchema.Events.TABLE_NEAME, null);
            return cursor.getCount();
        } catch (CursorIndexOutOfBoundsException e) { e.printStackTrace(); return 0; }
    }
*/
    private void addData(SQLiteDatabase db) {
        addEvent(db, "1", "CI1", Constants.Department.COMPUTER_ID, "X-Baffle", cd.d.c1, " Rs 60 per team", "1st April", " 12:00 PM", " Hiral Tank", "9998223923", R.drawable.x_baffle);
        addEvent(db, "2", "CI2", Constants.Department.COMPUTER_ID, "X-Programming-League", cd.d.c2, " Rs 30 per team", "1st April", "10:30 AM", " Shubhransh Bhargava ", "9624114190", R.drawable.x_programming_league);
        addEvent(db, "3", "CI3", Constants.Department.COMPUTER_ID, "X-Militia", cd.d.c3, " Rs 100 per team", "1st April", "10:00 AM", " Devraj Patel ", "8141132587", R.drawable.x_militia);
        addEvent(db, "4", "CI4", Constants.Department.COMPUTER_ID, "Err-X-Snapchat", cd.d.c4, " Rs 120 per team", "1st April", "01:30 PM", " Saurabh ", "9586464058", R.drawable.err_x_snapchat);
        addEvent(db, "5", "CI5", Constants.Department.COMPUTER_ID, "X-Web-Deco-Hunt", cd.d.c5, " Rs 100 per team (2 players per team)", "2nd April", "10:00 AM", "Patel Ayushkumar R", "8347947377", R.drawable.x_web_deco_hunt);
        addEvent(db, "6", "CI6", Constants.Department.COMPUTER_ID, "X-CounterStrike", cd.d.c6, "Rs 250 per team (5 players per team)", "1st April", "10.00AM", "Ketul Dave", "9033834607", R.drawable.x_counterstrike);
        addEvent(db, "7", "CI7", Constants.Department.COMPUTER_ID, "X-Techwizz", cd.d.c7, "Rs 30 per person", "2nd April", "11:30 AM", "Rutu Patel", "7874048202", R.drawable.x_techwizz);
        addEvent(db, "8", "CI8", Constants.Department.COMPUTER_ID, "X-FIFA-15", cd.d.c8, "Rs 50 per person", "1st April", " 10:00 AM", " Madhusudan Jhavar", "9033657231", R.drawable.x_fifa_15);
        addEvent(db, "9", "CI9", Constants.Department.COMPUTER_ID, "X-Code-Distraction", cd.d.c9, "Rs 30 per person", "2nd April", "10:30 AM", "Sachin Joshi", "7698617583", R.drawable.x_code_distraction);
        addEvent(db, "10", "CI10", Constants.Department.COMPUTER_ID, "X-Tech-Bingo", cd.d.c10, " Rs 30 per person", "1st April", "02:30 PM", "Bharat Ahuja", "8866049059", R.drawable.x_tech_bingo);
        addEvent(db, "11", "CI11", Constants.Department.COMPUTER_ID, "X-Startup-Workshop", cd.d.c11, "Rs 250 per person", "1st & 2nd April", " 10:30 AM", "Bhavya Thakkar", "8140639731", R.drawable.design_thinking);
        addEvent(db, "37", "CI12", Constants.Department.COMPUTER_ID, "X-NFS", cd.d.c12, "Rs 50 per person", "1st April", "10:30 AM", "Vishal Vaghela", "9558995276", R.drawable.x_nfs);

        addEvent(db, "12", "EC1", Constants.Department.EC_ID, "X-Circuit", cd.ec.c1, "Rs 100 per team (3 players per team)", "1st April", "11:30 AM", "Shah Keval", "9409015496", R.drawable.x_circuit);
        addEvent(db, "13", "EC2", Constants.Department.EC_ID, "X-Debate", cd.ec.c2, "Rs 30 per person", "1st April", "11:30 AM", "Shrinivas Iyengar", "8758465445", R.drawable.x_debate);
        addEvent(db, "14", "EC3", Constants.Department.EC_ID, "X-Range", cd.ec.c3, "Rs 30 per person", "2nd April", " 10:00 AM", "Khushbu Kishnani", "9409526893", R.drawable.x_range);
        addEvent(db, "15", "EC4", Constants.Department.EC_ID, "X-Scrabble", cd.ec.c4, "Rs 20 per person", "1st April", "11:30 AM", "Gunjan Patel ", "8469857447", R.drawable.x_scrabble);


        addEvent(db, "16", "EE1", Constants.Department.ELECTRICAL_ID, "X-Electrotech", cd.ee.c1, "Rs 50 per team (2 player per team)", "1st April", "11:00 AM ", " Smit Patel", "9409215321", R.drawable.x_electrotech);
        addEvent(db, "17", "EE2", Constants.Department.ELECTRICAL_ID, "X-Speedo", cd.ee.c2, " Rs 100 per team (2 or 3 players per team)", " 1st April", " 12:30 PM", "Vaibhav Vaidya", "8128812764", R.drawable.x_speedo);
        addEvent(db, "18", "EE3", Constants.Department.ELECTRICAL_ID, "X-Impromptu", cd.ee.c3, " Rs 50 per team (2 or 3 players per team)", "1st April", " 11:30 AM", " Ashutosh Patel ", "9978885854", R.drawable.x_impromptu);
        addEvent(db, "19", "EE4", Constants.Department.ELECTRICAL_ID, "X-Google-Maze", cd.ee.c4, " Rs 30 per person", "1st April", "12:00 PM", "Rahil Dave", "9824099144", R.drawable.x_google_maze);
        addEvent(db, "20", "EE5", Constants.Department.ELECTRICAL_ID, "X-Campus-Castle", cd.ee.c5, "Rs 50 per person", "1st April", "10:45 AM", "Vatsal Bhoyania", "9624760005", R.drawable.x_campus_castle);
        addEvent(db, "21", "EE6", Constants.Department.ELECTRICAL_ID, "X-Drag-And-Drop", cd.ee.c6, "Rs 50 per person", "1st April", "01:00 PM", "Akash Shah", "7622800636", R.drawable.x_drag_and_drop);
        addEvent(db, "22", "EE7", Constants.Department.ELECTRICAL_ID, "X-Mission-Impossible", cd.ee.c7, "Rs 20 per person", "1st April", "11:00 AM", "Manthan Andharia", "9979982870", R.drawable.x_mission_impossible);
        addEvent(db, "23", "EE8", Constants.Department.ELECTRICAL_ID, "X-Technopictionary", cd.ee.c8, "Rs 25 per person", "1st & 2nd April", "12:00 PM", "Suraj Pal", "7878159001", R.drawable.x_technopictionary);

        addEvent(db, "24", "MA1", Constants.Department.MECHANICAL_ID, "X-Aqua-Race", cd.me.c1, "Rs 50 per person (2 to 4  players per team)", "1st & 2nd April", "10:30 AM", "Parmar Dhavalsinh", "7874735581", R.drawable.x_aqua_race);
        addEvent(db, "25", "MA2", Constants.Department.MECHANICAL_ID, "X-Death-Race", cd.me.c2, " Rs 100 per team (2 to 4  players per team)", "1st & 2nd April", "10:30 AM", "Abhishek Jain ", "9408720367", R.drawable.x_death_race);
        addEvent(db, "26", "MA3", Constants.Department.MECHANICAL_ID, "X-Devil-Of-Death", cd.me.c3, ": Rs 200 per team (2 to 5  players per team)", " 1st & 2nd April", " 01:00 PM", "Sarin Patel", "9408138379", R.drawable.x_devil_of_death);
        addEvent(db, "27", "MA4", Constants.Department.MECHANICAL_ID, "X-Hockey", cd.me.c4, "", "1st & 2nd April", "10:30 AM", "Mitul Patel ", "8866814667", R.drawable.x_robo_hockey);
        addEvent(db, "28", "MA5", Constants.Department.MECHANICAL_ID, "X-Robo-Kabbadi", cd.me.c5, " Rs 100 Per Team", "1st & 2nd April", "10:30 AM", " Desai Harsh K", "8460836660", R.drawable.x_robo_kabaddi);
        addEvent(db, "29", "MA6", Constants.Department.MECHANICAL_ID, "X-Bird", cd.me.c6, " Rs 600 per person", "1st April", " 11:00 AM", "Shalin Ponoly", "8488089400", R.drawable.x_bird);
        addEvent(db, "30", "MA7", Constants.Department.MECHANICAL_ID, "X-Auto-Workshop", cd.me.c7, "Rs 150 per person", "1st & 2nd April", "  11:00 AM", "", "", R.drawable.x_auto_workshop);

        addEvent(db, "31", "C1", Constants.Department.CIVIL_ID, "Bridge-X-Mania", cd.ci.c1, "Rs 100 per team (2 to 4  players per team)", "1st April", "12:00 PM", "Umang Devmurari", "9033492414", R.drawable.bridge_x_mania);
        addEvent(db, "32", "C2", Constants.Department.CIVIL_ID, "Build-X", cd.ci.c2, "Rs 100 per team (2 to 3 players per team)", "2nd April", " 10:30 AM", "Rishabh Patel", "9824646497", R.drawable.build_x);
        addEvent(db, "32", "C3", Constants.Department.CIVIL_ID, "X-MockUp", cd.ci.c3, "Rs 100 per team (2 to 3 players per team)", "1st April", " 10:30 AM", "Jay Asodariya", "90333015307", R.drawable.x_mockup);
        addEvent(db, "34", "C4", Constants.Department.CIVIL_ID, "Civil-X-Hunt", cd.ci.c4, "Rs 30 per person", "2nd April", " 10:30 AM", "Patel Keyur", "9737306470", R.drawable.civil_x_hunt);
        addEvent(db, "35", "C5", Constants.Department.CIVIL_ID, "X-Paper-Structure", cd.ci.c5, "Rs 60 per team", "1st April", " 02:00 PM", "Vaibhav Parikh", "9687541768", R.drawable.x_paper);
        addEvent(db, "36", "C6", Constants.Department.CIVIL_ID, "Dome-X", cd.ci.c6, "Rs 100 per team (2 to 3  players per team)", "1st April", " 10:30 AM", "", "", R.drawable.dome_x);

        addEvent(db, "38", "MCA1", Constants.Department.MCA_ID, "X-Workshop", cd.mca.c1, " Rs 100 per person", "2nd April", "10:30 AM", "", "", R.drawable.x_mca_workshop);
        addEvent(db, "39", "MCA2", Constants.Department.MCA_ID, "X-IT-Quiz", cd.mca.c2, "Rs 100 per team (2 players per team)", "1st April", "1st April", " Nikunj Barvaliya", "8866153626", R.drawable.x_it_quiz);
        addEvent(db, "40", "MCA3", Constants.Department.MCA_ID, "X-Programming", cd.mca.c3, " Rs 50 per person", "1st April", "10:00 AM", " Parmar Falgun", "7096539061", R.drawable.x_programming);

        addEvent(db, "41", "SH1", Constants.Department.SCIENCE_HUMANITY_ID, "X-Mathematica", cd.sc.c1, "Rs 20 per person", "1st April", " 11:00 AM", "", "", R.drawable.x_mathematica);
    }
}
