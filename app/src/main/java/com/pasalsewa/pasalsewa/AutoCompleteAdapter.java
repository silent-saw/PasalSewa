package com.pasalsewa.pasalsewa;

        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.TextView;

        import java.util.ArrayList;

/**
 * Created by wicked_sick on 11/18/2017.
 */

public class AutoCompleteAdapter extends ArrayAdapter<String>{




    Context context;
    public AutoCompleteAdapter(@NonNull Context context, ArrayList<String>list) {
        super(context,0,list );
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.autocomplete_text_layout,null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(getItem(position));



        return view;

    }
}
