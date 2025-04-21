# Capi Copy Plugin

**Capi Copy**는 IntelliJ IDEA 및 기타 JetBrains IDE에서 사용 가능한 플러그인으로, 현재 열려 있는 파일의 경로와 내용을 클립보드에 손쉽게 복사할 수 있도록 도와줍니다.  
**Capi Copy** helps you copy the current file path and content — or all open files — to the clipboard in a GPT-friendly format.

> ✨ GPT 프롬프트에 붙여넣기 좋습니다.  
> ✨ Perfect for GPT-style prompt pasting.

---

## 🇰🇷 기능 요약 (Korean)

### 🧩 주요 기능

- **📄 파일 경로 및 내용 복사**
    - 현재 파일의 전체 경로와 내용을 함께 복사합니다.
    - 설정에서 복사 방식을 선택할 수 있습니다:
        - 📄 **파일 경로 + 내용 (기본)**  
          예시:
          ```
          📄 D:/myproject/Main.java

          ```java
          public class Main {
              public static void main(String[] args) {
                  System.out.println("Hello");
              }
          }
          ```
          ```
        - 🔋 **선택한 코드가 있다면 해당 코드만 복사**  
          👉 선택한 코드가 없을 경우, 기본 옵션(전체 파일 내용 복사)과 동일하게 동작합니다.  
          예시 (선택된 코드):
          ```
          📄 D:/myproject/Main.java

          ```java
          System.out.println("Hello");
          ```
          ```
        - 📂 **열려 있는 모든 파일 복사**  
          열려 있는 모든 파일의 경로와 내용을 순차적으로 복사합니다.

- **⚡ 더블 클릭 기능 (빠른 복사)**
    - 단축키 또는 액션을 0.5초 이내에 **두 번 실행하면** 설정과 무관하게 열린 모든 파일을 복사합니다.

- **⌨️ 단축키**
    - 기본 단축키: `Ctrl + Shift + Alt + Q` (변경 가능)

- **🔔 알림 지원**
    - 복사 결과를 IDE 오른쪽 하단에 풍선 알림으로 안내합니다.

- **🌐 다국어 지원**
    - IDE 언어 설정에 따라 자동 적용:
        - 🇰🇷 한국어
        - 🇺🇸 영어
        - 🇯🇵 일본어
        - 🇨🇳 중국어
        - 🇩🇪 독일어

- **🎨 다크/라이트 테마 SVG 아이콘 지원**

---

## 🇺🇸 Features (English)

### 🧩 Key Features

- **📄 Copy file path and content**
    - Copy the full path and content of the current file in one click.
    - Copy modes (configurable in settings):
        - 📄 **Path + Content (default)**  
        - 🔋 **Copy selection if available** (otherwise full content)  
        - 📂 **Copy all open files**

- **⚡ Double-click trigger**
    - Double-press the shortcut within 0.5 seconds to override settings and copy **all open files**.

- **⌨️ Shortcut**
    - Default: `Ctrl + Shift + Alt + Q` (can be changed)

- **🔔 Notifications**
    - Balloon popups for success/failure feedback

- **🌐 Multi-language UI**
    - Auto-adapts to IDE language:
        - 🇰🇷 Korean
        - 🇺🇸 English
        - 🇯🇵 Japanese
        - 🇨🇳 Chinese
        - 🇩🇪 German

- **🎨 Light/Dark theme SVG icon support**

---

## 🔧 설치 방법 / Installation

### 방법 1 / Option 1: `.zip` 플러그인 설치 (Install from ZIP)

1. 빌드된 `.zip` 플러그인 파일을 다운로드합니다.  
   Download the plugin ZIP file.
2. IntelliJ에서 `Settings > Plugins > ⚙️ > Install Plugin from Disk` 선택  
   Go to `Settings > Plugins > ⚙️ > Install Plugin from Disk`
3. `.zip` 파일 선택 → 설치 후 IDE 재시작  
   Select the ZIP file → Install and restart the IDE

### 방법 2 / Option 2: 직접 빌드 (Build from source)

```bash
git clone https://github.com/EleninJayTech/capi-copy.git
cd capi-copy
./gradlew build
```

> `build/distributions/` 폴더에 `.zip` 파일이 생성됩니다.  
> The plugin ZIP will be generated in `build/distributions/`

---

## 🧭 사용 방법 / How to Use

- **단축키 복사 / Use Shortcut**
    - `Ctrl + Shift + Alt + Q`로 실행
    - 두 번 빠르게 누르면 열린 모든 파일 복사  
      (Press twice to copy all open files)

- **마우스 메뉴 복사 / Use Mouse Right-click**
    - 편집기에서 우클릭 → `Copy File Path & Content`

- **복사 옵션 설정 / Change Settings**
    - `Settings > Tools > Capi Copy Settings` 메뉴에서 복사 방식 선택
    - Choose copy mode in plugin settings

---

## 📁 프로젝트 구조 / Project Structure

```
capi-copy/
├── build.gradle
├── settings.gradle
├── gradle/wrapper/
│   └── gradle-wrapper.properties
├── src/main/
│   ├── java/org/example/
│   │   ├── actions/CopyFilePathAndContentAction.java
│   │   ├── settings/CopyPluginConfigurable.java
│   │   └── settings/CopyPluginSettings.java
│   └── resources/
│       ├── META-INF/plugin.xml
│       ├── META-INF/pluginIcon.svg
│       ├── META-INF/pluginIcon_dark.svg
│       └── messages/messages_xx.properties (multi-lang)
```

---

## 💻 개발 환경 / Development Environment

- Java 17
- Gradle 7.4
- IntelliJ Plugin SDK 2022.2+

---

## 📦 빌드 산출물 / Build Output

- `/build/distributions/capi-copy-1.0.0.zip`

---

## 📜 라이센스 / License

이 플러그인은 MIT 라이선스 하에 배포됩니다.  
This plugin is licensed under the MIT License.

```
MIT License

Copyright (c) 2025 ELENIN Tech

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the “Software”), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 📬 문의 / Contact

- 이메일 / Email: `eleninjaytech@gmail.com`

---

## 🎯 목표 / Goal

GPT와 JetBrains 사용자 모두를 위한 **최적의 복사 유틸리티** 제공을 목표로 합니다.  
Our goal is to deliver the **most convenient copy utility** for GPT prompt users and JetBrains developers.