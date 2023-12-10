import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Patient> patients = Dump.getDump();
        //Tasks.getDictPatientBirthday(patients); // Получить словарь, сопоставляющий дату рождения с пациентом
        //Tasks.getCommonExp(patients); // список расходов кжд пациента
        //Tasks.getMinMax(patients); //получить мин и макс пациента (сумма всех расходов)
        //Tasks.findFirstInOct1999(patients); //получить первого пациента, родившегося в ОКТЯБРЕ (в декабре нет никого) 1999, метод findFirst
        //Tasks.ifSuperman(patients); //проверить, есть ли хоть один Абсолютно Здоровый чел. (нулевая длина списка расходов на здоровье)
        //Tasks.ifS100man(patients);   // метод nonMatch, проверить есть ли хоть 1 чел старше 100 лет
        Tasks.ifS100manAnother(patients);  //метод anyMatch, проверить есть ли хоть 1 чел старше 100 лет


    }

}

