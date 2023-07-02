package pl.jsystems.advancedjava.answers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class StreamsToMap3rdParameter
{
    public static void main(String[] args)
    {
        Payslip payslip1 = new Payslip(1, BigDecimal.valueOf(5000), LocalDate.now());
        Payslip payslip2 = new Payslip(2, BigDecimal.valueOf(6000), LocalDate.now());
        Payslip payslip3 = new Payslip(1, BigDecimal.valueOf(7000), LocalDate.now().plus(1, ChronoUnit.MONTHS));
        Payslip payslip4 = new Payslip(2, BigDecimal.valueOf(8000), LocalDate.now().plus(1, ChronoUnit.MONTHS));
        List<Payslip> payslips = new ArrayList<>(List.of(payslip1, payslip2, payslip3, payslip4));

        // PYTANIE
        // czy w trzecim parametrze, gdzie porównujemy wartości, by wybrać którą wpisać do mapy w przypadku duplikatów klucza,
        // czy tam możemy korzystać z obiektu zamiast samych wartości - np. żeby wybrać wypłatę dla najpóźniejszego miesiąca.
        Map<Integer, BigDecimal> example = payslips.stream()
                .collect(Collectors.toMap(payslip -> payslip.personId, payslip -> payslip.value, (value1, value2) -> value1.compareTo(value2) >= 0 ? value1 : value2));
        System.out.println(example);

        // Odpowiedź - nie wprost, musimy to zrobić w dwóch krokach :)

        // Przykład - po zgrupowaniu do Map<Integer, Payslip> wg określonych kryteriów,
        // przetwarzamy strumień entrySet (wpisów do mapy) i wyciągamy z nich to czego potrzebujemy -
        // tutaj 'value' czyli wartość wypłaty.
        Map<Integer, BigDecimal> solution1 = payslips.stream()
                .collect(Collectors.toMap(payslip -> payslip.personId, payslip -> payslip, (p1, p2) -> p1.date.compareTo(p2.date) > 0 ? p1 : p2))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().value()));
        System.out.println("Solution1:");
        System.out.println(solution1);

        // Inna opcja to posortowanie wczesniej po danym polu i potem zgrupowanie jak w podstawowym ćwiczeniu
        Map<Integer, BigDecimal> solution2 = payslips.stream()
                .sorted((p1, p2) -> p2.date.compareTo(p1.date))
                .collect(Collectors.toMap(payslip -> payslip.personId, payslip -> payslip.value, (value1, value2) -> value1));
        System.out.println("Solution2:");
        System.out.println(solution2);

    }


    private record Payslip(int personId, BigDecimal value, LocalDate date)
    {
    }

    ;
}
