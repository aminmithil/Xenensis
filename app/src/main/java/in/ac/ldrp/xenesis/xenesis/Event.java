package in.ac.ldrp.xenesis.xenesis;

public class Event {
    private String eventId, eventCode, departmentId;
    public String eventName, eventDescription, eventPrice, eventDate, eventTime, eventWinPrice, coordinateName, coordinateMobile;
    public int imageName;

    public Event (String eventId, String eventCode, String departmentId, String eventName, String eventDescription, String eventPrice, String eventDate, String eventTime, String coordinateName, String coordinateMobile, int imageName){
        this.eventId= eventId;
        this.eventCode = eventCode;
        this.departmentId = departmentId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventPrice = eventPrice;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventWinPrice = eventWinPrice;
        this. coordinateName = coordinateName;
        this. coordinateMobile = coordinateMobile;
        this.imageName = imageName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getEventId() {
        return eventId;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
