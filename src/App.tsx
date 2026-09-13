import { useState } from 'react'

function App() {
  const [activeTab, setActiveTab] = useState<'overview' | 'structure' | 'install' | 'features'>('overview')

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-white to-purple-50">
      {/* Header */}
      <header className="bg-gradient-to-r from-indigo-600 to-purple-600 text-white shadow-lg">
        <div className="max-w-6xl mx-auto px-4 py-8">
          <div className="flex items-center gap-4">
            <div className="text-5xl">📝</div>
            <div>
              <h1 className="text-3xl font-bold">Дневник Эмоций</h1>
              <p className="text-indigo-200 mt-1">Android приложение для отслеживания настроения</p>
            </div>
          </div>
          <div className="flex gap-2 mt-6 flex-wrap">
            <span className="px-3 py-1 bg-white/20 rounded-full text-sm">Kotlin 2.1</span>
            <span className="px-3 py-1 bg-white/20 rounded-full text-sm">Gradle 9.1</span>
            <span className="px-3 py-1 bg-white/20 rounded-full text-sm">AGP 9.0</span>
            <span className="px-3 py-1 bg-white/20 rounded-full text-sm">Room DB</span>
            <span className="px-3 py-1 bg-white/20 rounded-full text-sm">MVVM</span>
            <span className="px-3 py-1 bg-white/20 rounded-full text-sm">Material 3</span>
          </div>
        </div>
      </header>

      {/* Navigation */}
      <nav className="bg-white shadow-sm sticky top-0 z-10">
        <div className="max-w-6xl mx-auto px-4">
          <div className="flex gap-1 overflow-x-auto">
            {[
              { id: 'overview', label: '📋 Обзор', },
              { id: 'features', label: '✨ Функции' },
              { id: 'structure', label: '📁 Структура' },
              { id: 'install', label: '🛠 Установка' },
            ].map(tab => (
              <button
                key={tab.id}
                onClick={() => setActiveTab(tab.id as any)}
                className={`px-4 py-3 text-sm font-medium whitespace-nowrap border-b-2 transition-colors ${
                  activeTab === tab.id
                    ? 'border-indigo-600 text-indigo-600'
                    : 'border-transparent text-gray-500 hover:text-gray-700'
                }`}
              >
                {tab.label}
              </button>
            ))}
          </div>
        </div>
      </nav>

      {/* Content */}
      <main className="max-w-6xl mx-auto px-4 py-8">
        {activeTab === 'overview' && <OverviewTab />}
        {activeTab === 'features' && <FeaturesTab />}
        {activeTab === 'structure' && <StructureTab />}
        {activeTab === 'install' && <InstallTab />}
      </main>

      {/* Footer */}
      <footer className="bg-gray-800 text-gray-400 py-6 mt-12">
        <div className="max-w-6xl mx-auto px-4 text-center">
          <p>📝 Дневник Эмоций — Все файлы проекта в папке <code className="text-indigo-400">SERVER/</code></p>
          <p className="text-sm mt-2">Скопируйте содержимое SERVER/ в ваш Android Studio проект</p>
        </div>
      </footer>
    </div>
  )
}

