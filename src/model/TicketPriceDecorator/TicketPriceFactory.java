package model.TicketPriceDecorator;

import model.MetroCard;
import model.Settings;


import java.lang.reflect.InvocationTargetException;


public class TicketPriceFactory {
    private static TicketPriceFactory instance;
    public static TicketPriceFactory getInstance() {
        if (instance == null) {
            instance = new TicketPriceFactory();
        }
        return instance;
    }

    public TicketPrice createTicketPrice(boolean is24Min, boolean is64Plus, boolean isStudent, MetroCard metroCard) {
        String[] allowedDiscounts = Settings.getProperty("PRICEDISCOUNTS").split(",");

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
