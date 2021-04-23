package com.example.vizesinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
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
        emails.add(new Email("Vize Projesi", "hello@iamfurkan.com'dan size","Bu proje vize sınavı için geliştirilmiştir iyi okumalar dilerim.","22.04.2021"));


        ListView listView = findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(getApplicationContext(), emails);
        listView.setAdapter(adapter);

        EditText inputBox = findViewById(R.id.editText);
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String filterKey = charSequence.toString().toLowerCase();
                ArrayList<Email> filtered = new ArrayList<>();
                for(int p = 0; p < emails.size(); p++){
                    String header = emails.get(p).header.toLowerCase();
                    String content = emails.get(p).content.toLowerCase();
                    String desc = emails.get(p).desc.toLowerCase();
                    String date = emails.get(p).date.toLowerCase();
                    if (header.contains(filterKey) || content.contains(filterKey) || desc.contains(filterKey) || date.contains(filterKey)) {
                        filtered.add(emails.get(p));
                    }
                }
                MyAdapter filteredAdapter = new MyAdapter(getApplicationContext(), filtered);
                listView.setAdapter(filteredAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


}

class MyAdapter extends BaseAdapter {
    ArrayList<Email> _list;
    Context con;
MyAdapter(Context _context, ArrayList<Email> emails){
    _list = emails;
    con=_context;
}
    @Override
public int getCount() {
        return _list.size();
        }

@Override
public Object getItem(int i) {
        return i;
        }

@Override
public long getItemId(int i) {
        return i;
        }

@Override
public View getView(int i, View convertView, ViewGroup parent) {
                if (convertView==null){
                    convertView = LayoutInflater.from(con).inflate(R.layout.row_item,parent,false);
                }
                TextView header = convertView.findViewById(R.id.ri_header);
                TextView desc = convertView.findViewById(R.id.ri_desc);
                TextView content = convertView.findViewById(R.id.ri_content);
                TextView date = convertView.findViewById(R.id.ri_date);
                header.setText(_list.get(i).header);
                desc.setText(_list.get(i).desc);
                content.setText(_list.get(i).content);
                date.setText(_list.get(i).date);
                return convertView;
            }
        }