package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "prepare", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: LockFreeLinkedList.kt */
public final class SelectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1 extends LockFreeLinkedListNode.CondAddOp {
    final /* synthetic */ LockFreeLinkedListNode $node;
    final /* synthetic */ SelectBuilderImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SelectBuilderImpl$disposeOnSelect$$inlined$addLastIf$1(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, SelectBuilderImpl selectBuilderImpl) {
        super(lockFreeLinkedListNode2);
        this.$node = lockFreeLinkedListNode;
        this.this$0 = selectBuilderImpl;
    }

    public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode) {
        Intrinsics.checkParameterIsNotNull(lockFreeLinkedListNode, "affected");
        if (this.this$0.getState() == this.this$0) {
            return null;
        }
        return LockFreeLinkedListKt.getCONDITION_FALSE();
    }
}
