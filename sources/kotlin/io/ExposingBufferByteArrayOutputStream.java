package kotlin.io;

import com.itextpdf.text.html.HtmlTags;
import java.io.ByteArrayOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlin/io/ExposingBufferByteArrayOutputStream;", "Ljava/io/ByteArrayOutputStream;", HtmlTags.SIZE, "", "(I)V", "buffer", "", "getBuffer", "()[B", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* compiled from: FileReadWrite.kt */
final class ExposingBufferByteArrayOutputStream extends ByteArrayOutputStream {
    public ExposingBufferByteArrayOutputStream(int i) {
        super(i);
    }

    public final byte[] getBuffer() {
        byte[] bArr = this.buf;
        Intrinsics.checkExpressionValueIsNotNull(bArr, "buf");
        return bArr;
    }
}
