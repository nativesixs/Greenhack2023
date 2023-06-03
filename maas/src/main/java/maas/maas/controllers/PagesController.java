package maas.maas.controllers;

import maas.maas.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class PagesController {
//    @Value("${graphql.url:none}")
//    private String url;
    private static int totalSeats=15;

    private static String selectedSeat=null;
    private static String startDate=null;
    private static String endDate=null;
    private static ArrayList<String> isFree = new ArrayList<>();


    @GetMapping("/firstfloor")
    public String maasGet(Model model) {
        model.addAttribute("data", new maas.maas.controllers.Inputs());

        colorUpdate(model);

        return "firstfloor";
    }

    @RequestMapping(value="/firstfloor/book", method = RequestMethod.POST)
    public String index(@RequestParam(value = "action", required = false) String action,@ModelAttribute Inputs form, Model model) throws SQLException {
        model.addAttribute("action", action);
        selectedSeat=action;

        model.addAttribute("data", form);
        java.sql.Date start = getSQLdate(startDate);
        java.sql.Date end = getSQLdate(endDate);
        isFree=Booking.callByDate(start,end,selectedSeat);
        colorUpdate(model);


        return "redirect:/firstfloor";
    }


    @GetMapping("/firstfloor/book")
    public String firstfloorbook(Model model){
        model.addAttribute("data", new maas.maas.controllers.Inputs());

        return "firstfloor";
    }

    @PostMapping("/submitbooking")
    public String submitBooking(@ModelAttribute Inputs form, Model model) throws SQLException {
        if(Booking.isBookable(selectedSeat)){
            for(int i=0;i<isFree.size();i++){
                if(!isFree.get(i).equals("free")){
                    System.out.println("not free");
                    return "firstfloor";
                }
            }

            Booking.bookSeat(selectedSeat,"2023-06-03");
        }

        model.addAttribute("data", form);
        colorUpdate(model);

        return "firstfloor";
    }

    @PostMapping("/firstfloor/getdate")
    public String getdate(@ModelAttribute Inputs form, Model model) throws IOException, ExecutionException, InterruptedException, SQLException {
        model.addAttribute("data", form);

//        selectedSeat= String.valueOf(ThreadLocalRandom.current().nextInt(1, 2 + 1));
//        Booking.fetchMaster(selectedSeat);
//        model.addAttribute("monitor",Booking.monitor);
//        model.addAttribute("side",Booking.temp);
//        model.addAttribute("temp",Booking.side);


//        String st=form.getStartDate();
//        String en=form.getEndDate();
//        java.sql.Date start = getSQLdate(st);
//        java.sql.Date end = getSQLdate(en);
//        Booking.callByDate(start,end,selectedSeat);
        startDate=form.getStartDate();
        endDate=form.getEndDate();

        colorUpdate(model);

        return "firstfloor";
    }

    private static java.sql.Date getSQLdate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            java.util.Date utilDate = format.parse(Booking.getYear(date)+"/"+Booking.getMonth(date)+"/"+Booking.getDay(date));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void colorUpdate(Model model){
        ArrayList<String> booked = Booking.getBooked();
        String c=null;

        for(int i=0;i<booked.size();i++){
            c="color"+booked.get(i);
            model.addAttribute(c,"red");
        }
        for(int i=0;i<=totalSeats;i++){
            if(!booked.contains(String.valueOf(i))){
                c="color"+i;
                model.addAttribute(c,"green");
            }
        }
    }






}



