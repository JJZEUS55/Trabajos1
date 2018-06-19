package net.gshp.p3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class BottomActivity extends AppCompatActivity {
    private BottomNavigationView botView;
    private Fragment fragmento;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bot);

        botView = findViewById(R.id.bottom_navigation);
        botView.inflateMenu(R.menu.bottom_menu);
        fragmentManager = getSupportFragmentManager();
        botView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_mapa:
                        fragmento = new MapsActivity();
                        break;
                    case R.id.action_datosbd:
                        //Llamar fragmento datos
                        break;
                }
                final android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragmento).commit();

                return true;
            }
        });
    }
}
