package com.hawk.leetcode;


import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;


import static com.hawk.leetcode.Global.TAG;

/**
 * copied from ApiDemos ExpandableList1 sample
 */

public class MainActivity extends ExpandableListActivity {


    ExpandableListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our adapter
        mAdapter = new MyExpandableListAdapter();
        setListAdapter(mAdapter);
        registerForContextMenu(getExpandableListView());
    }
    /**
     * Support LongPress Sub-Menu
     *
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("LongPress Sub-Menu:");
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            Log.i(TAG, "LongPress at Group Item");
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            Log.i(TAG, "LongPress at Child Item");
        } else {
            Log.i(TAG, "LongPress at OTHER space");
        }

        // For long press
        menu.add(0, 0, 0, "type=" + type + ", group=" + group + ", child=" + child);
        menu.add(1, 1, 1, "LongPress Item-1");
        menu.add(1, 2, 2, "LongPress Item-2");
        menu.add(1, 3, 2, "LongPress Item-3");

    }

    /**
     * Single Click on Sub-Menu
     * */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        String title = ((TextView) info.targetView).getText().toString();
        Log.i(TAG, "title=" + title + "item.getItemId()= "+ item.getItemId());
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
            Log.i(TAG, "onContextItemSelected()    groupPos="+groupPos+"    childPos="+childPos);
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos, Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            Log.i(TAG, "onContextItemSelected()    groupPos="+groupPos);
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    /**
     * Single Click on Menu
     * */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "child => group=" + groupPosition + ", child=" + childPosition, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onChildClick()    groupPos="+groupPosition+"    childPos="+childPosition);
        findMatchClass(groupPosition, childPosition);
        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }

    public static boolean findMatchClass(int groupPos, int childPos) {
        String clazz = DataItem.children[groupPos][childPos];
        Class cls= null;
        try {
            // Dynamic Programming
            cls = Class.forName("com.hawk.leetcode.Exams." + clazz);
            Log.e(TAG, "Class found = " + cls.getName());
            Log.e(TAG, "Package = " + cls.getPackage());
            Object obj = cls.newInstance();
            ((BaseClass)obj).test();
            ((BaseClass)obj).result();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids.
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return DataItem.children[groupPosition][childPosition];
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return DataItem.children[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 200);

            TextView textView = new TextView(MainActivity.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPaddingRelative(36, 0, 0, 0);
            // Set the text alignment
            textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            textView.setPadding(140,0,0,0);
            return textView;
        }
        @Override
        public Object getGroup(int groupPosition) {
            return DataItem.groups[groupPosition];
        }
        @Override
        public int getGroupCount() {
            return DataItem.groups.length;
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            textView.setTextSize(40);
            textView.setTextColor(Color.RED);
            textView.setPadding(100,0,0,0);
            return textView;
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}