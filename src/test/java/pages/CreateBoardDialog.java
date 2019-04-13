package pages;

import org.openqa.selenium.By;

/**
 * Create Board dialog elements
 */
public enum CreateBoardDialog implements Element {

    ADD_BOARD_TITLE_FIELD("//input[@placeholder='Add board title']"),
    CREATE_BOARD_BUTTON("//form[@class='create-board-form']/button/span[2]"),
    CANCEL_BUTTON("//*[@id='classic']/div[3]/div/div/div/form/div/div/button");

    private String xpath;

    CreateBoardDialog(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public By getSelector() {
        return By.xpath(this.xpath);
    }
}
