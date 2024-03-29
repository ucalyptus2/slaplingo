package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import com.itextpdf.text.pdf.PdfBoolean;
import com.itextpdf.xmp.XMPConst;
import java.io.Serializable;

public abstract class NavType<T> {
    public static final NavType<boolean[]> BoolArrayType = new NavType<boolean[]>(true) {
        /* class androidx.navigation.NavType.AnonymousClass9 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "boolean[]";
        }

        public void put(Bundle bundle, String str, boolean[] zArr) {
            bundle.putBooleanArray(str, zArr);
        }

        @Override // androidx.navigation.NavType
        public boolean[] get(Bundle bundle, String str) {
            return (boolean[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public boolean[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType<Boolean> BoolType = new NavType<Boolean>(false) {
        /* class androidx.navigation.NavType.AnonymousClass8 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "boolean";
        }

        public void put(Bundle bundle, String str, Boolean bool) {
            bundle.putBoolean(str, bool.booleanValue());
        }

        @Override // androidx.navigation.NavType
        public Boolean get(Bundle bundle, String str) {
            return (Boolean) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public Boolean parseValue(String str) {
            if (PdfBoolean.TRUE.equals(str)) {
                return true;
            }
            if (PdfBoolean.FALSE.equals(str)) {
                return false;
            }
            throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
        }
    };
    public static final NavType<float[]> FloatArrayType = new NavType<float[]>(true) {
        /* class androidx.navigation.NavType.AnonymousClass7 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "float[]";
        }

        public void put(Bundle bundle, String str, float[] fArr) {
            bundle.putFloatArray(str, fArr);
        }

        @Override // androidx.navigation.NavType
        public float[] get(Bundle bundle, String str) {
            return (float[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public float[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType<Float> FloatType = new NavType<Float>(false) {
        /* class androidx.navigation.NavType.AnonymousClass6 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "float";
        }

        public void put(Bundle bundle, String str, Float f) {
            bundle.putFloat(str, f.floatValue());
        }

        @Override // androidx.navigation.NavType
        public Float get(Bundle bundle, String str) {
            return (Float) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public Float parseValue(String str) {
            return Float.valueOf(Float.parseFloat(str));
        }
    };
    public static final NavType<int[]> IntArrayType = new NavType<int[]>(true) {
        /* class androidx.navigation.NavType.AnonymousClass3 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "integer[]";
        }

        public void put(Bundle bundle, String str, int[] iArr) {
            bundle.putIntArray(str, iArr);
        }

        @Override // androidx.navigation.NavType
        public int[] get(Bundle bundle, String str) {
            return (int[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public int[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType<Integer> IntType = new NavType<Integer>(false) {
        /* class androidx.navigation.NavType.AnonymousClass1 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "integer";
        }

        public void put(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }

        @Override // androidx.navigation.NavType
        public Integer get(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public Integer parseValue(String str) {
            if (str.startsWith("0x")) {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
    };
    public static final NavType<long[]> LongArrayType = new NavType<long[]>(true) {
        /* class androidx.navigation.NavType.AnonymousClass5 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "long[]";
        }

        public void put(Bundle bundle, String str, long[] jArr) {
            bundle.putLongArray(str, jArr);
        }

        @Override // androidx.navigation.NavType
        public long[] get(Bundle bundle, String str) {
            return (long[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public long[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType<Long> LongType = new NavType<Long>(false) {
        /* class androidx.navigation.NavType.AnonymousClass4 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "long";
        }

        public void put(Bundle bundle, String str, Long l) {
            bundle.putLong(str, l.longValue());
        }

        @Override // androidx.navigation.NavType
        public Long get(Bundle bundle, String str) {
            return (Long) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public Long parseValue(String str) {
            if (str.endsWith("L")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.startsWith("0x")) {
                return Long.valueOf(Long.parseLong(str.substring(2), 16));
            }
            return Long.valueOf(Long.parseLong(str));
        }
    };
    public static final NavType<Integer> ReferenceType = new NavType<Integer>(false) {
        /* class androidx.navigation.NavType.AnonymousClass2 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "reference";
        }

        public void put(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }

        @Override // androidx.navigation.NavType
        public Integer get(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public Integer parseValue(String str) {
            throw new UnsupportedOperationException("References don't support parsing string values.");
        }
    };
    public static final NavType<String[]> StringArrayType = new NavType<String[]>(true) {
        /* class androidx.navigation.NavType.AnonymousClass11 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "string[]";
        }

        public void put(Bundle bundle, String str, String[] strArr) {
            bundle.putStringArray(str, strArr);
        }

        @Override // androidx.navigation.NavType
        public String[] get(Bundle bundle, String str) {
            return (String[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public String[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType<String> StringType = new NavType<String>(true) {
        /* class androidx.navigation.NavType.AnonymousClass10 */

        @Override // androidx.navigation.NavType
        public String getName() {
            return "string";
        }

        @Override // androidx.navigation.NavType
        public String parseValue(String str) {
            return str;
        }

        public void put(Bundle bundle, String str, String str2) {
            bundle.putString(str, str2);
        }

        @Override // androidx.navigation.NavType
        public String get(Bundle bundle, String str) {
            return (String) bundle.get(str);
        }
    };
    private final boolean mNullableAllowed;

