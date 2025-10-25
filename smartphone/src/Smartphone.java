public class Smartphone implements GPS, Cellular {
    private double latitude;
    private double longitude;
    private String ownerName;
    private String dialNumber;
    private String incomingNumber;
    private boolean inCall;

    public Smartphone() {
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.ownerName = "Unknown";
        this.dialNumber = null;
        this.incomingNumber = null;
        this.inCall = false;
    }

    public Smartphone(String ownerName, double latitude, double longitude) {
        this.ownerName = ownerName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dialNumber = null;
        this.incomingNumber = null;
        this.inCall = false;
    }

    @Override
    public double[] getCoordinates() {
        return new double[] { latitude, longitude };
    }

    @Override
    public void makeCall() {
        if (dialNumber == null || dialNumber.trim().isEmpty()) {
            System.out.println("No number set to dial. Use setDialNumber(...) first.");
            return;
        }
        if (inCall) {
            System.out.println("Already in a call.");
            return;
        }
        System.out.printf("%s: Calling %s...\n", ownerName, dialNumber);
        inCall = true;
    }

    @Override
    public void receiveCall() {
        if (inCall) {
            System.out.println("Already in a call; cannot receive another call.");
            return;
        }
        String caller = (incomingNumber == null || incomingNumber.isEmpty()) ? "Unknown" : incomingNumber;
        System.out.printf("%s: Incoming call from %s. Answering...\n", ownerName, caller);
        inCall = true;
    }

    public void endCall() {
        if (!inCall) {
            System.out.println("No active call to end.");
            return;
        }
        System.out.printf("%s: Call ended.\n", ownerName);
        inCall = false;
    }

    public void setCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setDialNumber(String dialNumber) {
        this.dialNumber = dialNumber;
    }

    public void setIncomingNumber(String incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    public boolean isInCall() {
        return inCall;
    }

    @Override
    public String toString() {
        return String.format("Smartphone(owner=%s, lat=%.6f, lon=%.6f)", ownerName, latitude, longitude);
    }

    public static void main(String[] args) {
        Smartphone s = new Smartphone("Ivan", 50.4501, 30.5234); // Київ
        System.out.println(s);

        double[] coords = s.getCoordinates();
        System.out.printf("Coordinates: lat=%.6f, lon=%.6f\n", coords[0], coords[1]);

        s.makeCall();

        s.setDialNumber("+380501234567");
        s.makeCall();

        s.endCall();

        s.setIncomingNumber("+380671112233");
        s.receiveCall();

        s.endCall();
    }
}
