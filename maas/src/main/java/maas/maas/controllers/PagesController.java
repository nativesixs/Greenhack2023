package maas.maas.controllers;

import maas.maas.Booking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Controller
public class PagesController {
//    @Value("${graphql.url:none}")
//    private String url;
    private static int totalSeats=4;

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
//        String st=form.getStartDate();
//        String en=form.getEndDate();
        java.sql.Date start = getSQLdate(startDate);
        java.sql.Date end = getSQLdate(endDate);
        isFree=Booking.callByDate(start,end,selectedSeat);
        colorUpdate(model);

        Booking.fetchMaster(selectedSeat);





        return "redirect:/firstfloor";
    }

    @GetMapping("/firstfloor/book")
    public String firstfloorbook(Model model){
        model.addAttribute("data", new maas.maas.controllers.Inputs());

        return "firstfloor";
    }

    @PostMapping("/submitbooking")
    public String submitBooking(@ModelAttribute Inputs form, Model model) throws SQLException {
//        model.addAttribute("data", new maas.maas.controllers.Inputs());


        System.out.println("cheack seat "+selectedSeat);
        if(Booking.isBookable(selectedSeat)){
            System.out.println("seat free");
            Booking.bookSeat(selectedSeat,"2023-06-03");
            System.out.println("seat was booked");
        }

        for(int j=0;j<isFree.size();j++){
            if(!isFree.get(j).equals("free")){
                System.out.println("good");
            }else {
                System.out.println("bad");
            }
        }

        model.addAttribute("data", form);
        colorUpdate(model);

        return "firstfloor";
    }

    @PostMapping("/firstfloor/getdate")
    public String getdate(@ModelAttribute Inputs form, Model model) throws IOException, ExecutionException, InterruptedException, SQLException {
        model.addAttribute("data", form);

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



