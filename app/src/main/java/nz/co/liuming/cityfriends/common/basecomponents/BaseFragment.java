package nz.co.liuming.cityfriends.common.basecomponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import nz.co.liuming.cityfriends.R;
import rx.Subscription;

/**
 * Created by liuming on 15/06/16.
 */
public class BaseFragment extends Fragment {

    @Nullable
    @BindView(R.id.progress)
    View mLoadingView;

    private Subscription mSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new TextView(getContext());
    }

    public String getTabTitle() {
        return "Base";
    }

    public void navigateToFragment(Fragment fragment) {
        navigateToFragment(fragment, false, false);
    }


    public void navigateToFragment(Fragment fragment, boolean animIn, boolean animOut) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        if (animIn && animOut) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (animIn) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (animOut) {
            fragmentTransaction.setCustomAnimations(0, 0, android.R.anim.fade_in, android.R.anim.fade_out);
        }

        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    protected Subscription doLoadData() {
        return null;
    }

    public void loadData() {
        unSubscribe();
        mSubscription = doLoadData();
    }

    private void unSubscribe() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    protected void showLoadingView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    protected void hideLoadingView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unSubscribe();
    }
}