function OverviewTab() {
  return (
    <div className="space-y-6">
      <div className="bg-white rounded-xl shadow-sm p-6 border border-gray-100">
        <h2 className="text-2xl font-bold text-gray-800 mb-4">О проекте</h2>
        <p className="text-gray-600 leading-relaxed">
          <strong>Дневник Эмоций</strong> — это Android-приложение для ежедневного отслеживания настроения. 
          Пользователь может записывать своё настроение по шкале от 1 до 10, описывать его в свободной форме, 
          добавлять теги и просматривать историю записей.
        </p>
        <p className="text-gray-600 leading-relaxed mt-3">
          Приложение также включает систему поддержки: если настроение низкое (≤3), пользователь получает 
          мотивирующие сообщения, аффирмации и практические советы для улучшения состояния.
        </p>
      </div>

      <div className="grid md:grid-cols-3 gap-4">
        <div className="bg-white rounded-xl shadow-sm p-5 border border-gray-100">
          <div className="text-3xl mb-2">📊</div>
          <h3 className="font-semibold text-gray-800">Отслеживание</h3>
          <p className="text-sm text-gray-500 mt-1">Шкала 1-10, описание, теги, история</p>
        </div>
        <div className="bg-white rounded-xl shadow-sm p-5 border border-gray-100">
          <div className="text-3xl mb-2">🔔</div>
          <h3 className="font-semibold text-gray-800">Уведомления</h3>
          <p className="text-sm text-gray-500 mt-1">Напоминания с настраиваемым временем</p>
        </div>
        <div className="bg-white rounded-xl shadow-sm p-5 border border-gray-100">
          <div className="text-3xl mb-2">💙</div>
          <h3 className="font-semibold text-gray-800">Поддержка</h3>
          <p className="text-sm text-gray-500 mt-1">Сообщения и советы при низком настроении</p>
        </div>
      </div>

      <div className="bg-gradient-to-r from-indigo-500 to-purple-500 rounded-xl p-6 text-white">
        <h3 className="text-xl font-bold mb-2">📂 Проект готов к открытию!</h3>
        <p className="text-indigo-100 mb-3">
          Все файлы Android-проекта находятся в папке <code className="bg-white/20 px-2 py-0.5 rounded">SERVER/</code>.
        </p>
        <div className="bg-white/10 rounded-lg p-4">
          <p className="text-sm font-medium">👉 Как открыть:</p>
          <p className="text-sm mt-1">Android Studio → File → Open → Выбери папку SERVER/ → Готово!</p>
        </div>
      </div>
    </div>
  )
}

function FeaturesTab() {
  const features = [
    {
      icon: '🎯',
      title: 'Шкала настроения 1-10',
      description: 'Выбор настроения через слайдер или быстрые кнопки. Каждому уровню соответствует эмодзи и описание.'
    },
    {
      icon: '✍️',
      title: 'Описание настроения',
      description: 'Свободное текстовое поле для описания своих чувств и мыслей.'
    },
    {
      icon: '🏷️',
      title: 'Теги',
      description: 'Отметьте что повлияло: работа, семья, здоровье, друзья, отдых, учёба.'
    },
    {
      icon: '📊',
      title: 'Статистика',
      description: 'Среднее настроение за неделю на главном экране.'
    },
    {
      icon: '📋',
      title: 'История записей',
      description: 'Полный список всех записей с датой, настроением и описанием.'
    },
    {
      icon: '🔔',
      title: 'Ежедневные напоминания',
      description: 'Настраиваемое время напоминания записать свои эмоции.'
    },
    {
      icon: '💙',
      title: 'Поддержка при низком настроении',
      description: 'При настроении ≤3 показывается поддерживающее сообщение.'
    },
    {
      icon: '✨',
      title: 'Аффирмации',
      description: 'Позитивные утверждения для поднятия настроения.'
    },
    {
      icon: '💡',
      title: 'Советы',
      description: 'Практические рекомендации: дыхание, прогулка, медитация.'
    },
    {
      icon: '💾',
      title: 'Локальное хранение',
      description: 'Все данные хранятся в Room Database на устройстве.'
    },
    {
      icon: '🔄',
      title: 'Восстановление уведомлений',
      description: 'Напоминания восстанавливаются после перезагрузки устройства.'
    },
    {
      icon: '🎨',
      title: 'Material Design 3',
      description: 'Современный дизайн с карточками, FAB и Material You.'
    },
  ]

  return (
    <div className="grid md:grid-cols-2 gap-4">
      {features.map((feature, index) => (
        <div key={index} className="bg-white rounded-xl shadow-sm p-5 border border-gray-100 hover:shadow-md transition-shadow">
          <div className="flex items-start gap-3">
            <span className="text-2xl">{feature.icon}</span>
            <div>
              <h3 className="font-semibold text-gray-800">{feature.title}</h3>
              <p className="text-sm text-gray-500 mt-1">{feature.description}</p>
            </div>
          </div>
        </div>
      ))}
    </div>
  )
}

