@echo off
cls
echo [INFO] Сборка Java-приложения...

REM Создаём папку build, если её нет
if not exist build mkdir build

REM Компилируем
javac --release 24 -d build src\UpdateSteps.java
if errorlevel 1 (
    echo ❌ Ошибка компиляции!
    pause
    exit /b 1
)


REM Копируем ресурсы в build/
if not exist build/resources mkdir build/resources
copy /Y src/resources/up.png build/resources/up.png >nul

REM Собираем JAR ВНУТРИ папки build
jar cfm build\UpdateSteps.jar MANIFEST.MF -C build .

echo [OK] Сборка завершена: UpdateSteps.jar создан.
pause