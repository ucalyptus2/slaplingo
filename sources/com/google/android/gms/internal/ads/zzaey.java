package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.2.0 */
public abstract class zzaey extends zzgt implements zzaez {
    public zzaey() {
        super("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    public static zzaez zzu(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        if (queryLocalInterface instanceof zzaez) {
            return (zzaez) queryLocalInterface;
        }
        return new zzafb(iBinder);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgt
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaep zzaep;
        if (i != 1) {
            return false;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            zzaep = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
            if (queryLocalInterface instanceof zzaep) {
                zzaep = (zzaep) queryLocalInterface;
            } else {
                zzaep = new zzaer(readStrongBinder);
            }
        }
        zza(zzaep, parcel.readString());
        parcel2.writeNoException();
        return true;
    }
}
