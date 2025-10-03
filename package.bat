@echo off
cls
echo 📦 Упаковка в .exe и архив...

REM Очистка старой папки dist
if exist dist rmdir /s /q dist


REM Укажите полный путь к jpackage.exe
set JDK_PATH="C:\Program Files\Java\jdk-24.0.2"

%JDK_PATH%\bin\jpackage ^
  --input "%~dp0build" ^
  --name "Update steps" ^
  --main-class UpdateSteps ^
  --main-jar "%~dp0build\UpdateSteps.jar" ^
  --icon "%~dp0resources\up.ico" ^
  --dest "%~dp0dist" ^
  --type app-image

if errorlevel 1 (
    echo ❌ Ошибка упаковки в .exe!
    pause
    exit /b 1
)

:end
echo 🎉 Готово! Файлы находятся в папке 'dist'.
pause