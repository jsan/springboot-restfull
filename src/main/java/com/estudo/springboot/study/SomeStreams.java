package com.estudo.springboot.study;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SomeStreams
{
    public static void main(String[] args)
    {
        List<Person> people = getPeople();

/*
    // Imperative approach ❌
        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }
        females.forEach(System.out::println);
*/

        // Declarative approach ✅
        // No filter
        List<Person> allPeople = people.stream()
                .collect(Collectors.toList());
        // allPeople.forEach(System.out::println);

        // Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        // females.forEach(System.out::println);

        // Sort Comparator
        List<Person> sorted = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        // sorted.forEach(System.out::println);

        // allMatch
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 6);
        // System.out.println(allMatch);

        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() == 1);
        // System.out.println(anyMatch);

        // None match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equalsIgnoreCase("antonio"));
        // System.out.println(noneMatch);

        // Max
        people.stream()
                .max(Comparator.comparing(Person::getAge));
        // .ifPresent(System.out::println);

        // Min
        people.stream()
                .min(Comparator.comparing(Person::getAge));
        // .ifPresent(System.out::println);

        // Group
        Map<Gender, List<Person>> collected = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        // collected.forEach((gender, people1) -> {
        //             System.out.println(gender);
        //             people1.forEach(System.out::println);
        //             System.out.println();
        //         });
        //
        people.stream()
                .filter(person -> person.gender.equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::toStringCustom)
                .ifPresent(System.out::println);

        people.stream()
                .filter(person -> person.gender.equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(person -> System.out.println(person.name));
    }

    private static List<Person> getPeople()
    {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }

    enum Gender
    {
        MALE, FEMALE
    }

    static class Person
    {

        private final String name;
        private final int age;
        private final Gender gender;

        public Person(String name, int age, Gender gender)
        {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName()
        {
            return name;
        }

        public int getAge()
        {
            return age;
        }

        public Gender getGender()
        {
            return gender;
        }

        @Override
        public String toString()
        {
            return "Person " +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender
                    ;
        }

        public String toStringCustom()
        {
            return "Person: " +
                    name +
                    ", " + age +
                    ", " + gender
                    ;
        }
    }
}
