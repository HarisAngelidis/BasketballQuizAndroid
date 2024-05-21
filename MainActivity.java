package com.example.basketball_quiz;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.example.basketball_quiz.UserInterface.AboutFragment;
import com.example.basketball_quiz.UserInterface.DataProcessFragment;
import com.example.basketball_quiz.UserInterface.QueryFragment;
import com.example.basketball_quiz.UserInterface.TablesFragment;
import com.example.basketball_quiz.UserInterface.UI_Menu_Fragment;
import com.example.basketball_quiz.database.MyAppDatabase;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    public static FirebaseFirestore db;

    public static com.example.basketball_quiz.database.MyAppDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},101);


            }



        }

        db = FirebaseFirestore.getInstance();

        fragmentManager = getSupportFragmentManager();
        // Create room database
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),
                MyAppDatabase.class, "QuestionsDB").allowMainThreadQueries().build();

        if (findViewById(R.id.fragment_container) != null) {

            fragmentManager.beginTransaction().add(R.id.fragment_container,
                    new UI_Menu_Fragment()).commit();
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.queries){
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            new QueryFragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawers();
                    return true;
                } else if (menuItem.getItemId() == R.id.process) {
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            new DataProcessFragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawers();
                    return true;
                } else if (menuItem.getItemId() == R.id.home) {
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            new UI_Menu_Fragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawers();
                    return true;
                } else if (menuItem.getItemId() == R.id.about) {
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            new AboutFragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                else if (menuItem.getItemId() == R.id.tables) {
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            new TablesFragment()).addToBackStack(null).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }

                else {
                    return false;
                }
            }
        });
    }


    public static void makeNotif(Context context,String title, String Text){

        String chanelId = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context,chanelId);
        builder.setSmallIcon(R.drawable.action_about);
        builder.setContentTitle(title);
        builder.setContentText(Text);
        builder.setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(chanelId);

            if(notificationChannel == null){

                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(chanelId,"Something",importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);


            }

        }

    notificationManager.notify(0,builder.build());

    }


}