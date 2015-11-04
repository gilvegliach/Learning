package it.gilvegliach.learning.threeviewslinearlayoutmanager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThreeViewsLLM;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new ThreeViewsLLM(this, ThreeViewsLLM.HORIZONTAL, false));
        rv.setAdapter(new MyAdapter());
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        final int[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = new View(viewGroup.getContext());
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT);
            v.setLayoutParams(lp);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            int color = colors[i % colors.length];
            viewHolder.itemView.setBackgroundColor(color);
        }

        @Override
        public int getItemCount() {
            return 8;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
