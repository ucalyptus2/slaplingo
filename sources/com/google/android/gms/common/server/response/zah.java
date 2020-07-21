package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zah implements FastParser.zaa<BigInteger> {
    zah() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ BigInteger zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return fastParser.zaf(bufferedReader);
    }
}