package com.google.android.gms.internal.p001authapi;

import android.util.Base64;
import java.util.Random;

/* renamed from: com.google.android.gms.internal.auth-api.zzal  reason: invalid package */
/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
public final class zzal {
    private static final Random zzcv = new Random();

    public static String zzr() {
        byte[] bArr = new byte[16];
        zzcv.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}
