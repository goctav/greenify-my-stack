package org.gms.service;

import jakarta.inject.Singleton;

@Singleton
public class CarbonEmissionsServiceImpl implements CarbonEmissionsService {

    public CarbonEmissionRating getRatingForRegion(String region) {
        return new CarbonEmissionRating(Math.random() * 100);
    }
}
