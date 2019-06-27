package android.jhaniteshkumar.com.tictactoecrosszero;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private ImageView arrow_1;
    private ImageView arrow_2;
//    private TextView mTextViewCountDown;
//    public static final long START_TIME_IN_MILLIS = 600000;
//
//    private CountDownTimer mCountDownTimer;
//    private  boolean mTimerRunning;
//    private  long mTimeLeftInMillis = START_TIME_IN_MILLIS;
//
//
//    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        //mTextViewCountDown = findViewById(R.id.text_view_countdown);

//        private void startTimer (){
//            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    mTimeLeftInMillis = millisUntilFinished;
//                    updateCountDownText();
//
//                }
//
//                @Override
//                public void onFinish() {
//
//                }
//            }.start();
//        }
//        private void updateCountDownText(){
//            int minutes = (int) (mTimeLeftInMillis /1000)/60;
//            int seconds = (int) (mTimeLeftInMillis/1000) %60 ;
//
//            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes,seconds);
//            mTextViewCountDown.setText(timeLeftFormatted);
//        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        TextView countDownTimer = findViewById(R.id.text_view_countdown);

    }

    @Override
    public void onClick(View v) {
        ImageView imageView1 = (ImageView) findViewById(R.id.arrow_1);
        ImageView imageView2 = (ImageView) findViewById(R.id.arrow_2);
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
            imageView2.setVisibility(View.INVISIBLE);
            imageView1.setVisibility(View.VISIBLE);
        } else {
            ((Button) v).setText("O");
            imageView2.setVisibility(View.VISIBLE);
            imageView1.setVisibility(View.INVISIBLE);
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }
}