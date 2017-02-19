package io.laterain.inobugs2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.fragment.AboutAlertDialogFragment;

public class AboutActivity extends AppCompatActivity {

    private int mTapCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView imageViewAboutLogo = (ImageView) findViewById(R.id.image_view_about_logo);
        imageViewAboutLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTapCount < 6) {
                    mTapCount++;
                } else {
                    mTapCount = 0;
                    new AboutAlertDialogFragment().show(getSupportFragmentManager(), null);
                }
            }
        });
    }

}
