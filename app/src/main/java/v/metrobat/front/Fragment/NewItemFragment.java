package v.metrobat.front.Fragment;


import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
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

                View focusView = null;
                boolean cancel = false;

                if (lostOrFound.equals("lost")) {
                    if (TextUtils.isEmpty(eDescription)) {
                        description.setError("Digues almenys un lloc de recollida.");
                        focusView = description;
                        cancel = true;
                    }
                    if (TextUtils.isEmpty(eTime)) {
                        hour.setError("A quina hora ho vas perdre?");
                        focusView = hour;
                        cancel = true;
                    }
                    if (TextUtils.isEmpty(eDate)) {
                        date.setError("Quin dia ho vas perdre?");
                        focusView = date;
                        cancel = true;
                    }
                    if (TextUtils.isEmpty(eName)) {
                        itemName.setError("Què has perdut?");
                        focusView = itemName;
                        cancel = true;
                    }
                }
                else {
                    if (TextUtils.isEmpty(eDescription)) {
                        description.setError("Digues almenys un lloc de recollida.");
                        focusView = hour;
                        cancel = true;
                    }
                    if (TextUtils.isEmpty(eTime)) {
                        hour.setError("A quina hora ho vas trobar?");
                        focusView = hour;
                        cancel = true;
                    }
                    if (TextUtils.isEmpty(eDate)) {
                        date.setError("Quin dia ho vas trobar?");
                        focusView = date;
                        cancel = true;
                    }
                    if (TextUtils.isEmpty(eName)) {
                        itemName.setError("Què has trobat?");
                        focusView = itemName;
                        cancel = true;
                    }
                }
                if (cancel) {
                    focusView.requestFocus();
                }
                else {
                    itemData.createItem(lostOrFound,
                            eName,
                            eTransport,eTransportLine,
                            eDate,eTime,
                            eDescription);
                    itemName.setText("");
                    transport.setSelection(0);
                    transportLine.setSelection(0);
                    date.setText("");
                    hour.setText("");
                    description.setText("");

                    InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                    ObjectsAdsListFragment fragment = new ObjectsAdsListFragment();
                    fm.beginTransaction().replace(R.id.content_navigation, fragment).commit();

                }

            }
        });

        return view;
    }
}
