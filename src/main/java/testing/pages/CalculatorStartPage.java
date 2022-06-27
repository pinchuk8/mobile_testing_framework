package testing.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import testing.BaseEntities.BasePage;
import testing.models.Item;

public class CalculatorStartPage extends BasePage {

    @AndroidFindBy(id = "com.github.vitalliuss.financeanalyzer:id/autoCompleteTextViewExpenseName")
    private MobileElement itemNameField;
    @AndroidFindBy(id = "com.github.vitalliuss.financeanalyzer:id/editTextExpenseAmount")
    private MobileElement currencyField;
    @AndroidFindBy(id = "com.github.vitalliuss.financeanalyzer:id/autoCompleteTextViewExpenseLabel")
    private MobileElement labelField;
    @AndroidFindBy(id = "com.github.vitalliuss.financeanalyzer:id/buttonExpenseSave")
    private MobileElement saveButton;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[1]")
    private MobileElement itemNameDataString;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[3]")
    private MobileElement currencyDataString;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[4]")
    private MobileElement labelDataString;
    @AndroidFindBy(id = "com.github.vitalliuss.financeanalyzer:id/listViewExpenseList")
    private MobileElement itemList;

    public MobileElement getItemList() {
        return itemList;
    }

    public String getItemNameDataString() {
        return itemNameDataString.getAttribute("text");
    }

    public String getCurrencyDataString() {
        return currencyDataString.getAttribute("text");
    }

    public String getLabelDataString() {
        return labelDataString.getAttribute("text");
    }

    public CalculatorStartPage enterItemValues(Item item) {
        itemNameField.sendKeys(item.getItemName());
        currencyField.sendKeys(String.valueOf(item.getCurrency()));
        labelField.sendKeys(item.getLabel());
        return this;
    }

    public void clickSaveButton() {
        saveButton.click();
    }
}
