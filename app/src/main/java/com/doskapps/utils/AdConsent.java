package com.doskapps.utils;

import android.content.Context;
import android.util.Log;

import com.doskapps.interfaces.AdConsentListener;
import com.doskapps.tonosgratis.R;
import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.consent.DebugGeography;

import java.net.MalformedURLException;
import java.net.URL;

public class AdConsent {

    private ConsentForm form;
    private Context context;
    private AdConsentListener adConsentListener;

    public AdConsent(Context context, AdConsentListener adConsentListener) {
        this.context = context;
        this.adConsentListener = adConsentListener;
    }

    public void checkForConsent() {
//        ConsentInformation.getInstance(context).addTestDevice("0EC517A202BD8C859CF67FB420E24129");
//        ConsentInformation.getInstance(context).setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);

        ConsentInformation consentInformation = ConsentInformation.getInstance(context);
        String[] publisherIds = {Constant.ad_publisher_id};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                Log.d("consentStatus", consentStatus.toString());

                switch (consentStatus) {
                    case PERSONALIZED:
                        adConsentListener.onConsentUpdate(consentStatus);
                        break;
                    case NON_PERSONALIZED:
                        adConsentListener.onConsentUpdate(consentStatus);
                        break;
                    case UNKNOWN:
                        if (ConsentInformation.getInstance(context)
                                .isRequestLocationInEeaOrUnknown()) {
                            requestConsent();
                        } else {
                            adConsentListener.onConsentUpdate(consentStatus);
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
                Log.e("aaa", "a");
            }
        });
    }

    public void requestConsent() {
        URL privacyUrl = null;
        try {
            privacyUrl = new URL(context.getString(R.string.privacy_policy_url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
        form = new ConsentForm.Builder(context, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        showForm();
                        // Consent form loaded successfully.
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }

                    @Override
                    public void onConsentFormClosed(ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        Log.d("consentStatus_form", consentStatus.toString());
                        adConsentListener.onConsentUpdate(consentStatus);
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        Log.d("errorDescription", errorDescription);
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .build();
        form.load();
    }

    private void showForm() {
        if (form != null) {
            form.show();
        }
    }

    public Boolean isUserFromEEA() {
        return ConsentInformation.getInstance(context).getDebugGeography() == DebugGeography.DEBUG_GEOGRAPHY_EEA;
    }
}
