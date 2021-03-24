package pageobjects.chain;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormPage {
    //bad practice to store test data in pageObject
    //initialize variables

    private String firstName = "Jason",
            lastName = "Born",
            gender = "Male",
            email = "pulsar@hotmail.com",
            phoneNumber = "9253339898",
            month = "4",
            year = "1977",
            day = "10",
            subject1 = "Computer Science",
            subject2 = "Math",
            hobbie1 = "Sports",
            hobbie2 = "Music",
            filename = "1.png",
            address = "LA, Oak str., 13",
            state = "NCR",
            city = "Noida",
            pageHeader = "Thanks for submitting the form";

    public StudentRegistrationFormPage openPage() {
        //open target page

        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }
    public StudentRegistrationFormPage fillForm() {
        //FILL OUT THE FORM

        //type name, surname, email, gender, phone number

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);

        setDate(year, month, day);
        //type subject

        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();

        //type hobbie

        $(byText(hobbie1)).click();
        $(byText(hobbie2)).click();

        //load file hello_world.txt

        //$("#uploadPicture").uploadFile(new File("src/test/resources/hello_world.txt")); //long way
        $("#uploadPicture").uploadFromClasspath("img/" + filename);             //optimal way

        //type address

        $("#currentAddress").setValue(address).pressTab();

        //type state

        $("#state").click();
        $(byText(state)).click();

        //type city

        $("#city").click();
        $(byText(city)).click();

        //submit the form

        $("#submit").pressEnter();

        return this;
    }

    public void setDate(String year, String month, String day) {

        //type date of birth

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue(month);    //month
        $(".react-datepicker__year-select").selectOptionByValue(year);      //year
        $(".react-datepicker__month").$(byText(day)).click();               //day
    }

    public StudentRegistrationFormPage checkData() {
        //CHECK THE FORM

        //check the header, should be "Thanks for submitting the form"

        $("#example-modal-sizes-title-lg").shouldHave(text(pageHeader));

        //check the form elements

        $(".table-responsive").shouldHave(
                text(firstName),
                text(lastName),
                text(email),
                text(gender),
                text(phoneNumber),
                text(day),
                text("may"),
                text(year),
                text(subject1),
                text(subject2),
                text(hobbie1),
                text(hobbie2),
                text(filename),
                text(address),
                text(state),
                text(city));

        return this;
    }
}
