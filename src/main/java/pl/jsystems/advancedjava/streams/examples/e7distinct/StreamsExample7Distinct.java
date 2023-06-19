package pl.jsystems.advancedjava.streams.examples.e7distinct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Predicate;

class StreamsExample7Distinct
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample7Distinct.class);

    public static void main(String[] args)
    {
        new StreamsExample7Distinct().run();
    }

    private void run()
    {
        List<Integer> integers = List.of(1, 2, 4, 42, 4, 2, 1);
        LOGGER.info("Original list: {}", integers);

        List<Integer> distinctIntegers = integers.stream().distinct().toList();
        LOGGER.info("Distinct list: {}", distinctIntegers);

        List<Object> objects  = List.of(new Object(), new Object());
        LOGGER.info("Original object list: {}", objects);

        List<Object> distinctObjects = objects.stream().distinct().toList();
        LOGGER.info("Distinct list: {}", distinctObjects);

        Person p1 = new Person("Adam", "Win", "123456");
        Person p2 = new Person("Wojtek", "Eon", "223456");
        Person p3 = new Person("Wojtek", "Iat", "323456");
        Person p4 = new Person("Wojtek", "Trela", "123456");
        Person p5 = new Person("Jacek", "Win", "523456");

        List<Person> people = List.of(p1, p2, p3, p4, p5);

        List<Person> distinctByFirstName = people.stream()
                .map(PersonWrapperWithFirstNameAsDistinctKey::new)
                .distinct()
                .map(PersonWrapperWithFirstNameAsDistinctKey::person)
                .toList();
        LOGGER.info("Distinct by first name list: {}", distinctByFirstName);

        List<Person> distinctByLastName = people.stream().filter(distinctByLastNamePredicate()).toList();
        LOGGER.info("Distinct by last name list: {}", distinctByLastName);

        // ConcurrentHashMap for thread safety.
        Set<String> seen = ConcurrentHashMap.newKeySet();
        List<Person> distinctBySsn = people.stream().filter(input -> seen.add(input.ssn)).toList();
        LOGGER.info("Distinct by ssn list: {}", distinctBySsn);
    }

    record Person(String firstName, String lastName, String ssn) {

    }

    record PersonWrapperWithFirstNameAsDistinctKey(Person person) {
        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonWrapperWithFirstNameAsDistinctKey that = (PersonWrapperWithFirstNameAsDistinctKey) o;
            return person.firstName.equals(that.person.firstName);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(person.firstName);
        }
    }

    private Predicate<? super Person> distinctByLastNamePredicate()
    {
        // ConcurrentHashMap for thread safety.
        Set<String> seen = ConcurrentHashMap.newKeySet();
        return (person) -> seen.add(person.lastName);
    }
}
