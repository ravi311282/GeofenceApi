package com.geofence.model;

import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class GeoFenceRequest {
    private Long id;

    @DecimalMax(value = "99999.99")
    @NotNull
    private BigDecimal longitude;
    @DecimalMax(value = "99999.99")
    @NotNull
    private BigDecimal latitude;
    @DecimalMax(value = "99999.99")
    @NotNull
    private BigDecimal radius;
}

