package io.fotoapparat.result;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: PendingResult.kt */
final class PendingResultKt$notifyOnMainThread$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function0 $function;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PendingResultKt$notifyOnMainThread$1(Function0 function0) {
        super(0);
        this.$function = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final void invoke() {
        this.$function.invoke();
    }
}
