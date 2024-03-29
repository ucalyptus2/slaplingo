package com.google.ads.mediation;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.2.0 */
final class zzb implements RewardedVideoAdListener {
    private final /* synthetic */ AbstractAdViewAdapter zzmp;

    zzb(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzmp = abstractAdViewAdapter;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdLoaded() {
        this.zzmp.zzmn.onAdLoaded(this.zzmp);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdOpened() {
        this.zzmp.zzmn.onAdOpened(this.zzmp);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoStarted() {
        this.zzmp.zzmn.onVideoStarted(this.zzmp);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdClosed() {
        this.zzmp.zzmn.onAdClosed(this.zzmp);
        InterstitialAd unused = this.zzmp.zzmm = null;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewarded(RewardItem rewardItem) {
        this.zzmp.zzmn.onRewarded(this.zzmp, rewardItem);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdLeftApplication() {
        this.zzmp.zzmn.onAdLeftApplication(this.zzmp);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoAdFailedToLoad(int i) {
        this.zzmp.zzmn.onAdFailedToLoad(this.zzmp, i);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public final void onRewardedVideoCompleted() {
        this.zzmp.zzmn.onVideoCompleted(this.zzmp);
    }
}
