package com.google.android.gms.internal.p001authapi;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzab  reason: invalid package */
/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
public interface zzab extends IInterface {
    void zzc(Status status, BeginSignInResult beginSignInResult) throws RemoteException;
}
