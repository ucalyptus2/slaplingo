package kotlin.collections.unsigned;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.text.html.HtmlTags;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u001b\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0006H\u0002ø\u0001\u0000J\u001a\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0013\u001a\u00020\nH\u0016J\u001a\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"kotlin/collections/unsigned/UArraysKt___UArraysJvmKt$asList$4", "Lkotlin/collections/AbstractList;", "Lkotlin/UShort;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", HtmlTags.SIZE, "", "getSize", "()I", "contains", "", "element", "contains-xj2QHRw", "(S)Z", "get", FirebaseAnalytics.Param.INDEX, "indexOf", "indexOf-xj2QHRw", "(S)I", "isEmpty", "lastIndexOf", "lastIndexOf-xj2QHRw", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* compiled from: _UArraysJvm.kt */
public final class UArraysKt___UArraysJvmKt$asList$4 extends AbstractList<UShort> implements RandomAccess {
    final /* synthetic */ short[] $this_asList;

    UArraysKt___UArraysJvmKt$asList$4(short[] sArr) {
        this.$this_asList = sArr;
    }

    @Override // kotlin.collections.AbstractCollection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return m381containsxj2QHRw(((UShort) obj).m297unboximpl());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UShort) {
            return m382indexOfxj2QHRw(((UShort) obj).m297unboximpl());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UShort) {
            return m383lastIndexOfxj2QHRw(((UShort) obj).m297unboximpl());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return UShortArray.m306getSizeimpl(this.$this_asList);
    }

    @Override // kotlin.collections.AbstractCollection
    public boolean isEmpty() {
        return UShortArray.m308isEmptyimpl(this.$this_asList);
    }

    /* renamed from: contains-xj2QHRw  reason: not valid java name */
    public boolean m381containsxj2QHRw(short s) {
        return UShortArray.m301containsxj2QHRw(this.$this_asList, s);
    }

    @Override // java.util.List, kotlin.collections.AbstractList
    public UShort get(int i) {
        return UShort.m250boximpl(UShortArray.m305getimpl(this.$this_asList, i));
    }

    /* renamed from: indexOf-xj2QHRw  reason: not valid java name */
    public int m382indexOfxj2QHRw(short s) {
        return ArraysKt.indexOf(this.$this_asList, s);
    }

    /* renamed from: lastIndexOf-xj2QHRw  reason: not valid java name */
    public int m383lastIndexOfxj2QHRw(short s) {
        return ArraysKt.lastIndexOf(this.$this_asList, s);
    }
}
