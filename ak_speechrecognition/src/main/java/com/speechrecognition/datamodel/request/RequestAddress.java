package com.speechrecognition.datamodel.request;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class RequestAddress {
  @Value("${request.address}")
 private String serverWordAddress;

}
