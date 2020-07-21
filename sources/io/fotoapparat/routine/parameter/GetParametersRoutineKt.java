package io.fotoapparat.routine.parameter;

import io.fotoapparat.hardware.Device;
import io.fotoapparat.parameter.camera.CameraParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"getCurrentParameters", "Lio/fotoapparat/parameter/camera/CameraParameters;", "Lio/fotoapparat/hardware/Device;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: GetParametersRoutine.kt */
public final class GetParametersRoutineKt {
    public static final CameraParameters getCurrentParameters(Device device) {
        Intrinsics.checkParameterIsNotNull(device, "receiver$0");
        return (CameraParameters) BuildersKt__BuildersKt.runBlocking$default(null, new GetParametersRoutineKt$getCurrentParameters$1(device, null), 1, null);
    }
}
