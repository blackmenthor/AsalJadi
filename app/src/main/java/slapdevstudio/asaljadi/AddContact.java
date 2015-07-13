package slapdevstudio.asaljadi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/07/2015.
 */
public class AddContact extends Activity implements CompoundButton.OnCheckedChangeListener{
    ListView contacts;
    List<Contact> contactsList;
    ContactsAdapter adapter;
    private final String TAG = "asaljadi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"on create");
        super.onCreate(savedInstanceState);
        contactsList = new ArrayList<>();
        populateContactList(AddContact.this);
        setContentView(R.layout.add_contact);
        contacts = (ListView) findViewById(R.id.list_contact);
        contacts.setItemsCanFocus(true);
        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact temp = contactsList.get(position);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                if(temp.isSelected()){
                    checkBox.setChecked(false);
                }else{
                    checkBox.setChecked(true);
                }
            }
        });
        Log.d(TAG,"contact pertamanya " + contactsList.get(0).getName() + " nomornya " + contactsList.get(0).getNumber() );
        adapter = new ContactsAdapter(contactsList,AddContact.this);
        contacts.setAdapter(adapter);
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void populateContactList(Context mContext){
        Cursor c1;
        // list Columns to retreive  , pass null to get all the columns
        String col[]={ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME};
        c1 = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, col, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        String personName = null, number = "";
        if(c1==null)
            return;
        // Fetch the Corresponding Phone Number of  Person Name
        try
        {
            if(c1.getCount() > 0)
            {
                while(c1.moveToNext())
                {

                    String id = c1.getString(c1.getColumnIndex(ContactsContract.Contacts._ID));
                    personName = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    if(id==null||personName==null)
                        continue;
                    Cursor cur = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id}, null);
                    if(cur==null)
                        continue;
                    number = "";
                    while(cur.moveToNext())
                    {
                        number = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }

                    Contact temp = new Contact(personName,number,id);
                    if(!temp.getNumber().equalsIgnoreCase(""))
                        contactsList.add(temp);

                    cur.close();
                }
            }
        }
        catch(Exception e)
        {
            Log.d(TAG,e.toString());
        }
        finally
        {
            c1.close();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = contacts.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION){
            Contact c = contactsList.get(pos);
            c.setSelected(isChecked);
        }
    }
}
