package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.2.0 */
public final class zzchx implements zzela<zzcqt<zzchj>> {
    private final zzelj<zzdvi> zzfnr;
    private final zzelj<zzdou> zzfoc;
    private final zzelj<zzcuf> zzfod;
    private final zzelj<zzcva> zzfoe;

    public zzchx(zzelj<zzdou> zzelj, zzelj<zzdvi> zzelj2, zzelj<zzcuf> zzelj3, zzelj<zzcva> zzelj4) {
        this.zzfoc = zzelj;
        this.zzfnr = zzelj2;
        this.zzfod = zzelj3;
        this.zzfoe = zzelj4;
    }

    @Override // com.google.android.gms.internal.ads.zzelj
    public final /* synthetic */ Object get() {
        return (zzcqt) zzelg.zza(new zzcve(this.zzfoc.get(), this.zzfnr.get(), this.zzfoe.get(), this.zzfod.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
