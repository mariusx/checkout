package biz.marius.ecom.checkout.repository;

import biz.marius.ecom.checkout.model.Watch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WatchRepository {
  public Map<String, List<Watch>> findByIdIn(List<String> distinctWatches) {
    return Map.of();
  }
}
