package com.wq.freeze.easyseparatorexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        assert recyclerView != null;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter());

        recyclerView.addItemDecoration(
                new EasySeparator.Builder(this)
                        .color(R.color.colorPrimaryDark)
                        .decorationHeight(2)
                        .decorationMargin(20)
                        .orientation(LinearLayoutManager.VERTICAL)
                        .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                            @Override
                            public boolean shouldDrawDecoration(int viewType) {
                                return viewType == Adapter.TYPE_A;
                            }
                        })
                        .build()
        );

        recyclerView.addItemDecoration(
                new EasySeparator.Builder(this)
                        .color(R.color.colorAccent)
                        .decorationHeight(8)
                        .decorationStartMargin(0)
                        .decorationEndMargin(40)
                        .orientation(LinearLayoutManager.VERTICAL)
                        .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                            @Override
                            public boolean shouldDrawDecoration(int viewType) {
                                return viewType == Adapter.TYPE_B;
                            }
                        })
                        .build()
        );

        recyclerView.addItemDecoration(
                new EasySeparator.Builder(this)
                        .drawable(R.drawable.separator_1)
                        .decorationHeight(20)
                        .decorationStartMargin(0)
                        .decorationEndMargin(0)
                        .orientation(LinearLayoutManager.VERTICAL)
                        .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                            @Override
                            public boolean shouldDrawDecoration(int viewType) {
                                return viewType == Adapter.TYPE_C;
                            }
                        })
                        .build()
        );

        recyclerView.addItemDecoration(
                new EasySeparator.Builder(this)
                        .drawable(R.drawable.indeterminate_0)
                        .decorationHeight(20)
                        .decorationStartMargin(20)
                        .decorationEndMargin(20)
                        .orientation(LinearLayoutManager.VERTICAL)
                        .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                            @Override
                            public boolean shouldDrawDecoration(int viewType) {
                                return viewType == Adapter.TYPE_D;
                            }
                        })
                        .build()
        );

        recyclerView.addItemDecoration(
                new EasySeparator.Builder(this)
                        .vectorDrawable(R.drawable.ic_drag_handle_black_24dp, null)
                        .decorationHeight(20)
                        .decorationStartMargin(0)
                        .decorationEndMargin(0)
                        .orientation(LinearLayoutManager.VERTICAL)
                        .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                            @Override
                            public boolean shouldDrawDecoration(int viewType) {
                                return viewType == Adapter.TYPE_E;
                            }
                        })
                        .build()
        );

        recyclerView.addItemDecoration(
                new EasySeparator.Builder(this)
                        .color(R.color.colorPrimaryDark)
                        .decorationHeight(8)
                        .orientation(LinearLayoutManager.VERTICAL)
                        .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                            @Override
                            public boolean shouldDrawDecoration(int viewType) {
                                return viewType == Adapter.TYPE_F;
                            }
                        })
                        .build()
        );

        recyclerView.addItemDecoration(
                new EasySeparator.Builder(this)
                        .color(R.color.colorAccent)
                        .decorationHeight(8)
                        .translation(8)
                        .orientation(LinearLayoutManager.VERTICAL)
                        .shouldDrawChecker(new EasySeparator.ShouldDrawChecker() {
                            @Override
                            public boolean shouldDrawDecoration(int viewType) {
                                return viewType == Adapter.TYPE_F;
                            }
                        })
                        .build()
        );
    }

    private class Adapter extends RecyclerView.Adapter<SimpleHolder> {

        public static final int TYPE_A = 0;
        public static final int TYPE_B = 1;
        public static final int TYPE_C = 2;
        public static final int TYPE_D = 3;
        public static final int TYPE_E = 4;
        public static final int TYPE_F = 5;

        @Override
        public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
        }

        @Override
        public void onBindViewHolder(SimpleHolder holder, int position) {
            int itemViewType = holder.getItemViewType();
            TextView itemView = (TextView) holder.itemView;
            switch (itemViewType) {
                case TYPE_A:
                    itemView.setText("TYPE_A");
                    break;
                case TYPE_B:
                    itemView.setText("TYPE_B");
                    break;
                case TYPE_C:
                    itemView.setText("TYPE_C");
                    break;
                case TYPE_D:
                    itemView.setText("TYPE_D");
                    break;
                case TYPE_E:
                    itemView.setText("TYPE_E");
                    break;
                case TYPE_F:
                    itemView.setText("TYPE_F");
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return 12;
        }

        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                case 1:
                    return TYPE_A;
                case 2:
                case 3:
                    return TYPE_B;
                case 4:
                case 5:
                    return TYPE_C;
                case 6:
                case 7:
                    return TYPE_D;
                case 8:
                case 9:
                    return TYPE_E;
                default:
                    return TYPE_F;
            }
        }
    }

    static class SimpleHolder extends RecyclerView.ViewHolder {

        public SimpleHolder(View itemView) {
            super(itemView);
        }
    }
}
