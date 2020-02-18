package com.example.harihara_medicals.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.harihara_medicals.HomePageActivity;
import com.example.harihara_medicals.R;
import com.example.harihara_medicals.Adapters.SliderAdapter;
import com.example.harihara_medicals.ui.Book_Dr.BookdrFragment;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SliderView sliderView;
    TextView text1;
    Button btn1,btn2,btn_menu;
    ImageView img1,img2,img3,img4,home_menu;
    RelativeLayout layout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
       /* homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
       sliderView =root.findViewById(R.id.imageSlider);
         final SliderAdapter  adapter = new SliderAdapter(root.getContext());
         adapter.setCount(3);
         sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
        text1=root.findViewById(R.id.home_doctor_visit);
        home_menu = root.findViewById(R.id.home_menu);
        home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity activity = (HomePageActivity) getActivity();
                if(activity!=null){
                    if(activity.drawerLayout.isDrawerOpen(GravityCompat.START)){
                        activity.drawerLayout.closeDrawer(GravityCompat.START);
                    }else {
                        activity.drawerLayout.openDrawer(GravityCompat.START);
                    }
                }
            }
        });
        img3=root.findViewById(R.id.home_doctor_visit_yes);
        img4=root.findViewById(R.id.home_doctor_visit_no);
        img1=root.findViewById(R.id.home_visit_dr1);
        img2=root.findViewById(R.id.home_visit_dr2);
        layout=root.findViewById(R.id.home_visit_layout);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setVisibility(View.GONE);
                img1.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.move_left);
                img2.startAnimation(animation1);
                Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                img3.startAnimation(animation2);
                Animation animation3 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in );
                img4.startAnimation(animation3);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog =new Dialog(getActivity());
                dialog.setContentView(R.layout.home_dailog_box);
                Button uplpadbtn=dialog.findViewById(R.id.home_dialog_upload);
                uplpadbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"upload done",Toast.LENGTH_SHORT).show();
                        text1.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.GONE);
                        img1.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                Button canclebtn=dialog.findViewById(R.id.home_dialog_cancel);
                canclebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"upload cancle",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new BookdrFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.nav_host_fragment, newFragment);

                transaction.commit();
              /* Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.move_left);
                img1.startAnimation(animation1);
                Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.move_left);
                text1.startAnimation(animation2);*/
            }
        });


        return root;
    }
}