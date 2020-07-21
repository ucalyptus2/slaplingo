package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.2.0 */
public final class zzcjk implements zzela<zzbyg<zzbva>> {
    private final zzelj<Executor> zzerc;
    private final zzelj<zzcjq> zzfmj;

    private zzcjk(zzelj<zzcjq> zzelj, zzelj<Executor> zzelj2) {
        this.zzfmj = zzelj;
        this.zzerc = zzelj2;
    }

    public static zzcjk zzad(zzelj<zzcjq> zzelj, zzelj<Executor> zzelj2) {
        return new zzcjk(zzelj, zzelj2);
    }

    @Override // com.google.android.gms.internal.ads.zzelj
    public final /* synthetic */ Object get() {
        return (zzbyg) zzelg.zza(new zzbyg(this.zzfmj.get(), this.zzerc.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
