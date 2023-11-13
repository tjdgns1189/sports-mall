package edu.spring.mall.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;


@Configuration
public class OauthConfig {

    @Bean(name = "clientRegistrationRepository")
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
                googleClientRegistration(),
                naverClientRegistration(),
                kakaoClientRegistration()
        );
    }

    private ClientRegistration googleClientRegistration() {
        // Google OAuth2 설정
        return ClientRegistration.withRegistrationId("google")
                .clientId("259608606224-ubjj97th9chjcjvvmugd0vkssi8t0oa8.apps.googleusercontent.com")
                .clientSecret("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")
                .scope(new String[]{"openid", "profile", "email"})
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/mall/oauth/google")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .clientName("Google")
                .build();
    }

    private ClientRegistration naverClientRegistration() {
        // 네이버 OAuth2 설정
        return ClientRegistration.withRegistrationId("naver")
                .clientId("H7PD5nLOq1YXa7eImpSC")
                .clientSecret("8P2x1Hivt0")
                .scope("name", "email","phone")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/mall/oauth/naver")
                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
                .tokenUri("https://nid.naver.com/oauth2.0/token")
                .userInfoUri("https://openapi.naver.com/v1/nid/me")
                .clientName("Naver")
                .build();
    }

    private ClientRegistration kakaoClientRegistration() {
        // 카카오 OAuth2 설정
        return ClientRegistration.withRegistrationId("kakao")
                .clientId("kakao-client-id")
                .clientSecret("kakao-client-secret") // 일부 설정에서는 필요 없을 수 있음
                .scope("profile", "account_email")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/kakao")
                .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                .tokenUri("https://kauth.kakao.com/oauth/token")
                .userInfoUri("https://kapi.kakao.com/v2/user/me")
                .clientName("Kakao")
                .build();
    }


}
