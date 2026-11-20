package com.seven17777.session2;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int count;
    private TextView counter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count = getPreferences(MODE_PRIVATE).getInt("count", 0);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(48, 48, 48, 48);
        layout.setBackgroundColor(Color.rgb(241, 245, 249));
        layout.setOnApplyWindowInsetsListener((v, insets) -> {
            v.setPadding(48 + insets.getSystemWindowInsetLeft(),
                    48 + insets.getSystemWindowInsetTop(),
                    48 + insets.getSystemWindowInsetRight(),
                    48 + insets.getSystemWindowInsetBottom());
            return insets;
        });
        TextView title = new TextView(this);
        title.setText("Session 2\nAndroid CI/CD Demo");
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.rgb(15, 23, 42));
        layout.addView(title);
        TextView author = new TextView(this);
        author.setText("Seven17777 | Built by GitHub Actions");
        author.setGravity(Gravity.CENTER);
        layout.addView(author);
        counter = new TextView(this);
        counter.setTextSize(64);
        counter.setGravity(Gravity.CENTER);
        layout.addView(counter);
        Button add = new Button(this);
        add.setText("Add one / 加一");
        add.setOnClickListener(v -> { count++; saveAndShow(); });
        layout.addView(add);
        Button reset = new Button(this);
        reset.setText("Reset / 重置");
        reset.setOnClickListener(v -> { count = 0; saveAndShow(); });
        layout.addView(reset);
        setContentView(layout);
        layout.requestApplyInsets();
        counter.setText(String.valueOf(count));
    }

    private void saveAndShow() {
        getPreferences(MODE_PRIVATE).edit().putInt("count", count).apply();
        counter.setText(String.valueOf(count));
    }
}
