package tinfoilxd.geoquiz;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class QuizActivity extends ActionBarActivity
{
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]
            {
                    new TrueFalse(R.string.question_oceans, true),
                    new TrueFalse(R.string.question_mideast,false),
                    new TrueFalse(R.string.question_africa,false),
                    new TrueFalse(R.string.question_americas,true),
                    new TrueFalse(R.string.question_asia,true)
            };

    private int mCurrentIndex;
    private boolean mIsCheater;

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //setting up default values
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB)
           getSupportActionBar().setSubtitle(R.string.action_bar_subtitle);

        //setting up member variables
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        //setting up buttons
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mPreviousButton = (ImageButton)findViewById(R.id.previous_button);

        //settuping up button listeners
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //you have to specify QuizActivity.this because this right now refers to the anonymous inner class
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                checkAnswer(false);
            }
        });
        mCheatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(QuizActivity.this,CheatActivity.class);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,mQuestionBank[mCurrentIndex].isAnswer());
                startActivityForResult(i, 0);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCurrentIndex = (mCurrentIndex - 1);
                if(mCurrentIndex == -1)
                    mCurrentIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });
        //setting up textview listener
        mQuestionTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        //checking bundle for any saved values after setup
        if(savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
       // super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    private void updateQuestion()
    {
        Log.d(TAG,String.format("Updating question text for question #%d",mCurrentIndex), new Exception());
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userAnswer)
    {
        int toastMessage;
        if(mIsCheater)
            toastMessage = R.string.judgement_toast;
        else
        {
            if (userAnswer == mQuestionBank[mCurrentIndex].isAnswer())
                toastMessage = R.string.correct_toast;
            else
                toastMessage = R.string.incorrect_toast;
        }
        Toast.makeText(this,toastMessage,Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
