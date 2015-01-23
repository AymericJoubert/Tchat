package Client.Guis;

public interface Gui {
    // Close the frame
    public void close();
    // Display a popup message on the frame
    public void popupMessage(String message);
    // Stop waiting message
    public void stopWaiting(boolean resetContainer);

    public void setVisible(boolean visible);
}
