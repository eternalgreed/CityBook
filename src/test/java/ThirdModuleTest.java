import org.junit.Assert;
import org.junit.Test;
import ru.abdullaev.Main;
import ru.abdullaev.model.City;
import ru.abdullaev.service.CityService;
import ru.abdullaev.service.InOutService;

import java.io.FileNotFoundException;
import java.util.List;

public class ThirdModuleTest {
    @Test
    public void maxPopulationTest() throws FileNotFoundException {
        InOutService inOutService = new InOutService();
        List<City> cities = inOutService.readToList(getClass().getResource("third_module.txt").getFile());
        String maxPopulationCityFromFile = new CityService(cities).maxPopulationCity();
        String string = String.format("[%d] = %,d",4, 165183);

        Assert.assertEquals(string, maxPopulationCityFromFile);
    }
}
