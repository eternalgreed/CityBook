import org.junit.Assert;
import org.junit.Test;
import ru.abdullaev.model.City;
import ru.abdullaev.service.CityService;
import ru.abdullaev.service.InOutService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FourthModuleTest {
    @Test
    public void countCityByRegionTest() throws FileNotFoundException {
        InOutService inOutService = new InOutService();
        List<City> cityList = inOutService.readToList(getClass().getResource("fourth_module.txt").getFile());
        List<String> fromFile = new CityService(cityList).countCitiesByRegion();
        System.out.println(fromFile);
        List<String> list = new ArrayList<>();
        list.add("Хакасия - 2");
        list.add("Алтай - 1");
        Assert.assertEquals(fromFile,list);

    }
}
