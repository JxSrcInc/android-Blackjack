package edu.nyu.cs101.android.li.michael;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class DisplayMessageActivity extends AppCompatActivity implements View.OnClickListener{

    private Player player = new Player();
    private Dealer dealer = new Dealer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Button hit = (Button) findViewById(R.id.buttonHit);
        hit.setOnClickListener(this); // calling onClick() method
        Button stand = (Button) findViewById(R.id.buttonStand);
        stand.setOnClickListener(this);
        Button next = (Button) findViewById(R.id.buttonContinue);
        next.setOnClickListener(this);

// Get the Intent that started this activity and extract the string
//Intent intent = getIntent();
String message = "Player Cards: "+player.getCards();//intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

// Capture the layout's TextView and set the string as its text
TextView textView = findViewById(R.id.playerView);
textView.setText(message);
    }

    @Override
    public void onClick(View view) {
        TextView playerView = findViewById(R.id.playerView);
        TextView dealerView = findViewById(R.id.dealerView);
        TextView resultView = findViewById(R.id.resultView);
        switch (view.getId()) {
            // player hit
            case R.id.buttonHit:
                if(!player.isBust() && player.isAskToHit()) {
                    player.action(player.HIT);
                    playerView.setText("Player Cards: "+player.getCards());
                }
                if(player.isBust()) {
                    getResult(dealerView, resultView);
                }
                break;
            // player stand
            case R.id.buttonStand:
                player.action(player.STAND);
                dealer.action();
                getResult(dealerView, resultView);
                break;
            // new game
            default:
                player.reset();
                dealer.reset();
                resultView.setText("Result:");
                dealerView.setText("Deaer Cards:");
                playerView.setText("Player Cards: "+player.getCards());
        }

    }

    private void getResult(TextView dealerView, TextView resultView) {
        dealerView.setText("Dealer Cards: "+dealer.getCards());
        if(player.isBust()) {
            resultView.setText("You went bust with "+(player.getTotal()+
                    ". Dealer wins with "+dealer.getTotal()));
        } else
        if(dealer.isBust()) {
            resultView.setText("Dealer went bust with "+(dealer.getTotal()+
                    ". You win with "+player.getTotal()));
        } else
        if(player.getTotal() > dealer.getTotal()) {
            resultView.setText("You win "+(player.getTotal()-dealer.getTotal()));
        } else
        if(dealer.getTotal() > player.getTotal()) {
            resultView.setText("Dealer win "+(dealer.getTotal()-player.getTotal()));
        } else {
            resultView.setText("It's a tie at "+player.getTotal());
        }
    }
}
