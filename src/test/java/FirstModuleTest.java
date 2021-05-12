import org.junit.Assert;
import org.junit.Test;
import ru.abdullaev.model.City;
import ru.abdullaev.service.InOutService;

import java.io.FileNotFoundException;
import java.util.List;

public class FirstModuleTest {

    @Test(expected = FileNotFoundException.class)
    public void exceptionThrowTest() throws FileNotFoundException {
        new InOutService().readToList("notexistingfile.txt");
    }

    @Test
    public void correctSaveToListTest() throws FileNotFoundException {
        InOutService inOutService = new InOutService();
        inOutService.readToList(getClass().getResource("first_module.txt").getFile());
        List<City> cityList = inOutService.getCityList();
        City fromFile = cityList.get(0);
        City city = new City("Агрыз","Татарстан","Приволжский",19299,"1646");
        Assert.assertEquals(city,fromFile);
    }
}
