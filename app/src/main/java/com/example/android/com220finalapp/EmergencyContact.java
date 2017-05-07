
package com.example.android.com220finalapp;

/* PERMISSIONS NEEDED IN MANIFEST
<uses-permission android:name="android.permission.CALL_PHONE" />
*/

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmergencyContact extends Activity {
    private static final int RESULT_PICK_CONTACT = 85500;
    private TextView textView1;
    private TextView textView2;
    private EditText textInput1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        //create objs t pointing to items on the UI
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textInput1 = (EditText) findViewById(R.id.message);
        textInput1.setText("Could you come get me? I'm too drunk to drive.");



        Button button= (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmergencySMS();
            }
        });
    }

    public void pickContact(View v) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    /**
     * Query the Uri and read contact details. Handle the picked contact data.
     *
     * @param data
     */
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();

            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            // Set the value to the textviews
            textView1.setText(name);
            textView2.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendEmergencySMS()
    {
        String message = textInput1.getText().toString();
        String phoneNo = textView2.getText().toString();

        Log.d("msg", message+phoneNo);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNo));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }



}
