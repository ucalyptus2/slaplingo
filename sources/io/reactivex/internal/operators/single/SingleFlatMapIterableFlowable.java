package io.reactivex.internal.operators.single;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;

public final class SingleFlatMapIterableFlowable<T, R> extends Flowable<R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final SingleSource<T> source;

    public SingleFlatMapIterableFlowable(SingleSource<T> singleSource, Function<? super T, ? extends Iterable<? extends R>> function) {
        this.source = singleSource;
        this.mapper = function;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.source.subscribe(new FlatMapIterableObserver(subscriber, this.mapper));
    }

    static final class FlatMapIterableObserver<T, R> extends BasicIntQueueSubscription<R> implements SingleObserver<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        Disposable d;

        /* renamed from: it  reason: collision with root package name */
        volatile Iterator<? extends R> f61it;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        final AtomicLong requested = new AtomicLong();

        FlatMapIterableObserver(Subscriber<? super R> subscriber, Function<? super T, ? extends Iterable<? extends R>> function) {
            this.actual = subscriber;
            this.mapper = function;
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            try {
                Iterator<T> it2 = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it2.hasNext()) {
                    this.actual.onComplete();
                    return;
                }
                this.f61it = it2;
                drain();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.actual.onError(th);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.d = DisposableHelper.DISPOSED;
            this.actual.onError(th);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.d.dispose();
            this.d = DisposableHelper.DISPOSED;
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> subscriber = this.actual;
                Iterator<? extends R> it2 = this.f61it;
                if (!this.outputFused || it2 == null) {
                    int i = 1;
                    while (true) {
                        if (it2 != null) {
                            long j = this.requested.get();
                            if (j == LongCompanionObject.MAX_VALUE) {
                                slowPath(subscriber, it2);
                                return;
                            }
                            long j2 = 0;
                            while (j2 != j) {
                                if (!this.cancelled) {
                                    try {
                                        subscriber.onNext(ObjectHelper.requireNonNull(it2.next(), "The iterator returned a null value"));
                                        if (!this.cancelled) {
                                            j2++;
                                            try {
                                                if (!it2.hasNext()) {
                                                    subscriber.onComplete();
                                                    return;
                                                }
                                            } catch (Throwable th) {
                                                Exceptions.throwIfFatal(th);
                                                subscriber.onError(th);
                                                return;
                                            }
                                        } else {
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        Exceptions.throwIfFatal(th2);
                                        subscriber.onError(th2);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (j2 != 0) {
                                BackpressureHelper.produced(this.requested, j2);
                            }
                        }
                        i = addAndGet(-i);
                        if (i != 0) {
                            if (it2 == null) {
                                it2 = this.f61it;
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    subscriber.onNext(null);
                    subscriber.onComplete();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void slowPath(Subscriber<? super R> subscriber, Iterator<? extends R> it2) {
            while (!this.cancelled) {
                try {
                    subscriber.onNext(it2.next());
                    if (!this.cancelled) {
                        try {
                            if (!it2.hasNext()) {
                                subscriber.onComplete();
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            subscriber.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    subscriber.onError(th2);
                    return;
                }
            }
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.f61it = null;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.f61it == null;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public R poll() throws Exception {
            Iterator<? extends R> it2 = this.f61it;
            if (it2 == null) {
                return null;
            }
            R requireNonNull = ObjectHelper.requireNonNull(it2.next(), "The iterator returned a null value");
            if (!it2.hasNext()) {
                this.f61it = null;
            }
            return requireNonNull;
        }
    }
}
