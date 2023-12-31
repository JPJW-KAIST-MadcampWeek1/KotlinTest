# Quiz & Ideal World Cup

### Kaist MadCamp week1

- tap 1 :
	- 연락처를 RecyclerView를 통해 보여주고, 각 연락처에 대한 정보를 tap해서 수정 가능합니다.
- tap 2:
	- 이미지를 GridView를 통해 보여줍니다. 추가 버튼을 통해 갤러리에서 이미지를 불러올 수 있습니다.
- tap 3 :
	- algorithm quiz tab으로 8개의 quiz로 구성하였습니다.
- tap 4 :
	- Programming Language World Cup으로   총 8강,4강,결승으로 이루어져 사용자가 선호하는 Programming Language를 선정하도록 구성하였습니다.


--- 
## a. 개발 팀원

- 서재원 - 한양대학교 컴퓨터소프트웨어학부 22학번
- 박진석 - 성균관대학교 소프트웨어학과 20학번


---

## b. 개발환경

- Language : Kotlin
- OS : Android
- IDE : Android studio
- Target Device : Galaxy S7



```
compileSdk 33
minSdk 21  
targetSdk 33
```


---
## c. 어플리케이션 소개


###  1. 로딩창
저희 앱의 테마에 맞게 코딩하는 사람을 로딩 화면 애니메이션으로 사용했어요. 이를 위해 Lottie 라이브러리를 사용했습니다. 만약 코드가 궁금하시면 activity_splash.xml에서 확인하시면 됩니다! 하지만 최신 버전이 저희 프로젝트와 호환되지 않았기 때문에 Lottie 버전을 6.0.0으로 낮춰서 진행했습니다.

```
├── app
        |__ src
              |__ main
                      |__ res
         ├── layout
            ├── activity_splash.xml
```
```
    implementation "com.airbnb.android:lottie-compose:6.0.0"
```
![image](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/68769481/7cba535b-1e46-4788-be28-d97ad27e23b9)

###  2. 메인화면
![스크린샷 2024-01-03 오후 1 52 58](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/149568715/53aa3992-3855-4baa-944c-3af796abbea0)
#### 주요 특징
- 로딩창 이후에 나타나는 메인화면으로 하단에 총 네개의 button이 있습니다.
- 첫번째 button은 연락처를 보여주는 button 입니다.
- 두번째 button은 갤러리를 보여주는 button 입니다.
- 세번째 button은 Algorithm Quiz를 보여주는 button 입니다.
- 네번째 button은  Programming Language World cup을 보여주는 button 입니다.
- 각 버튼을 누르면 해당 tab으로 이동합니다.
#### 기술설명
- 각각의 버튼을 누르면 loadFragment 메서드가 호출되어 해당 tab으로 이동합니다.
- loadFragment 메서드는 transaction을 이용해서 구현했습니다. 자세한 정보는 main/java/MainActivity를 참고하세요.

###  3. 연락처
![스크린샷 2024-01-03 오후 1 15 26](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/149568715/385b64de-1ebe-44b3-9772-96a90a454962)
![스크린샷 2024-01-03 오후 1 28 16](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/149568715/41e7dd66-1cfb-492b-9c49-d757858c5370)

#### 주요 특징
- 연락처 정보를 보여주는 tab으로 스크롤을 통해 모든 연락처를 볼 수 있습니다.
- 각각의 연락처를 클릭하면 연락처 정보를 수정할 수 있습니다.
- 수정창에서 연락처 정보를 변경한 후 우측 상단의 버튼을 누르면 수정정보가 반영된 연락처 정보 tab으로 돌아갑니다.
#### 기술설명
- contacts.json에 저장된 연락처 정보들을 RecyclerView를 통해 list형태로 보여줍니다.
- 각각의 연락처를 클릭했을 때 물결모양의 애니메이션이 나오도록 구현했습니다.
- 각각의 연락처를 클릭하면 intent를 통해 ContactEditActivity로 이동합니다.
- json파일은 런타임에서 읽기전용이기 때문에 런타임에서 수정사항을 json파일에 저장할 수 없는 문제가 있었습니다. 그리하여 수정사항을 내부저장소에서 관리하도록 구현했습니다. 자세한 정보는 main/java/ContactEditActivity를 참고하세요.
- 오른쪽 상단의 버튼을 클릭하면 updateContactInJson을 통해 다시 연락처 list로 돌아갑니다. 자세한 정보는 main/java/ContactEditActivity를 참고하세요.


