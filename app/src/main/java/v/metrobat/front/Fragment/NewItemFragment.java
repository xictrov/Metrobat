package v.metrobat.front.Fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

import back.ItemData;
import v.metrobat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewItemFragment extends Fragment {

    private ItemData itemData;

    private EditText itemName;
    private Spinner transport;
    private Spinner transportLine;
    private EditText date;
    private EditText hour;
    private EditText description;

    private Button publicaBtn;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private String eTransport, eTransportLine;

    private static final String ARG_LOSTORFOUND = "lostOrFound";
    private String lostOrFound;

    public NewItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param lostOrFound String
     * @return A new instance of fragment NewItemFragment.
     */
    public static NewItemFragment newInstance(String lostOrFound) {
        NewItemFragment fragment = new NewItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOSTORFOUND, lostOrFound);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.lostOrFound = getArguments().getString(ARG_LOSTORFOUND);
        }
        itemData = new ItemData(getActivity().getApplicationContext());
        itemData.open();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_item, container, false);

        itemName = (EditText) view.findViewById(R.id.editTextKind);
        transport = (Spinner) view.findViewById(R.id.transportSpinner);
        transportLine = (Spinner) view.findViewById(R.id.lineSpinner);
        date = (EditText) view.findViewById(R.id.editTextDate);
        hour = (EditText) view.findViewById(R.id.editTextTime);
        description = (EditText) view.findViewById(R.id.editTextDescription);
        publicaBtn = (Button) view.findViewById(R.id.publicaBtn);

        ArrayAdapter<CharSequence> adapterTransport = ArrayAdapter.createFromResource(getActivity(), R.array.transports_array, android.R.layout.simple_spinner_item);
        adapterTransport.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        transport.setAdapter(adapterTransport);

        transport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                ArrayAdapter<CharSequence> adapterLines;
                if (item.toString().equals("Metro")) {
                    adapterLines = ArrayAdapter.createFromResource(getActivity(), R.array.metroLines_array, android.R.layout.simple_spinner_item);
                }
                else {
                    adapterLines = ArrayAdapter.createFromResource(getActivity(), R.array.busLines_array, android.R.layout.simple_spinner_item);
                }
                adapterLines.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                transportLine.setAdapter(adapterLines);
                eTransport = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        transportLine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                eTransportLine = item.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        date.setText(selectedday + "/" + (selectedmonth+1) + "/" + selectedyear);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                //mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if (hourOfDay < 10 && minute >= 10) {
                                hour.setText("0" + hourOfDay + ":" + minute);
                            }
                            else if (hourOfDay>=10 && minute < 10) {
                                hour.setText(hourOfDay + ":" + "0" + minute);
                            }
                            else if (hourOfDay < 10 && minute < 10) {
                                hour.setText("0" + hourOfDay + ":" + "0" + minute);
                            }
                            else hour.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        publicaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eName = itemName.getText().toString();
                String eDate = date.getText().toString();
                String eTime = hour.getText().toString();
                String eDescription = description.getText().toString();

                if (lostOrFound.equals("lost")) {
                    if (Objects.equals(eName, "")) {
                        itemName.setError("Què has perdut?");
                    }
                    if (Objects.equals(eDate, "")) {
                        date.setError("Quin dia ho vas perdre?");
                    }
                    if (Objects.equals(eTime, "")) {
                        hour.setError("A quina hora ho vas perdre?");
                    }
                    if (Objects.equals(eDescription, "")) {
                        description.setError("Digues almenys un lloc de recollida.");
                    }
                }
                else {
                    if (Objects.equals(eName, "")) {
                        itemName.setError("Què has trobat?");
                    }
                    if (Objects.equals(eDate, "")) {
                        date.setError("Quin dia ho vas trobar?");
                    }
                    if (Objects.equals(eTime, "")) {
                        hour.setError("A quina hora ho vas trobar?");
                    }
                    if (Objects.equals(eDescription, "")) {
                        description.setError("Digues almenys un lloc de recollida.");
                    }
                }
            }
        });

        return view;
    }
}
