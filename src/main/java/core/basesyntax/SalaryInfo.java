package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate to = LocalDate.parse(dateTo.trim(), formatter);

        String[] resultList = new String[names.length];

        for (int n = 0; n < names.length; n++) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] parts = data[i].split(" ");
                LocalDate date = LocalDate.parse(parts[0], formatter);
                if (date.isBefore(from) || date.isAfter(to)) {
                    continue;
                }
                if (parts[1].equals(names[n])) {
                    salary += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                }

            }
            resultList[n] = names[n] + " - " + salary;
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom.trim() + " - " + dateTo.trim() + "\n");
        for (int i = 0; i < names.length; i++) {
            if (i > 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(resultList[i]);
        }
        return stringBuilder.toString();
    }
}