###  4. 갤러리
두 번째 탭은 사진 갤러리로 구현했습니다. Grid layout과 RecyclerView를 이용했고 이미지를 원활하게 불러오기 위해 Glide 라이브러리를 사용했습니다. 갤러리에 있는 기존 이미지들은 drawable 폴더애 저장돼 있고 '+' 버튼을 눌러 핸드폰 갤러리와 연동하여 사진을 선택해서 추가할 수 있습니다. 각 사진을 클릭할 경우 선택된 사진을 더 자세하게 볼 수 있습니다. 자세한 내용은 아래 파일들을 참고해주세요.
```
├── app
        |__ src
              |__ main
                      |__ res
         ├── layout
            ├── fragment_third.xml
            ├── activity_image_display.xml
            ├── items_image.xml
```
```
├── app
        |__ src
              |__ main
                      |__ java
         ├── com
            ├── example
               ├── ThirdFragment.kt
               ├── ViewModel.kt
               ├── ImageDisplayActivity.kt
               ├── GridSpacingItemDecoration.kt
               ├── ImageAdapter.kt
```
![image](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/68769481/ab6e3079-5c36-4b9f-afde-c9b4c6232215)
###  5. 알고리즘 퀴즈
세 번째 탭은 알고리즘 퀴즈를 만들어봤어요! 이 퀴즈는 저희가 학교 자료구조 및 알고리즘 과목에서 배운 지식을 활용해 총 8문제로 이루어져 있어요. 이 퀴즈를 객관식으로 구성해 알고리즘 지식을 간단히 테스트할 수 있습니다. 퀴즈 시작 화면에서도 coding man 애니메이션을 추가하고 문제를 풀 때마다 즉시 정답 여부를 확인할 수 있어요. 결과 화면에서는 총 점수를 확인할 수 있고, 재시작 버튼을 누르면 다시 시작할 수 있어요. 화면 이동은 GONE/VISIBLE을 활용해 동적으로 구성했어요. 문제 출처는 (https://onlineexammaker.com/kb/30-algorithms-quiz-questions-and-answers/ , https://www.sanfoundry.com/programming-questions-answers/#google_vignette )입니다. 자세한 내용은 아래 파일들을 참고해주세요.
```
├── app
        |__ src
              |__ main
                      |__ res
         ├── layout
            ├── fragment_fifth.xml   
               
```
```
├── app
        |__ src
              |__ main
                      |__ java
         ├── com
            ├── example
               ├── FifthFragment.kt
```
![image](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/68769481/2403c283-8207-4f53-8a64-56fad8a96c15)
![image](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/68769481/7308898c-0e2e-4384-93d0-669ffc38711e)
![image](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/68769481/d425fb66-1985-45b2-8b24-87067a07f846)

###  6. 프로그래밍 언어 이상형 월드컵
![스크린샷 2024-01-03 오후 1 34 12](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/149568715/5b7ef1a7-76c5-408e-ad65-180b1b0fc739)
<img width="240" alt="스크린샷 2024-01-03 오후 1 35 24" src="https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/149568715/04211618-e5a9-4645-9a14-b7ecd7dcc8a1">
![스크린샷 2024-01-03 오후 1 39 02](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/149568715/0985ccb7-a50c-4993-87d7-bba4582040ee)
![스크린샷 2024-01-03 오후 1 40 46](https://github.com/JPJW-KAIST-MadcampWeek1/KotlinTest/assets/149568715/203fe201-e9d2-440b-8d90-cea850ec74cb)

#### 주요 특징
- 프로그래밍 이상형 월드컵으로 8강, 4강, 결승전으로 구성되어 있습니다.
- start버튼으로 게임이 시작되고, 각 라운드에서 사용자는 자신이 선호하는 프로그래밍 언어의 이미지를 클릭합니다.
- 각 프로그래밍 언어 이미지와 버튼 이미지는 생성 AI인 "DALL-E"가 만든 이미지를 사용했습니다.
- 각 라운드가 바뀔 때 현재 라운드에 대한 정보를 상단에 크게 표시합니다.
- 결승전 이후 Winner를 보여주고, Restart 버튼으로 게임을 재시작할 수 있습니다.
#### 기술설명
- 이상형 월드컵 tab을 클릭하면 Intent를 통해 시작 화면을 보여주는 StartActivity로 이동합니다. 
- Start버튼을 클릭하면  Intent를 통해 이상형월드컵이 진행되는 FourthFragment로 이동합니다. 자세한 정보는 main/java/StartActivity를 참고하세요.
- 8개의 프로그래밍 언어들에 대한 정보들은 내부저장소에 저장되어 있고, round는 각 언어들의 정보를 담고있는 ProgrammingLanguage class의 rank property로 관리됩니다. 자세한 로직은 main/java/ideal_worldcup/FourthFragment를 참고하세요.
- 라운드가 변화할 때마다 showCurrentRoundTitle 메소드가 호출되어  현재 라운드에 대한 정보를 크게 보여줍니다. View.INVISIBLE과 View.VISIBLE로 구현했습니다. 자세한 로직은 main/java/ideal_worldcup/FourthFragment를 참고하세요.
- 결승전이 끝나면 우승한 언어의 정보들과 함께 Intent를 통해 WinnerActivity로 이동합니다.
- WinnerActivity에서 Restart 버튼을 클릭하면 Intent를 통해  이상형 월드컵을 진행할 수 있는 FourthFragment로 이동합니다. 자세한 정보는 main/java/WinnerActivity를 참고하세요.

APK Link: https://drive.google.com/file/d/1GEjw72UveTlGgtyKz1iJNsAtwNi9iklC/view?usp=sharing

