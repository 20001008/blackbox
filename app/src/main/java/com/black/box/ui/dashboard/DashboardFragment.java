package com.black.box.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.black.box.R;

import java.util.ArrayList;
import java.util.List;

import com.black.box.ui.list.list_user_helper;
import com.black.box.ui.list.list_user_note;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<list_user_note> data=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView=root.findViewById(R.id.bankuan_rec);
        data.add(new list_user_note(R.mipmap.iconmonstr_document_thin_24,"聘用信息"));
        data.add(new list_user_note(R.mipmap.iconmonstr_document_thin_24,"求职版块"));
        data.add(new list_user_note(R.mipmap.iconmonstr_document_thin_24,"经验之谈"));
        data.add(new list_user_note(R.mipmap.iconmonstr_document_thin_24,"生活分享"));
        data.add(new list_user_note(R.mipmap.iconmonstr_document_thin_24,"鱼塘"));
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL,false));
        list_user_helper user_helper=new list_user_helper(R.layout.layout_list_user,data);
        
        recyclerView.setAdapter(user_helper);
        return root;
    }
}