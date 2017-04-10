package com.kainos.joannec.moodtrackeruni;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OnClickListenerCreateEntry implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.entry_input_form, null, false);
        final EditText editTextMoodName = (EditText) formElementsView.findViewById(R.id.editTextMoodName);
        final EditText editTextMoodLocation = (EditText) formElementsView.findViewById(R.id.editTextLocation);
        //add date field capture
        //add image picker

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Mood")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                String moodName = editTextMoodName.getText().toString();
                                String moodLocation = editTextMoodLocation.getText().toString();
                                //add date field capture
                                //add image picker
                                Entry entry = new Entry();
                                entry.moodName = moodName;
                                entry.location = moodLocation;
                                //add date field capture
                                //add image picker
                                boolean createSuccessful = new EntryController(context).create(entry);
                                //toast confirmation
                                if(createSuccessful){
                                    Toast.makeText(context, "Mood information was saved.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to save mood information.", Toast.LENGTH_SHORT).show();
                                }


                            }

                        }).show();
    }
}