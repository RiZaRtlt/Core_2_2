import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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

        long minor = persons.stream()
                .filter(per -> per.getAge() < 18)
                .count();

        System.out.println("1. Несовершеннолетних " + minor);

        long conscript = persons.stream()
                .filter(per -> per.getAge() >= 18 && per.getAge() <= 27)
                .count();

        System.out.println("2. Призывников " + conscript);

        long workableMan = persons.stream()
                .filter(per -> per.getEducation() == Education.HIGHER)
                .filter(per -> per.getSex() == Sex.MAN)
                .filter(per -> per.getAge() >= 18 && per.getAge() < 65)
                .count();

        long workableWoman = persons.stream()
                .filter(per -> per.getEducation() == Education.HIGHER)
                .filter(per -> per.getSex() == Sex.WOMAN)
                .filter(per -> per.getAge() >= 18 && per.getAge() < 60)
                .count();

        System.out.println("3. Работоспособное население " + (workableMan + workableWoman));
    }
}
