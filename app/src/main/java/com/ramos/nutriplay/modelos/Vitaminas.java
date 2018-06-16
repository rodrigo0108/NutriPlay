package com.ramos.nutriplay.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Vitaminas implements Parcelable{
    private String vit_a,vit_c,vit_d;

    public Vitaminas() {
    }

    public Vitaminas(String vit_a, String vit_c, String vit_d) {
        this.vit_a = vit_a;
        this.vit_c = vit_c;
        this.vit_d = vit_d;
    }

    public String getVit_a() {
        return vit_a;
    }

    public void setVit_a(String vit_a) {
        this.vit_a = vit_a;
    }

    public String getVit_c() {
        return vit_c;
    }

    public void setVit_c(String vit_c) {
        this.vit_c = vit_c;
    }

    public String getVit_d() {
        return vit_d;
    }

    public void setVit_d(String vit_d) {
        this.vit_d = vit_d;
    }

    @Override
    public String toString() {
        return "Vitaminas{" +
                "vit_a='" + vit_a + '\'' +
                ", vit_c='" + vit_c + '\'' +
                ", vit_d='" + vit_d + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vit_a);
        dest.writeString(vit_c);
        dest.writeString(vit_d);
    }


    public Vitaminas(Parcel in) {
        vit_a = in.readString();
        vit_c = in.readString();
        vit_d = in.readString();
    }

    public static final Creator<Vitaminas> CREATOR = new Creator<Vitaminas>() {
        @Override
        public Vitaminas createFromParcel(Parcel in) {
            return new Vitaminas(in);
        }

        @Override
        public Vitaminas[] newArray(int size) {
            return new Vitaminas[size];
        }
    };
}
