# 실습 환경 안내

`05-1_스마트앱프로젝트_실습문제.md`의 10개 문제를 실제로 구현해 볼 수 있도록 구성한 스켈레톤 프로젝트입니다.

## 사용법

1. 앱을 실행하면 런처 화면(`MainActivity`)에 문제 1~10 버튼이 나옵니다.
2. 각 버튼은 해당 문제의 진입 화면으로 이동합니다.
3. 코드 안의 `// TODO: N번 문제 - ...` 주석을 찾아 직접 채우면 됩니다.
4. xml 레이아웃·리소스와 뼈대 코드는 이미 완성되어 있습니다.

## 빌드 환경

- Kotlin + ViewBinding (`buildFeatures.viewBinding = true`)
  - AGP 9.x는 Kotlin이 내장되어 있어 별도 kotlin 플러그인을 적용하지 않습니다.
- RecyclerView (문제 6·7·8·10)
- Navigation (fragment-ktx / ui-ktx) (문제 3·4·5)
- Room + KSP (문제 8·10) — `ksp(libs.androidx.room.compiler)`

## 문제별 패키지

| 문제 | 패키지 | 진입 Activity | 핵심 TODO |
|---|---|---|---|
| 1 | `p01event` | `ExerciseActivity` | 체크/라디오/레이팅/TextWatcher/클릭·롱클릭 리스너 |
| 2 | `p02spinner` | `LostFoundActivity` | ArrayAdapter, 공유 OnItemSelectedListener, `applyFilters()` |
| 3 | `p03bottomnav` | `BottomNavActivity` | 메뉴·그래프·AppBarConfiguration 에 schoolFragment 추가 |
| 4 | `p04facilitydetail` | `FacilityNavActivity` | `bundleOf` + `navigate(action)`, `arguments` 수신 분기 |
| 5 | `p05drawer` | `DrawerMainActivity` | AppBarConfiguration 에 drawerLayout, 옵션 메뉴 |
| 6 | `p06recycler` | `NoticeActivity` | Adapter 3메소드 + 배지 GONE/VISIBLE + layoutManager |
| 7 | `p07recycleraction` | `NoticeActionActivity` | 함수형 매개변수 클릭/롱클릭, AlertDialog, 삭제 |
| 8 | `p08memo` | `MemoActivity` | thread + Handler 로 DB 작업, 저장 |
| 9 | `p09contact` | `ContactActivity` | SQLiteOpenHelper onCreate, ContentValues, Cursor |
| 10 | `p10book` | `BookActivity` | 등록/수정 다이얼로그 1함수, 필터, 작성자 검사, 메뉴 |

## 참고

- 문제 8·10의 `RoomDB.kt`(Entity/Dao/Database)는 KSP 코드 생성을 위해 완성되어 있습니다.
  어노테이션 작성 자체를 연습하려면 본문을 지우고 다시 채워 보세요.
- 모범 구현과 설계 판단은 `05-1_스마트앱프로젝트_실습문제.md`를 참고하세요.
