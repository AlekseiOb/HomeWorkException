import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PersonalDataApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
            String input = scanner.nextLine();

            String[] tokens = input.split(" ");
            if (tokens.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите 6 значений.");
            }

            String lastName = tokens[0];
            String firstName = tokens[1];
            String middleName = tokens[2];
            String birthDate = tokens[3];
            long phoneNumber = Long.parseLong(tokens[4]);
            char gender = tokens[5].charAt(0);

            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Неверный формат даты рождения. Используйте dd.mm.yyyy.");
            }

            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Неверное значение пола. Используйте 'f' или 'm'.");
            }

            String dataLine = lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
                writer.write(dataLine);
                writer.newLine();
                System.out.println("Данные успешно записаны в файл.");
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл:");
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат номера телефона. Введите целое число без форматирования.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
