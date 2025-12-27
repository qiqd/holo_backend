package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.History;
import org.holo.repo.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
  private final HistoryRepository historyRepository;
  private final UserContent userContent;

  public List<History> queryHistory() {
    String userId = userContent.getUserId();
    return historyRepository.findAllByUserId(userId);
  }

  public void saveHistory(List<History> historyList) {
   historyList.forEach(history -> { historyRepository.findById(history.getId()).ifPresentOrElse(historyFromDb -> {
     historyFromDb.setPosition(history.getPosition());
     historyFromDb.setEpisodeIndex(history.getEpisodeIndex());
     historyFromDb.setLineIndex(history.getLineIndex());
     historyFromDb.setLastViewAt(history.getLastViewAt());
     historyFromDb.setUpdatedAt(LocalDateTime.now());
     historyRepository.save(historyFromDb);
   }, () -> {
     history.setUserId(userContent.getUserId());
     history.setCreatedAt(LocalDateTime.now());
     history.setUpdatedAt(LocalDateTime.now());
     historyRepository.save(history);
   });});

  }

  public void deleteHistoryById(String id) {
    historyRepository.deleteByUserIdAndId(userContent.getUserId(), id);
  }

  public void removeAllHistory() {
    historyRepository.deleteAllByUserId(userContent.getUserId());
  }
}
