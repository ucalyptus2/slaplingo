package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.0 */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zze();
    public int landmarkType;
    public int mode;
    public float proportionalMinFaceSize;
    public boolean trackingEnabled;
    public boolean zzcv;
    public int zzcw;

    public zzf() {
    }

    public zzf(int i, int i2, int i3, boolean z, boolean z2, float f) {
        this.mode = i;
        this.landmarkType = i2;
        this.zzcw = i3;
        this.zzcv = z;
        this.trackingEnabled = z2;
        this.proportionalMinFaceSize = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.mode);
        SafeParcelWriter.writeInt(parcel, 3, this.landmarkType);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcw);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcv);
        SafeParcelWriter.writeBoolean(parcel, 6, this.trackingEnabled);
        SafeParcelWriter.writeFloat(parcel, 7, this.proportionalMinFaceSize);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
