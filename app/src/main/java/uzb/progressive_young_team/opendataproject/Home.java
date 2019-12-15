package uzb.progressive_young_team.opendataproject;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uzb.progressive_young_team.opendataproject.authentication.AuthenticationActivity;
import uzb.progressive_young_team.opendataproject.authentication.LoginRegisterActivity;

public class Home extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Set generic layout parameters

        if( firebaseUser != null ) {
            Intent intent  = new Intent(Home.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent  = new Intent(Home.this, LoginRegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
