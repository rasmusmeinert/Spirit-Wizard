package GUI.Components;

public class UpdateMessage {
    private Object object;
    private Object message;

    public UpdateMessage(Object object, Object message) {
        this.object = object;
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public Object getMessage() {
        return message;
    }
}
