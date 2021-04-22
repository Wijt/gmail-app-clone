package com.example.vizesinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Email> emails;
    public MyAdapter adapter;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set notification number to 28
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        BadgeDrawable badge = bottomNavigation.getOrCreateBadge(R.id.menu_1);
        badge.setNumber(28);

        //create an Array
        emails = new ArrayList<Email>();
        emails.add(new Email("Google Maps Timeline", "🌍 Test YK, Mart ayı özetiniz","Bu Zaman Çizelgesi e-postası, gittiğiniz yerleri göstermektedir.","11 Nis"));
        emails.add(new Email("Google Maps Timeline", "🌍 Test YK, Şubat ayı özetiniz","Bu Zaman Çizelgesi e-postası, gittiğiniz yerleri göstermektedir.","6 Mar"));
        emails.add(new Email("Google Maps Timeline", "🌍 Test YK, Ocak ayı özetiniz","Bu Zaman Çizelgesi e-postası, gittiğiniz yerleri göstermektedir.","6 Şub"));
        emails.add(new Email("Google Maps Timeline", "🌍 Test YK, 2020 yılına ait güncellenmiş takviminiz","COVID-19 nedeniyle 2020 yılında dünya genelinde karantina süreçleri başlamıştır.","9 Oca"));
        emails.add(new Email("Google Photos", "Google Fotoğraflar depolama alanınız","Merhaba Test Yk, Google Photos depolama alanınız azalmaktadır. Bilginize.","16.12.2020"));
        emails.add(new Email("Google Drive", "Google Drive depolama alanınız","Merhaba Test Yk, Google Drive depolama alanınız azalmaktadır. Bilginize.","16.11.2020"));
        emails.add(new Email("hello@iamfurkan.com", "Vize sınavı için","Merhaba hocam, bu sayfa vize sınavı için hazırlanmıştır. Kolay gelsin.","16.11.2020"));

        listView = findViewById(R.id.listView);
        adapter = new MyAdapter(emails);
        listView.setAdapter(adapter);
        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    public class MyAdapter extends BaseAdapter implements Filterable {

        private ArrayList<Email> mOriginalValues; // Original Values
        private ArrayList<Email> mDisplayedValues;    // Values to be displayed

        public MyAdapter(ArrayList<Email> mProductArrayList) {
            this.mOriginalValues = mProductArrayList;
            this.mDisplayedValues = mProductArrayList;
        }

        @Override
        public int getCount() {
            return mDisplayedValues.size();
        }

        @Override
        public Object getItem(int i) {
            return mDisplayedValues.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.row_item,parent,false);
            }
            TextView header = convertView.findViewById(R.id.ri_header);
            TextView desc = convertView.findViewById(R.id.ri_desc);
            TextView content = convertView.findViewById(R.id.ri_content);
            TextView date = convertView.findViewById(R.id.ri_date);
            header.setText(emails.get(i).header);
            desc.setText(emails.get(i).desc);
            content.setText(emails.get(i).content);
            date.setText(emails.get(i).date);
            return convertView;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,FilterResults results) {
                    mDisplayedValues = (ArrayList<Email>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    ArrayList<Email> FilteredArrList = new ArrayList<Email>();

                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<Email>(mDisplayedValues); // saves the original data in mOriginalValues
                    }

                    /********
                     *
                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                     *  else does the Filtering and returns FilteredArrList(Filtered)
                     *
                     ********/
                    if (constraint == null || constraint.length() == 0) {

                        // set the Original result to return
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i=0;i<mDisplayedValues.size();i++){
                            Email email = (Email) listView.getAdapter().getItem(i);
                            String header = email.header.toLowerCase();
                            String desc = email.desc.toLowerCase();
                            String content = email.content.toLowerCase();
                            String date = email.date.toLowerCase();
                            if (header.contains(constraint) || desc.contains(constraint)|| content.contains(constraint) || date.contains(constraint))
                                FilteredArrList.add(email);
                        }
                        /*for (int i = 0; i < mOriginalValues.size(); i++) {
                            String header = mOriginalValues.get(i).header.toLowerCase();
                            String desc = mOriginalValues.get(i).desc.toLowerCase();
                            String content = mOriginalValues.get(i).content.toLowerCase();
                            String date = mOriginalValues.get(i).date.toLowerCase();
                            if (header.contains(constraint) || desc.contains(constraint)|| content.contains(constraint) || date.contains(constraint)) {
                                FilteredArrList.add(
                                    new Email(
                                            mOriginalValues.get(i).header,
                                            mOriginalValues.get(i).desc,
                                            mOriginalValues.get(i).content,
                                            mOriginalValues.get(i).date
                                    )
                                );
                            }
                        }*/
                        notifyDataSetChanged();
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };

            return filter;
        }
    }

}