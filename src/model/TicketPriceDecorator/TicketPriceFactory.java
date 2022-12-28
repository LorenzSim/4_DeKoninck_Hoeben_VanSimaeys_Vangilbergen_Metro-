package model.TicketPriceDecorator;

import model.MetroCard;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TicketPriceFactory {
    private static TicketPriceFactory instance;
    public static TicketPriceFactory getInstance() {
        if (instance == null) {
            instance = new TicketPriceFactory();
        }
        return instance;
    }

    public TicketPrice createTicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard metroCard) {
        Properties properties = new Properties();
        TicketPrice result = new BasicTicketPrice();
        result.setIs24Min(is24Min);
        result.setIs64Plus(is64Plus);
        result.setStudent(isStudent);
        try {
            InputStream is = Files.newInputStream(Paths.get("src/bestanden/settings.properties"));
            properties.load(is);
            is.close();
            List<String> priceDiscounts = Arrays.asList(properties.getProperty("PRICEDISCOUNTS").split(","));

            if( isStudent && priceDiscounts.contains(TicketPriceDiscountEnum.STUDENTDISCOUNT.toString())) {
                result = new StudentDiscount(result);
            }
            if (is64Plus && priceDiscounts.contains(TicketPriceDiscountEnum.AGE64PLUSDISCOUNT.toString())) {
                result = new Age64PlusDiscount(result);
            }
            if (metroCard.getAantalGebruikt() > 50 && priceDiscounts.contains(TicketPriceDiscountEnum.FREQUENTTRAVELLERDISCOUNT.toString())) {
                result = new FrequentTravellerDiscount(result);
            }
            if (priceDiscounts.contains(TicketPriceDiscountEnum.CHRISTMASLEAVEDISCOUNT.toString())) {
                result = new ChristmasLeaveDiscount(result);
            }
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
