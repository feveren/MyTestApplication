package com.rent.mytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StableIDsActivity extends AppCompatActivity {
    private List<Model> mData;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stable_ids);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle("ListView StableIDs");
        setSupportActionBar(toolbar);

        ListView listView = (ListView) this.findViewById(R.id.list_view);
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Model model = new Model();
            model.id = i + 1;
            model.name = "name_" + model.id;
            model.desc = "desc_" + model.id;
            mData.add(model);
        }
        listView.setAdapter(mAdapter = new ListAdapter(mData));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stable_ids_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                mData.get(3).name += "_updated";
                mData.get(5).desc += "_updated";
                mAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ListAdapter extends BaseAdapter {
        private List<Model> mData;

        public ListAdapter(List<Model> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            System.out.println("getItem " + position + ", hashCode " + mData.get(position).hashCode());
            return mData.get(position).hashCode();
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(StableIDsActivity.this).inflate(R.layout.name_desc_item, parent, false);
                holder = new ViewHolder();
                holder.textName = (TextView) convertView.findViewById(R.id.name);
                holder.textDesc = (TextView) convertView.findViewById(R.id.desc);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Model model = mData.get(position);
            holder.textName.setText(model.name);
            holder.textDesc.setText(model.desc);
            System.out.println("getView position: " + position + ", " + model.toString());
            return convertView;
        }

        class ViewHolder {
            TextView textName;
            TextView textDesc;
        }
    }

    private class Model {
        public int id;
        public String name;
        public String desc;

        @Override
        public String toString() {
            return "Model{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (desc != null ? desc.hashCode() : 0);
            return result;
        }
    }
}
