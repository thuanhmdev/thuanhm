package com.domain.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactAdminResponse {

    private String facebookLink;
    private String messengerLink;
    private String xLink;
    private String instagramLink;
    private String zaloLink;
    private String fanpageFacebookLink;
}
