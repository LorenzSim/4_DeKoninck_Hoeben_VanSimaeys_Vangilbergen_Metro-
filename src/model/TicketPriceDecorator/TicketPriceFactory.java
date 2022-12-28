package model.TicketPriceDecorator;

import model.MetroCard;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        List<String> allowedDiscounts;

        try {
            InputStream is = Files.newInputStream(Paths.get("src/bestanden/settings.properties"));
            properties.load(is);
            is.close();
            allowedDiscounts = Arrays.asList(properties.getProperty("PRICEDISCOUNTS").split(","));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TicketPrice result = new BasicTicketPrice();

        result.setMetroCard(metroCard);
        result.setIs24Min(is24Min);
        result.setStudent(isStudent);
        result.setIs64Plus(is64Plus);

        for (String discount : allowedDiscounts) {
            if (discount.isEmpty()) break;
            try {
                Class<?> discountClass = Class.forName(TicketPriceDiscountEnum.valueOf(discount).getGetClassFileName());
                result = (TicketPrice) discountClass.getConstructor(TicketPrice.class).newInstance(result);
            }
            catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return result;

    }
}
