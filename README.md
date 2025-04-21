# Capi Copy Plugin

**Capi Copy**는 IntelliJ IDEA 및 기타 JetBrains IDE에서 사용 가능한 플러그인으로, 현재 열려 있는 파일의 경로와 내용을 클립보드에 손쉽게 복사할 수 있도록 도와줍니다.

> ✨ **GPT 프롬프트에 파일 경로와 내용을 붙여넣을 때 유용합니다.**

---

## 🧩 주요 기능

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

- **🌐 다국어 지원 (Localization)**
    - IDE 언어 설정에 따라 다음 언어로 자동 적용됩니다:
        - 🇰🇷 한국어
        - 🇺🇸 영어
        - 🇯🇵 일본어
        - 🇨🇳 중국어
        - 🇩🇪 독일어

- **🎨 아이콘 지원**
    - 다크/라이트 테마에 최적화된 SVG 아이콘 자동 적용

---

## 🔧 설치 방법

### 방법 1. 빌드된 `.zip` 플러그인 설치

1. 빌드된 `.zip` 플러그인 파일을 다운로드합니다.
2. IntelliJ에서 `Settings (Ctrl + Alt + S)` → `Plugins` → `⚙️ (톱니바퀴)` → `Install Plugin from Disk` 선택
3. 다운로드한 `.zip` 파일 선택 후 설치
4. IDE 재시작

### 방법 2. 직접 빌드해서 설치

```bash
git clone https://github.com/EleninJayTech/capi-copy.git
cd capi-copy
./gradlew build
```

- 빌드 후 `build/distributions/` 디렉토리에 `.zip` 생성됨
- 동일한 방식으로 IDE에 설치 가능

---

## 🧭 사용 방법

- **단축키 복사**
    - `Ctrl + Shift + Alt + Q`로 복사 기능 실행
    - 빠르게 두 번 누르면 열린 모든 파일 복사

- **마우스 메뉴 복사**
    - 편집기에서 마우스 우클릭 → `Copy File Path & Content` 메뉴 선택

- **복사 옵션 설정**
    - `Settings > Tools > Capi Copy Settings` 메뉴에서 복사 방식을 설정할 수 있습니다.

---

## 🧩 프로젝트 구조

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
│       └── messages/messages_xx.properties (다국어)
```

---

## 💻 개발 환경

- Java 17
- Gradle 7.4
- IntelliJ Plugin SDK 2022.2+

---

## 📦 빌드 산출물

- `/build/distributions/capi-copy-1.0.0.zip`

---

## 📜 라이센스

본 플러그인은 사내 배포용으로 개발되었으며 외부 배포 시 라이선스 조정이 필요할 수 있습니다.
> 기본적으로 Apache 2.0 기반 개발 방식 참고

---

## 📬 문의

- 이메일: `eleninjaytech@gmail.com`

---

## 🎯 목표

GPT와 JetBrains 사용자 모두를 위한 **최적의 복사 유틸리티** 제공을 목표로 합니다.