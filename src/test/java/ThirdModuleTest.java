import org.junit.Assert;
import org.junit.Test;
import ru.abdullaev.service.CityService;
import ru.abdullaev.service.InOutService;

import java.io.FileNotFoundException;

public class ThirdModuleTest {
    @Test
    public void maxPopulationTest() throws FileNotFoundException {
        InOutService inOutService = new InOutService();
        inOutService.readToList(getClass().getResource("third_module.txt").getFile());
        String maxPopulationCityFromFile = new CityService().maxPopulationCity(inOutService.getCityList());
        String string = String.format("[%d] = %,d",4, 165183);
        Assert.assertEquals(string, maxPopulationCityFromFile);
    }
}
