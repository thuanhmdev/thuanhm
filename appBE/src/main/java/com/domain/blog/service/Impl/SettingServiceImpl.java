package com.domain.blog.service.Impl;

import com.domain.blog.entity.Setting;
import com.domain.blog.dto.request.SettingRequest;
import com.domain.blog.dto.response.ContactAdminResponse;
import com.domain.blog.dto.response.SettingResponse;
import com.domain.blog.repository.SettingRepository;
import com.domain.blog.service.SettingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j(topic = "SETTING-SERVICE")
@Tag(name = "SETTING-SERVICE")
public class SettingServiceImpl implements SettingService {
    private final SettingRepository settingsRepository;

    @PostConstruct
    public void initializeSettings() {
        if (settingsRepository.count() == 0) {
            Setting setting = new Setting();
            setting.setId("setting");
            setting.setTitle("Trang chủ");
            setting.setSiteName("ThuanHM");
            setting.setDescription("Chào mừng các bạn đến với website của tôi!");
            setting.setEmail("thuanhmdev@gmail.com");
            setting.setFacebookLink("");
            setting.setMessengerLink("");
            setting.setInstagramLink("");
            setting.setFanpageFacebookLink("");
            settingsRepository.save(setting);
        }
    }


    public SettingResponse getSettingResponse() {
        Setting setting = getSetting();
        return SettingResponse.builder()
                .id(setting.getId())
                .title(setting.getTitle())
                .siteName(setting.getSiteName())
                .email(setting.getEmail())
                .description(setting.getDescription())
                .facebookLink(setting.getFacebookLink())
                .messengerLink(setting.getMessengerLink())
                .instagramLink(setting.getInstagramLink())
                .fanpageFacebookLink(setting.getFanpageFacebookLink())
                .build();
    }

    public Setting getSetting() {
        Optional<Setting> setting = settingsRepository.findById("setting");
        return setting.orElse(null);
    }


    public SettingResponse updateSetting(SettingRequest setting) {
        Setting settingUpdate = getSetting();
        if (setting == null) {
            return null;
        }
        settingUpdate.setTitle(setting.getTitle());
        settingUpdate.setDescription(setting.getTitle());
        settingUpdate.setSiteName(setting.getSiteName());
        settingUpdate.setEmail(setting.getEmail());
        settingUpdate.setFacebookLink(setting.getFacebookLink());
        settingUpdate.setMessengerLink(setting.getMessengerLink());
        settingUpdate.setInstagramLink(setting.getInstagramLink());
        settingUpdate.setFanpageFacebookLink(setting.getFanpageFacebookLink());

        settingsRepository.save(settingUpdate);

        return SettingResponse.builder()
                .id(settingUpdate.getId())
                .title(settingUpdate.getTitle())
                .siteName(settingUpdate.getSiteName())
                .email(settingUpdate.getEmail())
                .description(settingUpdate.getDescription())
                .facebookLink(settingUpdate.getFacebookLink())
                .messengerLink(settingUpdate.getMessengerLink())
                .instagramLink(settingUpdate.getInstagramLink())
                .fanpageFacebookLink(settingUpdate.getFanpageFacebookLink())
                .build();


    }


    public ContactAdminResponse getInformationContactAdmin() {
        Setting setting = getSetting();
        return ContactAdminResponse.builder()
                .facebookLink(setting.getFacebookLink())
                .messengerLink(setting.getMessengerLink())
                .instagramLink(setting.getInstagramLink())
                .fanpageFacebookLink(setting.getFanpageFacebookLink())
                .build();
    }


    public String getSiteNameSetting() {
        Optional<Setting> setting = settingsRepository.findById("setting");
        if (setting.isPresent()) {
            return setting.get().getSiteName();
        }
        return "";
    }

    public String getEmailSetting() {
        Optional<Setting> setting = settingsRepository.findById("setting");
        if (setting.isPresent()) {
            return setting.get().getEmail();
        }
        return "";
    }
}