package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.2.0 */
public final class zzcgp implements zzela<zzcgm> {
    private final zzelj<String> zzfrk;
    private final zzelj<zzcck> zzfvp;
    private final zzelj<zzccd> zzgbk;

    private zzcgp(zzelj<String> zzelj, zzelj<zzccd> zzelj2, zzelj<zzcck> zzelj3) {
        this.zzfrk = zzelj;
        this.zzgbk = zzelj2;
        this.zzfvp = zzelj3;
    }

    public static zzcgp zzm(zzelj<String> zzelj, zzelj<zzccd> zzelj2, zzelj<zzcck> zzelj3) {
        return new zzcgp(zzelj, zzelj2, zzelj3);
    }

    @Override // com.google.android.gms.internal.ads.zzelj
    public final /* synthetic */ Object get() {
        return new zzcgm(this.zzfrk.get(), this.zzgbk.get(), this.zzfvp.get());
    }
}
