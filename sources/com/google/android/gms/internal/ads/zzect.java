package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-ads@@19.2.0 */
abstract class zzect implements zzdwc {
    private final zzecr zzhxh;
    private final zzecr zzhxi;

    public zzect(byte[] bArr) throws InvalidKeyException {
        this.zzhxh = zze(bArr, 1);
        this.zzhxi = zze(bArr, 0);
    }

    /* access modifiers changed from: package-private */
    public abstract zzecr zze(byte[] bArr, int i) throws InvalidKeyException;

    @Override // com.google.android.gms.internal.ads.zzdwc
    public byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= (Integer.MAX_VALUE - this.zzhxh.zzbcl()) - 16) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + this.zzhxh.zzbcl() + 16);
            if (allocate.remaining() >= bArr.length + this.zzhxh.zzbcl() + 16) {
                int position = allocate.position();
                this.zzhxh.zza(allocate, bArr);
                allocate.position(position);
                byte[] bArr3 = new byte[this.zzhxh.zzbcl()];
                allocate.get(bArr3);
                allocate.limit(allocate.limit() - 16);
                if (bArr2 == null) {
                    bArr2 = new byte[0];
                }
                byte[] bArr4 = new byte[32];
                this.zzhxi.zzd(bArr3, 0).get(bArr4);
                int length = bArr2.length % 16 == 0 ? bArr2.length : (bArr2.length + 16) - (bArr2.length % 16);
                int remaining = allocate.remaining();
                int i = remaining % 16;
                int i2 = (i == 0 ? remaining : (remaining + 16) - i) + length;
                ByteBuffer order = ByteBuffer.allocate(i2 + 16).order(ByteOrder.LITTLE_ENDIAN);
                order.put(bArr2);
                order.position(length);
                order.put(allocate);
                order.position(i2);
                order.putLong((long) bArr2.length);
                order.putLong((long) remaining);
                byte[] zze = zzedr.zze(bArr4, order.array());
                allocate.limit(allocate.limit() + 16);
                allocate.put(zze);
                return allocate.array();
            }
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
