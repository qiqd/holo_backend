package org.holo.repo;

import org.holo.entity.AppSetting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSettingRepository extends MongoRepository<AppSetting, String> {
  AppSetting queryFirstByUserId(String userId);
}
