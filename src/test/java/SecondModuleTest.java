import org.junit.Assert;
import org.junit.Test;
import ru.abdullaev.model.City;
import ru.abdullaev.service.CityService;
import ru.abdullaev.service.InOutService;

import java.io.FileNotFoundException;
import java.util.List;

public class SecondModuleTest {
    @Test
    public void sortByNameTest() throws FileNotFoundException {
        InOutService inOutService = new InOutService();
        List<City> notSorted = inOutService.readToList(getClass().getResource("second_module_notsorted.txt").getFile());

        List<City> sortedFile = inOutService.readToList(getClass().getResource("second_module_name_sorted.txt").getFile());
        List<City> sorted = new CityService(notSorted).sortByName();

        Assert.assertEquals(sorted, sortedFile);
    }

    @Test
    public void sortByDistrictTest() throws FileNotFoundException {
        InOutService inOutService = new InOutService();
        List<City> notSorted = inOutService.readToList(getClass().getResource("second_module_notsorted.txt").getFile());

        List<City> sortedFile = inOutService.readToList(getClass().getResource("second_module_district_sorted.txt").getFile());
        List<City> sorted = new CityService(notSorted).sortByDistrictThanByName();

        Assert.assertEquals(sorted, sortedFile);
    }
}
