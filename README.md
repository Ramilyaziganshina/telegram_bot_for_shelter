# Telegram-bot for shelter 
## О проекте
<b>Telegram-bot for shelter</b> - это бот для телеграмм, который позволяет сэкономить время общения с клиентами, желающими взять животное из приюта, путем стандартных ответов на часто задаваемые вопросы. 
<br>
## В проекте использовались
1. <b>Spring Boot (Web)</b>
2. <b>Spring-test</b>
3. <b>Hibernate</b>
4. <b>Liquibase</b>
<br>
## Структура проекта
<br>  - Папка config хранит в себе токен и имя бота для того, чтобы их можно было запрашивать из методов с помощью геттеров. А так же класс, который служит для инициализации бота, без него каждую команду придется оборачивать в try\catch для вылавливания TelegramApiException
<br>  - В папке model находятся все сущности, которые пригодились в этом проекте. В частности это животное, которое можно забрать домой, их фото, отчеты, которые клиент присылает ежедневно в течение месяца после усыновления, а так же сам клиент, который может быть опекуном или нет (только интересующимся).
<br>  - В папке repository находятся интерфейсы, которые связаны с базой данных, сущности из папки model хранятся в этой базе.
<br>  - Папка service разделена на 2 части, function содержит в себе сервисы для функционирования кнопок в боте, а service_database - сервисы для работы с базой данных.
<br>
<br> Так же в проекте реализовано unit тестирования с использованием maven-зависимости j-unit.
<br>
## Зависимости maven, использованные в проекте
<br>
<br>- spring-boot-starter
<br>- liquibase-core
<br>- openjson
<br>- spring-boot-starter-web
<br>- spring-boot-starter-test
<br>- telegrambots
<br>- postgresql
<br>- spring-boot-starter-data-jpa
<br>
