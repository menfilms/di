# 📝 Дневник Эмоций — Android App

## ⚡ Быстрый старт

1. Открой **Android Studio**
2. **File → Open**
3. Выбери папку **SERVER/** (этот проект)
4. Дождись Gradle Sync
5. Нажми **Run ▶**

**Всё! Проект полностью готов к запуску.**

---

## Описание
Приложение для отслеживания настроения с поддержкой в трудные моменты.

## Функционал
- ✅ Запись настроения от 1 до 10
- ✅ Краткое описание настроения
- ✅ Теги (работа, семья, здоровье, друзья, отдых, учёба)
- ✅ История всех записей
- ✅ Среднее настроение за неделю
- ✅ Уведомления-напоминания (настраиваемое время)
- ✅ Поддержка при низком настроении (≤3)
- ✅ Аффирмации и советы
- ✅ Сохранение данных в Room Database

## Структура проекта
```
SERVER/
├── .idea/                      — Конфигурация Android Studio
├── app/
│   ├── build.gradle            — Зависимости модуля
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/emotiondiary/app/
│       │   ├── MainActivity.kt          — Главный экран
│       │   ├── AddEntryActivity.kt      — Добавление записи
│       │   ├── HistoryActivity.kt       — История записей
│       │   ├── SettingsActivity.kt      — Настройки
│       │   ├── SupportDialogFragment.kt — Диалог поддержки
│       │   ├── EmotionDiaryApplication.kt
│       │   ├── adapters/
│       │   │   └── EmotionAdapter.kt
│       │   ├── data/
│       │   │   ├── EmotionEntry.kt      — Модель (Entity)
│       │   │   ├── EmotionDao.kt        — DAO
│       │   │   ├── AppDatabase.kt       — Room Database
│       │   │   ├── EmotionRepository.kt — Репозиторий
│       │   │   └── Converters.kt
│       │   ├── notifications/
│       │   │   └── NotificationHelper.kt
│       │   ├── receivers/
│       │   │   ├── NotificationReceiver.kt
│       │   │   └── BootReceiver.kt
│       │   ├── utils/
│       │   │   └── SupportMessages.kt
│       │   └── viewmodels/
│       │       └── EmotionViewModel.kt
│       └── res/
│           ├── layout/          — 6 layout файлов
│           ├── drawable/        — Иконки и фоны
│           ├── values/          — Цвета, строки, темы
│           └── mipmap-anydpi-v26/ — Иконка приложения
├── gradle/wrapper/              — Gradle Wrapper
├── build.gradle                 — Корневой build файл
├── settings.gradle              — Настройки проекта
├── gradle.properties
├── gradlew / gradlew.bat        — Скрипты Gradle
└── README.md
```

## Как открыть в Android Studio

### Вариант 1: Просто открыть (рекомендуется)
1. Открой Android Studio
2. **File → Open**
3. Выбери папку `SERVER/`
4. Дождись Gradle Sync (2-5 минут при первом запуске)
5. Нажми **Run ▶** (Shift+F10)

### Вариант 2: Если нужна ручная настройка
1. Создай новый проект (Empty Activity, Kotlin)
2. Package name: `com.emotiondiary.app`
3. Скопируй файлы из SERVER/ в проект
4. Sync Gradle

## Требования
- Android Studio Hedgehog (2023.1.1) или новее
- Kotlin 1.9+ (обычно идёт с Android Studio)
- Min SDK 26 (Android 8.0)
- Target SDK 34 (Android 14)
- JDK 17 (обычно идёт с Android Studio)

## Технологии
- **Kotlin** — язык разработки
- **Room Database** — локальное хранение данных
- **MVVM** — архитектура
- **Material Design 3** — UI
- **Coroutines** — асинхронность
- **LiveData/ViewModel** — реактивность

## Уведомления
- Ежедневные напоминания (настраиваемое время, по умолчанию 20:00)
- Сообщения поддержки при низком настроении (≤3)
- Восстановление после перезагрузки устройства
- Требуют разрешения POST_NOTIFICATIONS на Android 13+
