@echo off
chcp 65001 >nul
cls
echo [INFO] Сборка Java-приложения...

REM Удаляем старую папку build, если есть
if exist build rmdir /s /q build

REM Создаём папку build
mkdir build

REM Компилируем с release 25 (совместимо с вашей JDK)
javac --release 25 -d build src/UpdateSteps.java
if errorlevel 1 (
    echo [ERROR] Ошибка компиляции!
    pause
    exit /b 1
)

REM Копируем ресурсы в build/resources/
if not exist "build\resources" mkdir "build\resources"
copy /Y "src\resources\up.png" "build\resources\up.png" >nul

REM Собираем JAR в корне проекта
jar cfm UpdateSteps.jar MANIFEST.MF -C build .

if errorlevel 1 (
    echo [ERROR] Ошибка при создании JAR!
    pause
    exit /b 1
)

echo [OK] Сборка завершена: UpdateSteps.jar создан.
pause