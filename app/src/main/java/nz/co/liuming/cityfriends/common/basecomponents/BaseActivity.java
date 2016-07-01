package nz.co.liuming.cityfriends.common.basecomponents;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import nz.co.liuming.cityfriends.R;

/**
 * Created by liuming on 15/06/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
