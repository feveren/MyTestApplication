package com.rent.mytestapplication;

import android.content.res.ColorStateList;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.text_title)
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new TextFragment()).commitAllowingStateLoss();

        textTitle.setTextColor(buildSelector(0xff000000, getResources().getColor(android.R.color.holo_blue_bright)));
        textTitle.setBackgroundDrawable(build());
    }

    private ColorStateList buildSelector(int defaultColor, int selectedColor) {
        int[][] states = new int[2][];
        // 负号代表false，否则为true
        states[0] = new int[] { -android.R.attr.state_selected };
        states[1] = new int[] { android.R.attr.state_selected };
        int[] colors = new int[2];
        colors[0] = defaultColor;
        colors[1] = selectedColor;
        return new ColorStateList(states, colors);
    }

    private StateListDrawable build() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[] { -android.R.attr.state_selected }, getResources().getDrawable(android.R.color.darker_gray));
        stateListDrawable.addState(new int[] { android.R.attr.state_selected }, getResources().getDrawable(android.R.color.holo_blue_bright));
        return stateListDrawable;
    }

    @OnClick(R.id.btn_submit)
    public void onClick() {
        textTitle.setSelected(!textTitle.isSelected());
        textTitle.setText("button clicked, isSelected() current is " + textTitle.isSelected());
    }

    @Optional
    @OnClick(R.id.top_sheet)
    public void testNullClick() {}

    class TextFragment extends Fragment {
        @BindView(R.id.text_title)
        TextView textTitle;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.activity_butter_knife, container, false);
            ButterKnife.bind(this, root);
            return root;
        }

        @OnClick(R.id.btn_submit)
        public void onClick() {
            textTitle.setText("button clicked in Fragment");
        }
    }
}
