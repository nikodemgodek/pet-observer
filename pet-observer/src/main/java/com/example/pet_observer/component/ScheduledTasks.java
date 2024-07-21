package com.example.pet_observer.component;

import com.example.pet_observer.model.Email;
import com.example.pet_observer.model.Pet;
import com.example.pet_observer.model.Vaccine;
import com.example.pet_observer.repository.PetRepository;
import com.example.pet_observer.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ScheduledTasks {

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${spring.application.appName}")
    private String applicationName;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PetRepository petRepository;

    //@Scheduled(cron = "0 0 9 * * ?")
    @Scheduled(cron = "0 51 21 * * *")
    public void TaskSendReminder() throws MessagingException {

        int daysBefore = 5;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();

        List<Pet> pets = petRepository.findAll();
        for(Pet pet : pets) {
            for(Vaccine vaccine : pet.getVaccines()) {

                LocalDate vaccineDate = LocalDate.parse(vaccine.getDateAdministered(), dtf);
                LocalDate reminderDate = vaccineDate.plusDays(30);

                String htmlContent = String.format("<!DOCTYPE html>\n" +
                        "<html lang=\"pl\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            font-family: Arial, sans-serif;\n" +
                        "            background-color: #f4f4f4;\n" +
                        "            margin: 0;\n" +
                        "            padding: 0;\n" +
                        "        }\n" +
                        "        .container {\n" +
                        "            max-width: 600px;\n" +
                        "            margin: 50px auto;\n" +
                        "            background-color: #ffffff;\n" +
                        "            padding: 20px;\n" +
                        "            border-radius: 8px;\n" +
                        "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                        "        }\n" +
                        "        .header {\n" +
                        "            background-color: #007BFF;\n" +
                        "            color: #ffffff;\n" +
                        "            padding: 10px 20px;\n" +
                        "            border-radius: 8px 8px 0 0;\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        .header h1 {\n" +
                        "            margin: 0;\n" +
                        "        }\n" +
                        "        .content {\n" +
                        "            padding: 20px;\n" +
                        "        }\n" +
                        "        .content p {\n" +
                        "            font-size: 16px;\n" +
                        "            line-height: 1.5;\n" +
                        "        }\n" +
                        "        .content a {\n" +
                        "            color: #007BFF;\n" +
                        "            text-decoration: none;\n" +
                        "        }\n" +
                        "        .footer {\n" +
                        "            text-align: center;\n" +
                        "            padding: 10px;\n" +
                        "            background-color: #f4f4f4;\n" +
                        "            border-radius: 0 0 8px 8px;\n" +
                        "        }\n" +
                        "        .footer p {\n" +
                        "            margin: 0;\n" +
                        "            font-size: 12px;\n" +
                        "            color: #777777;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <div class=\"header\">\n" +
                        "            <h1>Przypomnienie!</h1>\n" +
                        "        </div>\n" +
                        "        <div class=\"content\">\n" +
                        "            <p>Witaj właścicielu pupila <b>%s</b>,</p>\n" +
                        "            <p>Za %d dni skończy się działanie szczepionki:<br><br><b>%s</b></p>\n" +
                        "            <p>Udaj się niezwłocznie do weterynarza w celu reaktywacji szczepionki.</p>\n" +
                        "            <p>Pozdrawiam,<br>PetObserver</p>\n" +
                        "        </div>\n" +
                        "        <div class=\"footer\">\n" +
                        "            <p>&copy; 2024 Twoja Firma. Wszelkie prawa zastrzeżone.</p>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                "</html>", pet.getName(), daysBefore, vaccine.getName());

                //if(currentDate.isAfter(reminderDate.minusDays(daysBefore)) && currentDate.isBefore(reminderDate)) {

                    String message = String.format("Za %d dni skończy się szczepionka %s dla pupila %s", daysBefore, vaccine.getName(), pet.getName());
                    String subject = String.format("%s - przypomnienie", applicationName);
                    String fromEmail = "nikodem.godek6@gmail.com";

                    Email e = new Email(message, subject, fromEmail);
                    emailService.sendEmail(e, htmlContent);
                //}

            }

        }
    }
}
