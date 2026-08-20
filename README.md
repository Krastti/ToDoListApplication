# Task Tracker (ToDoList)

Веб-приложение для управления проектами и задачами. Реализовано в рамках учебного проекта.

## Стек технологий

- **Java** + **Spring Boot**
- **Hibernate** / **Spring Data JPA**
- **PostgreSQL**
- **Thymeleaf** (серверный рендеринг шаблонов)
- **Maven**
- **Bean Validation**

## Функциональность

- Создание, редактирование и удаление проектов
- Создание, редактирование и удаление задач внутри проекта
- Статусы задач
- Дедлайны задач
- Серверная валидация форм с выводом ошибок прямо в шаблоне

## Структура проекта

```
src/main/java/ru/krastti/
├── entity/            # JPA-сущности: User, Project, Task
├── enums/              # Enum'ы (статус, приоритет задачи)
├── repository/         # Spring Data JPA репозитории
├── service/            # Бизнес-логика, транзакции
├── controller/          # Контроллеры (MVC)
└── exception/          # Глобальная обработка исключений

src/main/resources/
└── templates/           # Thymeleaf-шаблоны
```

## Модель данных

**User** — id, username, email, password
**Project** — id, name, description, user, createdAt, tasks
**Task** — id, title, description, status, priority, deadline, project

Связи: `User` 1—N `Project`, `Project` 1—N `Task`

## Валидация

Используется `@Valid` + `BindingResult` на уровне контроллеров.

Ключевые ограничения:
- `User.username` — обязательно, 3–30 символов
- `User.email` — обязательно, формат email
- `User.password` — обязательно
- `Project.name` — обязательно, до 30 символов
- `Task.title` — обязательно, до 30 символов
- `Task.status` — обязательны (enum)

## Запуск

1. Поднять PostgreSQL и создать базу данных
2. Указать параметры подключения в `application.properties`
3. Собрать и запустить:
   ```
   mvn spring-boot:run
   ```
4. Приложение будет доступно на `http://localhost:8080`

## Требования

- JDK 17+
- Maven 3.8+
- PostgreSQL 14+