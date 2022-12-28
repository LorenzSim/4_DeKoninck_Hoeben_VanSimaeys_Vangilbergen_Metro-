package model.TicketPriceDecorator;

public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT("model.TicketPriceDecorator.Age64PlusDiscount"),
    CHRISTMASLEAVEDISCOUNT("model.TicketPriceDecorator.ChristmasLeaveDiscount"),
    STUDENTDISCOUNT("model.TicketPriceDecorator.StudentDiscount"),
    FREQUENTTRAVELLERDISCOUNT("model.TicketPriceDecorator.FrequentTravellerDiscount");

    private String classFileName;
    TicketPriceDiscountEnum(String getClassFileName) {
        this.classFileName = getClassFileName;
    }

    public String getGetClassFileName() {
        return classFileName;
    }
}
