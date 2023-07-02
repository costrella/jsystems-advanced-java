package pl.jsystems.advancedjava.answers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class StreamsMinByOptional
{
    public static void main(String[] args)
    {
        Payslip payslip1 = new Payslip(1, BigDecimal.valueOf(5000), LocalDate.now());
        Payslip payslip2 = new Payslip(2, BigDecimal.valueOf(6000), LocalDate.now().plus(1, ChronoUnit.MONTHS));
        Payslip payslip3 = new Payslip(1, BigDecimal.valueOf(7000), LocalDate.now());
        Payslip payslip4 = new Payslip(2, BigDecimal.valueOf(8000), LocalDate.now().plus(1, ChronoUnit.MONTHS));
        List<Payslip> payslips = new ArrayList<>(List.of(payslip1, payslip2, payslip3, payslip4));

        // PYTANIE
        // czy ta mapa może nie mieć optionala? chcielibyśmy format Map<Integer, Payslip>...
        Map<Integer, Optional<Payslip>> example = payslips.stream()
                .collect(Collectors.groupingBy(payslip -> payslip.personId, Collectors.minBy(Comparator.comparing(p -> p.value))));
        System.out.println(example);

        // Odpowiedź - nie :) kompilator nie moze zapewnić, że zawsze gdy ustawiony jest personId, ustawiony będzie również value

        // Inne rozwiązania, które realizują powyższe wymagania (bez Optional)
        // 1. toMap tworzy mapę, w 3cim argumencie podajemy funkcję definiującą co się ma stać jeżeli klucze się powtarzają.
        // możemy zaimplementować własne minBy. Mozemy się też zabezpiecznyć przed nullami.
        Map<Integer, Payslip> solution1 = payslips.stream()
                .collect(Collectors.toMap(payslip -> payslip.personId, payslip -> payslip, (p1, p2) -> p1.value.compareTo(p2.value) < 0 ? p1 : p2));
        System.out.println(solution1);

        // Wynik z przykładu z pytaniem możemy dodatkowo przetworzyć. Przetwarzamy entrySet (czyli mapę, wpis po wpisie)
        // odfiltrowujemy te gdzie optional jest pusty, i tworzymy ową mapę.
        Map<Integer, Payslip> solution2 = payslips.stream()
                .collect(Collectors.groupingBy(payslip -> payslip.personId, Collectors.minBy(Comparator.comparing(p -> p.value))))
                .entrySet().stream()
                .filter(entry -> entry.getValue().isPresent())
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));
        System.out.println(solution2);
    }


    private record Payslip(int personId, BigDecimal value, LocalDate date)
    {
    }
}
