package com.google.android.gms.internal.ads;

import com.itextpdf.text.pdf.codec.TIFFConstants;

/* compiled from: com.google.android.gms:play-services-ads@@19.2.0 */
final class zzecq {
    static byte[] zzq(byte[] bArr) {
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[16];
            for (int i = 0; i < 16; i++) {
                bArr2[i] = (byte) ((bArr[i] << 1) & TIFFConstants.TIFFTAG_SUBFILETYPE);
                if (i < 15) {
                    bArr2[i] = (byte) (bArr2[i] | ((byte) ((bArr[i + 1] >> 7) & 1)));
                }
            }
            bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & 135)) ^ bArr2[15]);
            return bArr2;
        }
        throw new IllegalArgumentException("value must be a block.");
    }
}
