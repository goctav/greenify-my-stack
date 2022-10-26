package org.gms.service;

import jakarta.inject.Singleton;
import org.gms.dto.StackDto;

@Singleton
public class CarbonEmissionsServiceImpl implements CarbonEmissionsService {

    public CarbonEmissionRating getRatingForRegion(String region) {
        return new CarbonEmissionRating(Math.random() * 100);
    }

    @Override
    public CarbonEmissionRating getRatingForStack(StackDto stack) {
        return new CarbonEmissionRating(Math.random() * 100); // TODO actual calculation
    }
}
