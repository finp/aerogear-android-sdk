package org.aerogear.mobile.core.http;

import java.util.ArrayList;
import java.util.List;

import org.aerogear.mobile.core.configuration.https.CertificatePinningEntry;

import okhttp3.CertificatePinner;

public class OkHttpCertificatePinningParser implements HttpCertificatePinningParser {

    private final List<CertificatePinningEntry> pinningConfig;

    public OkHttpCertificatePinningParser(final List<CertificatePinningEntry> pinningConfig) {
        this.pinningConfig = new ArrayList<>(pinningConfig);
    }

    @Override
    public CertificatePinner parse() {
        CertificatePinner.Builder certPinnerBuilder = new CertificatePinner.Builder();
        for (CertificatePinningEntry pinningEntry : pinningConfig) {
            certPinnerBuilder.add(pinningEntry.getHostName(), pinningEntry.getCertificateHash());
        }
        return certPinnerBuilder.build();
    }
}
