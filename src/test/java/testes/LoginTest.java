package testes;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LoginTest {

    @Test
    public void loginSuccess() throws InterruptedException {
        // user, password and original URL got censored due to reasons
        final String url = "https://mantisbt.org";
        String user = "user", password = "password";

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        //Creating a driver
        WebDriver chrome = new ChromeDriver();

        //Going to the url
        chrome.navigate().to(url);

        //Select the input element and inputting the user into the element
        WebElement inputUser = chrome.findElement(By.id("username"));
        inputUser.sendKeys(user);

        //Finding the "Entrar" button and clicking on the "Entrar" button
        WebElement entrarButton1 = chrome.findElement(By.xpath("//*[@value='Entrar']"));
        entrarButton1.click();

        //Getting to the next page
        //Finding the password input and inputting the password
        WebElement passwordInput = chrome.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        //Finding the "Entrar" button of this page and clicking on the "Entrar" Button
        WebElement entrarButton2 = chrome.findElement(By.xpath("//*[@value='Entrar']"));
        entrarButton2.click();



        //System.out.println("If this executed the test 1st was successful!");

        //End of the first test
        //
        //
        //
        //
        //Starting the second test
        final String groupNameCheck = "grupoCianoClaro ( Grupo Ciano Claro )";

        //Delaying for the page to load
        WebDriverWait wait1 = new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='breadcrumbs']//a[@href='/account_page.php']")));

        //Finding the element to check
        WebElement check = chrome.findElement(By.xpath("//div[@id='breadcrumbs']//a[@href='/account_page.php']"));

        //Checking the element's content
        Assert.assertEquals(check.getText(), groupNameCheck);
//        if (check.getText().equals(groupNameCheck)) {
//            System.out.println("If this executed the 2nd test was successful!");
//        } else {
//            throw new RuntimeException();
//        } reinventei a roda

        //End of the second test
        //
        //
        //
        //
        //Starting the third test

        //Finding the "Criar Tarefa" button and clicking on it
        WebElement createTask = chrome.findElement(By.xpath("//a[@href='/bug_report_page.php']"));
        createTask.click();

        //Array of IDs and values
        String[] taskIDs = {"category_id", "reproducibility", "severity", "priority", "handler_id", "summary", "description",
        "steps_to_reproduce", "additional_info", "tag_select"};
        String[] taskValues = {"2", "50", "40", "20", "122", "1"};
        String[] inputLabels = {"Criar uma tarefa no sistema de bug tracking Mantis.",
        "O código foi realizado pelo grupoCianoClaro e tem o objetivo de automatizar a criação de tarefas no Mantis.",

        "Acessar o Mantis (https://mantis-prova.base2.com.br/)\n" +
                "2. Efetuar login (usuario: grupoXX, senha 123456, onde XX é o número do seu grupo)\n" +
                "3. Clicar em “Criar tarefa” no menu lateral\n" +
                "4. Selecionar uma categoria\n" +
                "5. Selecionar uma frequência\n" +
                "6. Selecionar uma prioridade\n" +
                "7. Preencher o campo “Resumo”\n" +
                "8. Preencher o campo “Descrição”\n" +
                "9. Preencher o campo “Passos para reproduzir”\n" +
                "10. Preencher o campo “Informações adicionais”\n" +
                "11. (Opcional) Inserir anexo em “Enviar arquivos”\n" +
                "12. Clicar em “Criar nova tarefa”",

        "Agradeço grandemente por nos ter dado essa oportunidade de nos ensinar e acompanhar em um projeto tão interessante!"};

        //Fill all the select tags and the input areas
        int i = 0, j = 0;
        for (String id : taskIDs) {

            //Finding the element
            WebElement taskCategory = chrome.findElement(By.id(id));

            //Checks if the tag type is "select" or not
            if (taskCategory.getTagName().equals("select")) {
                Select option = new Select(taskCategory);

                //List<WebElement> options = option.getOptions();
                //for (WebElement element : options) {
                //    System.out.println(element.getAttribute("value"));
                //}

                option.selectByValue(taskValues[i]);
                i++;

            } else {

                WebElement label = chrome.findElement(By.id(id));
                label.sendKeys(inputLabels[j]);

                j++;
            }
        }
        Thread.sleep(30000);

        //Finish the task creation
        WebElement finish = chrome.findElement(By.xpath("//input[@value='Criar Nova Tarefa']"));
        finish.click();
    }
}
