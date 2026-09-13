# 📝 Дневник Эмоций — Android App

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
app/src/main/
├── java/com/emotiondiary/app/
│   ├── MainActivity.kt          — Главный экран
│   ├── AddEntryActivity.kt      — Добавление записи
│   ├── HistoryActivity.kt       — История записей
│   ├── SettingsActivity.kt      — Настройки
│   ├── SupportDialogFragment.kt — Диалог поддержки
│   ├── EmotionDiaryApplication.kt — Application класс
│   ├── adapters/
│   │   └── EmotionAdapter.kt    — Адаптер для RecyclerView
│   ├── data/
│   │   ├── EmotionEntry.kt      — Модель данных
│   │   ├── EmotionDao.kt        — DAO для Room
│   │   ├── AppDatabase.kt       — Room Database
│   │   ├── EmotionRepository.kt — Репозиторий
│   │   └── Converters.kt        — TypeConverters
│   ├── notifications/
│   │   └── NotificationHelper.kt — Уведомления
│   ├── receivers/
│   │   ├── NotificationReceiver.kt — Приёмник уведомлений
│   │   └── BootReceiver.kt      — Восстановление после перезагрузки
│   ├── utils/
│   │   └── SupportMessages.kt   — Сообщения поддержки
│   └── viewmodels/
│       └── EmotionViewModel.kt  — ViewModel
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_add_entry.xml
│   │   ├── activity_history.xml
│   │   ├── activity_settings.xml
│   │   ├── dialog_support.xml
│   │   └── item_emotion_entry.xml
│   ├── drawable/
│   │   ├── ic_add.xml
│   │   ├── ic_heart.xml
│   │   ├── ic_notification.xml
│   │   ├── ic_back.xml
│   │   └── bottom_sheet_background.xml
│   └── values/
│       ├── colors.xml
│       ├── strings.xml
│       └── themes.xml
└── AndroidManifest.xml
```

## Как установить в Android Studio

1. Создай новый проект в Android Studio (Empty Activity, Kotlin)
2. Package name: `com.emotiondiary.app`
3. Minimum SDK: API 26 (Android 8.0)
4. Скопируй файлы из папки SERVER в свой проект:
   - `settings.gradle` → корень проекта
   - `build.gradle` (root) → корень проекта
   - `app/build.gradle` → в папку app/
   - `app/src/` → в папку app/src/
5. Sync Gradle
6. Запусти на устройстве или эмуляторе

## Требования
- Android Studio Hedgehog (2023.1.1) или новее
- Kotlin 1.9+
- Min SDK 26 (Android 8.0)
- Target SDK 34 (Android 14)
