# 정답 파일 모음

각 문제의 **완성 정답**입니다. 스켈레톤(`app/src/main/java/.../pNN...`)의 `// TODO` 를 직접 채운 뒤, 여기 코드와 비교해 보세요.

> 이 폴더는 `app/docs` 아래라 **빌드에 포함되지 않습니다.** (참고용) IDE에서 일부 참조가 빨갛게 보일 수 있지만 정상입니다. 복사해서 스켈레톤 파일에 붙여 넣으면 그대로 동작합니다.

| 문제 | 정답 파일 | 핵심 |
|---|---|---|
| 1 | `P01_ExerciseActivity.kt` | `setOnCheckedChangeListener` + `isChecked` 직접 읽기, RatingBar, TextWatcher, 롱클릭 `true` |
| 2 | `P02_LostFound.kt` | ArrayAdapter, 리스너 1개 공유, `allitems`/`items` + `applyFilters` |
| 3 | `P03_BottomNavActivity.kt` | 메뉴·그래프·AppBarConfiguration 에 `schoolFragment` 추가 (xml은 파일 상단 주석) |
| 4 | `P04_FacilityDetail.kt` | `bundleOf` + `navigate(action, bundle)`, `arguments` 수신 `when` |
| 5 | `P05_DrawerMainActivity.kt` | `AppBarConfiguration(setOf(...), drawerLayout)`, 옵션 메뉴 |
| 6 | `P06_Notice.kt` | ViewHolder bind(배지 GONE/VISIBLE), Adapter 3메소드, layoutManager |
| 7 | `P07_NoticeAction.kt` | 함수형 매개변수 `(Notice)->Unit`, AlertDialog, 롱클릭 삭제 |
| 8 | `P08_Memo.kt` | Room + `thread{}` / `Handler(mainLooper).post{}` |
| 9 | `P09_Contact.kt` | SQLiteOpenHelper onCreate, ContentValues, Cursor `moveToNext` |
| 10 | `P10_Book.kt` | 등록/수정 1함수, 작성자 검사, 필터, 수정 후 `applyFilters()` |

## 문제 1에서 자주 하는 실수 (복습)

- 체크박스에 `setOnClickListener` 대신 **`setOnCheckedChangeListener`** 를 쓴다. 롱클릭 초기화로 `isChecked = false` 할 때 자동 갱신되기 때문.
- `onWarmUp` 같은 **상태 변수를 따로 두지 말고** `binding.cbWarmUp.isChecked` 를 그때그때 읽는다. (변수를 두면 위젯 상태와 어긋나기 쉽다)
- `Toast.makeText(...).show()` 의 `.show()` 빠뜨리지 않기.