function StructureTab() {
  const structure = [
    { path: 'SERVER/settings.gradle', desc: 'Настройки проекта Gradle' },
    { path: 'SERVER/build.gradle', desc: 'Корневой build файл' },
    { path: 'SERVER/app/build.gradle', desc: 'Зависимости и конфигурация модуля app' },
    { path: 'SERVER/app/proguard-rules.pro', desc: 'Правила ProGuard' },
    { path: 'SERVER/app/src/main/AndroidManifest.xml', desc: 'Манифест приложения' },
    { path: 'SERVER/app/src/main/java/.../MainActivity.kt', desc: 'Главный экран' },
    { path: 'SERVER/app/src/main/java/.../AddEntryActivity.kt', desc: 'Экран добавления записи' },
    { path: 'SERVER/app/src/main/java/.../HistoryActivity.kt', desc: 'Экран истории' },
    { path: 'SERVER/app/src/main/java/.../SettingsActivity.kt', desc: 'Экран настроек' },
    { path: 'SERVER/app/src/main/java/.../SupportDialogFragment.kt', desc: 'Диалог поддержки' },
    { path: 'SERVER/app/src/main/java/.../EmotionDiaryApplication.kt', desc: 'Application класс' },
    { path: 'SERVER/app/src/main/java/.../data/EmotionEntry.kt', desc: 'Модель данных (Entity)' },
    { path: 'SERVER/app/src/main/java/.../data/EmotionDao.kt', desc: 'DAO интерфейс для Room' },
    { path: 'SERVER/app/src/main/java/.../data/AppDatabase.kt', desc: 'Room Database' },
    { path: 'SERVER/app/src/main/java/.../data/EmotionRepository.kt', desc: 'Репозиторий' },
    { path: 'SERVER/app/src/main/java/.../data/Converters.kt', desc: 'TypeConverters для Room' },
    { path: 'SERVER/app/src/main/java/.../viewmodels/EmotionViewModel.kt', desc: 'ViewModel (MVVM)' },
    { path: 'SERVER/app/src/main/java/.../adapters/EmotionAdapter.kt', desc: 'RecyclerView адаптер' },
    { path: 'SERVER/app/src/main/java/.../notifications/NotificationHelper.kt', desc: 'Система уведомлений' },
    { path: 'SERVER/app/src/main/java/.../receivers/NotificationReceiver.kt', desc: 'Приёмник уведомлений' },
    { path: 'SERVER/app/src/main/java/.../receivers/BootReceiver.kt', desc: 'Восстановление после перезагрузки' },
    { path: 'SERVER/app/src/main/java/.../utils/SupportMessages.kt', desc: 'Сообщения поддержки' },
    { path: 'SERVER/app/src/main/res/layout/*.xml', desc: 'Layout файлы (6 шт.)' },
    { path: 'SERVER/app/src/main/res/drawable/*.xml', desc: 'Векторные иконки и фоны' },
    { path: 'SERVER/app/src/main/res/values/*.xml', desc: 'Цвета, строки, темы' },
  ]

  return (
    <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
      <div className="p-4 bg-gray-50 border-b">
        <h2 className="text-lg font-bold text-gray-800">📁 Структура файлов проекта</h2>
        <p className="text-sm text-gray-500">Все файлы находятся в папке SERVER/</p>
      </div>
      <div className="divide-y">
        {structure.map((file, index) => (
          <div key={index} className="px-4 py-3 flex items-center gap-3 hover:bg-gray-50">
            <span className="text-indigo-500 text-sm">📄</span>
            <code className="text-sm text-indigo-700 font-mono flex-1">{file.path}</code>
            <span className="text-xs text-gray-400 hidden md:block">{file.desc}</span>
          </div>
        ))}
      </div>
    </div>
  )
}

