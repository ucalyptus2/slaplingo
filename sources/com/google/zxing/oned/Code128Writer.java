package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 241;
    private static final char ESCAPE_FNC_2 = 242;
    private static final char ESCAPE_FNC_3 = 243;
    private static final char ESCAPE_FNC_4 = 244;

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int i;
        int length = str.length();
        if (length < 1 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt < ' ' || charAt > '~') {
                switch (charAt) {
                    default:
                        throw new IllegalArgumentException("Bad character in input: " + charAt);
                    case 241:
                    case 242:
                    case 243:
                    case 244:
                        break;
                }
            }
        }
        ArrayList<int[]> arrayList = new ArrayList();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 1;
        while (i4 < length) {
            int i8 = 99;
            int i9 = 100;
            if (!isDigits(str, i4, i6 == 99 ? 2 : 4)) {
                i8 = 100;
            }
            if (i8 == i6) {
                switch (str.charAt(i4)) {
                    case 241:
                        i9 = 102;
                        break;
                    case 242:
                        i9 = 97;
                        break;
                    case 243:
                        i9 = 96;
                        break;
                    case 244:
                        break;
                    default:
                        if (i6 != 100) {
                            i9 = Integer.parseInt(str.substring(i4, i4 + 2));
                            i4++;
                            break;
                        } else {
                            i9 = str.charAt(i4) - ' ';
                            break;
                        }
                }
                i4++;
            } else {
                i = i6 == 0 ? i8 == 100 ? 104 : 105 : i8;
                i6 = i8;
            }
            arrayList.add(Code128Reader.CODE_PATTERNS[i]);
            i5 += i * i7;
            if (i4 != 0) {
                i7++;
            }
        }
        arrayList.add(Code128Reader.CODE_PATTERNS[i5 % 103]);
        arrayList.add(Code128Reader.CODE_PATTERNS[106]);
        int i10 = 0;
        for (int[] iArr : arrayList) {
            for (int i11 : iArr) {
                i10 += i11;
            }
        }
        boolean[] zArr = new boolean[i10];
        for (int[] iArr2 : arrayList) {
            i2 += appendPattern(zArr, i2, iArr2, true);
        }
        return zArr;
    }

    private static boolean isDigits(CharSequence charSequence, int i, int i2) {
        int i3 = i2 + i;
        int length = charSequence.length();
        while (i < i3 && i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < '0' || charAt > '9') {
                if (charAt != 241) {
                    return false;
                }
                i3++;
            }
            i++;
        }
        if (i3 <= length) {
            return true;
        }
        return false;
    }
}
