package kmap.web.dataConfig;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by msaidi on 20.04.14.
 */
public interface DataConfigReopistory extends CrudRepository<DataConfig, Long> {

    DataConfig findByName(String name);
}