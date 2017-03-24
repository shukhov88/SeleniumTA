package by.stqa.selenium.tasks1_17;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class Task14 extends TestBaseOld {

    @Test
    public void newProductCreation() {
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("li#app-:nth-child(3)")).click();
        driver.findElement(By.cssSelector("a.button")).click();

        int linksSize = driver.findElements(By.cssSelector("i.fa.fa-external-link")).size();

        for (int i = 0; i < linksSize; i++) {
            List<WebElement> linksList = driver.findElements(By.cssSelector("i.fa.fa-external-link"));

            String mainWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();

            linksList.get(i).click();

            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);

        }

        System.out.println("1");
    }
}
