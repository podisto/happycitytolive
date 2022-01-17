package com.simba.happycitytolive.application.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Created by podisto on 17/01/2022.
 */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TrancheAge {
    private final int min;
    private final int max;

}
