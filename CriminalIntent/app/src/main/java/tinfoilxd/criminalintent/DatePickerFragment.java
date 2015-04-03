package tinfoilxd.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment
{
    public static final String EXTRA_DATE = "tinfoilxd.crimintalintent.date";
    private Date mDate;

    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE,date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mDate = (Date)getArguments().getSerializable(DatePickerFragment.EXTRA_DATE);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        //inflates the dialogfragment and creates it. This method is used by the show method in crimefragment
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);
        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_datePicker);

        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                mDate = new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE,mDate);


            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setTitle(R.string.date_picker_title)
                .create();
    }
    private void sendResult(int resultCode)
    {
        //getTargetFragment() is set in crimefragment
        if(getTargetFragment() == null)
            return;

        //creates an intent to return to crimefragment with the extra_date as an extra
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE,mDate);

        //sets result of requestcode 0 and resultcode 0 as i
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode, i);
    }
}
