package com.kainos.joannec.moodtrackeruni;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OnClickListenerCreateEntry implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.entry_input_form, null, false);
        final EditText editTextMoodName = (EditText) formElementsView.findViewById(R.id.editTextMoodName);
        final EditText editTextMoodLocation = (EditText) formElementsView.findViewById(R.id.editTextLocation);
        final Spinner spinner = (Spinner) formElementsView.findViewById(R.id.spinnerMood);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.moods_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Mood")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                String moodName = editTextMoodName.getText().toString();
                                String moodLocation = editTextMoodLocation.getText().toString();
                                //String moodRate = spinner.setOnItemSelectedListener();
                                String moodString = spinner.getSelectedItem().toString();
                                //convert user string choice to database flag
                                int moodRating = 3;
                                String moodCategory;
                                switch (moodString) {
                                    case "amazing!": moodRating = 5;
                                        break;

                                    case "happy":   moodRating = 4;
                                        break;

                                    case "so-so": moodRating = 3;
                                        break;

                                    case "down": moodRating = 2;
                                        break;

                                    case "terrible": moodRating = 1;
                                        break;

                                }


                                Entry entry = new Entry(moodName,moodLocation,moodRating);
                               /* entry.moodName = moodName;
                                entry.location = moodLocation;*/

                                //add mood spinner



                                boolean createSuccessful = new EntryController(context).create(entry);
                                //toast confirmation
                                if(createSuccessful){
                                    Toast.makeText(context, "Mood information was saved.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to save mood information.", Toast.LENGTH_SHORT).show();
                                }
                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readEntries();

                            }

                        }).show();
    }
}