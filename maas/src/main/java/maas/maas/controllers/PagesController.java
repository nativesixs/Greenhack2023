package maas.maas.controllers;

import maas.maas.Booking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Controller
public class PagesController {
//    @Value("${graphql.url:none}")
//    private String url;

    private static String selectedSeat=null;


    @GetMapping("/firstfloor")
    public String maasGet(Model model) {
        model.addAttribute("data", new maas.maas.controllers.Inputs());
//        model.addAttribute("logger","");
        return "firstfloor";
    }

    @RequestMapping(value="/firstfloor/book", method = RequestMethod.POST)
    public String index(@RequestParam(value = "action", required = false) String action,@ModelAttribute Inputs form, Model model) throws SQLException {
        model.addAttribute("action", action);
        selectedSeat=action;
//        return "redirect:/firstfloor/book";
        return "redirect:/firstfloor";
    }

    @GetMapping("/firstfloor/book")
    public String firstfloorbook(Model model){
        model.addAttribute("data", new maas.maas.controllers.Inputs());

        return "firstfloor";
    }

    @PostMapping("/submitbooking")
    public String submitBooking(Model model) throws SQLException {
        model.addAttribute("data", new maas.maas.controllers.Inputs());

        System.out.println("cheack seat "+selectedSeat);
        if(Booking.isBookable(selectedSeat)){
            System.out.println("seat free");
            Booking.bookSeat(selectedSeat,"2023-06-03");
            System.out.println("seat was booked");
        }

        return "firstfloor";
    }

    @PostMapping("/firstfloor/getdate")
    public String getdate(@ModelAttribute Inputs form, Model model) throws IOException, ExecutionException, InterruptedException{
        model.addAttribute("data", form);
        String en=form.getStartDate();
        String st=form.getEndDate();

        model.addAttribute("color","black");

        return "firstfloor";
    }








}



