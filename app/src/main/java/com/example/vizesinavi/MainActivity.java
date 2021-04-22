package com.example.vizesinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Email> emails;
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
        emails.add(new Email("Google Maps Timeline", "🌍 Test YK, 2020 yılına ait günvellenmiş takviminiz","COVID-19 nedeniyle 2020 yılında dünya genelinde karantina süreçleri başlamıştır.","9 Oca"));
        emails.add(new Email("Google Photos", "Google Fotoğraflar depolama alanınız","Merhaba Test Yk, Google Photos depolama alanınız azalmaktadır. Bilginize.","16.12.2020"));
        emails.add(new Email("Google Photos", "Google Fotoğraflar depolama alanınız","Merhaba Test Yk, Google Photos depolama alanınız azalmaktadır. Bilginize.","16.12.2020"));
        emails.add(new Email("Google Photos", "Google Fotoğraflar depolama alanınız","Merhaba Test Yk, Google Photos depolama alanınız azalmaktadır. Bilginize.","16.12.2020"));
        emails.add(new Email("Google Photos", "Google Fotoğraflar depolama alanınız","Merhaba Test Yk, Google Photos depolama alanınız azalmaktadır. Bilginize.","16.12.2020"));
        emails.add(new Email("Google Photos", "Google Fotoğraflar depolama alanınız","Merhaba Test Yk, Google Photos depolama alanınız azalmaktadır. Bilginize.","16.12.2020"));
        emails.add(new Email("Google Photos", "Google Fotoğraflar depolama alanınız","Merhaba Test Yk, Google Photos depolama alanınız azalmaktadır. Bilginize.","16.12.2020"));

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return emails.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
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
        });

    }


}