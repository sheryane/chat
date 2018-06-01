package serializationexercise;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SerializationSampleObject example = new SerializationSampleObject();

        example.setStringValue("taki tam przyklad");
        example.setTransientStringValue("to się zmieni po serializacji na null");
        example.setIntValue(880488);
        example.setTransientIntValue(39829357);

        System.out.println(example.toString());

        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("example.ser"))) {
            output.writeObject(example);
        }

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("example.ser"))) {
            SerializationSampleObject serializedExample = (SerializationSampleObject) input.readObject();
            System.out.println(serializedExample.toString());
        }

        System.out.println("\n***************\n");

        SerializationSampleObject serializationSampleObject = new SerializationSampleObject(
                "loremipsum",
                "loreimpsumdolorsitamet",
                12345,
                123456789);

        System.out.println(serializationSampleObject.toString());

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("SerializedSampleObject.ser"))) {
            output.writeObject(serializationSampleObject);
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("SerializedSampleObject.ser"))) {
            SerializationSampleObject readSerialization = (SerializationSampleObject) input.readObject();
            System.out.println(readSerialization.toString());
        }
    }
}