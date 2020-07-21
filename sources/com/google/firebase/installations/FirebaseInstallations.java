package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FirebaseInstallations implements FirebaseInstallationsApi {
    private static final String CHIME_FIREBASE_APP_NAME = "CHIME_ANDROID_SDK";
    private static final int CORE_POOL_SIZE = 0;
    private static final long KEEP_ALIVE_TIME_IN_SECONDS = 30;
    private static final String LOCKFILE_NAME_GENERATE_FID = "generatefid.lock";
    private static final int MAXIMUM_POOL_SIZE = 1;
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        /* class com.google.firebase.installations.FirebaseInstallations.AnonymousClass1 */
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, String.format("firebase-installations-executor-%d", Integer.valueOf(this.mCount.getAndIncrement())));
        }
    };
    private static final Object lockGenerateFid = new Object();
    private final ExecutorService backgroundExecutor;
    private String cachedFid;
    private final RandomFidGenerator fidGenerator;
    private final FirebaseApp firebaseApp;
    private final IidStore iidStore;
    private final List<StateListener> listeners;
    private final Object lock;
    private final ExecutorService networkExecutor;
    private final PersistedInstallation persistedInstallation;
    private final FirebaseInstallationServiceClient serviceClient;
    private final Utils utils;

    FirebaseInstallations(FirebaseApp firebaseApp2, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo) {
        this(new ThreadPoolExecutor(0, 1, (long) KEEP_ALIVE_TIME_IN_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY), firebaseApp2, new FirebaseInstallationServiceClient(firebaseApp2.getApplicationContext(), userAgentPublisher, heartBeatInfo), new PersistedInstallation(firebaseApp2), new Utils(), new IidStore(firebaseApp2), new RandomFidGenerator());
    }

    FirebaseInstallations(ExecutorService executorService, FirebaseApp firebaseApp2, FirebaseInstallationServiceClient firebaseInstallationServiceClient, PersistedInstallation persistedInstallation2, Utils utils2, IidStore iidStore2, RandomFidGenerator randomFidGenerator) {
        this.lock = new Object();
        this.cachedFid = null;
        this.listeners = new ArrayList();
        this.firebaseApp = firebaseApp2;
        this.serviceClient = firebaseInstallationServiceClient;
        this.persistedInstallation = persistedInstallation2;
        this.utils = utils2;
        this.iidStore = iidStore2;
        this.fidGenerator = randomFidGenerator;
        this.backgroundExecutor = executorService;
        this.networkExecutor = new ThreadPoolExecutor(0, 1, (long) KEEP_ALIVE_TIME_IN_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY);
    }

    private void preConditionChecks() {
        Preconditions.checkNotEmpty(getApplicationId());
        Preconditions.checkNotEmpty(getProjectIdentifier());
        Preconditions.checkNotEmpty(getApiKey());
        Preconditions.checkArgument(Utils.isValidAppIdFormat(getApplicationId()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(Utils.isValidApiKeyFormat(getApiKey()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    /* access modifiers changed from: package-private */
    public String getProjectIdentifier() {
        return this.firebaseApp.getOptions().getProjectId();
    }

    public static FirebaseInstallations getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseInstallations getInstance(FirebaseApp firebaseApp2) {
        Preconditions.checkArgument(firebaseApp2 != null, "Null is not a valid value of FirebaseApp.");
        return (FirebaseInstallations) firebaseApp2.get(FirebaseInstallationsApi.class);
    }

    /* access modifiers changed from: package-private */
    public String getApplicationId() {
        return this.firebaseApp.getOptions().getApplicationId();
    }

    /* access modifiers changed from: package-private */
    public String getApiKey() {
        return this.firebaseApp.getOptions().getApiKey();
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.firebaseApp.getName();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<String> getId() {
        preConditionChecks();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.trySetResult(doGetId());
        return taskCompletionSource.getTask();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<InstallationTokenResult> getToken(boolean z) {
        preConditionChecks();
        Task<InstallationTokenResult> addGetAuthTokenListener = addGetAuthTokenListener();
        this.backgroundExecutor.execute(FirebaseInstallations$$Lambda$1.lambdaFactory$(this, z));
        return addGetAuthTokenListener;
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<Void> delete() {
        return Tasks.call(this.backgroundExecutor, FirebaseInstallations$$Lambda$2.lambdaFactory$(this));
    }

    private Task<InstallationTokenResult> addGetAuthTokenListener() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        GetAuthTokenListener getAuthTokenListener = new GetAuthTokenListener(this.utils, taskCompletionSource);
        synchronized (this.lock) {
            this.listeners.add(getAuthTokenListener);
        }
        return taskCompletionSource.getTask();
    }

    private void triggerOnStateReached(PersistedInstallationEntry persistedInstallationEntry) {
        synchronized (this.lock) {
            Iterator<StateListener> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                if (it2.next().onStateReached(persistedInstallationEntry)) {
                    it2.remove();
                }
            }
        }
    }

    private void triggerOnException(PersistedInstallationEntry persistedInstallationEntry, Exception exc) {
        synchronized (this.lock) {
            Iterator<StateListener> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                if (it2.next().onException(persistedInstallationEntry, exc)) {
                    it2.remove();
                }
            }
        }
    }

    private String doGetId() {
        String str = this.cachedFid;
        if (str != null) {
            return str;
        }
        PersistedInstallationEntry prefsWithGeneratedIdMultiProcessSafe = getPrefsWithGeneratedIdMultiProcessSafe();
        this.networkExecutor.execute(FirebaseInstallations$$Lambda$3.lambdaFactory$(this));
        return prefsWithGeneratedIdMultiProcessSafe.getFirebaseInstallationId();
    }

    /* access modifiers changed from: private */
    public void doGetAuthToken(boolean z) {
        PersistedInstallationEntry prefsWithGeneratedIdMultiProcessSafe = getPrefsWithGeneratedIdMultiProcessSafe();
        if (z) {
            prefsWithGeneratedIdMultiProcessSafe = prefsWithGeneratedIdMultiProcessSafe.withClearedAuthToken();
        }
        triggerOnStateReached(prefsWithGeneratedIdMultiProcessSafe);
        this.networkExecutor.execute(FirebaseInstallations$$Lambda$4.lambdaFactory$(this, z));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doNetworkCallIfNecessary(boolean r3) {
        /*
            r2 = this;
            com.google.firebase.installations.local.PersistedInstallationEntry r0 = r2.getMultiProcessSafePrefs()
            boolean r1 = r0.isErrored()     // Catch:{ IOException -> 0x005b }
            if (r1 != 0) goto L_0x0022
            boolean r1 = r0.isUnregistered()     // Catch:{ IOException -> 0x005b }
            if (r1 == 0) goto L_0x0011
            goto L_0x0022
        L_0x0011:
            if (r3 != 0) goto L_0x001d
            com.google.firebase.installations.Utils r3 = r2.utils     // Catch:{ IOException -> 0x005b }
            boolean r3 = r3.isAuthTokenExpired(r0)     // Catch:{ IOException -> 0x005b }
            if (r3 == 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            return
        L_0x001d:
            com.google.firebase.installations.local.PersistedInstallationEntry r3 = r2.fetchAuthTokenFromServer(r0)     // Catch:{ IOException -> 0x005b }
            goto L_0x0026
        L_0x0022:
            com.google.firebase.installations.local.PersistedInstallationEntry r3 = r2.registerFidWithServer(r0)     // Catch:{ IOException -> 0x005b }
        L_0x0026:
            r2.insertOrUpdatePrefs(r3)
            boolean r0 = r3.isRegistered()
            if (r0 == 0) goto L_0x0035
            java.lang.String r0 = r3.getFirebaseInstallationId()
            r2.cachedFid = r0
        L_0x0035:
            boolean r0 = r3.isErrored()
            if (r0 == 0) goto L_0x0046
            com.google.firebase.installations.FirebaseInstallationsException r0 = new com.google.firebase.installations.FirebaseInstallationsException
            com.google.firebase.installations.FirebaseInstallationsException$Status r1 = com.google.firebase.installations.FirebaseInstallationsException.Status.BAD_CONFIG
            r0.<init>(r1)
            r2.triggerOnException(r3, r0)
            goto L_0x005a
        L_0x0046:
            boolean r0 = r3.isNotGenerated()
            if (r0 == 0) goto L_0x0057
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "cleared fid due to auth error"
            r0.<init>(r1)
            r2.triggerOnException(r3, r0)
            goto L_0x005a
        L_0x0057:
            r2.triggerOnStateReached(r3)
        L_0x005a:
            return
        L_0x005b:
            r3 = move-exception
            r2.triggerOnException(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.FirebaseInstallations.doNetworkCallIfNecessary(boolean):void");
    }

    private void insertOrUpdatePrefs(PersistedInstallationEntry persistedInstallationEntry) {
        synchronized (lockGenerateFid) {
            CrossProcessLock acquire = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            try {
                this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(persistedInstallationEntry);
            } finally {
                if (acquire != null) {
                    acquire.releaseAndClose();
                }
            }
        }
    }

    private PersistedInstallationEntry getPrefsWithGeneratedIdMultiProcessSafe() {
        PersistedInstallationEntry readPersistedInstallationEntryValue;
        synchronized (lockGenerateFid) {
            CrossProcessLock acquire = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            try {
                readPersistedInstallationEntryValue = this.persistedInstallation.readPersistedInstallationEntryValue();
                if (readPersistedInstallationEntryValue.isNotGenerated()) {
                    readPersistedInstallationEntryValue = this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(readPersistedInstallationEntryValue.withUnregisteredFid(readExistingIidOrCreateFid(readPersistedInstallationEntryValue)));
                }
            } finally {
                if (acquire != null) {
                    acquire.releaseAndClose();
                }
            }
        }
        return readPersistedInstallationEntryValue;
    }

    private String readExistingIidOrCreateFid(PersistedInstallationEntry persistedInstallationEntry) {
        if ((!this.firebaseApp.getName().equals(CHIME_FIREBASE_APP_NAME) && !this.firebaseApp.isDefaultApp()) || !persistedInstallationEntry.shouldAttemptMigration()) {
            return this.fidGenerator.createRandomFid();
        }
        String readIid = this.iidStore.readIid();
        return TextUtils.isEmpty(readIid) ? this.fidGenerator.createRandomFid() : readIid;
    }

    private PersistedInstallationEntry registerFidWithServer(PersistedInstallationEntry persistedInstallationEntry) throws IOException {
        InstallationResponse createFirebaseInstallation = this.serviceClient.createFirebaseInstallation(getApiKey(), persistedInstallationEntry.getFirebaseInstallationId(), getProjectIdentifier(), getApplicationId(), persistedInstallationEntry.getFirebaseInstallationId().length() == 11 ? this.iidStore.readToken() : null);
        int i = AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode[createFirebaseInstallation.getResponseCode().ordinal()];
        if (i == 1) {
            return persistedInstallationEntry.withRegisteredFid(createFirebaseInstallation.getFid(), createFirebaseInstallation.getRefreshToken(), this.utils.currentTimeInSecs(), createFirebaseInstallation.getAuthToken().getToken(), createFirebaseInstallation.getAuthToken().getTokenExpirationTimestamp());
        }
        if (i == 2) {
            return persistedInstallationEntry.withFisError("BAD CONFIG");
        }
        throw new IOException();
    }

    private PersistedInstallationEntry fetchAuthTokenFromServer(PersistedInstallationEntry persistedInstallationEntry) throws IOException {
        TokenResult generateAuthToken = this.serviceClient.generateAuthToken(getApiKey(), persistedInstallationEntry.getFirebaseInstallationId(), getProjectIdentifier(), persistedInstallationEntry.getRefreshToken());
        int i = AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode[generateAuthToken.getResponseCode().ordinal()];
        if (i == 1) {
            return persistedInstallationEntry.withAuthToken(generateAuthToken.getToken(), generateAuthToken.getTokenExpirationTimestamp(), this.utils.currentTimeInSecs());
        }
        if (i == 2) {
            return persistedInstallationEntry.withFisError("BAD CONFIG");
        }
        if (i == 3) {
            this.cachedFid = null;
            return persistedInstallationEntry.withNoGeneratedFid();
        }
        throw new IOException();
    }

    /* renamed from: com.google.firebase.installations.FirebaseInstallations$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
        static {
            /*
                com.google.firebase.installations.remote.TokenResult$ResponseCode[] r0 = com.google.firebase.installations.remote.TokenResult.ResponseCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.firebase.installations.FirebaseInstallations.AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode = r0
                r1 = 1
                com.google.firebase.installations.remote.TokenResult$ResponseCode r2 = com.google.firebase.installations.remote.TokenResult.ResponseCode.OK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = com.google.firebase.installations.FirebaseInstallations.AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.installations.remote.TokenResult$ResponseCode r3 = com.google.firebase.installations.remote.TokenResult.ResponseCode.BAD_CONFIG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r2 = com.google.firebase.installations.FirebaseInstallations.AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.installations.remote.TokenResult$ResponseCode r3 = com.google.firebase.installations.remote.TokenResult.ResponseCode.AUTH_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.firebase.installations.remote.InstallationResponse$ResponseCode[] r2 = com.google.firebase.installations.remote.InstallationResponse.ResponseCode.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                com.google.firebase.installations.FirebaseInstallations.AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode = r2
                com.google.firebase.installations.remote.InstallationResponse$ResponseCode r3 = com.google.firebase.installations.remote.InstallationResponse.ResponseCode.OK     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = com.google.firebase.installations.FirebaseInstallations.AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.firebase.installations.remote.InstallationResponse$ResponseCode r2 = com.google.firebase.installations.remote.InstallationResponse.ResponseCode.BAD_CONFIG     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.FirebaseInstallations.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public Void deleteFirebaseInstallationId() throws FirebaseInstallationsException, IOException {
        this.cachedFid = null;
        PersistedInstallationEntry multiProcessSafePrefs = getMultiProcessSafePrefs();
        if (multiProcessSafePrefs.isRegistered()) {
            try {
                this.serviceClient.deleteFirebaseInstallation(getApiKey(), multiProcessSafePrefs.getFirebaseInstallationId(), getProjectIdentifier(), multiProcessSafePrefs.getRefreshToken());
            } catch (FirebaseException unused) {
                throw new FirebaseInstallationsException("Failed to delete a Firebase Installation.", FirebaseInstallationsException.Status.BAD_CONFIG);
            }
        }
        insertOrUpdatePrefs(multiProcessSafePrefs.withNoGeneratedFid());
        return null;
    }

    private PersistedInstallationEntry getMultiProcessSafePrefs() {
        PersistedInstallationEntry readPersistedInstallationEntryValue;
        synchronized (lockGenerateFid) {
            CrossProcessLock acquire = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            try {
                readPersistedInstallationEntryValue = this.persistedInstallation.readPersistedInstallationEntryValue();
            } finally {
                if (acquire != null) {
                    acquire.releaseAndClose();
                }
            }
        }
        return readPersistedInstallationEntryValue;
    }
}
