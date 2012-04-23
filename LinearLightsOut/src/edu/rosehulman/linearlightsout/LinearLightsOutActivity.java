package edu.rosehulman.linearlightsout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LinearLightsOutActivity extends Activity {

    private LightsOutGame mGame;
    private TextView mGameStateTextView;
    private Button[] mButtons;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mGameStateTextView = (TextView) findViewById(R.id.game_state_text_view);

        mButtons = new Button[] { (Button) findViewById(R.id.button0),
                (Button) findViewById(R.id.button1), (Button) findViewById(R.id.button2),
                (Button) findViewById(R.id.button3), (Button) findViewById(R.id.button4),
                (Button) findViewById(R.id.button5), (Button) findViewById(R.id.button6), };

        mGame = new LightsOutGame(mButtons.length);

        for (int i = 0; i < mButtons.length; i++) {
            final int index = i;

            mButtons[i].setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mGame.pressedButtonAtIndex(index);
                    updateDisplay();
                }

            });
        }

        ((Button) findViewById(R.id.new_game_button)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mGame = new LightsOutGame();
                updateDisplay();
            }

        });

        mGame = new LightsOutGame();
        updateDisplay();
    }

    private void updateDisplay() {
        if (mGame.checkForWin()) {
            mGameStateTextView.setText(getString(R.string.you_win));
        } else {
            mGameStateTextView.setText(getString(R.string.instructions));
        }

        for (int i = 0; i < mButtons.length; i++) {
            mButtons[i].setText(mGame.getValueAtIndex(i) == 0 ? getString(R.string.off_symbol)
                    : getString(R.string.on_symbol));
        }
    }
}