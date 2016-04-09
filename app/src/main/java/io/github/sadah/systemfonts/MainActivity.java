package io.github.sadah.systemfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<String> items = new ArrayList<>();
        File[] files = new File("/system/fonts/").listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                items.add(files[i].getName());
            }
        }

        FontFileAdapter adapter = new FontFileAdapter(
                this, R.layout.font_list, items
        );

        listView.setAdapter(adapter);

    }

    public class FontFileAdapter extends ArrayAdapter<String> {

        private LayoutInflater layoutInflater;

        public FontFileAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View contextView, ViewGroup parent){
            if(contextView == null){
                contextView = layoutInflater.inflate(
                        R.layout.font_list,
                        parent,
                        false
                );
            }
            String fontName = (String) getItem(position);
            Typeface typeface = Typeface.createFromFile("/system/fonts/" + fontName);

            TextView nameTextView = ((TextView) contextView.findViewById(R.id.name));
            nameTextView.setTypeface(typeface);
            nameTextView.setText(fontName);

            TextView textView = ((TextView) contextView.findViewById(R.id.loc));
            textView.setTypeface(typeface);
            textView.setText("ABCDEFG あいうえお");

            return contextView;
        }
    }
}