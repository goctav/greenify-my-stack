package org.gms.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

public interface CarbonEmissionsService {

    CarbonEmissionRating getRatingForRegion(String region);


    @AllArgsConstructor
    @Getter
    class CarbonEmissionRating {

        private double rating;
    }
}
