package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.4 */
public final class zzpu implements zzdv<zzpt> {
    private static zzpu zza = new zzpu();
    private final zzdv<zzpt> zzb;

    public static boolean zzb() {
        return ((zzpt) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzpt) zza.zza()).zzb();
    }

    private zzpu(zzdv<zzpt> zzdv) {
        this.zzb = zzdu.zza((zzdv) zzdv);
    }

    public zzpu() {
        this(zzdu.zza(new zzpw()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdv
    public final /* synthetic */ zzpt zza() {
        return this.zzb.zza();
    }
}
