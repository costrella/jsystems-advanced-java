package pl.jsystems.advancedjava.answers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class StreamsPartitionBySthElseThanBoolean
{
    public static void main(String[] args)
    {
        Payslip payslip1 = new Payslip(1, BigDecimal.valueOf(5000), LocalDate.now());
        Payslip payslip2 = new Payslip(2, BigDecimal.valueOf(6000), LocalDate.now());
        Payslip payslip3 = new Payslip(1, BigDecimal.valueOf(7000), LocalDate.now().plus(1, ChronoUnit.MONTHS));
        Payslip payslip4 = new Payslip(2, BigDecimal.valueOf(8000), LocalDate.now().plus(1, ChronoUnit.MONTHS));
        List<Payslip> payslips = new ArrayList<>(List.of(payslip1, payslip2, payslip3, payslip4));

        // PYTANIE
        // czy jest inna opcja partycjonowania niż partitionBy, która zwraca Map<Boolean, ...>?
        Map<Boolean, List<Payslip>> example = payslips.stream()
                .collect(Collectors.partitioningBy(payslip -> payslip.personId <=1));
        System.out.println(example);

        // Odpowiedź - nie ma wbudowanych rozwiązań, możemy korzystać z bibliotek Guava lub Apache Commons 4,
        // które udostępniają takie funkcje. My możemy zrobić coś na wzór hashmapy.
        // Liczba 8 w poniższym przykładzie to maksymalna liczba wpisów do mapy (partycji), które chcemy mieć.
        Map<Integer, List<Payslip>> solution1 = payslips.stream()
                .collect(Collectors.groupingBy(payslip -> payslip.hashCode() % 8, Collectors.toList()));
        System.out.println("Solution1:");
        System.out.println(solution1);
    }


    private record Payslip(int personId, BigDecimal value, LocalDate date)
    {
    }
}
