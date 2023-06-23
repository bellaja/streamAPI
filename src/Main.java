import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> ageStream1 = persons.stream();
        long count = ageStream1.filter(person -> person.getAge() > 18)
                .count();
        System.out.println(count);


        List<String> personStream = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(personStream);

        List<Person> personStream1 = persons.stream()
                .filter(person -> person.getEducation()==Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (Sex.MAN== person.getSex() && person.getAge() <= 65)
                        || (Sex.WOMAN == person.getSex() && person.getAge() <= 60))
                .sorted(comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(personStream1);


    }
}