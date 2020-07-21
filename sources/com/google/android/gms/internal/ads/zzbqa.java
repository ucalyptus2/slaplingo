package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.2.0 */
final class zzbqa implements zzduu<zzasm> {
    private final /* synthetic */ zzbpz zzfqe;

    zzbqa(zzbpz zzbpz) {
        this.zzfqe = zzbpz;
    }

    @Override // com.google.android.gms.internal.ads.zzduu
    public final void zzb(Throwable th) {
        this.zzfqe.zzfpz.zzbg(false);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzduu
    public final /* synthetic */ void onSuccess(@NullableDecl zzasm zzasm) {
        this.zzfqe.zzfpz.zzbg(true);
    }
}
