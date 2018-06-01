package serializationexercise;

import java.io.Serializable;

public class SerializationSampleObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String stringValue;
    private transient String transientStringValue;
    private int intValue;
    private transient int transientIntValue;

    public SerializationSampleObject() {
        System.out.println("Object created!");
    }

    {
        System.out.println("Object initialized!");
    }

    public SerializationSampleObject(String stringValue, String transientStringValue, int intValue, int transientIntValue) {
        this.stringValue = stringValue;
        this.transientStringValue = transientStringValue;
        this.intValue = intValue;
        this.transientIntValue = transientIntValue;
    }

    @Override
    public String toString() {
        return "stringValue: " + stringValue + ", transientStringValue: " + transientStringValue + ", intValue: "
                + intValue + ", transientIntValue: " + transientIntValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getTransientStringValue() {
        return transientStringValue;
    }

    public void setTransientStringValue(String transientStringValue) {
        this.transientStringValue = transientStringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getTransientIntValue() {
        return transientIntValue;
    }

    public void setTransientIntValue(int transientIntValue) {
        this.transientIntValue = transientIntValue;
    }
}
