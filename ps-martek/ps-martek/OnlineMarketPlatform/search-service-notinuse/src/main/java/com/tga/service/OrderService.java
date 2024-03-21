package com.tga.service;

import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
  

    @Autowired
    private RestClient restClient;
}
