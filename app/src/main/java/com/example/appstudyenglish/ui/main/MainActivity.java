package com.example.appstudyenglish.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityMainBinding;
import com.example.appstudyenglish.ui.fragment.home.HomeFragment;
import com.example.appstudyenglish.ui.fragment.khoa_hoc_cua_ban.KhoaHocCuaBanFragment;
import com.example.appstudyenglish.ui.fragment.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeFragment homeFragment;
    private int check;
    private long backPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        onCLickMenu();
        check = getIntent().getIntExtra("check",0);
        if(check == 1){
            homeFragment = new HomeFragment();
            getFragment(homeFragment);
            binding.bottomNav.getMenu().findItem(R.id.homeFragment).setChecked(true);
        }else {
            getFragment(new KhoaHocCuaBanFragment());
            binding.bottomNav.getMenu().findItem(R.id.bookmarkFragment).setChecked(true);
        }
    }

    private void onCLickMenu() {
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case  R.id.bookmarkFragment :
                    getFragment(new KhoaHocCuaBanFragment());
                    binding.bottomNav.getMenu().findItem(R.id.bookmarkFragment).setChecked(true);
                    break;
                case  R.id.homeFragment :
                    binding.bottomNav.getMenu().findItem(R.id.homeFragment).setChecked(true);
                    getFragment(new HomeFragment());
                    break;
                case  R.id.userFragment :
                    binding.bottomNav.getMenu().findItem(R.id.userFragment).setChecked(true);
                    getFragment(new ProfileFragment());
                    break;
            }
            return false;
        });
    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMain,fragment,Fragment.class.getName())
                .commit();
    }
    @Override
    public void onBackPressed() {
        if(backPressTime +2000 > System.currentTimeMillis()){
            finishAffinity();
            System.exit(0);
            return;
        }else {
            Toast.makeText(this,"Nhấn 2 lần để thoát app",Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }
}