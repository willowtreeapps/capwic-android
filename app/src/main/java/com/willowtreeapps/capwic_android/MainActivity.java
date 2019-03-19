package com.willowtreeapps.capwic_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {

    private String KEY_CAPWIC = "capwic-jmu-2019";
    private String KEY_TEAMDATA = "teamdata";
    private String KEY_TEAM_ANDROID = "TeamAndroid";

    /**
     * This is your team's data which is sent to your path in the Realtime Database.
     */
    private TeamAndroid teamAndroid;

    private DatabaseReference database;
    private Button readyButton;
    private TextView readyFromDatabaseTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.database = FirebaseDatabase.getInstance().getReference();

        instantiateResources();
    }

    private void instantiateResources() {
        this.readyFromDatabaseTextView = findViewById(R.id.ready_from_database_text_view);
        this.readyButton = findViewById(R.id.ready_button);

        setRealtimeDatabaseTeamListener();
        setReadyButtonListener();
    }

    /**
     * This method sets up a listener to a specified path in the Realtime Database.
     * When the Realtime Database is changed, the {@link ValueEventListener#onDataChange(DataSnapshot)}
     * method is called.
     */
    private void setRealtimeDatabaseTeamListener() {
        DatabaseReference myTeamDatabaseReference = this.database.child(KEY_CAPWIC)
                .child(KEY_TEAMDATA)
                .child(KEY_TEAM_ANDROID);

        ValueEventListener teamListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                TeamAndroid team = data.getValue(TeamAndroid.class);

                if (team != null) {
                    if (team.getIsReady()) {
                        readyFromDatabaseTextView.setText(getString(R.string.true_text));
                        teamAndroid = team;
                    } else {
                        readyFromDatabaseTextView.setText(getString(R.string.false_text));
                        teamAndroid = team;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myTeamDatabaseReference.addValueEventListener(teamListener);
    }

    private void setReadyButtonListener() {
        this.readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teamAndroid != null) {
                    MainActivity.this.teamAndroid.setReady(!teamAndroid.isReady);

                    database.child(KEY_CAPWIC)
                            .child(KEY_TEAMDATA)
                            .child(KEY_TEAM_ANDROID)
                            .setValue(teamAndroid);
                } else {
                    MainActivity.this.teamAndroid = new TeamAndroid();

                    database.child(KEY_CAPWIC)
                            .child(KEY_TEAMDATA)
                            .child(KEY_TEAM_ANDROID)
                            .setValue(teamAndroid);
                }
            }
        });
    }
}
