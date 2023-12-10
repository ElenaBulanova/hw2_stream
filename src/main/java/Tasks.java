import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Tasks {
    public static void getDictPatientBirthday(List<Patient> patients) {
        Map<LocalDate, List<String>> curMap = patients
                .stream()
                .collect(
                        Collectors.groupingBy(p -> p.getBirthDate(),  //Patient::getBirthDate
                                Collectors.mapping(p -> p.getFio(), Collectors.toList())));
//        Map<LocalDate, List<String>> sortedMap = new TreeMap<>(curMap);
//        sortedMap.entrySet().forEach(System.out::println);
        TreeMap<LocalDate, List<String>> treeMap = new TreeMap<LocalDate, List<String>>();
        treeMap.putAll(curMap);
        treeMap.entrySet().forEach(System.out::println);
    }

    //foreach - список расходов кжд пациента
    public static void getCommonExp(List<Patient> patients) {
        patients
                .stream()
                .map(p -> p.getFio() + ": " + p.getExpenses())
                .forEach(System.out::println);
    }

    //получить мин и макс пациента (сумма всех расходов)
    public static void getMinMax(List<Patient> patients) {
        Patient minPat = patients
                .stream()
                .min(Comparator.comparingInt(Tasks::getPersonalExp)).get(); // исправлено Идеей. Было руками написано как в след стр
        Patient maxPat = patients
                .stream()
                .max((p1, p2) -> Integer.compare(getPersonalExp(p1), getPersonalExp(p2))).get();
        System.out.println(minPat.getFio() + "  " + getPersonalExp(minPat) + " (расшифровка: " + minPat.getExpenses() + " )");
        System.out.println(maxPat.getFio() + "  " + getPersonalExp(maxPat) + " (расшифровка: " + maxPat.getExpenses() + " )");
    }

    private static int getPersonalExp(Patient p) {
        return p
                .getExpenses()
                .stream()
                .reduce((acc, cur) -> acc + cur)
                .get();
    }

    //получить первого пациента, родившегося в декабре 1999, метод findFirst
    public static void findFirstInOct1999(List<Patient> patients) {
        LocalDate start = LocalDate.of(1999, 10, 01);
        LocalDate end = LocalDate.of(1999, 10, 31);
        Optional<Patient> first = patients
                .stream()
                .filter(patient -> (patient.getBirthDate().isBefore(end) && patient.getBirthDate().isAfter(start)))
                .findFirst();
        System.out.println(first.get());
    }

    //проверить, есть ли хоть один Абсолютно Здоровый чел. (нулевая длина списка расходов на здоровье)
    public static void ifSuperman(List<Patient> patients) {
        boolean ifExist = patients
                .stream()
                //.anyMatch(p -> getPersonalExp(p) == 0) //мне кажется, что так правильнее :)))
                .allMatch(p -> p.getExpenses().size() > 0) //это не работает: расходов 0, а список не пустой, просто с пустыми элементами
                ;
        if (!ifExist) System.out.println("Абсолютно здоровый человек есть!");
        else System.out.println("Абсолютно здоровых людей не бывает :(");
        ;
    }

    // метод nonMatch, проверить есть ли хоть 1 чел старше 100 лет
    public static void ifS100man(List<Patient> patients) {
        boolean ifExist = patients
                .stream()
                .noneMatch(p -> p.getBirthDate().isBefore(LocalDate.now().minusYears(100)));
        if (ifExist) System.out.println("нет таких");
        else System.out.println("есть долгожитель");
    }

    //метод anyMatch, проверить есть ли хоть 1 чел старше 100 лет
    public static void ifS100manAnother(List<Patient> patients) {
        boolean ifExist = patients
                .stream()
                .anyMatch(p -> p.getBirthDate().isBefore(LocalDate.now().minusYears(100)));
        if (!ifExist) System.out.println("нет таких");
        else System.out.println("есть долгожитель");
    }

}


