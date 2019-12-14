package uzb.progressive_young_team.opendataproject;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uzb.progressive_young_team.opendataproject.authentication.AuthenticationActivity;

public class Home extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if( firebaseUser != null ) {
            startActivity(new Intent(Home.this, MainActivity.class));
        } else {
            startActivity(new Intent(Home.this, AuthenticationActivity.class));
        }
    }
}
