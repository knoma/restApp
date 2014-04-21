package kmap.web.dataConfig;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by msaidi on 20.04.14.
 */
public interface DataConfigReopistory extends CrudRepository<DataConfig, Long> {

    DataConfig findByName(String name);
}