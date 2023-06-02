package maas.maas.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
    public String index(@RequestParam(value = "action", required = false) String action, Model model,Inputs form) {
//        model.addAttribute("data", form);
        model.addAttribute("action", action);
//        maas.maas.Booking.main(action);
        selectedSeat=action;

        return "redirect:/firstfloor/book";
    }

    @GetMapping("/firstfloor/book")
    public String firstfloorbook(Model model) {
        model.addAttribute("data", new maas.maas.controllers.Inputs());
        return "firstfloor";
    }

    @PostMapping("/submitbooking")
    public String submitBooking(Model model) {
        model.addAttribute("data", new maas.maas.controllers.Inputs());
        System.out.println("submit");
        return "firstfloor";
    }



//    @PostMapping("/firstfloor")
//    public String maasSub(@ModelAttribute maas.maas.controllers.Inputs form, Model model) throws IOException, ExecutionException, InterruptedException{
//        model.addAttribute("data", form);
//
////        String log = maas.maas.main(ckod,mode, inventory,url);
////        model.addAttribute("logger",log);
//
//        return "firstfloor";
//    }

//    @PostMapping("/firstfloor/*")
//    public String postdb(@ModelAttribute maas.maas.controllers.Inputs form, Model model) throws IOException, ExecutionException, InterruptedException{
//        model.addAttribute("data", form);
//        String id = form.getId();
//
//
//
//        System.out.println("clk "+id);
//        model.addAttribute("logger",id);
//        System.out.println(id);
//        return "firstfloor";
//    }





}



