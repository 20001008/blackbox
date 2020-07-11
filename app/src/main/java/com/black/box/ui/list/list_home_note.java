package com.black.box.ui.list;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

public class list_home_note {
    private String User;
    private String Time;
    private String Tittle;
    private String SubTittle;
    private Drawable head;
    private Drawable t1;
    private Drawable t2;
    private Drawable t3;
    public list_home_note(String User, String Time, String Tittle, String SubTittle, @Nullable Drawable head, @Nullable Drawable t1,@Nullable Drawable t2,@Nullable Drawable t3)
    {
        this.head=head;
        this.t1=t1;
        this.t2=t2;
        this.t3=t3;
        this.User=User;
        this.Time=Time;
        this.Tittle=Tittle;
        this.SubTittle=SubTittle;
    }

    public String getTittle() {
        return Tittle;
    }

    public String getTime() {
        return Time;
    }

    public Drawable getHead() {
        return head;
    }

    public Drawable getT1() {
        return t1;
    }

    public Drawable getT2() {
        return t2;
    }

    public Drawable getT3() {
        return t3;
    }

    public String getUser() {
        return User;
    }

    public String getSubTittle() {
        return SubTittle;
    }
}
