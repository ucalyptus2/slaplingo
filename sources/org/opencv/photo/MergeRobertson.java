package org.opencv.photo;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

public class MergeRobertson extends MergeExposures {
    @Override // org.opencv.photo.MergeExposures, org.opencv.core.Algorithm
    private static native void delete(long j);

    @Override // org.opencv.photo.MergeExposures
    private static native void process_0(long j, long j2, long j3, long j4, long j5);

    private static native void process_1(long j, long j2, long j3, long j4);

    protected MergeRobertson(long j) {
        super(j);
    }

    @Override // org.opencv.photo.MergeExposures, org.opencv.core.Algorithm
    public static MergeRobertson __fromPtr__(long j) {
        return new MergeRobertson(j);
    }

    @Override // org.opencv.photo.MergeExposures
    public void process(List<Mat> list, Mat mat, Mat mat2, Mat mat3) {
        process_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public void process(List<Mat> list, Mat mat, Mat mat2) {
        process_1(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    @Override // org.opencv.photo.MergeExposures, org.opencv.core.Algorithm
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