function InstallTab() {
  const steps = [
    {
      step: 1,
      title: 'Открой Android Studio',
      description: 'Запусти Android Studio (рекомендуется Meerkat 2025.1 или новее).',
    },
    {
      step: 2,
      title: 'Нажми File → Open',
      description: 'В меню выбери File → Open, затем найди и выбери папку SERVER/ с проектом.',
    },
    {
      step: 3,
      title: 'Выбери Gradle JDK 17',
      description: 'Если появится предупреждение о несовместимости JVM, нажми "Apply compatible Gradle JDK configuration" или выбери JDK 17 (jbr-17) в настройках Gradle.',
    },
    {
      step: 4,
      title: 'Дождись Gradle Sync',
      description: 'Android Studio автоматически определит проект и начнёт синхронизацию Gradle. Дождись завершения (может занять 2-5 минут при первом запуске).',
    },
    {
      step: 5,
      title: 'Запусти приложение',
      description: 'Подключи Android-устройство или создай эмулятор. Нажми зелёную кнопку Run ▶ или Shift+F10.',
    },
  ]

  return (
    <div className="space-y-6">
      <div className="bg-white rounded-xl shadow-sm p-6 border border-gray-100">
        <h2 className="text-2xl font-bold text-gray-800 mb-4">🛠 Инструкция по установке</h2>
        <div className="space-y-6">
          {steps.map((s) => (
            <div key={s.step} className="flex gap-4">
              <div className="flex-shrink-0 w-10 h-10 bg-indigo-100 text-indigo-700 rounded-full flex items-center justify-center font-bold">
                {s.step}
              </div>
              <div>
                <h3 className="font-semibold text-gray-800">{s.title}</h3>
                <p className="text-sm text-gray-500 mt-1">{s.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>

      <div className="bg-amber-50 border border-amber-200 rounded-xl p-5">
        <h3 className="font-semibold text-amber-800 flex items-center gap-2">
          <span>⚠️</span> Важные замечания
        </h3>
        <ul className="mt-3 space-y-2 text-sm text-amber-700">
          <li>• Требуется Android Studio Meerkat (2025.1+) или новее</li>
          <li>• JDK 17+ (обычно идёт с Android Studio как JBR 17)</li>
          <li>• Min SDK 26 (Android 8.0 Oreo)</li>
          <li>• Target SDK 35 (Android 15)</li>
          <li>• Для уведомлений на Android 13+ нужно разрешение POST_NOTIFICATIONS</li>
          <li>• При первом открытии Android Studio скачает Gradle — нужен интернет</li>
          <li>• Если появится ошибка SDK — укажи путь к Android SDK в local.properties</li>
        </ul>
      </div>

      <div className="bg-blue-50 border border-blue-200 rounded-xl p-5">
        <h3 className="font-semibold text-blue-800 flex items-center gap-2">
          <span>🔧</span> Версии проекта
        </h3>
        <div className="mt-3 grid grid-cols-2 gap-2 text-sm text-blue-700">
          <div><strong>Gradle:</strong> 9.1.0</div>
          <div><strong>AGP:</strong> 9.0.1</div>
          <div><strong>Kotlin:</strong> 2.1.0</div>
          <div><strong>JDK:</strong> 17+</div>
          <div><strong>Compile SDK:</strong> 35</div>
          <div><strong>Target SDK:</strong> 35</div>
        </div>
      </div>

      <div className="bg-green-50 border border-green-200 rounded-xl p-5">
        <h3 className="font-semibold text-green-800 flex items-center gap-2">
          <span>✅</span> Готово к открытию!
        </h3>
        <p className="text-sm text-green-700 mt-2">
          Папка <code className="bg-green-100 px-1 rounded">SERVER/</code> содержит 
          полностью настроенный проект Android Studio. Просто открой её через 
          <strong> File → Open</strong> и всё заработает!
        </p>
      </div>

      <div className="bg-white rounded-xl shadow-sm p-6 border border-gray-100">
        <h3 className="font-semibold text-gray-800 mb-3">📦 Зависимости проекта</h3>
        <div className="grid md:grid-cols-2 gap-3">
          {[
            'AndroidX Core KTX 1.15.0',
            'Material Design 3 (1.12.0)',
            'Room Database 2.6.1',
            'Lifecycle ViewModel 2.8.7',
            'RecyclerView 1.3.2',
            'ConstraintLayout 2.2.0',
            'Kotlin Coroutines 1.9.0',
            'Gson 2.11.0',
          ].map((dep, i) => (
            <div key={i} className="flex items-center gap-2 text-sm text-gray-600">
              <span className="text-green-500">✓</span> {dep}
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default App
