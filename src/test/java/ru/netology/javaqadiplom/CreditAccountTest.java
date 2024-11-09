package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }
    @Test
    public void shouldAddToPositiveBalance1() { // баланс должен обновиться с учетом изначального баланса
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }
    @Test
    public void shouldAddNegative() { // баланс не должен пополниться
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldAddMoreCreditLimit() { // баланс не должен пополниться, так как заходит за кредитный лимит
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(6_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNullAccountRate() {
        // проверка исключений по ставке, ставка не может быть отрицательной,
        // но может равняться 0
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    5_000,
                    0
            );
        });
    }
    @Test
    public void shouldNegativeAccountRate() { // проверка исключений по ставке
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    5_000,
                    -2
            );
        });
    }
    @Test
    public void shouldNegativeInitialBalance() {
        // проверка исключений по начальному счету, он не может быть отрицательным
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -200,
                    5_000,
                    15
            );
        });
    }

    @Test
    public void shouldNegativeCreditLimit() {
        // проверка исключений по начальному счету, он не может быть отрицательным
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    2_000,
                    -200,
                    15
            );
        });
    }

    @Test
    public void shouldPay() { //пополнение, баланс принимает значение баланс минус сумма покупки.
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(250);

        Assertions.assertEquals(750, account.getBalance());
    }

    @Test
    public void shouldSetAmountWithPercent() { //balance / 100 * rate
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(1200); //1000 - 1200 = -200/100 * 15 = -30

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    public void shouldSetNegativeAmountWithPercent() { //balance / 100 * rate
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(-1200);

        Assertions.assertEquals(1000, account.yearChange());
    }
    @Test
    public void shouldSetNegativeAmountWithPercent() { //balance / 100 * rate
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(200);

        Assertions.assertEquals(800, account.yearChange());
    }








}
