package pl.jsystems.advancedjava.streams.exercises.e8concatandreduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.CargoLoadingMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.repositories.GPSTrackingMessageRepository;

import java.util.List;
import java.util.stream.Stream;

class StreamsExercise8ConcatAndReduce
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise8ConcatAndReduce.class);

    public static void main(String[] args)
    {
        new StreamsExercise8ConcatAndReduce().run();
    }

    private static final GPSTrackingMessageRepository GPS_TRACKING_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();
    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();
    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();

    private void run()
    {
        LOGGER.info("Time to consume some messages!");
        MessageLogger messageLogger = new MessageLogger();
        receiveAndStore(messageLogger);

        List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();
        List<Message<CargoUnloadedMessageContent>> cargoUnloadedMessages = CARGO_UNLOADED_MESSAGE_REPOSITORY.findAll();
        LOGGER.info("All cargo loaded messages:");

        // todo stuff
    }


    private static void receiveAndStore(MessageLogger messageLogger)
    {
        new GPSTrackingMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            GPS_TRACKING_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved GPS message: {}", message);
        });

        new CargoLoadedMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved GPS message: {}", message);
        });

        new CargoUnloadedMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            CARGO_UNLOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved GPS message: {}", message);
        });
    }
}
