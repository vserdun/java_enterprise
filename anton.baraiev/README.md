# java_enterprise

##task 1

    Дан класс BankAccount
    Нужно написать метод
    public static List<BankAccount> findDuplicates(Collection<BankAccount > collA, Collection<BankAccount > collB);
    который возвращает дубликаты банковских аккаунтов, которые есть в обеих коллекциях.

    BankAccount содержит поля acc_id, acc_login_name, acc_password_hash
    Одинаковыми считаем аккаунты, у которых совпадают все 3 поля: acc_id, acc_login_name, acc_password_hash
    По производительности: метод findDuplicates должен работать не больше нескольких секунд, если на вход получает
    2 коллекции по 100 тысяч элементов в каждой

##task 2

    Создать простую реализацию HashMap. Поддерживаются методы get() и put()
    ﻿
    Можно использовать фиксированный размер HashMap, например 16
    Можно не дженерализировать, а привязать реализацию к конкретному типу, например String