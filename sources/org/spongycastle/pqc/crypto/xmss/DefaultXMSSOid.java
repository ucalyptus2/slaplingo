package org.spongycastle.pqc.crypto.xmss;

import com.itextpdf.text.pdf.security.DigestAlgorithms;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.opencv.imgproc.Imgproc;

public final class DefaultXMSSOid implements XMSSOid {
    private static final Map<String, DefaultXMSSOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(createKey("SHA-256", 32, 16, 67, 10), new DefaultXMSSOid(16777217, "XMSS_SHA2-256_W16_H10"));
        hashMap.put(createKey("SHA-256", 32, 16, 67, 16), new DefaultXMSSOid(33554434, "XMSS_SHA2-256_W16_H16"));
        hashMap.put(createKey("SHA-256", 32, 16, 67, 20), new DefaultXMSSOid(50331651, "XMSS_SHA2-256_W16_H20"));
        hashMap.put(createKey(DigestAlgorithms.SHA512, 64, 16, Imgproc.COLOR_RGB2YUV_YV12, 10), new DefaultXMSSOid(67108868, "XMSS_SHA2-512_W16_H10"));
        hashMap.put(createKey(DigestAlgorithms.SHA512, 64, 16, Imgproc.COLOR_RGB2YUV_YV12, 16), new DefaultXMSSOid(83886085, "XMSS_SHA2-512_W16_H16"));
        hashMap.put(createKey(DigestAlgorithms.SHA512, 64, 16, Imgproc.COLOR_RGB2YUV_YV12, 20), new DefaultXMSSOid(100663302, "XMSS_SHA2-512_W16_H20"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 10), new DefaultXMSSOid(117440519, "XMSS_SHAKE128_W16_H10"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 16), new DefaultXMSSOid(134217736, "XMSS_SHAKE128_W16_H16"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 20), new DefaultXMSSOid(150994953, "XMSS_SHAKE128_W16_H20"));
        hashMap.put(createKey("SHAKE256", 64, 16, Imgproc.COLOR_RGB2YUV_YV12, 10), new DefaultXMSSOid(167772170, "XMSS_SHAKE256_W16_H10"));
        hashMap.put(createKey("SHAKE256", 64, 16, Imgproc.COLOR_RGB2YUV_YV12, 16), new DefaultXMSSOid(184549387, "XMSS_SHAKE256_W16_H16"));
        hashMap.put(createKey("SHAKE256", 64, 16, Imgproc.COLOR_RGB2YUV_YV12, 20), new DefaultXMSSOid(201326604, "XMSS_SHAKE256_W16_H20"));
        oidLookupTable = Collections.unmodifiableMap(hashMap);
    }

    private DefaultXMSSOid(int i, String str) {
        this.oid = i;
        this.stringRepresentation = str;
    }

    public static DefaultXMSSOid lookup(String str, int i, int i2, int i3, int i4) {
        if (str != null) {
            return oidLookupTable.get(createKey(str, i, i2, i3, i4));
        }
        throw new NullPointerException("algorithmName == null");
    }

    private static String createKey(String str, int i, int i2, int i3, int i4) {
        if (str != null) {
            return str + "-" + i + "-" + i2 + "-" + i3 + "-" + i4;
        }
        throw new NullPointerException("algorithmName == null");
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSOid
    public int getOid() {
        return this.oid;
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSOid
    public String toString() {
        return this.stringRepresentation;
    }
}
