package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.2.0 */
public final class zzcwe implements zzela<zzcwa> {
    private final zzelj<Context> zzere;
    private final zzelj<zzcae> zzgkp;

    public zzcwe(zzelj<Context> zzelj, zzelj<zzcae> zzelj2) {
        this.zzere = zzelj;
        this.zzgkp = zzelj2;
    }

    @Override // com.google.android.gms.internal.ads.zzelj
    public final /* synthetic */ Object get() {
        return new zzcwa(this.zzere.get(), this.zzgkp.get());
    }
}
