package model.TicketPriceDecorator;

public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT("Age64PlusDiscount"),
    CHRISTMASLEAVEDISCOUNT("ChristmasLeaveDiscount"),
    STUDENTDISCOUNT("StudentDiscount"),
    FREQUENTTRAVELLERDISCOUNT("FrequentTravellerDiscount");

    String stringValue;
    TicketPriceDiscountEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
