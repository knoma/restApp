package kmap.web.dataConfig;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msaidi on 20.04.14.
 */
@RestController
@RequestMapping(value="/data")
public class DataConfigController {

    @RequestMapping(method= RequestMethod.GET)
    public List<DataConfig> getAllSeries() throws InterruptedException {Thread.sleep(4000);	//pause to better show sync/async RestTemplate behavior

        List<DataConfig> l = new ArrayList<>();

        for (int i=0; i< 1000; i++){
            l.add(new DataConfig(""+1,""+1));
        }

        return l;
    }
}
