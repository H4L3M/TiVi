package ma.nokhbativi.ads

import android.app.Activity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds

class UnityAdManager(private val activity: Activity) : DefaultLifecycleObserver,
    IUnityAdsInitializationListener,
    IUnityAdsLoadListener, IUnityAdsShowListener {

    private val placementId = "Interstitial_Android"

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        UnityAds.initialize(activity, "4503555", false, this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        UnityAds.load(placementId, this)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        UnityAds.show(activity, placementId, this)
    }


    override fun onUnityAdsShowFailure(
        placementId: String?,
        error: UnityAds.UnityAdsShowError?,
        message: String?
    ) {
        UnityAds.load(placementId, this)
    }

    override fun onUnityAdsShowStart(placementId: String?) {
        UnityAds.load(placementId, this)
    }

    override fun onUnityAdsShowClick(placementId: String?) {
        UnityAds.load(placementId, this)
    }

    override fun onUnityAdsShowComplete(
        placementId: String?,
        state: UnityAds.UnityAdsShowCompletionState?
    ) {
        UnityAds.load(placementId, this)
    }

    override fun onUnityAdsAdLoaded(placementId: String?) {

    }

    override fun onUnityAdsFailedToLoad(
        placementId: String?,
        error: UnityAds.UnityAdsLoadError?,
        message: String?
    ) {
        UnityAds.load(placementId, this)
    }

    override fun onInitializationComplete() {
        UnityAds.load(placementId, this)
    }

    override fun onInitializationFailed(
        error: UnityAds.UnityAdsInitializationError?,
        message: String?
    ) {
        UnityAds.initialize(activity, "4503555", false, this)
    }
}