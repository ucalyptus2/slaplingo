package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicQueueDisposable;

public final class ObservableFromArray<T> extends Observable<T> {
    final T[] array;

    public ObservableFromArray(T[] tArr) {
        this.array = tArr;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        FromArrayDisposable fromArrayDisposable = new FromArrayDisposable(observer, this.array);
        observer.onSubscribe(fromArrayDisposable);
        if (!fromArrayDisposable.fusionMode) {
            fromArrayDisposable.run();
        }
    }

    static final class FromArrayDisposable<T> extends BasicQueueDisposable<T> {
        final Observer<? super T> actual;
        final T[] array;
        volatile boolean disposed;
        boolean fusionMode;
        int index;

        FromArrayDisposable(Observer<? super T> observer, T[] tArr) {
            this.actual = observer;
            this.array = tArr;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.fusionMode = true;
            return 1;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public T poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i == tArr.length) {
                return null;
            }
            this.index = i + 1;
            return ObjectHelper.requireNonNull(tArr[i], "The array element is null");
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.index == this.array.length;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.index = this.array.length;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        /* access modifiers changed from: package-private */
        public void run() {
            T[] tArr = this.array;
            int length = tArr.length;
            for (int i = 0; i < length && !isDisposed(); i++) {
                T t = tArr[i];
                if (t == null) {
                    Observer<? super T> observer = this.actual;
                    observer.onError(new NullPointerException("The " + i + "th element is null"));
                    return;
                }
                this.actual.onNext(t);
            }
            if (!isDisposed()) {
                this.actual.onComplete();
            }
        }
    }
}
