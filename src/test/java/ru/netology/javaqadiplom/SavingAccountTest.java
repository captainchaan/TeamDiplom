package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    // Адд тесты
    @Test // Пополнение баланса
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test // Пополнение баланса превышающего ограничения по Максимальному Балансу
    public void shouldOverMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                4_000,
                5
        );

        account.add(3_000);

        int expected = 2_000;
        int actual = account.getBalance();


        Assertions.assertEquals(expected, actual);
    }

    // Тесты на ставку
    @Test // Ставка равна 0
    public void shouldNullRate() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                4_000,
                0
        );

        int expected = 0;
        int actual = account.rate;

        Assertions.assertEquals(expected, actual);
    }
    @Test // Ставка равна 0
    public void shouldPositiveRate() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                4_000,
                5
        );

        int expected = 5;
        int actual = account.rate;

        Assertions.assertEquals(expected, actual);
    }

    @Test // Ставка принимает отрицательное значение
    public void shouldNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    4_000,
                    -2
            );

        });
    }

    // Тесты баланса
    @Test // Баланс не может быть отрицательным
    public void shouldNegativeBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -2_000,
                    1_000,
                    4_000,
                    5
            );

        });
    }

    @Test // Баланс не может быть отрицательным
    public void shouldNegativeMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    -4_000,
                    5
            );

        });
    }

    @Test
    public void shouldNegativeBalanceAndMiBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -1_000,
                    -1_000,
                    4_000,
                    5
            );

        });
    }

    @Test // Баланс не может быть ниже Минимального значения
    public void shouldBalanceOverMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    3_000,
                    4_000,
                    5
            );

        });

    }

    @Test // Баланс не может быть выше Максимального значения
    public void shouldBalanceOverMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    6_000,
                    3_000,
                    4_000,
                    5
            );

        });

    }

    // Тесты на покупку
    @Test // Покупка в пределах ограничений
    public void shouldCorrectPay() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        int expected = 1_500;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Покупка не может иметь отрицательное значение
    public void shouldNegativePay() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(-500);

        int expected = 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Покупка сверх Баланса, не может приводить к отрицательному значению баланса
    public void shouldOverBalancePay() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        int expected = 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Покупка не может приводить баланс меньше Минимального лимита
    public void shouldOverMinBalancePay() {
        SavingAccount account = new SavingAccount(
                8_000,
                5_000,
                10_000,
                5
        );

        account.pay(4_000);

        int expected = 8_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Изменения баланса по процентной годовой ставке
    public void shouldYearChange() {
        SavingAccount account = new SavingAccount(
                200,
                0,
                1_000,
                15
        );


        Assertions.assertEquals(30, account.yearChange());
    }

    @Test // Изменения баланса по процентной годовой ставке
    public void shouldYearChangeNull() {
        SavingAccount account = new SavingAccount(
                200,
                0,
                1_000,
                0
        );


        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldGetMinBalance() {
        SavingAccount account = new SavingAccount(
                8_000,
                5_000,
                10_000,
                5
        );
        int expectedMinBalance = 5_000;
        Assertions.assertEquals(expectedMinBalance, account.getMinBalance());

    }



    @Test
    public void shouldGetMaxBalance() {
        SavingAccount account = new SavingAccount(
                8_000,
                5_000,
                10_000,
                5
        );
        int expectedMaxBalance = 10_000;
        Assertions.assertEquals(expectedMaxBalance, account.getMaxBalance());

    }
}

