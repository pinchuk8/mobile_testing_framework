package testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import testing.BaseEntities.BaseTest;
import testing.models.Item;
import testing.pages.CalculatorStartPage;
import testing.utils.Randomization;

public class SmokeTests extends BaseTest {
    @Test
    public void addItem() {
        Item item = new Item();
        item.setItemName(Randomization.getRandomString(4));
        item.setCurrency(Randomization.getRandomInt(5));
        item.setLabel(Randomization.getRandomString(4));
        CalculatorStartPage calculatorStartPage = new CalculatorStartPage();
        calculatorStartPage.enterItemValues(item);
        calculatorStartPage.clickSaveButton();
//        SoftAssert asert=new SoftAssert();
//        asert.assertEquals(calculatorStartPage.getItemNameDataString(),item.getItemName(),"names are not equals");
//        asert.assertEquals(calculatorStartPage.getCurrencyDataString(),item.getCurrency(),"currency are not equals");
//        asert.assertEquals(calculatorStartPage.getLabelDataString(),item.getLabel(),"labels are not equals");
//        asert.assertAll();
        Assert.assertTrue(calculatorStartPage.getItemList().isDisplayed());
    }
}
