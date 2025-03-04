package com.domain.blog.service;

import com.domain.blog.entity.Setting;
import com.domain.blog.dto.request.SettingRequest;
import com.domain.blog.dto.response.ContactAdminResponse;
import com.domain.blog.dto.response.SettingResponse;

public interface SettingService {


    SettingResponse getSettingResponse();

    Setting getSetting();

    SettingResponse updateSetting(SettingRequest setting);

    ContactAdminResponse getInformationContactAdmin();

    String getSiteNameSetting();

    String getEmailSetting();
}