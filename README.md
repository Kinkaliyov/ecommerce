# 🛒 E-Commerce Backend (Final Project)

Финальный проект по курсу Java & Spring Boot. Полноценный Backend для интернет-магазина с авторизацией, корзиной, заказами и загрузкой изображений.

## 📌 Основная информация
- **Стек:** Java 21, Spring Boot 4.0.5, Maven, MySQL 8, Spring Security, JWT, Spring Data JPA, Swagger/OpenAPI, Lombok.
- **Сроки:** 20 дней (Дедлайн: ~30.11.2026).
- **Frontend:** Готовый шаблон (интеграция на финальном этапе).
- **База данных:** `ecommerce_db`
- **Порт приложения:** `1515`
- **Оригинальные критерии:** [Google Docs](https://docs.google.com/document/d/1GwbZC5KO1uxoVcZKFTnKULIs0QxXmuUJTssa_sZM114/edit?usp=sharing)

## 🚀 Как запустить локально
1. Создайте базу данных в MySQL:
   `CREATE SCHEMA ecommerce_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;`
2. Настройте `src/main/resources/application.properties` (url, username, password).
3. Запустите `EcommerceApplication.java`.
4. Swagger: `http://localhost:1515/swagger-ui.html` *(будет добавлено позже)*.

---

## 📋 Детальный чек-лист по критериям (Раздел 4-19)

### 🏗️ Этап 1: База данных и Entity (Раздел 4)
- [x] Создание Spring Boot проекта, подключение MySQL
- [x] Создание структуры пакетов (`entity`, `repository`, `service`, `service.impl`, `controller`, `dto`, `config`, `security`, `exception`, `filter`)
- [ ] **Entity User:** `id`, `username`, `name`, `surname`, `email`, `password`, `role` (USER, SELLER, ADMIN), `enabled`
- [ ] **Entity Category:** `id`, `name`
- [ ] **Entity Product:** `id`, `brand`, `model`, `description`, `price`, `amount`, `imageUrl`, `rating`, `category` (ManyToOne), `seller` (ManyToOne)
- [ ] **Entity Cart:** `id`, `user` (OneToOne)
- [ ] **Entity CartItem:** `id`, `cart` (ManyToOne), `product` (ManyToOne), `quantity`
- [ ] **Entity Order:** `id`, `user` (ManyToOne), `totalPrice`, `status` (PENDING, CONFIRMED, CANCELLED), `createdAt`, `name`, `surname`, `email`, `phone`, `country`, `city`, `address`, `zipCode`
- [ ] **Entity OrderItem:** `id`, `order` (ManyToOne), `product` (ManyToOne), `quantity`, `price`
- [ ] Проверить создание таблиц в MySQL

### 🔐 Этап 2: Безопасность и Auth API (Раздел 6)
- [ ] **Spring Security & JWT:** Настроить `SecurityConfig`, `JwtAuthFilter`, `JwtService`. Запретить доступ к API без токена (кроме публичных).
- [ ] **Валидация (Раздел 5):** Username/email не пустые, правильный формат email, пароль мин. длина, цена > 0, amount >= 0, quantity > 0.
- [ ] **Регистрация:** `POST /api/auth/register` (уникальный email/username, пароль зашифрован, **роль по умолчанию только USER**).
- [ ] **Логин:** `POST /api/auth/login` (выдача JWT).
- [ ] **Текущий юзер:** `GET /api/users/me`.
- [ ] **Выход:** `POST /api/auth/logout`.

### 📦 Этап 3: Product & Category API (Раздел 7, 8)
- [ ] **Category API:**
    - `GET /api/categories` (все)
    - `GET /api/categories/{id}`
    - `POST /api/categories` (только ADMIN)
    - `PUT /api/categories/{id}` (только ADMIN)
    - `DELETE /api/categories/{id}` (только ADMIN, **запрет удаления, если есть товары**)
- [ ] **Product CRUD:**
    - `GET /api/products` (все)
    - `GET /api/products/{id}`
    - `POST /api/products` (SELLER, ADMIN)
    - `PUT /api/products/{id}` (только владелец или ADMIN)
    - `DELETE /api/products/{id}` (только владелец или ADMIN)
    - `GET /api/products/my` (товары текущего продавца)

### 🔍 Этап 4: Поиск, Фильтрация, Сортировка, Пагинация (Раздел 9)
- [ ] **Фильтрация и поиск:** `GET /api/products/filter` (по названию, бренду, категории, minPrice, maxPrice через Specifications).
- [ ] **Сортировка цены (возрастание):** `GET /api/products/sort-by-low-to-high`
- [ ] **Сортировка цены (убывание):** `GET /api/products/sort-by-high-to-low`
- [ ] **Сортировка рейтинга (возрастание):** `GET /api/products/sort-by-low-to-high-rating`
- [ ] **Сортировка рейтинга (убывание):** `GET /api/products/sort-by-high-to-low-rating`
- [ ] **Pagination:** Параметры `page`, `size` (Pageable).

### 🖼️ Этап 5: File Upload API (Раздел 10)
- [ ] Создать `ProductFileService`.
- [ ] `POST /api/products/{id}/image` (загрузка, проверка формата JPG/JPEG/PNG, лимит размера, сохранение в локальную папку, запись пути в БД).
- [ ] `GET /api/products/{id}/image` (получение файла, доступно всем).
- [ ] `DELETE /api/products/{id}/image` (удаление, только владелец/ADMIN).
- [ ] Обработка ошибок, если картинки нет.

### 🛒 Этап 6: Cart API (Раздел 11)
- [ ] `GET /api/cart` (получить корзину юзера).
- [ ] `POST /api/cart/items` (добавить; если есть — увеличить).
- [ ] `PUT /api/cart/items/{id}` (изменить количество).
- [ ] `DELETE /api/cart/items/{id}` (удалить товар).
- [ ] `DELETE /api/cart` (очистить корзину).
- [ ] Проверка остатков (`amount`) на складе. Автоматический расчет суммы.

### 📦 Этап 7: Order API (Раздел 12)
- [ ] `POST /api/orders` (оформление заказа через `@Transactional`: проверка склада, расчет суммы, создание Order/OrderItem, списание amount, очистка корзины).
- [ ] `GET /api/orders/my` (история заказов юзера).
- [ ] `GET /api/orders/{id}` (заказ по ID).
- [ ] `GET /api/orders` (все заказы, только ADMIN).
- [ ] `PUT /api/orders/{id}/status` (изменить статус, только ADMIN).

### 🛡️ Этап 8: Exception Handling & Swagger (Раздел 13, 16)
- [ ] **GlobalExceptionHandler:** 400, 401, 403, 404, 409 (товар не найден, категория не найдена, заказ не найден, файл не найден, неверный формат файла, превышен размер, недостаточно товаров, нет доступа, email/username занят).
- [ ] **Swagger:** Подключить, настроить JWT-авторизацию в OpenAPI.
- [ ] **Тестирование:** Прогнать все эндпоинты через Postman/Swagger (Регистрация, Логин, Товары, Картинки, Корзина, Заказы, Ошибки).

### 🌐 Этап 9: Frontend и Защита (Раздел 15)
- [ ] **Подключение Frontend:** Связать готовые HTML/JS страницы (Create Account, Login, Home, Shop, Product Page, New Product, User Products, User, Cart, Checkout, Orders, About, Contact) с REST API.
- [ ] Запретить использование `localStorage` для данных, кроме токена.
- [ ] Проверить отображение картинок на фронтенде.
- [ ] **Финальная проверка** перед защитой.

---

## ✨ Дополнительные задания (Раздел 17 - Опционально)
- [ ] Email Verification (OTP)
- [ ] Forgot Password
- [ ] Refresh Token
- [ ] Caching каталога товаров
- [ ] Unit Testing (JUnit/Mockito)
- [ ] Contact Form (сохранение сообщений)

---

## 📝 Заметки и решения
- Фронтенд берется готовый (дизайн не меняем).
- Донор кода: старый проект `BookMgmt` (JWT, ExceptionHandler, Specifications).
- Docker, CI/CD и деплой не требуются по условию задания.