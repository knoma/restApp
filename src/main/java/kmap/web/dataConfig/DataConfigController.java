package kmap.web.dataConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by msaidi on 20.04.14.
 */
@RestController
@RequestMapping(value = "/data")
public class DataConfigController {


    @Qualifier("dataConfigService")
    @Autowired
    private DataConfigService configService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DataConfig> getAllSeries() {
        List<DataConfig> l = configService.loadDataConfig();

        return l;
    }
}
