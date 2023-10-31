# QA-java-diplom-1
В проекте тестируется программа, которая помогает заказать бургер в Stellar Burgers. 
В данной части произведено покртытие ее юнит-тестами.

## Использованные технологии и зависимости
Проект выполнен с использованием Java 11, Junit 4.13.2, mockito-core 5.5.0

## Список тестов
* BunTest 
* BurgerParametrizeTest 
* BurgerTest
* IngredientParametrizedTest
* IngredientTest

## Запуск тестов
Для запуска тестов необходимо запустить команду
```shell
mvn clean test
```

## Генерация отчета о тестировании
Для генерации отчета необходимо запустить команду
```shell
mvn clean verify
```
Отчет можно посмотреть в папке target\site\jacoco\index.html
