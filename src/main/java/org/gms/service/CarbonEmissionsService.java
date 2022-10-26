package org.gms.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.gms.dto.StackDto;

public interface CarbonEmissionsService {

    CarbonEmissionRating getRatingForRegion(String region);

    CarbonEmissionRating getRatingForStack(StackDto stack);

    @AllArgsConstructor
    @Getter
    class CarbonEmissionRating {

        private double rating;
    }
}
