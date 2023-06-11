package biz.marius.ecom.checkout.repository;

import biz.marius.ecom.checkout.model.Watch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class WatchRepository {
  private final Map<String, Watch> watches = new ConcurrentHashMap<>();

  public WatchRepository() {
    watches.put("001", new Watch("001", 100));
    watches.put("002", new Watch("002", 80));
    watches.put("003", new Watch("003", 50));
    watches.put("004", new Watch("004", 30));

  }

  /**
   * Find by ids in list and return Map grouped with List of Watches
   * @param ids Ids to find
   * @return Watches grouped by id
   */
  public Map<String, List<Watch>> findByIdIn(List<String> ids) {
    return ids.stream()
        .filter(watches::containsKey)
        .map(watches::get).collect(Collectors.groupingBy(Watch::getId));
  }

}