    public abstract T get(Bundle bundle, String str);

    public abstract String getName();

    public abstract T parseValue(String str);

    public abstract void put(Bundle bundle, String str, T t);

    NavType(boolean z) {
        this.mNullableAllowed = z;
    }

    public boolean isNullableAllowed() {
        return this.mNullableAllowed;
    }

    /* access modifiers changed from: package-private */
    public T parseAndPut(Bundle bundle, String str, String str2) {
        T parseValue = parseValue(str2);
        put(bundle, str, parseValue);
        return parseValue;
    }

    public String toString() {
        return getName();
    }

    /* JADX DEBUG: Type inference failed for r1v9. Raw type applied. Possible types: androidx.navigation.NavType<float[]>, androidx.navigation.NavType<?> */
    /* JADX DEBUG: Type inference failed for r1v11. Raw type applied. Possible types: androidx.navigation.NavType<java.lang.String[]>, androidx.navigation.NavType<?> */
    /* JADX DEBUG: Type inference failed for r1v13. Raw type applied. Possible types: androidx.navigation.NavType<boolean[]>, androidx.navigation.NavType<?> */
    /* JADX DEBUG: Type inference failed for r1v15. Raw type applied. Possible types: androidx.navigation.NavType<long[]>, androidx.navigation.NavType<?> */
    /* JADX DEBUG: Type inference failed for r1v17. Raw type applied. Possible types: androidx.navigation.NavType<int[]>, androidx.navigation.NavType<?> */
    public static NavType<?> fromArgType(String str, String str2) {
        String str3;
        if (IntType.getName().equals(str)) {
            return IntType;
        }
        if (IntArrayType.getName().equals(str)) {
            return IntArrayType;
        }
        if (LongType.getName().equals(str)) {
            return LongType;
        }
        if (LongArrayType.getName().equals(str)) {
            return LongArrayType;
        }
        if (BoolType.getName().equals(str)) {
            return BoolType;
        }
        if (BoolArrayType.getName().equals(str)) {
            return BoolArrayType;
        }
        if (StringType.getName().equals(str)) {
            return StringType;
        }
        if (StringArrayType.getName().equals(str)) {
            return StringArrayType;
        }
        if (FloatType.getName().equals(str)) {
            return FloatType;
        }
        if (FloatArrayType.getName().equals(str)) {
            return FloatArrayType;
        }
        if (ReferenceType.getName().equals(str)) {
            return ReferenceType;
        }
        if (str == null || str.isEmpty()) {
            return StringType;
        }
        try {
            if (!str.startsWith(".") || str2 == null) {
                str3 = str;
            } else {
                str3 = str2 + str;
            }
            if (str.endsWith(XMPConst.ARRAY_ITEM_NAME)) {
                str3 = str3.substring(0, str3.length() - 2);
                Class<?> cls = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls)) {
                    return new ParcelableArrayType(cls);
                }
                if (Serializable.class.isAssignableFrom(cls)) {
                    return new SerializableArrayType(cls);
                }
            } else {
                Class<?> cls2 = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls2)) {
                    return new ParcelableType(cls2);
                }
                if (Enum.class.isAssignableFrom(cls2)) {
                    return new EnumType(cls2);
                }
                if (Serializable.class.isAssignableFrom(cls2)) {
                    return new SerializableType(cls2);
                }
            }
            throw new IllegalArgumentException(str3 + " is not Serializable or Parcelable.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Failed to insert an additional move for type inference into block B:0:0x0000 */
    /* JADX WARN: Failed to insert an additional move for type inference into block B:3:0x0008 */
    /* JADX WARN: Failed to insert an additional move for type inference into block B:6:0x0010 */
    /* JADX DEBUG: Additional 3 move instruction added to help type inference */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [androidx.navigation.NavType, androidx.navigation.NavType<java.lang.Float>] */
    /* JADX WARN: Type inference failed for: r1v7, types: [androidx.navigation.NavType<java.lang.Long>, androidx.navigation.NavType] */
    /* JADX WARN: Type inference failed for: r1v8, types: [androidx.navigation.NavType, androidx.navigation.NavType<java.lang.Integer>] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        androidx.navigation.NavType.BoolType.parseValue(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return androidx.navigation.NavType.BoolType;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return androidx.navigation.NavType.StringType;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        androidx.navigation.NavType.LongType.parseValue(r1);
        r1 = androidx.navigation.NavType.LongType;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        androidx.navigation.NavType.FloatType.parseValue(r1);
        r1 = androidx.navigation.NavType.FloatType;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0008 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0010 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0018 */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.navigation.NavType inferFromValue(java.lang.String r1) {
        /*
            androidx.navigation.NavType<java.lang.Integer> r0 = androidx.navigation.NavType.IntType     // Catch:{ IllegalArgumentException -> 0x0008 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0008 }
            androidx.navigation.NavType<java.lang.Integer> r1 = androidx.navigation.NavType.IntType     // Catch:{ IllegalArgumentException -> 0x0008 }
            return r1
        L_0x0008:
            androidx.navigation.NavType<java.lang.Long> r0 = androidx.navigation.NavType.LongType     // Catch:{ IllegalArgumentException -> 0x0010 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0010 }
            androidx.navigation.NavType<java.lang.Long> r1 = androidx.navigation.NavType.LongType     // Catch:{ IllegalArgumentException -> 0x0010 }
            return r1
        L_0x0010:
            androidx.navigation.NavType<java.lang.Float> r0 = androidx.navigation.NavType.FloatType     // Catch:{ IllegalArgumentException -> 0x0018 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0018 }
            androidx.navigation.NavType<java.lang.Float> r1 = androidx.navigation.NavType.FloatType     // Catch:{ IllegalArgumentException -> 0x0018 }
            return r1
        L_0x0018:
            androidx.navigation.NavType<java.lang.Boolean> r0 = androidx.navigation.NavType.BoolType     // Catch:{ IllegalArgumentException -> 0x0020 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0020 }
            androidx.navigation.NavType<java.lang.Boolean> r1 = androidx.navigation.NavType.BoolType     // Catch:{ IllegalArgumentException -> 0x0020 }
            return r1
        L_0x0020:
            androidx.navigation.NavType<java.lang.String> r1 = androidx.navigation.NavType.StringType
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType.inferFromValue(java.lang.String):androidx.navigation.NavType");
    }

    static NavType inferFromValueType(Object obj) {
        if (obj instanceof Integer) {
            return IntType;
        }
        if (obj instanceof int[]) {
            return IntArrayType;
        }
        if (obj instanceof Long) {
            return LongType;
        }
        if (obj instanceof long[]) {
            return LongArrayType;
        }
        if (obj instanceof Float) {
            return FloatType;
        }
        if (obj instanceof float[]) {
            return FloatArrayType;
        }
        if (obj instanceof Boolean) {
            return BoolType;
        }
        if (obj instanceof boolean[]) {
            return BoolArrayType;
        }
        if ((obj instanceof String) || obj == null) {
            return StringType;
        }
        if (obj instanceof String[]) {
            return StringArrayType;
        }
        if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new ParcelableArrayType(obj.getClass().getComponentType());
        }
        if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new SerializableArrayType(obj.getClass().getComponentType());
        }
        if (obj instanceof Parcelable) {
            return new ParcelableType(obj.getClass());
        }
        if (obj instanceof Enum) {
            return new EnumType(obj.getClass());
        }
        if (obj instanceof Serializable) {
            return new SerializableType(obj.getClass());
        }
        throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
    }

    public static final class ParcelableType<D> extends NavType<D> {
        private final Class<D> mType;

        public ParcelableType(Class<D> cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls) || Serializable.class.isAssignableFrom(cls)) {
                this.mType = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Parcelable or Serializable.");
        }

        @Override // androidx.navigation.NavType
        public void put(Bundle bundle, String str, D d) {
            this.mType.cast(d);
            if (d == null || (d instanceof Parcelable)) {
                bundle.putParcelable(str, d);
            } else if (d instanceof Serializable) {
                bundle.putSerializable(str, d);
            }
        }

        @Override // androidx.navigation.NavType
        public D get(Bundle bundle, String str) {
            return bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public D parseValue(String str) {
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return this.mType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mType.equals(((ParcelableType) obj).mType);
        }

        public int hashCode() {
            return this.mType.hashCode();
        }
    }

    public static final class ParcelableArrayType<D extends Parcelable> extends NavType<D[]> {
        private final Class<D[]> mArrayType;

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, (Parcelable[]) ((Parcelable[]) obj));
        }

        /* JADX DEBUG: Type inference failed for r3v7. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<D[]> */
        public ParcelableArrayType(Class<D> cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls)) {
                try {
                    this.mArrayType = Class.forName("[L" + cls.getName() + ";");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalArgumentException(cls + " does not implement Parcelable.");
            }
        }

        public void put(Bundle bundle, String str, D[] dArr) {
            this.mArrayType.cast(dArr);
            bundle.putParcelableArray(str, dArr);
        }

        @Override // androidx.navigation.NavType
        public D[] get(Bundle bundle, String str) {
            return (Parcelable[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public D[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return this.mArrayType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mArrayType.equals(((ParcelableArrayType) obj).mArrayType);
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }

    public static class SerializableType<D extends Serializable> extends NavType<D> {
        private final Class<D> mType;

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, (Serializable) ((Serializable) obj));
        }

        public SerializableType(Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            } else if (!cls.isEnum()) {
                this.mType = cls;
            } else {
                throw new IllegalArgumentException(cls + " is an Enum. You should use EnumType instead.");
            }
        }

        SerializableType(boolean z, Class<D> cls) {
            super(z);
            if (Serializable.class.isAssignableFrom(cls)) {
                this.mType = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Serializable.");
        }

        public void put(Bundle bundle, String str, D d) {
            this.mType.cast(d);
            bundle.putSerializable(str, d);
        }

        @Override // androidx.navigation.NavType
        public D get(Bundle bundle, String str) {
            return bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public D parseValue(String str) {
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return this.mType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SerializableType)) {
                return false;
            }
            return this.mType.equals(((SerializableType) obj).mType);
        }

        public int hashCode() {
            return this.mType.hashCode();
        }
    }

    public static final class EnumType<D extends Enum> extends SerializableType<D> {
        private final Class<D> mType;

        public EnumType(Class<D> cls) {
            super(false, cls);
            if (cls.isEnum()) {
                this.mType = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " is not an Enum type.");
        }

        @Override // androidx.navigation.NavType, androidx.navigation.NavType.SerializableType, androidx.navigation.NavType.SerializableType
        public D parseValue(String str) {
            D[] enumConstants = this.mType.getEnumConstants();
            for (D d : enumConstants) {
                if (d.name().equals(str)) {
                    return d;
                }
            }
            throw new IllegalArgumentException("Enum value " + str + " not found for type " + this.mType.getName() + ".");
        }

        @Override // androidx.navigation.NavType, androidx.navigation.NavType.SerializableType
        public String getName() {
            return this.mType.getName();
        }
    }

    public static final class SerializableArrayType<D extends Serializable> extends NavType<D[]> {
        private final Class<D[]> mArrayType;

        @Override // androidx.navigation.NavType
        public /* bridge */ /* synthetic */ void put(Bundle bundle, String str, Object obj) {
            put(bundle, str, (Serializable[]) ((Serializable[]) obj));
        }

        /* JADX DEBUG: Type inference failed for r3v7. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<D[]> */
        public SerializableArrayType(Class<D> cls) {
            super(true);
            if (Serializable.class.isAssignableFrom(cls)) {
                try {
                    this.mArrayType = Class.forName("[L" + cls.getName() + ";");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
        }

        public void put(Bundle bundle, String str, D[] dArr) {
            this.mArrayType.cast(dArr);
            bundle.putSerializable(str, dArr);
        }

        @Override // androidx.navigation.NavType
        public D[] get(Bundle bundle, String str) {
            return (Serializable[]) bundle.get(str);
        }

        @Override // androidx.navigation.NavType
        public D[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.NavType
        public String getName() {
            return this.mArrayType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mArrayType.equals(((SerializableArrayType) obj).mArrayType);
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }
}
