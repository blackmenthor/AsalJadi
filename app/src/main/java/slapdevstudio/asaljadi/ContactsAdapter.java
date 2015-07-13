package slapdevstudio.asaljadi;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by User on 11/07/2015.
 */
class ContactsAdapter extends ArrayAdapter<Contact> {

    private List<Contact> contactList;
    private Context context;
    private final String TAG = "AsalJadi" ;

    public ContactsAdapter(List<Contact> contactList, Context context){
        super(context,R.layout.contact_view, contactList);
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.contact_view,null);
            TextView contactName = (TextView) v.findViewById(R.id.contact_name);
            TextView contactNumber = (TextView) v.findViewById(R.id.contact_number);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);

            checkBox.setOnCheckedChangeListener((AddContact) context);

        Contact c = contactList.get(position);
            contactName.setText(c.getName());
            contactNumber.setText(c.getNumber());
            checkBox.setChecked(c.isSelected());
            checkBox.setTag(c);
        return v;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}
