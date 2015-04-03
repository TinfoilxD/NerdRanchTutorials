package tinfoilxd.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends ActionBarActivity
{
    //define constants
    public static final String EXTRA_ANSWER_IS_TRUE = "tinfoilxd.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "tinfoilxd.geoquiz.answer_shown";

    //define member variables
    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShown;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //initializing default values
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsShown = false;

        //getting extras from intent
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);

        //adding listeners

        mShowAnswerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAnswerIsShown = true;
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                setAnswerShownResult(true);
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    private void setAnswerShownResult(boolean answerShown)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,answerShown);
        setResult(Activity.RESULT_OK,data);
    }
}
