package nz.co.liuming.cityfriends.users;

import android.os.Bundle;
import android.os.PersistableBundle;

import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseActivity;
import nz.co.liuming.cityfriends.common.utils.FragmentManagerUtils;
import nz.co.liuming.cityfriends.users.fragments.LoginFragment;

/**
 * Created by liuming on 21/06/16.
 */
public class LoginActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        FragmentManagerUtils.addFragment(this,LoginFragment.newInstance(),"LoginFragment",FragmentManagerUtils.Animation.NONE);
    }
}
