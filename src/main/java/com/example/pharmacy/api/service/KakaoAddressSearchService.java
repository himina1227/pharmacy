package com.example.pharmacy.api.service;

import com.example.pharmacy.api.dto.KakaoApiResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoAddressSearchService {

    private final KakaoUriBuilderService kakaoUriBuilderService;

    private final RestTemplate restTemplate;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    public KakaoApiResponse requestAddressSearch(String address) {

        if (ObjectUtils.isEmpty(address)) return null;

        URI uri = kakaoUriBuilderService.buildUriByAddressSearch(address);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponse.class).getBody();
    }
}
