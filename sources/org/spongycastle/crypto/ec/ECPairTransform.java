package org.spongycastle.crypto.ec;

import org.spongycastle.crypto.CipherParameters;

public interface ECPairTransform {
    void init(CipherParameters cipherParameters);

    ECPair transform(ECPair eCPair);
}
