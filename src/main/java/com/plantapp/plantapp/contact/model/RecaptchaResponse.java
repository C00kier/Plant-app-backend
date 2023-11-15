package com.plantapp.plantapp.contact.model;

import lombok.Getter;
import lombok.Setter;


public record RecaptchaResponse(Boolean success,String challege_ts,String hostname,Double score, String action) {
}
