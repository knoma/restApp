package kmap.web.dataConfig;

import kmap.web.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by msaidi on 20.04.14.
 */
@Service
public class DataConfigService {


    @PostConstruct
    protected void initialize() {
        reopistory.save(new DataConfig("user", "demo"));
        reopistory.save(new DataConfig("admin", "admin"));
    }

    @Qualifier("dataConfigReopistory")
    @Autowired
    private DataConfigReopistory reopistory;


     List<DataConfig> loadDataConfig(){
         return (List<DataConfig>) reopistory.findAll();
     }

    public DataConfig loadByName(String s) {
        return reopistory.findByName(s);
    }
}
