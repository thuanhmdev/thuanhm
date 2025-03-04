package com.domain.blog.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SettingResponse {

    private String id;
    private String title;
    private String siteName;
    private String email;
    private String description;
    private String facebookLink;
    private String messengerLink;
    private String instagramLink;
    private String fanpageFacebookLink;
}
