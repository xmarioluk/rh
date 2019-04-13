package pages;

import org.openqa.selenium.By;

/**
 * Card dialog elements
 */
public enum CardDialog implements Element {

    ADD_CHECKLIST("//*[@id='classic']/div[3]/div/div/div/div[6]/div[1]/div/a[3]"),
    CHECKLIST_TITLE("//*[@id='id-checklist']"),
    CHECKLIST_ADD_BUTTON("//*[@id='classic']/div[4]/div/div[2]/div/div/div/form/input[2]"),
    CHECKLIST("//*[@id='classic']/div[3]/div/div/div/div[5]/div[8]/div/div[1]/div/h3"),
    DESCRIPTION("//*[@id='classic']/div[3]/div/div/div/div[5]/div[2]/div[2]/div/div/p[1]/a"),
    DESCRIPTION_TEXTAREA("//*[@id='classic']/div[3]/div/div/div/div[5]/div[2]/div[2]/div/div/div[3]/textarea"),
    DESCRIPTION_SAVE("//*[@id='classic']/div[3]/div/div/div/div[5]/div[2]/div[2]/div/div/div[3]/div/input"),
    DESCRIPTION_CONTENT("//*[@id='classic']/div[3]/div/div/div/div[5]/div[2]/div[2]/div/div/div[2]/p"),
    ADD_ATTACHMENT("//*[@id='classic']/div[3]/div/div/div/div[6]/div[1]/div/a[5]"),
    ATTACH_FROM_COMPUTER("//*[@id='classic']/div[4]/div/div[2]/div/div/div/ul/li[1]/form[@class='realfile']/input[3]"),
    ATTACHMENT_CONTENT("//*[@id='classic']/div[3]/div/div/div/div[5]/div[7]/div[2]/div/div/p/span[1]");

    private String xpath;

    CardDialog(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public By getSelector() {
        return By.xpath(xpath);
    }
}
