package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.AppSetting;
import org.holo.repo.AppSettingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppSettingHistoryService {
  private final AppSettingRepository appSettingRepository;
  private final UserContent userContent;

  public void saveAppSetting(AppSetting appSetting) {
    appSetting.setUserId(userContent.getUserId());
    AppSetting setting = Optional.ofNullable(appSettingRepository.queryFirstByUserId(userContent.getUserId())).orElse(new AppSetting());
    appSetting.setId(setting.getId());
    appSettingRepository.save(appSetting);
  }

  public AppSetting queryAppSetting() {
    return appSettingRepository.queryFirstByUserId(userContent.getUserId());
  }
}
