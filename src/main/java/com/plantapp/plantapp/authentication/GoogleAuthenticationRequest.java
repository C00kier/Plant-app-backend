package com.plantapp.plantapp.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoogleAuthenticationRequest {
    private String credential;
    private String clientId;
    private String select_by;
}
