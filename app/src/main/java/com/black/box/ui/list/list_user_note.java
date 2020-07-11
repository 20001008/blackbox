package com.black.box.ui.list;

import java.util.ArrayList;
import java.util.List;

public class list_user_note {
    private int Icon;
    private String Name;
    private List<list_user_note> data=new ArrayList<>();
    public list_user_note(int icon,String Name)
    {
        this.Icon=icon;
        this.Name=Name;
    }

    public int getIcon() {
        return Icon;
    }

    public String getName() {
        return Name;
    }
}
