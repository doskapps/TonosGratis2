package com.doskapps.interfaces;

import com.google.ads.consent.ConsentStatus;

public interface AdConsentListener {
    void onConsentUpdate(ConsentStatus consentStatus);
}
