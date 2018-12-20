package com.android.systemui.statusbar.phone.air;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * SystemUI
 * 数据标题
 */
public class SystemUITitle implements Parcelable {
    private String title;

    public SystemUITitle(String title) {
        this.title = title;
    }

    protected SystemUITitle(Parcel in) {
        title = in.readString();
    }

    public static final Creator<SystemUITitle> CREATOR = new Creator<SystemUITitle>() {
        @Override
        public SystemUITitle createFromParcel(Parcel in) {
            return new SystemUITitle(in);
        }

        @Override
        public SystemUITitle[] newArray(int size) {
            return new SystemUITitle[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
