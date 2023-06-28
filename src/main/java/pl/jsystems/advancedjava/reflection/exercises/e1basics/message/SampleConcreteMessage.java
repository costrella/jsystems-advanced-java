package pl.jsystems.advancedjava.reflection.exercises.e1basics.message;


import pl.jsystems.advancedjava.reflection.exercises.e1basics.contents.CargoLoadedMessageContent;

import java.time.Instant;
import java.util.UUID;

public class SampleConcreteMessage extends Message<CargoLoadedMessageContent>
{
    public SampleConcreteMessage(UUID id, CargoLoadedMessageContent content, Instant sentAt)
    {
        super(id, content, sentAt);
    }
}
